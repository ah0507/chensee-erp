package net.chensee.platform.erp.action.contract.service.impl;

import net.chensee.base.action.user.business.UserBus;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.base.common.po.BaseInfoPo;
import net.chensee.base.utils.CreateAndUpdateInfoUtil;
import net.chensee.base.utils.ResFolderUtils;
import net.chensee.base.utils.UserUtil;
import net.chensee.platform.erp.action.contract.mapper.ContractDao;
import net.chensee.platform.erp.action.contract.mapper.ContractPayStageDao;
import net.chensee.platform.erp.action.contract.mapper.ContractProductDao;
import net.chensee.platform.erp.action.contract.mapper.ContractTrackDao;
import net.chensee.platform.erp.action.contract.po.ContractPayStagePo;
import net.chensee.platform.erp.action.contract.po.ContractPo;
import net.chensee.platform.erp.action.contract.po.ContractProductPo;
import net.chensee.platform.erp.action.contract.po.ContractTrackPo;
import net.chensee.platform.erp.action.contract.service.ContractService;
import net.chensee.platform.erp.action.contract.vo.ContractPayStageVo;
import net.chensee.platform.erp.action.contract.vo.ContractProductVo;
import net.chensee.platform.erp.action.contract.vo.ContractTrackVo;
import net.chensee.platform.erp.action.contract.vo.ContractVo;
import net.chensee.platform.erp.action.order.mapper.OrderContractDao;
import net.chensee.platform.erp.action.order.mapper.OrderContractPayStageDao;
import net.chensee.platform.erp.action.order.mapper.OrderContractProductDao;
import net.chensee.platform.erp.action.order.mapper.OrderDao;
import net.chensee.platform.erp.action.order.po.OrderContractPayStagePo;
import net.chensee.platform.erp.action.order.po.OrderContractPo;
import net.chensee.platform.erp.action.order.po.OrderContractProductPo;
import net.chensee.platform.erp.action.order.po.OrderPo;
import net.chensee.platform.erp.action.order.vo.OrderContractPayStageVo;
import net.chensee.platform.erp.action.order.vo.OrderContractVo;
import net.chensee.platform.erp.utils.DataHandlerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

@Service
@Transactional
public class ContractServiceImpl implements ContractService {
    private static final Logger logger = LoggerFactory.getLogger(ContractPo.class);

    @Resource
    private OrderDao orderDao;
    @Resource
    private ContractDao contractDao;
    @Resource
    private ContractProductDao contractProductDao;
    @Resource
    private ContractPayStageDao contractPayStageDao;
    @Resource
    private ContractTrackDao contractTrackDao;
    @Resource
    private OrderContractDao orderContractDao;
    @Resource
    private OrderContractProductDao orderContractProductDao;
    @Resource
    private OrderContractPayStageDao orderContractPayStageDao;
    @Resource
    private UserBus userBus;

    @Override
    public ObjectResponse getAllContracts(Integer pageSize, Integer pageNumber, Integer direct) {
        try {
            Set<Long> currentFolders = ResFolderUtils.getCurrentFolder();
            for (Long folderId : currentFolders) {
                logger.debug("-----------当前可视文件夹"+folderId);
            }
            List<ContractVo> contractVos = contractDao.getAllContracts(pageSize * (pageNumber - 1), pageSize, direct, currentFolders);
            contractVos = this.getContractInfo(contractVos);
            Integer count = contractDao.getCount(null, null, direct, currentFolders);
            Map map = new HashMap();
            map.put("data", contractVos);
            map.put("total" ,count);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("获取全部合同发生异常",e);
            return ObjectResponse.fail("获取全部合同发生异常");
        }
    }

    @Override
    public ObjectResponse getByOtherPartyId(Long otherPartyId, Integer pageSize, Integer pageNumber, Integer direct) {
        try {
            List<ContractVo> contractVos = contractDao.getByOtherPartyId(otherPartyId, pageSize * (pageNumber - 1), pageSize, direct);
            contractVos = this.getContractInfo(contractVos);
            Integer count = contractDao.getCountByOtherPartyId(otherPartyId, direct);
            Map map = new HashMap();
            map.put("data", contractVos);
            map.put("total" ,count);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("获取对方所有合同发生异常",e);
            return ObjectResponse.fail("获取对方所有合同发生异常");
        }
    }

    @Override
    public ObjectResponse getByOrderNameAndContractNo(String orderName, String contractNo, Integer pageSize, Integer pageNumber, Integer direct) {
        try {
            Set<Long> currentFolders = ResFolderUtils.getCurrentFolder();
            List<ContractVo> contractVos = contractDao.getByOrderNameAndContractNo(orderName, contractNo, pageSize * (pageNumber - 1), pageSize, direct);
            contractVos = this.getContractInfo(contractVos);
            Integer count = contractDao.getCount(orderName, contractNo, direct, currentFolders);
            Map map = new HashMap();
            map.put("data", contractVos);
            map.put("total" ,count);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("根据合同名称或合同编号查询合同",e);
            return ObjectResponse.fail("根据合同名称或合同编号查询合同");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse addContract(Long orderContractId, Integer direct){
        try {
            //根据订单中合同实体类的ID获取合同信息和订单信息
            OrderPo orderPo = orderDao.getByContractId(orderContractId);
            // Long userId = UserUtil.getCurrentUser().getId();
            System.out.println("======"+UserUtil.getCurrentUser().getId());
            /*if (!userBus.isExistUserInSelectDept(userId, orderPo.getDeptId())) {
                return ObjectResponse.fail("当前用户不属于该部门");
            }*/

            OrderContractPo contract = orderContractDao.getById(orderContractId);
            if(contract != null && orderPo != null) {
                //添加合同实体信息
                ContractPo contractPo = new ContractPo();
                BeanUtils.copyProperties(contract, contractPo);
                contractPo.setDirect(direct);
                contractPo.setStatus(BaseInfoPo.Status_Able);
                contractPo.setName(orderPo.getName());
                contractPo.setNumber(orderPo.getNumber());
                contractPo.setAmount(orderPo.getAmount());
                contractPo.setDeptId(orderPo.getDeptId());
                CreateAndUpdateInfoUtil.addCreateInfo(contractPo);
                CreateAndUpdateInfoUtil.addUpdateInfo(contractPo);
                contractDao.addContract(contractPo);
                //根据订单信息中合同ID获取合同中的商品和付款信息
                List<OrderContractProductPo> products = orderContractProductDao.getPoByContractId(orderContractId);
                List<OrderContractPayStagePo> payStages = orderContractPayStageDao.getPoByContractId(orderContractId);
                ContractProductPo productPo = null;
                ContractPayStagePo payStagePo = null;
                int amount = 0;
                if(products != null && products.size() > 0) {
                    for (OrderContractProductPo product : products) {
                        productPo = new ContractProductPo();
                        BeanUtils.copyProperties(product, productPo);
                        productPo.setContractId(contractPo.getId());
                        productPo.setStatus(BaseInfoPo.Status_Able);
                        productPo.setFolderId(contractPo.getFolderId());
                        CreateAndUpdateInfoUtil.addCreateInfo(productPo);
                        CreateAndUpdateInfoUtil.addUpdateInfo(productPo);
                        contractProductDao.addContractProduct(productPo);
                    }
                }
                if(payStages != null && payStages.size() > 0) {
                    for (OrderContractPayStagePo payStage : payStages) {
                        payStagePo = new ContractPayStagePo();
                        BeanUtils.copyProperties(payStage, payStagePo);
                        payStagePo.setContractId(contractPo.getId());
                        payStagePo.setStatus(BaseInfoPo.Status_Able);
                        payStagePo.setFolderId(contractPo.getFolderId());
                        CreateAndUpdateInfoUtil.addCreateInfo(payStagePo);
                        CreateAndUpdateInfoUtil.addUpdateInfo(payStagePo);
                        if(payStage.getAmount() != null) {
                            amount += payStage.getAmount();
                        }

                        contractPayStageDao.addContractPayStage(payStagePo);
                    }
                }
                contractPo.setAmount(amount);
                CreateAndUpdateInfoUtil.addUpdateInfo(contractPo);
                contractDao.updateContract(contractPo);
            }
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("根据订单ID生成合同出现异常", e);
            return BaseResponse.fail("根据订单ID生成合同出现异常");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse updateContract(ContractVo contractVo) {
        try {
            Long userId = UserUtil.getCurrentUser().getId();
            if (!userBus.isExistUserInSelectDept(userId, contractVo.getDeptId())) {
                return ObjectResponse.fail("当前用户不属于该部门");
            }
            ContractPo contractPo = new ContractPo();
            BeanUtils.copyProperties(contractVo, contractPo);
            contractPo.setAmount(DataHandlerUtil.enlargeFactor(contractVo.getAmount()));
            CreateAndUpdateInfoUtil.addUpdateInfo(contractPo);
            contractDao.updateContract(contractPo);

            if (contractVo.getPayMethods() != null) {
                List<ContractPayStageVo> currentPayStages = contractVo.getPayMethods();
                List<ContractPayStageVo> originalPayStages = contractPayStageDao.getByContractId(contractVo.getId());
                //更新两个payStages同等数量的payStage
                updatePayStages(contractVo,currentPayStages,originalPayStages);
                //更新两个payStages不同数量中剩余其他的payStage
                updateOtherPayStages(contractVo,currentPayStages,originalPayStages);
            }

            if(contractVo.getSteps() != null) {
                List<Long> ids = new ArrayList<>();
                List<ContractTrackVo> tracks = contractTrackDao.getByContractId(contractVo.getId());
                boolean isExist = false;
                ContractTrackPo trackPo = null;
                int index = 1;
                for (ContractTrackVo step : contractVo.getSteps()) {
                    trackPo = new ContractTrackPo();
                    BeanUtils.copyProperties(step, trackPo);
                    trackPo.setIndex(index);
                    trackPo.setContractId(contractVo.getId());
                    index++;
                    isExist = this.isExist(step, tracks);
                    if(isExist) {
                        CreateAndUpdateInfoUtil.addUpdateInfo(trackPo);
                        contractTrackDao.updateContractTrack(trackPo);
                    } else {
                        trackPo.setStatus(BaseInfoPo.Status_Able);
                        trackPo.setFolderId(contractVo.getFolderId());
                        CreateAndUpdateInfoUtil.addCreateInfo(trackPo);
                        CreateAndUpdateInfoUtil.addUpdateInfo(trackPo);
                        contractTrackDao.addContractTrack(trackPo);
                    }
                    ids.add(trackPo.getId());
                }
                //将多余的阶段删除
                contractTrackDao.deleteRedundancy(contractVo.getId(), ids);
            }
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("修改合同出现异常", e);
            return BaseResponse.fail("修改合同出现异常");
        }
    }

    //更新两个payStages同等数量的payStage
    private void updatePayStages(ContractVo contractVo,
                                 List<ContractPayStageVo> currentPayStages,
                                 List<ContractPayStageVo> originalPayStages) {
        ContractPayStagePo payStagePo;
        List<ContractPayStageVo> loopContracts =
                (currentPayStages.size() >= originalPayStages.size() ? originalPayStages : currentPayStages);
        for (int i = 0; i<loopContracts.size() ; i++) {
            ContractPayStageVo ContractPayStageVo = currentPayStages.get(i);
            payStagePo = new ContractPayStagePo();
            BeanUtils.copyProperties(ContractPayStageVo, payStagePo);
            payStagePo.setId(ContractPayStageVo.getId());
            payStagePo.setRate(DataHandlerUtil.enlargeFactor(ContractPayStageVo.getRate()));
            payStagePo.setAmount(DataHandlerUtil.enlargeFactor(ContractPayStageVo.getAmount()));
            payStagePo.setOrderId(contractVo.getOrderId());
            payStagePo.setContractId(contractVo.getId());
            CreateAndUpdateInfoUtil.addUpdateInfo(payStagePo);
            contractPayStageDao.updateContractPayStage(payStagePo);
        }
    }

    //更新两个payStages不同数量中剩余其他的payStage
    private void updateOtherPayStages(ContractVo contractVo,
                                      List<ContractPayStageVo> currentPayStages,
                                      List<ContractPayStageVo> originalPayStages) {
        ContractPayStagePo payStagePo;
        if (currentPayStages.size() > originalPayStages.size()) {
            for (int i=originalPayStages.size() ; i<currentPayStages.size() ; i++) {
                ContractPayStageVo contractPayStageVo = currentPayStages.get(i);
                payStagePo = new ContractPayStagePo();
                BeanUtils.copyProperties(contractPayStageVo, payStagePo);
                payStagePo.setRate(DataHandlerUtil.enlargeFactor(contractPayStageVo.getRate()));
                payStagePo.setAmount(DataHandlerUtil.enlargeFactor(contractPayStageVo.getAmount()));
                payStagePo.setOrderId(contractVo.getOrderId());
                payStagePo.setContractId(contractVo.getId());
                payStagePo.setFolderId(contractVo.getFolderId());
                payStagePo.setStatus(BaseInfoPo.Status_Able);
                CreateAndUpdateInfoUtil.addCreateInfo(payStagePo);
                CreateAndUpdateInfoUtil.addUpdateInfo(payStagePo);
                contractPayStageDao.addContractPayStage(payStagePo);
            }
        }
        if (currentPayStages.size() < originalPayStages.size()) {
            for (int i=currentPayStages.size() ; i<originalPayStages.size() ;i++) {
                Long payStageId = originalPayStages.get(i).getId();
                contractPayStageDao.deleteContractPayStage(payStageId);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse deleteContract(Long id) throws Exception{
        try {
            contractDao.deleteContract(id);
            contractProductDao.deleteByContractId(id);
            contractPayStageDao.deleteByContractId(id);
            contractTrackDao.deleteByContractId(id);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("删除合同出现异常", e);
            return BaseResponse.fail("删除合同出现异常");
        }
    }

    @Override
    public ObjectResponse getById(Integer direct, Long contractId) {
        try {
            Set<Long> currentFolders = ResFolderUtils.getCurrentFolder();
            /*for (Long folderId : currentFolders) {
                logger.debug("-----------当前可视文件夹"+folderId);
            }*/
            //根据合同ID得到合同的基本信息
            List<ContractVo> contractVos = contractDao.getById(direct, contractId, currentFolders);
            ContractVo contractVo = null;
            if (contractVos != null && contractVos.size() != 0) {
                //根据合同ID查询合同中附带的商品和付款信息
                for (ContractVo vo : contractVos) {
                    vo.setAmount(DataHandlerUtil.narrowFactor(vo.getAmount()));
                }
                List<ContractProductVo> contractProductVos = contractProductDao.getByContractId(contractId);
                List<ContractPayStageVo> contractPayStageVos = contractPayStageDao.getByContractId(contractId);
                for (ContractPayStageVo contractPayStageVo : contractPayStageVos) {
                    contractPayStageVo.setAmount(DataHandlerUtil.narrowFactor(contractPayStageVo.getAmount()));
                    contractPayStageVo.setRate(DataHandlerUtil.narrowFactor(contractPayStageVo.getRate()));
                }
                List<ContractTrackVo> contractTrackVos = contractTrackDao.getByContractId(contractId);
                contractVo = contractVos.get(0);
                contractVo.setProducts(contractProductVos);
                contractVo.setPayMethods(contractPayStageVos);
                contractVo.setSteps(contractTrackVos);
                return ObjectResponse.ok(contractVo);
            }else {
                return ObjectResponse.ok("没有查询到该合同信息");
            }
        } catch (Exception e) {
            logger.error("查询合同发生异常",e);
            return ObjectResponse.fail("查询合同发生异常");
        }
    }

    private List<ContractVo> getContractInfo(List<ContractVo> contractVos) {
        List<ContractProductVo> productVos = null;
        List<ContractPayStageVo> payStageVos = null;
        List<ContractTrackVo> trackVos = null;
        if(contractVos != null && contractVos.size() > 0) {
            for (ContractVo contractVo : contractVos) {
                contractVo.setAmount(DataHandlerUtil.narrowFactor(contractVo.getAmount()));
                productVos = contractProductDao.getByContractId(contractVo.getId());
                payStageVos = contractPayStageDao.getByContractId(contractVo.getId());
                trackVos = contractTrackDao.getByContractId(contractVo.getId());
                for (ContractPayStageVo payStageVo : payStageVos) {
                    payStageVo.setRate(DataHandlerUtil.narrowFactor(payStageVo.getRate()));
                    payStageVo.setAmount(DataHandlerUtil.narrowFactor(payStageVo.getAmount()));
                }
                contractVo.setProducts(productVos);
                contractVo.setPayMethods(payStageVos);
                contractVo.setSteps(trackVos);
            }
        }
        return contractVos;
    }

    private boolean isExist(ContractTrackVo contractTrackVo, List<ContractTrackVo> contractTrackVos) {
        if(contractTrackVos != null && contractTrackVos.size() > 0) {
            boolean flag = false;
            for (ContractTrackVo trackVo : contractTrackVos) {
                if(contractTrackVo.getId() == trackVo.getId()) {
                    flag = true;
                    break;
                }
            }
            return flag;
        }
        return false;
    }
}
