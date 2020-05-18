package net.chensee.platform.erp.action.finance.service.impl;

import net.chensee.base.action.user.business.UserBus;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.base.common.po.BaseInfoPo;
import net.chensee.base.utils.CreateAndUpdateInfoUtil;
import net.chensee.base.utils.ResFolderUtils;
import net.chensee.base.utils.UserUtil;
import net.chensee.platform.erp.action.finance.mapper.CollectDao;
import net.chensee.platform.erp.action.finance.po.CollectPo;
import net.chensee.platform.erp.action.finance.service.CollectService;
import net.chensee.platform.erp.action.finance.vo.CollectVo;
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
 * @date : 2019/5/27 17:31
 */
@Service
@Transactional
public class CollectServiceImpl implements CollectService {
    private static final Logger logger = LoggerFactory.getLogger(CollectPo.class);

    @Resource
    private CollectDao collectDao;
    @Resource
    private UserBus userBus;

    @Override
    public ObjectResponse addCollect(CollectPo collectPo) {
        try {
            Long userId = UserUtil.getCurrentUser().getId();
            if (userId != null && !userBus.isExistUserInSelectDept(userId, collectPo.getDeptId())) {
                return ObjectResponse.fail("当前用户不属于该部门");
            }
            collectPo.setStatus(BaseInfoPo.Status_Able);
            CreateAndUpdateInfoUtil.addCreateInfo(collectPo);
            CreateAndUpdateInfoUtil.addUpdateInfo(collectPo);
            collectDao.addCollect(collectPo);
            return ObjectResponse.ok(collectPo.getId());
        } catch (Exception e) {
            logger.error("添加收款出现异常", e);
            return ObjectResponse.fail("添加收款出现异常");
        }
    }

    @Override
    public BaseResponse updateCollect(CollectPo collectPo) {
        try {
            Long userId = UserUtil.getCurrentUser().getId();
            if (!userBus.isExistUserInSelectDept(userId, collectPo.getDeptId())) {
                return ObjectResponse.fail("当前用户不属于该部门");
            }
            CreateAndUpdateInfoUtil.addUpdateInfo(collectPo);
            collectDao.updateCollect(collectPo);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("修改收款出现异常", e);
            return BaseResponse.fail("修改收款出现异常");
        }
    }

    @Override
    public BaseResponse deleteCollect(Long id) {
        try {
            collectDao.deleteCollect(id);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("删除收款出现异常", e);
            return BaseResponse.fail("删除收款出现异常");
        }
    }

    @Override
    public ObjectResponse getAllCollect(Integer pageSize, Integer pageNumber) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            List<CollectVo> collectVos = collectDao.getAllCollect(pageSize * (pageNumber - 1), pageSize, currentFolder);
            for (CollectVo collectVo : collectVos) {
                collectVo.setAmount(DataHandlerUtil.narrowFactor(collectVo.getAmount()));
            }
            Integer count = collectDao.getCount(null, null, currentFolder);
            Map map = new HashMap();
            map.put("data", collectVos);
            map.put("total" ,count);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("获取所有收款信息发生异常",e);
            return ObjectResponse.fail("获取所有收款信息发生异常");
        }
    }

    @Override
    public ObjectResponse getByCustomerNameAndNumber(String customerName, String number, Integer pageSize, Integer pageNumber) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            List<CollectVo> collectVos = collectDao.getByCustomerNameAndNumber(customerName, number, pageSize * (pageNumber - 1), pageSize);
            for (CollectVo collectVo : collectVos) {
                collectVo.setAmount(DataHandlerUtil.narrowFactor(collectVo.getAmount()));
            }
            Integer count = collectDao.getCount(customerName, number,currentFolder);
            Map map = new HashMap();
            map.put("data", collectVos);
            map.put("total" ,count);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("根据客户名称和收款编号查询发生异常",e);
            return ObjectResponse.fail("根据客户名称和收款编号查询发生异常");
        }
    }

    @Override
    public ObjectResponse getByContractId(Long contractId) {
        try {
            List<CollectVo> collectVos = collectDao.getByContractId(contractId);
            for (CollectVo collectVo : collectVos) {
                collectVo.setAmount(DataHandlerUtil.narrowFactor(collectVo.getAmount()));
            }
            return ObjectResponse.ok(collectVos);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("根据合同查询收款信息发生异常",e);
            return ObjectResponse.fail("根据合同查询收款信息发生异常");
        }
    }

    @Override
    public ObjectResponse getCollectById(Long id) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            CollectVo collectVo = collectDao.getCollectById(id,currentFolder).get(0);
            collectVo.setAmount(DataHandlerUtil.narrowFactor(collectVo.getAmount()));

            return ObjectResponse.ok(collectVo);
        } catch (Exception e) {
            logger.error("根据ID查询收款信息发生异常",e);
            return ObjectResponse.fail("查询收款信息发生异常");
        }
    }
}
