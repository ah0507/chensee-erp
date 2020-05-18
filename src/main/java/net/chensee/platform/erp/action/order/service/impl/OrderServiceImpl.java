package net.chensee.platform.erp.action.order.service.impl;

import net.chensee.base.action.user.business.UserBus;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.base.common.po.BaseInfoPo;
import net.chensee.base.utils.CreateAndUpdateInfoUtil;
import net.chensee.base.utils.ResFolderUtils;
import net.chensee.base.utils.UserUtil;
import net.chensee.platform.erp.action.order.mapper.OrderContractDao;
import net.chensee.platform.erp.action.order.mapper.OrderContractPayStageDao;
import net.chensee.platform.erp.action.order.mapper.OrderContractProductDao;
import net.chensee.platform.erp.action.order.mapper.OrderDao;
import net.chensee.platform.erp.action.order.po.OrderContractPayStagePo;
import net.chensee.platform.erp.action.order.po.OrderContractPo;
import net.chensee.platform.erp.action.order.po.OrderContractProductPo;
import net.chensee.platform.erp.action.order.po.OrderPo;
import net.chensee.platform.erp.action.order.service.OrderService;
import net.chensee.platform.erp.action.order.vo.OrderContractPayStageVo;
import net.chensee.platform.erp.action.order.vo.OrderContractProductVo;
import net.chensee.platform.erp.action.order.vo.OrderContractVo;
import net.chensee.platform.erp.action.order.vo.OrderVo;
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
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderPo.class);


    @Resource
    private OrderDao orderDao;
    @Resource
    private OrderContractDao orderContractDao;
    @Resource
    private OrderContractProductDao orderContractProductDao;
    @Resource
    private OrderContractPayStageDao orderContractPayStageDao;
    @Resource
    private UserBus userBus;
    private List<OrderVo> orderVos;

    @Override
    public ObjectResponse getAllOrders(Integer pageSize, Integer pageNumber, Integer direct) {
        //一次型查出订单、合同、产品、付款方式
        //还是循环查出
        //还是进入详情页重新请求数据
        //暂时循环查出
        Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
        List<OrderVo> orderVos = orderDao.getAllOrders(pageSize * (pageNumber - 1), pageSize, direct, currentFolder);
        for (OrderVo orderVo : orderVos) {
            if (orderVo.getFileIds() != null) {
                orderVo.setFileIdList(Arrays.asList(orderVo.getFileIds().split(",")));
            }
        }
        orderVos = this.getOrderInfo(orderVos);
        Integer count = orderDao.getCount(null, null, direct, currentFolder);
        Map map = new HashMap();
        map.put("data", orderVos);
        map.put("total" ,count);
        return ObjectResponse.ok(map);
    }

    @Override
    public ObjectResponse getByNameAndNo(String name, String no, Integer pageSize, Integer pageNumber, Integer direct) {
        Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
        List<OrderVo> orderVos = orderDao.getByNameAndNo(name, no, pageSize * (pageNumber - 1), pageSize, direct);
        for (OrderVo orderVo : orderVos) {
            if (orderVo.getFileIds() != null) {
                orderVo.setFileIdList(Arrays.asList(orderVo.getFileIds().split(",")));
            }
        }
        orderVos = this.getOrderInfo(orderVos);
        Integer count = orderDao.getCount(name, no, direct, currentFolder);
        Map map = new HashMap();
        map.put("data", orderVos);
        map.put("total" ,count);
        return ObjectResponse.ok(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse addOrder(OrderVo orderVo) throws Exception{
        try {
            Long userId = UserUtil.getCurrentUser().getId();
            if (!userBus.isExistUserInSelectDept(userId, orderVo.getDeptId())) {
                return ObjectResponse.fail("当前用户不属于该部门");
            }

            //首先添加订单实体信息
            OrderPo orderPo = new OrderPo();
            BeanUtils.copyProperties(orderVo, orderPo);
            orderPo.setAmount(DataHandlerUtil.enlargeFactor(orderVo.getAmount()));
            orderPo.setStatus(BaseInfoPo.Status_Able);
            orderPo.setFolderId(orderVo.getFolderId());
            CreateAndUpdateInfoUtil.addCreateInfo(orderPo);
            CreateAndUpdateInfoUtil.addUpdateInfo(orderPo);
            orderDao.addOrder(orderPo);

            //如果订单中包含合同事宜
            if(orderVo.getContracts() != null) {
                OrderContractPo contractPo = null;
                List<OrderContractProductVo> products = null;
                List<OrderContractPayStageVo> payStages = null;
                for (OrderContractVo contractVo : orderVo.getContracts()) {
                    //添加订单相关的合同实体信息（该实体信息为【订单-合同关联信息】，注意与【合同实体信息】区分）
                    contractPo = new OrderContractPo();
                    contractPo.setOrderId(orderPo.getId());
                    contractPo.setFolderId(orderPo.getFolderId());
                    contractPo.setStatus(BaseInfoPo.Status_Able);
                    CreateAndUpdateInfoUtil.addCreateInfo(contractPo);
                    CreateAndUpdateInfoUtil.addUpdateInfo(contractPo);
                    orderContractDao.addOrderContract(contractPo);
                    //获取合同信息中的商品信息和付款信息
                    products = contractVo.getProducts();
                    payStages = contractVo.getPayMethods();
                    OrderContractProductPo product = null;
                    OrderContractPayStagePo payStage = null;
                    //添加合同中的商品信息实体类
                    for (OrderContractProductVo p : products) {
                        product = new OrderContractProductPo();
                        BeanUtils.copyProperties(p, product);
                        product.setOrderId(orderPo.getId());
                        product.setOrderContractId(contractPo.getId());
                        product.setProductId(p.getProductId());
                        product.setAmounts(p.getAmounts());
                        product.setFolderId(orderPo.getFolderId());
                        product.setStatus(BaseInfoPo.Status_Able);
                        CreateAndUpdateInfoUtil.addCreateInfo(product);
                        CreateAndUpdateInfoUtil.addUpdateInfo(product);
                        orderContractProductDao.addOrderContractProduct(product);
                    }
                    //添加合同中的付款信息实体类
                    for (OrderContractPayStageVo p : payStages) {
                        payStage = new OrderContractPayStagePo();
                        BeanUtils.copyProperties(p, payStage);
                        payStage.setRate(DataHandlerUtil.enlargeFactor(p.getRate()));
                        payStage.setAmount(DataHandlerUtil.enlargeFactor(p.getAmount()));
                        payStage.setOrderId(orderPo.getId());
                        payStage.setOrderContractId(contractPo.getId());
                        payStage.setFolderId(orderPo.getFolderId());
                        payStage.setStatus(BaseInfoPo.Status_Able);
                        CreateAndUpdateInfoUtil.addCreateInfo(payStage);
                        CreateAndUpdateInfoUtil.addUpdateInfo(payStage);
                        orderContractPayStageDao.addOrderContractPayStage(payStage);
                    }
                }
            }
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("添加订单出现异常", e);
        }
        return BaseResponse.fail();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse updateOrder(OrderVo orderVo) throws Exception{
        try {
            Long userId = UserUtil.getCurrentUser().getId();
            if (!userBus.isExistUserInSelectDept(userId, orderVo.getDeptId())) {
                return ObjectResponse.fail("当前用户不属于该部门");
            }
            OrderPo orderPo = new OrderPo();
            BeanUtils.copyProperties(orderVo, orderPo);
            orderPo.setAmount(DataHandlerUtil.enlargeFactor(orderVo.getAmount()));
            CreateAndUpdateInfoUtil.addUpdateInfo(orderPo);
            orderDao.updateOrder(orderPo);
            OrderContractPo contractPo = null;
            List<OrderContractProductVo> products = null;
            List<OrderContractPayStageVo> payStages = null;
            OrderContractProductPo productPo = null;
            OrderContractPayStagePo payStagePo = null;
            List<OrderContractVo> currentContracts = orderVo.getContracts();
            if (currentContracts == null) {
                return BaseResponse.ok();
            }
            //获取数据库中关联的合同信息
            List<OrderContractVo> originalContracts = orderContractDao.getByOrderId(orderVo.getId());
            //更新两个contracts同等数量的contracts
            updateContracts(orderPo, currentContracts, originalContracts);
            //更新两个contracts不同数量中剩余其他的contracts
            updateOtherContracts(orderPo, currentContracts, originalContracts);

            //更新订单合同相关的产品信息和付款阶段信息
            //获取更新合同信息后，数据库中该订单关联的所有合同
            /*List<OrderContractVo> newOriginalContracts = orderContractDao.getByOrderId(orderPo.getId());
            for (OrderContractVo orderContractVo : newOriginalContracts) {
                //orderContractVo只包含orderContractId
                Long orderContractVoId = orderContractVo.getId();
                //遍历接收到的orderVo关联的contracts，取出其中orderContractId和上面的orderContractId相等的contracts
                for (OrderContractVo curOrderContractVo : currentContracts) {
                    if (curOrderContractVo.getId() == orderContractVoId) {
                        List<OrderContractProductVo> currentProducts = curOrderContractVo.getProducts();
                        List<OrderContractPayStageVo> currentPayStages = curOrderContractVo.getPayMethods();
                        //遍历其中的products和payStages
                        List<OrderContractProductVo> originalProducts = orderContractProductDao.getByContractId(orderContractVo.getId());
                        List<OrderContractPayStageVo> originalPayStages = orderContractPayStageDao.getByContractId(orderContractVo.getId());
                        
                    }
                }
            }*/

            /*//新建三个list，用来存放订单目前所有的相关联信息在数据库中的ID
            List<Long> currentContractList = new ArrayList<>();
            List<Long> currentProductList = new ArrayList<>();
            List<Long> currentPayStageList = new ArrayList<>();
            if (orderVo.getContracts() != null) {
                for (OrderContractVo contractVo : orderVo.getContracts()) {
                    contractPo = new OrderContractPo();
                    BeanUtils.copyProperties(contractVo, contractPo);
                    contractPo.setOrderId(orderPo.getId());
                    //如果新增加了合同，则需要addContract
                    if (contractPo.getId() == null) {
                        contractPo.setStatus(orderVo.getStatus());
                        contractPo.setFolderId(orderVo.getFolderId());
                        orderContractDao.addOrderContract(contractPo);
                    }else {
                        orderContractDao.updateOrderContract(contractPo);
                    }
                    products = contractVo.getProducts();
                    payStages = contractVo.getPayMethods();
                    OrderContractProductPo product = null;
                    OrderContractPayStagePo payStage = null;

                    for (OrderContractProductVo p : products) {
                        product = new OrderContractProductPo();
                        BeanUtils.copyProperties(p, product);
                        product.setOrderId(orderPo.getId());
                        product.setOrderContractId(contractPo.getId());
                        if(product.getId() != null && product.getId() != 0) {
                            //如果有对应的合同产品信息，则更新信息
                            orderContractProductDao.updateOrderContractProduct(product);
                        } else {
                            //如果没有对应的合同产品信息，则新增信息
                            product.setFolderId(orderVo.getFolderId());
                            product.setStatus(BaseInfoPo.Status_Able);
                            orderContractProductDao.addOrderContractProduct(product);
                        }
                        //获取上面的所有合同产品信息的id，查询数据库中所有订单对应的合同产品信息，把多余的关联信息删除
                        // TODO
                        currentProductList.add(product.getId());
                        logger.info("-------产品信息----ID---："+product.getId()+"------");
                    }
                    for (OrderContractPayStageVo p : payStages) {
                        payStage = new OrderContractPayStagePo();
                        BeanUtils.copyProperties(p, payStage);
                        payStage.setRate(DataHandlerUtil.enlargeFactor(p.getRate()));
                        payStage.setAmount(DataHandlerUtil.enlargeFactor(p.getAmount()));
                        payStage.setOrderId(orderPo.getId());
                        payStage.setOrderContractId(contractPo.getId());
                        if(payStage.getId() != null && payStage.getId() != 0) {
                            orderContractPayStageDao.updateOrderContractPayStage(payStage);
                        } else {
                            payStage.setFolderId(orderVo.getFolderId());
                            payStage.setStatus(BaseInfoPo.Status_Able);
                            orderContractPayStageDao.addOrderContractPayStage(payStage);
                        }
                    }
                }
            }*/
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("修改订单出现异常", e);
        }
        return BaseResponse.fail();
    }

    //更新两个contracts同等数量的contract
    private void updateContracts(OrderPo orderPo,
                                 List<OrderContractVo> currentContracts,
                                 List<OrderContractVo> originalContracts) {
        OrderContractPo contractPo;
        List<OrderContractVo> loopContracts =
                (currentContracts.size() >= originalContracts.size() ? originalContracts : currentContracts);
        for (int i = 0; i < loopContracts.size(); i++) {
            OrderContractVo contractVo = currentContracts.get(i);
            contractPo = new OrderContractPo();
            contractPo.setId(originalContracts.get(i).getId());
            BeanUtils.copyProperties(contractVo, contractPo);
            contractPo.setOrderId(orderPo.getId());
            CreateAndUpdateInfoUtil.addUpdateInfo(contractPo);
            orderContractDao.updateOrderContract(contractPo);

            List<OrderContractProductVo> currentProducts = contractVo.getProducts();
            List<OrderContractProductVo> originalProducts = orderContractProductDao.getByContractId(contractVo.getId());
            //更新两个products同等数量的product
            updateProducts(orderPo, contractVo, currentProducts ,originalProducts);
            //更新两个products不同数量中剩余其他的product
            updateOtherProducts(orderPo, contractVo, currentProducts ,originalProducts);

            List<OrderContractPayStageVo> currentPayStages = contractVo.getPayMethods();
            List<OrderContractPayStageVo> originalPayStages = orderContractPayStageDao.getByContractId(contractVo.getId());
            //更新两个payStages同等数量的payStage
            updatePayStages(orderPo, contractVo, currentPayStages ,originalPayStages);
            //更新两个payStages不同数量中剩余其他的payStage
            updateOtherPayStages(orderPo, contractVo, currentPayStages ,originalPayStages);
        }
    }

    //更新两个contracts不同数量中剩余其他的contract
    private void updateOtherContracts(OrderPo orderPo,
                                      List<OrderContractVo> currentContracts,
                                      List<OrderContractVo> originalContracts) {
        OrderContractPo contractPo;
        OrderContractProductPo productPo;
        OrderContractPayStagePo payStagePo;
        if (currentContracts.size() > originalContracts.size()) {
            for (int i=originalContracts.size() ; i<currentContracts.size() ; i++) {
                OrderContractVo contractVo = currentContracts.get(i);
                if (contractVo != null) {
                    contractPo = new OrderContractPo();
                    BeanUtils.copyProperties(contractVo, contractPo);
                    contractPo.setOrderId(orderPo.getId());
                    contractPo.setStatus(BaseInfoPo.Status_Able);
                    contractPo.setFolderId(orderPo.getFolderId());
                    CreateAndUpdateInfoUtil.addCreateInfo(contractPo);
                    CreateAndUpdateInfoUtil.addUpdateInfo(contractPo);
                    orderContractDao.addOrderContract(contractPo);
                    contractVo.setId(contractPo.getId());
                    //添加额外的合同关联的产品和付款阶段信息
                    addExtraProductsAndPayStages(orderPo, contractVo);
                }
            }
        }
        if (currentContracts.size() < originalContracts.size()) {
            for (int i=currentContracts.size() ; i<originalContracts.size() ;i++) {
                Long contractId = originalContracts.get(i).getId();
                orderContractDao.deleteOrderContract(contractId);
                //删除多余的合同关联的产品和付款阶段信息
                deleteExcessProductsAndPayStages(contractId);
            }
        }
    }

    private void addExtraProductsAndPayStages(OrderPo orderPo, OrderContractVo contractVo) {
        OrderContractProductPo productPo;
        OrderContractPayStagePo payStagePo;
        //添加额外的合同关联的产品和付款阶段信息
        List<OrderContractProductVo> extraProducts = contractVo.getProducts();
        for (OrderContractProductVo extraProduct : extraProducts) {
            productPo = new OrderContractProductPo();
            BeanUtils.copyProperties(extraProduct, productPo);
            productPo.setOrderId(orderPo.getId());
            productPo.setOrderContractId(contractVo.getId());
            productPo.setFolderId(orderPo.getFolderId());
            productPo.setStatus(BaseInfoPo.Status_Able);
            CreateAndUpdateInfoUtil.addCreateInfo(productPo);
            CreateAndUpdateInfoUtil.addUpdateInfo(productPo);
            orderContractProductDao.addOrderContractProduct(productPo);
        }
        List<OrderContractPayStageVo> extraPayStages = contractVo.getPayMethods();
        for (OrderContractPayStageVo extraPayStage : extraPayStages) {
            payStagePo = new OrderContractPayStagePo();
            BeanUtils.copyProperties(extraPayStage, payStagePo);
            payStagePo.setRate(DataHandlerUtil.enlargeFactor(extraPayStage.getRate()));
            payStagePo.setAmount(DataHandlerUtil.enlargeFactor(extraPayStage.getAmount()));
            payStagePo.setOrderId(orderPo.getId());
            payStagePo.setOrderContractId(contractVo.getId());
            payStagePo.setFolderId(orderPo.getFolderId());
            payStagePo.setStatus(BaseInfoPo.Status_Able);
            CreateAndUpdateInfoUtil.addCreateInfo(payStagePo);
            CreateAndUpdateInfoUtil.addUpdateInfo(payStagePo);
            orderContractPayStageDao.addOrderContractPayStage(payStagePo);
        }
    }

    private void deleteExcessProductsAndPayStages(Long contractId) {
        //删除多余的合同关联的产品和付款阶段信息
        List<OrderContractProductVo> excessProducts = orderContractProductDao.getByContractId(contractId);
        for (OrderContractProductVo excessProduct : excessProducts) {
            orderContractProductDao.deleteOrderContractProduct(excessProduct.getId());
        }
        List<OrderContractPayStageVo> excessPayStages = orderContractPayStageDao.getByContractId(contractId);
        for (OrderContractPayStageVo excessPayStage : excessPayStages) {
            orderContractPayStageDao.deleteOrderContractPayStage(excessPayStage.getId());
        }
    }

    //更新两个products同等数量的product
    private void updateProducts(OrderPo orderPo,
                                OrderContractVo contractVo,
                                List<OrderContractProductVo> currentProducts,
                                List<OrderContractProductVo> originalProducts) {
        OrderContractProductPo productPo;
        List<OrderContractProductVo> loopContracts =
                (currentProducts.size() >= originalProducts.size() ? originalProducts : currentProducts);
        for (int i=0; i<loopContracts.size() ; i++) {
            OrderContractProductVo orderContractProductVo = currentProducts.get(i);
            productPo = new OrderContractProductPo();
            BeanUtils.copyProperties(orderContractProductVo, productPo);
            productPo.setId(originalProducts.get(i).getId());
            productPo.setOrderId(orderPo.getId());
            productPo.setOrderContractId(contractVo.getId());
            CreateAndUpdateInfoUtil.addUpdateInfo(productPo);
            orderContractProductDao.updateOrderContractProduct(productPo);
        }
    }

    //更新两个products不同数量中剩余其他的product
    private void updateOtherProducts(OrderPo orderPo,
                                     OrderContractVo contractVo,
                                     List<OrderContractProductVo> currentProducts,
                                     List<OrderContractProductVo> originalProducts) {
        OrderContractProductPo productPo;
        if (currentProducts.size() > originalProducts.size()) {
            for (int i=originalProducts.size() ; i<currentProducts.size() ; i++) {
                OrderContractProductVo orderContractProductVo = currentProducts.get(i);
                if (orderContractProductVo != null) {
                    productPo = new OrderContractProductPo();
                    BeanUtils.copyProperties(orderContractProductVo, productPo);
                    productPo.setOrderId(orderPo.getId());
                    productPo.setOrderContractId(contractVo.getId());
                    productPo.setFolderId(orderPo.getFolderId());
                    productPo.setStatus(BaseInfoPo.Status_Able);
                    CreateAndUpdateInfoUtil.addCreateInfo(productPo);
                    CreateAndUpdateInfoUtil.addUpdateInfo(productPo);
                    orderContractProductDao.addOrderContractProduct(productPo);
                }
            }
        }
        if (currentProducts.size() < originalProducts.size()) {
            for (int i=currentProducts.size() ; i<originalProducts.size() ;i++) {
                Long productId = originalProducts.get(i).getId();
                orderContractProductDao.deleteOrderContractProduct(productId);
            }
        }
    }

    //更新两个payStages同等数量的payStage
    private void updatePayStages(OrderPo orderPo,
                                 OrderContractVo contractVo,
                                 List<OrderContractPayStageVo> currentPayStages,
                                 List<OrderContractPayStageVo> originalPayStages) {
        OrderContractPayStagePo payStagePo;
        List<OrderContractPayStageVo> loopContracts =
                (currentPayStages.size() >= originalPayStages.size() ? originalPayStages : currentPayStages);
        for (int i = 0; i<loopContracts.size() ; i++) {
            OrderContractPayStageVo orderContractPayStageVo = currentPayStages.get(i);
            payStagePo = new OrderContractPayStagePo();
            BeanUtils.copyProperties(orderContractPayStageVo, payStagePo);
            payStagePo.setId(orderContractPayStageVo.getId());
            payStagePo.setRate(DataHandlerUtil.enlargeFactor(orderContractPayStageVo.getRate()));
            payStagePo.setAmount(DataHandlerUtil.enlargeFactor(orderContractPayStageVo.getAmount()));
            payStagePo.setOrderId(orderPo.getId());
            payStagePo.setOrderContractId(contractVo.getId());
            CreateAndUpdateInfoUtil.addUpdateInfo(payStagePo);
            orderContractPayStageDao.updateOrderContractPayStage(payStagePo);
        }
    }

    //更新两个payStages不同数量中剩余其他的payStage
    private void updateOtherPayStages(OrderPo orderPo,
                                      OrderContractVo contractVo,
                                      List<OrderContractPayStageVo> currentPayStages,
                                      List<OrderContractPayStageVo> originalPayStages) {
        OrderContractPayStagePo payStagePo;
        if (currentPayStages.size() > originalPayStages.size()) {
            for (int i=originalPayStages.size() ; i<currentPayStages.size() ; i++) {
                OrderContractPayStageVo orderContractPayStageVo = currentPayStages.get(i);
                payStagePo = new OrderContractPayStagePo();
                BeanUtils.copyProperties(orderContractPayStageVo, payStagePo);
                payStagePo.setRate(DataHandlerUtil.enlargeFactor(orderContractPayStageVo.getRate()));
                payStagePo.setAmount(DataHandlerUtil.enlargeFactor(orderContractPayStageVo.getAmount()));
                payStagePo.setOrderId(orderPo.getId());
                payStagePo.setOrderContractId(contractVo.getId());
                payStagePo.setFolderId(orderPo.getFolderId());
                payStagePo.setStatus(BaseInfoPo.Status_Able);
                CreateAndUpdateInfoUtil.addCreateInfo(payStagePo);
                CreateAndUpdateInfoUtil.addUpdateInfo(payStagePo);
                orderContractPayStageDao.addOrderContractPayStage(payStagePo);
            }
        }
        if (currentPayStages.size() < originalPayStages.size()) {
            for (int i=currentPayStages.size() ; i<originalPayStages.size() ;i++) {
                Long payStageId = originalPayStages.get(i).getId();
                orderContractPayStageDao.deleteOrderContractPayStage(payStageId);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse deleteOrder(Long id) throws Exception{
        try {
            orderDao.deleteOrder(id);
            orderContractDao.deleteByOrderId(id);
            orderContractProductDao.deleteByOrderId(id);
            orderContractPayStageDao.deleteByOrderId(id);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("删除订单出现异常", e);
        }
        return BaseResponse.fail();
    }

    @Override
    public ObjectResponse getById(Integer direct, Long orderId) {
        Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
        List<OrderVo> orderVos = orderDao.getById(direct, orderId, currentFolder);
        if (orderVos != null && orderVos.size() != 0) {
            for (OrderVo orderVo : orderVos) {
                if (orderVo.getFileIds() != null) {
                    orderVo.setFileIdList(Arrays.asList(orderVo.getFileIds().split(",")));
                }
            }
            orderVos = this.getOrderInfo(orderVos);
            return ObjectResponse.ok(orderVos.get(0));
        }
        return ObjectResponse.fail("没有找到该订单信息");

    }

    private List<OrderVo> getOrderInfo(List<OrderVo> orderVos) {
        List<OrderContractVo> contractVos = null;
        List<OrderContractProductVo> productVos = null;
        List<OrderContractPayStageVo> payStageVos = null;
        if(orderVos != null && orderVos.size() > 0) {
            for (OrderVo orderVo : orderVos) {
                orderVo.setAmount(DataHandlerUtil.narrowFactor(orderVo.getAmount()));
                contractVos = orderContractDao.getByOrderId(orderVo.getId());
                if(contractVos != null && contractVos.size() > 0) {
                    for (OrderContractVo contractVo : contractVos) {
                        productVos = orderContractProductDao.getByContractId(contractVo.getId());
                        payStageVos = orderContractPayStageDao.getByContractId(contractVo.getId());
                        for (OrderContractPayStageVo payStageVo : payStageVos) {
                            payStageVo.setRate(DataHandlerUtil.narrowFactor(payStageVo.getRate()));
                            payStageVo.setAmount(DataHandlerUtil.narrowFactor(payStageVo.getAmount()));
                        }
                        contractVo.setProducts(productVos);
                        contractVo.setPayMethods(payStageVos);
                    }
                }
                orderVo.setContracts(contractVos);
            }
        }
        return orderVos;
    }
}
