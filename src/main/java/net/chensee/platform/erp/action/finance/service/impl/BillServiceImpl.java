package net.chensee.platform.erp.action.finance.service.impl;

import net.chensee.base.action.user.business.UserBus;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.base.common.po.BaseInfoPo;
import net.chensee.base.utils.CreateAndUpdateInfoUtil;
import net.chensee.base.utils.ResFolderUtils;
import net.chensee.base.utils.UserUtil;
import net.chensee.platform.erp.action.finance.mapper.BillDao;
import net.chensee.platform.erp.action.finance.mapper.BillProductDao;
import net.chensee.platform.erp.action.finance.po.BillPo;
import net.chensee.platform.erp.action.finance.po.BillProductPo;
import net.chensee.platform.erp.action.finance.service.BillService;
import net.chensee.platform.erp.action.finance.vo.BillProductVo;
import net.chensee.platform.erp.action.finance.vo.BillVo;
import net.chensee.platform.erp.utils.DataHandlerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author : shibo
 * @date : 2019/5/27 16:04
 */
@Service
@Transactional
public class BillServiceImpl implements BillService {
    private static final Logger logger = LoggerFactory.getLogger(BillPo.class);

    @Resource
    private BillDao billDao;
    @Resource
    private BillProductDao billProductDao;
    @Resource
    private UserBus userBus;

    @Override
    public ObjectResponse getAllBill(Integer pageSize, Integer pageNumber, Integer direct) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            List<BillVo> billVos = billDao.getAllBill(pageSize * (pageNumber - 1), pageSize, direct, currentFolder);
            List<BillProductVo> billProductVos = null;
            if(billVos != null && billVos.size() > 0) {
                for (BillVo billVo : billVos) {
                    billVo.setTotalAmount(DataHandlerUtil.narrowFactor(billVo.getTotalAmount()));
                    billVo.setTotalTaxAmount(DataHandlerUtil.narrowFactor(billVo.getTotalTaxAmount()));
                    billVo.setNumeralAmount(DataHandlerUtil.narrowFactor(billVo.getNumeralAmount()));
                    billProductVos = billProductDao.getByBillId(billVo.getId());
                    for (BillProductVo billProductVo : billProductVos) {
                        billProductVo.setTaxRate(DataHandlerUtil.narrowFactor(billProductVo.getTaxRate()));
                        billProductVo.setAmount(DataHandlerUtil.narrowFactor(billProductVo.getAmount()));
                        billProductVo.setTaxAmount(DataHandlerUtil.narrowFactor(billProductVo.getTaxAmount()));
                        billProductVo.setPrice(DataHandlerUtil.narrowFactor(billProductVo.getPrice()));
                    }
                    billVo.setBillProductVos(billProductVos);
                }
            }
            Integer count = billDao.getCount(null, null, direct, currentFolder);
            Map map = new HashMap();
            map.put("data", billVos);
            map.put("total" ,count);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("获取所有票据发生异常",e);
            return ObjectResponse.fail("获取所有票据发生异常");
        }
    }

    @Override
    public ObjectResponse getByContractId(Integer direct, Long contractId, Integer pageSize, Integer pageNumber) {
        try {
            List<BillVo> billVos = billDao.getByContractId(direct, contractId, pageSize * (pageNumber - 1), pageSize);
            List<BillProductVo> billProductVos = null;
            if(billVos != null && billVos.size() > 0) {
                for (BillVo billVo : billVos) {
                    billVo.setTotalAmount(DataHandlerUtil.narrowFactor(billVo.getTotalAmount()));
                    billVo.setTotalTaxAmount(DataHandlerUtil.narrowFactor(billVo.getTotalTaxAmount()));
                    billVo.setNumeralAmount(DataHandlerUtil.narrowFactor(billVo.getNumeralAmount()));
                    billProductVos = billProductDao.getByBillId(billVo.getId());
                    for (BillProductVo billProductVo : billProductVos) {
                        billProductVo.setTaxRate(DataHandlerUtil.narrowFactor(billProductVo.getTaxRate()));
                        billProductVo.setAmount(DataHandlerUtil.narrowFactor(billProductVo.getAmount()));
                        billProductVo.setTaxAmount(DataHandlerUtil.narrowFactor(billProductVo.getTaxAmount()));
                        billProductVo.setPrice(DataHandlerUtil.narrowFactor(billProductVo.getPrice()));
                    }
                    billVo.setBillProductVos(billProductVos);
                }
            }
            Integer count = billDao.getCountByContractId(direct, contractId);
            Map map = new HashMap();
            map.put("data", billVos);
            map.put("total" ,count);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("根据合同获取票据出现异常", e);
            return ObjectResponse.fail("根据合同获取票据出现异常");
        }
    }

    @Override
    public ObjectResponse getByOtherPartyNameAndNumber(String otherPartyName, String number, Integer pageSize, Integer pageNumber, Integer direct) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            List<BillVo> billVos = billDao.getByOtherPartyNameAndNumber(otherPartyName, number, pageSize * (pageNumber - 1), pageSize, direct);
            List<BillProductVo> billProductVos = null;
            if(billVos != null && billVos.size() > 0) {
                for (BillVo billVo : billVos) {
                    billVo.setTotalAmount(DataHandlerUtil.narrowFactor(billVo.getTotalAmount()));
                    billVo.setTotalTaxAmount(DataHandlerUtil.narrowFactor(billVo.getTotalTaxAmount()));
                    billVo.setNumeralAmount(DataHandlerUtil.narrowFactor(billVo.getNumeralAmount()));
                    billProductVos = billProductDao.getByBillId(billVo.getId());
                    for (BillProductVo billProductVo : billProductVos) {
                        billProductVo.setTaxRate(DataHandlerUtil.narrowFactor(billProductVo.getTaxRate()));
                        billProductVo.setAmount(DataHandlerUtil.narrowFactor(billProductVo.getAmount()));
                        billProductVo.setTaxAmount(DataHandlerUtil.narrowFactor(billProductVo.getTaxAmount()));
                        billProductVo.setPrice(DataHandlerUtil.narrowFactor(billProductVo.getPrice()));
                    }
                    billVo.setBillProductVos(billProductVos);
                }
            }
            Integer count = billDao.getCount(otherPartyName, number, direct, currentFolder);
            Map map = new HashMap();
            map.put("data", billVos);
            map.put("total" ,count);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("获取票据出现异常", e);
            return ObjectResponse.fail("获取票据出现异常");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse addBill(BillVo billVo) throws Exception{
        try {
            Long userId = UserUtil.getCurrentUser().getId();
            if (!userBus.isExistUserInSelectDept(userId, billVo.getDeptId())) {
                return ObjectResponse.fail("当前用户不属于该部门");
            }
            BillPo billPo = new BillPo();
            BeanUtils.copyProperties(billVo, billPo);
            billPo.setTotalAmount(DataHandlerUtil.enlargeFactor(billVo.getTotalAmount()));
            billPo.setTotalTaxAmount(DataHandlerUtil.enlargeFactor(billVo.getTotalTaxAmount()));
            billPo.setNumeralAmount(DataHandlerUtil.enlargeFactor(billVo.getNumeralAmount()));
            billPo.setStatus(BaseInfoPo.Status_Able);
            billPo.setFolderId(billVo.getFolderId());
            CreateAndUpdateInfoUtil.addCreateInfo(billPo);
            CreateAndUpdateInfoUtil.addUpdateInfo(billPo);
            billDao.addBill(billPo);
            BillProductPo productPo = null;
            List<BillProductVo> products = billVo.getBillProductVos();
            if(products != null && products.size() > 0) {
                for (BillProductVo product : products) {
                    productPo = new BillProductPo();
                    BeanUtils.copyProperties(product, productPo);
                    productPo.setPrice(DataHandlerUtil.enlargeFactor(product.getPrice()));
                    productPo.setTaxRate(DataHandlerUtil.enlargeFactor(product.getTaxRate()));
                    productPo.setAmount(DataHandlerUtil.enlargeFactor(product.getAmount()));
                    productPo.setTaxAmount(DataHandlerUtil.enlargeFactor(product.getTaxAmount()));
                    productPo.setBillId(billPo.getId());
                    productPo.setStatus(BaseInfoPo.Status_Able);
                    productPo.setFolderId(billVo.getFolderId());
                    CreateAndUpdateInfoUtil.addCreateInfo(productPo);
                    CreateAndUpdateInfoUtil.addUpdateInfo(productPo);
                    billProductDao.addBillProduct(productPo);
                }
            }
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("添加票据出现异常", e);
            return BaseResponse.fail("添加票据出现异常");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse updateBill(BillVo billVo) throws Exception{
        try {
            Long userId = UserUtil.getCurrentUser().getId();
            if (!userBus.isExistUserInSelectDept(userId, billVo.getDeptId())) {
                return ObjectResponse.fail("当前用户不属于该部门");
            }
            BillPo billPo = new BillPo();
            BeanUtils.copyProperties(billVo, billPo);
            billPo.setTotalAmount(DataHandlerUtil.enlargeFactor(billVo.getTotalAmount()));
            billPo.setTotalTaxAmount(DataHandlerUtil.enlargeFactor(billVo.getTotalTaxAmount()));
            billPo.setNumeralAmount(DataHandlerUtil.enlargeFactor(billVo.getNumeralAmount()));
            CreateAndUpdateInfoUtil.addUpdateInfo(billPo);
            billDao.updateBill(billPo);
            BillProductPo productPo = null;
            List<BillProductVo> products = billVo.getBillProductVos();
            if(products != null && products.size() > 0) {
                for (BillProductVo product : products) {
                    //TODO 需要将排除掉的产品删除，系统中多处同理

                    productPo = new BillProductPo();
                    BeanUtils.copyProperties(product, productPo);
                    productPo.setPrice(DataHandlerUtil.enlargeFactor(product.getPrice()));
                    productPo.setTaxRate(DataHandlerUtil.enlargeFactor(product.getTaxRate()));
                    productPo.setAmount(DataHandlerUtil.enlargeFactor(product.getAmount()));
                    productPo.setTaxAmount(DataHandlerUtil.enlargeFactor(product.getTaxAmount()));
                    if(product.getId() != null && product.getId() != 0) {
                        CreateAndUpdateInfoUtil.addUpdateInfo(productPo);
                        billProductDao.updateBillProduct(productPo);
                    } else {
                        productPo.setBillId(billPo.getId());
                        productPo.setStatus(BaseInfoPo.Status_Able);
                        productPo.setFolderId(billVo.getFolderId());
                        CreateAndUpdateInfoUtil.addCreateInfo(productPo);
                        CreateAndUpdateInfoUtil.addUpdateInfo(productPo);
                        billProductDao.addBillProduct(productPo);
                    }

                }
            }
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("修改票据出现异常", e);
            return BaseResponse.fail("修改票据出现异常");
        }
    }

    @Override
    public BaseResponse deleteBill(Long id) {
        try {
            billDao.deleteBill(id);
            billProductDao.deleteByBillId(id);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("删除票据出现异常", e);
            return BaseResponse.fail("删除票据出现异常");
        }
    }

    @Override
    public ObjectResponse getReceiveById(Long id) {
        try {
            List<BillVo> billVos = billDao.getReceiveById(id);
            List<BillProductVo> billProductVos = null;
            BillVo billVo = billVos.get(0);
            billVo.setTotalAmount(DataHandlerUtil.narrowFactor(billVo.getTotalAmount()));
            billVo.setTotalTaxAmount(DataHandlerUtil.narrowFactor(billVo.getTotalTaxAmount()));
            billVo.setNumeralAmount(DataHandlerUtil.narrowFactor(billVo.getNumeralAmount()));
            billProductVos = billProductDao.getByBillId(billVo.getId());
            for (BillProductVo billProductVo : billProductVos) {
                billProductVo.setTaxRate(DataHandlerUtil.narrowFactor(billProductVo.getTaxRate()));
                billProductVo.setAmount(DataHandlerUtil.narrowFactor(billProductVo.getAmount()));
                billProductVo.setTaxAmount(DataHandlerUtil.narrowFactor(billProductVo.getTaxAmount()));
                billProductVo.setPrice(DataHandlerUtil.narrowFactor(billProductVo.getPrice()));
            }
            billVo.setBillProductVos(billProductVos);

            return ObjectResponse.ok(billVo);
        } catch (Exception e) {
            logger.error("根据ID获取票据出现异常", e);
            return ObjectResponse.fail("根据ID获取票据出现异常");
        }
    }

    @Override
    public ObjectResponse getPayById(Long id) {
        try {
            List<BillVo> billVos = billDao.getPayById(id);
            List<BillProductVo> billProductVos = null;
            BillVo billVo = billVos.get(0);
            billVo.setTotalAmount(DataHandlerUtil.narrowFactor(billVo.getTotalAmount()));
            billVo.setTotalTaxAmount(DataHandlerUtil.narrowFactor(billVo.getTotalTaxAmount()));
            billVo.setNumeralAmount(DataHandlerUtil.narrowFactor(billVo.getNumeralAmount()));
            billProductVos = billProductDao.getByBillId(billVo.getId());
            for (BillProductVo billProductVo : billProductVos) {
                billProductVo.setTaxRate(DataHandlerUtil.narrowFactor(billProductVo.getTaxRate()));
                billProductVo.setAmount(DataHandlerUtil.narrowFactor(billProductVo.getAmount()));
                billProductVo.setTaxAmount(DataHandlerUtil.narrowFactor(billProductVo.getTaxAmount()));
                billProductVo.setPrice(DataHandlerUtil.narrowFactor(billProductVo.getPrice()));
            }
            billVo.setBillProductVos(billProductVos);

            return ObjectResponse.ok(billVo);
        } catch (Exception e) {
            logger.error("根据ID获取票据出现异常", e);
            return ObjectResponse.fail("根据ID获取票据出现异常");
        }
    }
}
