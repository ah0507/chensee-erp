package net.chensee.platform.erp.action.customer.service.impl;

import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.base.common.po.BaseInfoPo;
import net.chensee.base.utils.CreateAndUpdateInfoUtil;
import net.chensee.platform.erp.action.customer.mapper.FinancialInfoDao;
import net.chensee.platform.erp.action.customer.po.FinancialInfoPo;
import net.chensee.platform.erp.action.customer.service.FinancialInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author : shibo
 * @date : 2019/5/20 15:55
 */
@Service
@Transactional
public class FinancialInfoServiceImpl implements FinancialInfoService {
    private static final Logger logger = LoggerFactory.getLogger(FinancialInfoPo.class);

    @Resource
    private FinancialInfoDao financialInfoDao;

    @Override
    public ObjectResponse getById(Long id) {
        return ObjectResponse.ok(financialInfoDao.getById(id));
    }

    @Override
    public BaseResponse addFinancialInfo(FinancialInfoPo financialInfoPo) {
        try {
            financialInfoPo.setStatus(BaseInfoPo.Status_Able);
            CreateAndUpdateInfoUtil.addCreateInfo(financialInfoPo);
            CreateAndUpdateInfoUtil.addUpdateInfo(financialInfoPo);
            financialInfoDao.addFinancialInfo(financialInfoPo);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("添加客户财务信息出现异常", e);
            return BaseResponse.fail("添加客户财务信息出现异常");
        }
    }

    @Override
    public BaseResponse updateFinancialInfo(FinancialInfoPo financialInfoPo) {
        try {
            CreateAndUpdateInfoUtil.addUpdateInfo(financialInfoPo);
            financialInfoDao.updateFinancialInfo(financialInfoPo);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("修改客户财务信息出现异常", e);
            return BaseResponse.fail("修改客户财务信息出现异常");
        }
    }

    @Override
    public BaseResponse deleteFinancialInfo(Long id) {
        try {
            financialInfoDao.deleteFinancialInfo(id);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("删除客户财务信息出现异常", e);
            return BaseResponse.fail("删除客户财务信息出现异常");
        }
    }
}
