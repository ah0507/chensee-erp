package net.chensee.platform.erp.action.finance.service.impl;

import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.base.utils.ResFolderUtils;
import net.chensee.platform.erp.action.finance.mapper.FinanceOfCompanyDao;
import net.chensee.platform.erp.action.finance.po.BillPo;
import net.chensee.platform.erp.action.finance.po.FinanceOfCompanyPo;
import net.chensee.platform.erp.action.finance.service.FinanceOfCompanyService;
import net.chensee.platform.erp.action.finance.vo.BillProductVo;
import net.chensee.platform.erp.action.finance.vo.BillVo;
import net.chensee.platform.erp.action.finance.vo.FinanceOfCompanyVo;
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

@Service
@Transactional
public class FinanceOfCompanyServiceImpl implements FinanceOfCompanyService {
    private static final Logger logger = LoggerFactory.getLogger(FinanceOfCompanyServiceImpl.class);

    @Resource
    private FinanceOfCompanyDao financeOfCompanyDao;

    @Override
    public ObjectResponse getAllRecords(Integer pageSize, Integer pageNumber) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            List<FinanceOfCompanyVo> financeOfCompanyVos =
                    financeOfCompanyDao.getAllRecords(pageSize * (pageNumber - 1), pageSize, currentFolder);
            Integer count = financeOfCompanyDao.getCount(null, currentFolder);
            Map map = new HashMap();
            map.put("data", financeOfCompanyVos);
            map.put("total" ,count);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("获取所有本公司财务信息记录发生异常",e);
            return ObjectResponse.fail("获取所有本公司财务信息记录发生异常");
        }
    }

    @Override
    public ObjectResponse getByCondition(String userName, Integer pageSize, Integer pageNumber) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            List<FinanceOfCompanyVo> financeOfCompanyVos =
                    financeOfCompanyDao.getByCondition(userName, pageSize * (pageNumber - 1), pageSize, currentFolder);
            Integer count = financeOfCompanyDao.getCount(userName, currentFolder);
            Map map = new HashMap();
            map.put("data", financeOfCompanyVos);
            map.put("total" ,count);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("根据条件获取本公司财务信息记录发生异常",e);
            return ObjectResponse.fail("根据条件获取本公司财务信息记录发生异常");
        }
    }

    @Override
    public BaseResponse addRecord(FinanceOfCompanyPo financeOfCompanyPo) {
        try {
            financeOfCompanyDao.addRecord(financeOfCompanyPo);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("新增本公司财务信息记录发生异常",e);
            return BaseResponse.fail("新增本公司财务信息记录发生异常");
        }
    }

    @Override
    public BaseResponse updateRecord(FinanceOfCompanyPo financeOfCompanyPo) {
        try {
            financeOfCompanyDao.updateRecord(financeOfCompanyPo);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("更新本公司财务信息记录发生异常",e);
            return BaseResponse.fail("更新本公司财务信息记录发生异常");
        }
    }

    @Override
    public BaseResponse deleteRecord(Long id) {
        try {
            financeOfCompanyDao.deleteRecord(id);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("根据ID删除本公司财务信息记录发生异常",e);
            return BaseResponse.fail("删除本公司财务信息记录发生异常");
        }
    }


}
