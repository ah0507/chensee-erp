package net.chensee.platform.erp.action.product.service.impl;

import net.chensee.base.action.user.business.UserBus;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.base.common.po.BaseInfoPo;
import net.chensee.base.exception.BusinessRuntimeException;
import net.chensee.base.utils.CreateAndUpdateInfoUtil;
import net.chensee.base.utils.ResFolderUtils;
import net.chensee.base.utils.UserUtil;
import net.chensee.platform.erp.action.product.mapper.*;
import net.chensee.platform.erp.action.product.po.*;
import net.chensee.platform.erp.action.product.service.ProductService;
import net.chensee.platform.erp.action.product.vo.*;
import net.chensee.platform.erp.utils.DataHandlerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductPo.class);

    @Resource
    private ProductDao productDao;
    @Resource
    private ProductInputDao productInputDao;
    @Resource
    private ProductOutputDao productOutputDao;
    @Resource
    private OutputBillDao outputBillDao;
    @Resource
    private InputBillDao inputBillDao;
    @Resource
    private UserBus userBus;

    @Override
    public ObjectResponse getAllProducts(Integer pageSize, Integer pageNumber) {
        Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
        List<ProductVo> productVos = productDao.getAllProducts(pageSize * (pageNumber - 1), pageSize, currentFolder);
        Integer count = productDao.getCount(null, null, currentFolder);
        Map map = new HashMap();
        map.put("data", productVos);
        map.put("total", count);
        return ObjectResponse.ok(map);
    }

    @Override
    public ObjectResponse getProductById(Long id) {
        Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
        List<ProductVo> productVos = productDao.getProductById(id, currentFolder);
        return ObjectResponse.ok(productVos.get(0));
    }

    @Override
    public ObjectResponse getByNameAndType(String name, String typeName, Integer pageSize, Integer pageNumber) {
        Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
        List<ProductVo> productVos = productDao.getByNameAndType(name, typeName,
                pageSize * (pageNumber - 1), pageSize, currentFolder);
        Integer count = productDao.getCount(name, typeName, currentFolder);
        Map map = new HashMap();
        map.put("data", productVos);
        map.put("total", count);
        return ObjectResponse.ok(map);
    }

    @Override
    public ObjectResponse addProduct(ProductPo productPo) {
        try {
            CreateAndUpdateInfoUtil.addCreateInfo(productPo);
            CreateAndUpdateInfoUtil.addUpdateInfo(productPo);
            productPo.setStatus(BaseInfoPo.Status_Able);
            productDao.addProduct(productPo);
            return ObjectResponse.ok(productPo.getId());
        } catch (Exception e) {
            logger.error("添加产品出现异常", e);
        }
        return ObjectResponse.fail();
    }

    @Override
    public BaseResponse updateProduct(ProductPo productPo) {
        try {
            CreateAndUpdateInfoUtil.addUpdateInfo(productPo);
            productDao.updateProduct(productPo);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("修改产品出现异常", e);
        }
        return BaseResponse.fail();
    }

    @Override
    public BaseResponse deleteProduct(Long id) {
        try {
            productDao.deleteProduct(id);
            productOutputDao.deleteProductOutputByBillId(id);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("删除产品出现异常", e);
        }
        return BaseResponse.fail();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResponse addProductInput(InputBillVo inputBillVo) throws Exception {
        try {
            Long userId = UserUtil.getCurrentUser().getId();
            if (!userBus.isExistUserInSelectDept(userId, inputBillVo.getDeptId())) {
                return ObjectResponse.fail("当前用户不属于该部门");
            }
            InputBillPo inputBillPo = new InputBillPo();
            BeanUtils.copyProperties(inputBillVo, inputBillPo);
            inputBillPo.setSumAmount(DataHandlerUtil.enlargeFactor(inputBillVo.getSumAmount()));
            inputBillPo.setStatus(BaseInfoPo.Status_Able);
            inputBillPo.setFolderId(inputBillVo.getFolderId());
            //TODO
            // 添加剩余数量和出库状态
            CreateAndUpdateInfoUtil.addCreateInfo(inputBillPo);
            CreateAndUpdateInfoUtil.addUpdateInfo(inputBillPo);
            inputBillDao.addInputBill(inputBillPo);
            List<ProductInputVo> inputVos = inputBillVo.getInputVos();
            if (inputVos != null) {
                ProductInputPo inputPo = null;
                for (ProductInputVo inputVo : inputVos) {
                    inputPo = new ProductInputPo();
                    BeanUtils.copyProperties(inputVo, inputPo);
                    inputPo.setInputBillId(inputBillPo.getId());
                    inputPo.setPrice(DataHandlerUtil.enlargeFactor(inputVo.getPrice()));
                    inputPo.setTotal(DataHandlerUtil.enlargeFactor(inputVo.getTotal()));
                    inputPo.setFolderId(inputBillVo.getFolderId());
                    inputPo.setStatus(BaseInfoPo.Status_Able);
                    inputPo.setOutputStatus(ProductOutputPo.NO_OUTPUT_STATUS);
                    inputPo.setSurplusNumber(inputPo.getAmount());
                    CreateAndUpdateInfoUtil.addCreateInfo(inputPo);
                    CreateAndUpdateInfoUtil.addUpdateInfo(inputPo);
                    productInputDao.addProductInput(inputPo);
                    updateStockAmountByProductId(inputVo);
                }
            }
            return ObjectResponse.ok(inputBillPo.getId());
        } catch (Exception e) {
            logger.error("添加产品入库信息出现异常", e);
        }
        return ObjectResponse.fail();
    }

    private void updateStockAmountByProductId(ProductInputVo inputVo) {
        Integer stockAmountById = productDao.getStockAmountById(inputVo.getProductId());
        productDao.updateStockAmount(inputVo.getProductId(),stockAmountById);
    }

    @Override
    public ObjectResponse getAllProductInputs(Integer pageSize, Integer pageNumber) {
        List<InputBillVo> inputBillVos = inputBillDao.getAllInputBill(pageSize * (pageNumber - 1), pageSize);
        List<ProductInputVo> inputVos = null;
        for (InputBillVo inputBillVo : inputBillVos) {
            inputBillVo.setSumAmount(DataHandlerUtil.narrowFactor(inputBillVo.getSumAmount()));
            inputVos = productInputDao.getByBillId(inputBillVo.getId());
            for (ProductInputVo inputVo : inputVos) {
                inputVo.setPrice(DataHandlerUtil.narrowFactor(inputVo.getPrice()));
                inputVo.setTotal(DataHandlerUtil.narrowFactor(inputVo.getTotal()));
            }
            inputBillVo.setInputVos(inputVos);
        }
        Integer count = inputBillDao.getCount(null,null, null, null);
        Map map = new HashMap();
        map.put("data", inputBillVos);
        map.put("total", count);
        return ObjectResponse.ok(map);
    }

    @Override
    public ObjectResponse getInputByCondition(String contractNumber, String purchaserDeptName,
                                              Date startTime, Date endTime,
                                              Integer pageSize, Integer pageNumber) {
        List<InputBillVo> inputBillVos =
                inputBillDao.getByCondition(contractNumber, purchaserDeptName, startTime, endTime, pageSize * (pageNumber - 1), pageSize);
        List<ProductInputVo> inputVos = null;
        for (InputBillVo inputBillVo : inputBillVos) {
            inputBillVo.setSumAmount(DataHandlerUtil.narrowFactor(inputBillVo.getSumAmount()));
            inputVos = productInputDao.getByBillId(inputBillVo.getId());
            for (ProductInputVo inputVo : inputVos) {
                inputVo.setPrice(DataHandlerUtil.narrowFactor(inputVo.getPrice()));
                inputVo.setTotal(DataHandlerUtil.narrowFactor(inputVo.getTotal()));
            }
            inputBillVo.setInputVos(inputVos);
        }
        Integer count = inputBillDao.getCount(contractNumber, purchaserDeptName, startTime, endTime);
        Map map = new HashMap();
        map.put("data", inputBillVos);
        map.put("total", count);
        return ObjectResponse.ok(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse deleteProductInput(Long id) throws Exception {
        try {
            inputBillDao.deleteInputBill(id);
            productInputDao.deleteProductInputByBillId(id);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("删除产品入库信息出现异常", e);
        }
        return BaseResponse.fail();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResponse addProductOutput(OutputBillVo outputBillVo) {
        Long userId = UserUtil.getCurrentUser().getId();
        if (userId != null && !userBus.isExistUserInSelectDept(userId, outputBillVo.getDeptId())) {
            return ObjectResponse.fail("当前用户不属于该部门");
        }
        OutputBillPo outputBillPo = new OutputBillPo();
        BeanUtils.copyProperties(outputBillVo, outputBillPo);
        outputBillPo.setSumAmount(DataHandlerUtil.enlargeFactor(outputBillVo.getSumAmount()));
        outputBillPo.setFolderId(outputBillVo.getFolderId());
        outputBillPo.setStatus(BaseInfoPo.Status_Able);
        CreateAndUpdateInfoUtil.addCreateInfo(outputBillPo);
        CreateAndUpdateInfoUtil.addUpdateInfo(outputBillPo);
        outputBillDao.addOutputBill(outputBillPo);
        List<ProductOutputVo> outputVos = outputBillVo.getOutputVos();
        if (outputVos != null) {
            ProductOutputPo outputPo = null;
            for (ProductOutputVo outputVo : outputVos) {
                outputPo = new ProductOutputPo();
                //出库时根据策略调整保存入库信息
                this.handleInputInfo(outputPo, outputBillPo, outputVo);
            }
        }
        return ObjectResponse.ok();
    }

    private void handleInputInfo(ProductOutputPo outputPo, OutputBillPo outputBillPo, ProductOutputVo outputVo) {
        Integer number = outputBillDao.getCurrentProductNumber(outputVo.getProductId());
        //TODO 暂时忽略库存不足
        if (number == null || number < outputVo.getAmount()) {
            //throw new BusinessRuntimeException("本产品库存不足！");
        }
        this.saveOutputPo(outputPo, outputBillPo, outputVo);
        Integer amount = outputVo.getAmount();
        int count = 0;
        while (true) {
            ProductInputPo productInputPo = this.getInputPoByStrategy(outputVo, count);
            if (productInputPo == null) {
                break;
            }
            Integer surplusNumber = productInputPo.getSurplusNumber();
            if (amount < surplusNumber) {
                int afterAmount = surplusNumber - amount;
                this.updateInputAndAddLog(productInputPo, outputPo,afterAmount);
                productInputPo.setOutputStatus(ProductOutputPo.SOME_OUTPUT_STATUS);
                productInputPo.setSurplusNumber(afterAmount);
                outputBillDao.updateInputInfo(productInputPo);
                break;
            } else {
                this.updateInputAndAddLog(productInputPo, outputPo, 0);
                productInputPo.setOutputStatus(ProductOutputPo.ALL_OUTPUT_STATUS);
                productInputPo.setSurplusNumber(0);
                outputBillDao.updateInputInfo(productInputPo);
                amount = amount - surplusNumber;
                count++;
            }
        }

    }

    private void saveOutputPo(ProductOutputPo outputPo, OutputBillPo outputBillPo, ProductOutputVo outputVo) {
        //保存出库信息
        BeanUtils.copyProperties(outputVo, outputPo);
        outputPo.setOutputBillId(outputBillPo.getId());
        outputPo.setPrice(DataHandlerUtil.enlargeFactor(outputVo.getPrice()));
        outputPo.setTotal(DataHandlerUtil.enlargeFactor(outputVo.getTotal()));
        outputPo.setFolderId(outputBillPo.getFolderId());
        outputPo.setStatus(BaseInfoPo.Status_Able);
        CreateAndUpdateInfoUtil.addCreateInfo(outputPo);
        CreateAndUpdateInfoUtil.addUpdateInfo(outputPo);
        productOutputDao.addProductOutput(outputPo);
    }

    /**
     * 保存出库日志
     * @param productInputPo
     * @param outputPo
     * @param afterAmount
     */
    private void updateInputAndAddLog(ProductInputPo productInputPo, ProductOutputPo outputPo, int afterAmount) {
        OutputLogPo outputLogPo = new OutputLogPo();
        outputLogPo.setOutputId(outputPo.getId());
        outputLogPo.setInputId(productInputPo.getId());
        outputLogPo.setBeforeOutputNumber(productInputPo.getSurplusNumber());
        outputLogPo.setAfterOutputNumber(afterAmount);
        outputLogPo.setOutputNumber(productInputPo.getSurplusNumber() - afterAmount);
        CreateAndUpdateInfoUtil.addCreateInfo(outputLogPo);
        outputBillDao.addOutputLogPo(outputLogPo);
    }

    private ProductInputPo getInputPoByStrategy(ProductOutputVo outputVo, int count) {
        ProductInputPo productInputPo = null;
        switch (outputVo.getStrategy()) {
            case 1:
                productInputPo = outputBillDao.getInputByAsc(outputVo.getProductId(), count);
                break;
            case 2:
                productInputPo = outputBillDao.getInputByDesc(outputVo.getProductId(), count);
                break;
            default:
                throw new BusinessRuntimeException(100, outputVo.getStrategy().getClass().getName());
        }
        return productInputPo;
    }

    @Override
    public ObjectResponse getAllProductOutput(Integer pageSize, Integer pageNumber) {
        List<OutputBillVo> outputBillVos = outputBillDao.getAllOutputBill(pageSize * (pageNumber - 1), pageSize);
        List<ProductOutputVo> outputVos = null;
        for (OutputBillVo outputBillVo : outputBillVos) {
            outputBillVo.setSumAmount(DataHandlerUtil.narrowFactor(outputBillVo.getSumAmount()));
            outputVos = productOutputDao.getByBillId(outputBillVo.getId());
            for (ProductOutputVo outputVo : outputVos) {
                outputVo.setPrice(DataHandlerUtil.narrowFactor(outputVo.getPrice()));
                outputVo.setTotal(DataHandlerUtil.narrowFactor(outputVo.getTotal()));
            }
            outputBillVo.setOutputVos(outputVos);
        }
        Integer count = outputBillDao.getCount(null, null, null, null);
        Map map = new HashMap();
        map.put("data", outputBillVos);
        map.put("total", count);
        return ObjectResponse.ok(map);
    }

    @Override
    public ObjectResponse getOutputByCondition(String contractNumber, String employeeDeptName,
                                               Date startTime, Date endTime,
                                               Integer pageSize, Integer pageNumber) {
        List<OutputBillVo> outputBillVos =
                outputBillDao.getByCondition(contractNumber, employeeDeptName, startTime, endTime, pageSize * (pageNumber - 1), pageSize);
        List<ProductOutputVo> outputVos = null;
        for (OutputBillVo outputBillVo : outputBillVos) {
            outputBillVo.setSumAmount(DataHandlerUtil.narrowFactor(outputBillVo.getSumAmount()));
            outputVos = productOutputDao.getByBillId(outputBillVo.getId());
            for (ProductOutputVo outputVo : outputVos) {
                outputVo.setPrice(DataHandlerUtil.narrowFactor(outputVo.getPrice()));
                outputVo.setTotal(DataHandlerUtil.narrowFactor(outputVo.getTotal()));
            }
            outputBillVo.setOutputVos(outputVos);
        }
        Integer count = outputBillDao.getCount(contractNumber, employeeDeptName, startTime, endTime);
        Map map = new HashMap();
        map.put("data", outputBillVos);
        map.put("total", count);
        return ObjectResponse.ok(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse deleteProductOutput(Long id) throws Exception {
        try {
            outputBillDao.deleteOutputBill(id);
            productOutputDao.deleteProductOutputByBillId(id);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("删除产品出库信息出现异常", e);
        }
        return BaseResponse.fail();
    }

    @Override
    public ObjectResponse getAllProductStockAmount(Integer pageSize, Integer pageNumber) {
        Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
        List<ProductVo> allProducts = productDao.getAllProducts(pageSize * (pageNumber - 1), pageSize, currentFolder);
        ArrayList<ProductStockVo> productStockVos = getProductStockVos(allProducts);

        Integer count = productDao.getCount(null, null, currentFolder);
        Map map = new HashMap();
        map.put("data", productStockVos);
        map.put("total", count);
        return ObjectResponse.ok(map);
    }

    private ArrayList<ProductStockVo> getProductStockVos(List<ProductVo> allProducts) {
        ArrayList<ProductStockVo> productStockVos = new ArrayList<>();
        ProductStockVo productStockVo;
        for (ProductVo productVo : allProducts) {
            productStockVo = new ProductStockVo();
            Integer surplusNumber = 0;
            List<ProductInputVo> productInputVos = productInputDao.getByProductId(productVo.getId());
            if (productInputVos != null && productInputVos.size() != 0) {
                for (ProductInputVo productInputVo : productInputVos) {
                    surplusNumber += productInputVo.getSurplusNumber();
                }
                BeanUtils.copyProperties(productVo, productStockVo);
                productStockVo.setAmounts(surplusNumber);
                logger.debug("不为空---："+productStockVo);
            }else {
                BeanUtils.copyProperties(productVo, productStockVo);
                productStockVo.setAmounts(0);
                logger.debug("为空---："+productStockVo);
            }
            productStockVos.add(productStockVo);
        }
        return productStockVos;
    }

    @Override
    public ObjectResponse getAllProductStockAmountByCondition(String name, String typeName,
                                                              Integer pageSize, Integer pageNumber) {
        Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
        List<ProductVo> allProducts = productDao.getByNameAndType(name, typeName,
                pageSize * (pageNumber - 1), pageSize, currentFolder);
        ArrayList<ProductStockVo> productStockVos = new ArrayList<>();
        ProductStockVo productStockVo;
        //获取所有产品信息
        for (ProductVo productVo : allProducts) {
            productStockVo = new ProductStockVo();
            Integer surplusNumber = 0;
            //根据每个产品信息获取该产品的入库信息
            List<ProductInputVo> productInputVos = productInputDao.getByProductId(productVo.getId());
            if (productInputVos != null && productInputVos.size() != 0) {
                for (ProductInputVo productInputVo : productInputVos) {
                    surplusNumber += productInputVo.getSurplusNumber();
                }
                BeanUtils.copyProperties(productVo, productStockVo);
                productStockVo.setAmounts(surplusNumber);
            }else {
                BeanUtils.copyProperties(productVo, productStockVo);
                productStockVo.setAmounts(0);
            }
            productStockVos.add(productStockVo);
        }

        Integer count = productDao.getCount(name, typeName, currentFolder);
        Map map = new HashMap();
        map.put("data", productStockVos);
        map.put("total", count);
        return ObjectResponse.ok(map);
    }

    @Override
    public ObjectResponse getProductInputById(Long id) {
        List<InputBillVo> inputBillVos = inputBillDao.getProductInputById(id);
        InputBillVo inputBillVo = inputBillVos.get(0);
        inputBillVo.setSumAmount(DataHandlerUtil.narrowFactor(inputBillVo.getSumAmount()));
        List<ProductInputVo> inputVos = productInputDao.getByBillId(inputBillVo.getId());
        for (ProductInputVo inputVo : inputVos) {
            inputVo.setPrice(DataHandlerUtil.narrowFactor(inputVo.getPrice()));
            inputVo.setTotal(DataHandlerUtil.narrowFactor(inputVo.getTotal()));
        }
        inputBillVo.setInputVos(inputVos);

        return ObjectResponse.ok(inputBillVo);
    }

    @Override
    public ObjectResponse getProductOutputById(Long id) {
        List<OutputBillVo> outputBillVos = outputBillDao.getProductOutputById(id);
        OutputBillVo outputBillVo = outputBillVos.get(0);
        outputBillVo.setSumAmount(DataHandlerUtil.narrowFactor(outputBillVo.getSumAmount()));
        List<ProductOutputVo> outputVos = productOutputDao.getByBillId(outputBillVo.getId());
        for (ProductOutputVo outputVo : outputVos) {
            outputVo.setPrice(DataHandlerUtil.narrowFactor(outputVo.getPrice()));
            outputVo.setTotal(DataHandlerUtil.narrowFactor(outputVo.getTotal()));
        }
        outputBillVo.setOutputVos(outputVos);

        return ObjectResponse.ok(outputBillVo);
    }


}
