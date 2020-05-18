package net.chensee.platform.erp.action.finance.service.impl;

import net.chensee.base.action.user.business.UserBus;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.base.common.po.BaseInfoPo;
import net.chensee.base.utils.CreateAndUpdateInfoUtil;
import net.chensee.base.utils.ResFolderUtils;
import net.chensee.base.utils.UserUtil;
import net.chensee.platform.erp.action.finance.mapper.PaymentDao;
import net.chensee.platform.erp.action.finance.po.PaymentPo;
import net.chensee.platform.erp.action.finance.service.PaymentService;
import net.chensee.platform.erp.action.finance.vo.PaymentVo;
import net.chensee.platform.erp.utils.DataHandlerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author : shibo
 * @date : 2019/6/19 11:42
 */
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentPo.class);

    @Resource
    private PaymentDao paymentDao;
    @Resource
    private UserBus userBus;

    @Override
    public ObjectResponse addPayment(PaymentPo paymentPo) {
        try {
            Long userId = UserUtil.getCurrentUser().getId();
            if (!userBus.isExistUserInSelectDept(userId, paymentPo.getDeptId())) {
                return ObjectResponse.fail("当前用户不属于该部门");
            }
            CreateAndUpdateInfoUtil.addCreateInfo(paymentPo);
            CreateAndUpdateInfoUtil.addUpdateInfo(paymentPo);
            paymentPo.setStatus(BaseInfoPo.Status_Able);
            paymentDao.addPayment(paymentPo);
            return ObjectResponse.ok(paymentPo.getId());
        } catch (Exception e) {
            logger.error("添加付款出现异常", e);
            return ObjectResponse.fail("添加付款出现异常");
        }
    }

    @Override
    public BaseResponse updatePayment(PaymentPo paymentPo) {
        try {
            Long userId = UserUtil.getCurrentUser().getId();
            if (!userBus.isExistUserInSelectDept(userId, paymentPo.getDeptId())) {
                return ObjectResponse.fail("当前用户不属于该部门");
            }
            CreateAndUpdateInfoUtil.addUpdateInfo(paymentPo);
            paymentDao.updatePayment(paymentPo);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("修改付款出现异常", e);
            return BaseResponse.fail("修改付款出现异常");
        }
    }

    @Override
    public BaseResponse deletePayment(Long id) {
        try {
            paymentDao.deletePayment(id);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("删除付款出现异常", e);
            return BaseResponse.fail("删除付款出现异常");
        }
    }

    @Override
    public ObjectResponse getAllPayment(Integer pageSize, Integer pageNumber) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            List<PaymentVo> paymentVos = paymentDao.getAllPayment(pageSize * (pageNumber - 1), pageSize, currentFolder);
            for (PaymentVo paymentVo : paymentVos) {
                paymentVo.setAmount(DataHandlerUtil.narrowFactor(paymentVo.getAmount()));
            }
            Integer count = paymentDao.getCount(null, null, currentFolder);
            Map map = new HashMap();
            map.put("data", paymentVos);
            map.put("total" ,count);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("获取所有付款信息发生异常",e);
            return ObjectResponse.fail("获取所有付款信息发生异常");
        }
    }

    @Override
    public ObjectResponse getBySupplierNameAndNumber(String supplierName, String number, Integer pageSize, Integer pageNumber) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            List<PaymentVo> paymentVos = paymentDao.getBySupplierNameAndNumber(supplierName, number, pageSize * (pageNumber - 1), pageSize);
            for (PaymentVo paymentVo : paymentVos) {
                paymentVo.setAmount(DataHandlerUtil.narrowFactor(paymentVo.getAmount()));
            }
            Integer count = paymentDao.getCount(supplierName, number, currentFolder);
            Map map = new HashMap();
            map.put("data", paymentVos);
            map.put("total" ,count);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("根据供应商名称和付款编号查询发生异常",e);
            return ObjectResponse.fail("根据供应商名称和付款编号查询发生异常");
        }
    }

    @Override
    public ObjectResponse getByContractId(Long contractId) {
        try {
            List<PaymentVo> paymentVos = paymentDao.getByContractId(contractId);
            for (PaymentVo paymentVo : paymentVos) {
                paymentVo.setAmount(DataHandlerUtil.narrowFactor(paymentVo.getAmount()));
            }
            return ObjectResponse.ok(paymentVos);
        } catch (Exception e) {
            logger.error("根据合同查询付款信息发生异常",e);
            return ObjectResponse.fail("根据合同查询付款信息发生异常");
        }
    }

    @Override
    public ObjectResponse getPaymentById(Long id) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            PaymentVo paymentVo = paymentDao.getPaymentById(id,currentFolder).get(0);
            paymentVo.setAmount(DataHandlerUtil.narrowFactor(paymentVo.getAmount()));

            return ObjectResponse.ok(paymentVo);
        } catch (Exception e) {
            logger.error("根据合同查询付款信息发生异常",e);
            return ObjectResponse.fail("根据合同查询付款信息发生异常");
        }
    }
}
