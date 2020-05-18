package net.chensee.platform.erp.action.customer.service.impl;

import net.chensee.base.action.user.business.UserBus;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.base.common.po.BaseInfoPo;
import net.chensee.base.utils.CreateAndUpdateInfoUtil;
import net.chensee.base.utils.ResFolderUtils;
import net.chensee.base.utils.UserUtil;
import net.chensee.platform.erp.action.customer.mapper.CustomerActivityDao;
import net.chensee.platform.erp.action.customer.po.CustomerActivityPo;
import net.chensee.platform.erp.action.customer.service.CustomerActivityService;
import net.chensee.platform.erp.action.customer.vo.CustomerActivityVo;
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
 * @date : 2019/5/20 9:36
 */
@Service
@Transactional
public class CustomerActivityServiceImpl implements CustomerActivityService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerActivityPo.class);
    @Resource
    private CustomerActivityDao customerActivityDao;
    @Resource
    private UserBus userBus;

    @Override
    public ObjectResponse addCustomerActivity(CustomerActivityPo customerActivityPo) {
        try {
            Long userId = UserUtil.getCurrentUser().getId();
            if (!userBus.isExistUserInSelectDept(userId, customerActivityPo.getDeptId())) {
                return ObjectResponse.fail("当前用户不属于该部门");
            }
            customerActivityPo.setStatus(BaseInfoPo.Status_Able);
            CreateAndUpdateInfoUtil.addCreateInfo(customerActivityPo);
            CreateAndUpdateInfoUtil.addUpdateInfo(customerActivityPo);
            customerActivityDao.addCustomerActivity(customerActivityPo);
            return ObjectResponse.ok(customerActivityPo.getId());
        } catch (Exception e) {
            logger.error("添加客户拜访活动出现异常", e);
            return ObjectResponse.fail("添加客户拜访活动出现异常");
        }
    }

    @Override
    public BaseResponse updateCustomerActivity(CustomerActivityPo customerActivityPo) {
        try {
            Long userId = UserUtil.getCurrentUser().getId();
            if (!userBus.isExistUserInSelectDept(userId, customerActivityPo.getDeptId())) {
                return ObjectResponse.fail("当前用户不属于该部门");
            }
            CreateAndUpdateInfoUtil.addUpdateInfo(customerActivityPo);
            customerActivityDao.updateCustomerActivity(customerActivityPo);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("修改客户拜访活动出现异常", e);
            return BaseResponse.fail("修改客户拜访活动出现异常");
        }
    }

    @Override
    public BaseResponse deleteCustomerActivity(Long id) {
        try {
            customerActivityDao.deleteCustomerActivity(id);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("删除客户拜访活动出现异常", e);
            return BaseResponse.fail("删除客户拜访活动出现异常");
        }
    }

    @Override
    public ObjectResponse getAllCustomerActivity(Integer pageSize, Integer pageNumber) {
        try {
            Set<Long> currentFolders = ResFolderUtils.getCurrentFolder();
            List<CustomerActivityVo> customerActivityVos = customerActivityDao.getAllCustomerActivity(pageSize * (pageNumber -1), pageSize, currentFolders);
            Integer count = customerActivityDao.getCount(null, null, currentFolders);
            Map map = new HashMap();
            map.put("data", customerActivityVos);
            map.put("total" ,count);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("获取客户拜访活动出现异常", e);
            return ObjectResponse.fail("获取客户拜访活动出现异常");
        }
    }

    @Override
    public ObjectResponse getByThemeAndNo(String theme, String no, Integer pageSize, Integer pageNumber) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            List<CustomerActivityVo> customerActivityVos = customerActivityDao.getByThemeAndNo(theme, no, pageSize * (pageNumber -1), pageSize);
            Integer count = customerActivityDao.getCount(theme, no, currentFolder);
            Map map = new HashMap();
            map.put("data", customerActivityVos);
            map.put("total" ,count);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("查找客户拜访活动出现异常", e);
            return ObjectResponse.fail("查找客户拜访活动出现异常");
        }
    }

    @Override
    public BaseResponse getCustomerActivityById(Long id) {
        try {
            Set<Long> currentFolders = ResFolderUtils.getCurrentFolder();
            return ObjectResponse.ok(customerActivityDao.getCustomerActivityById(id,currentFolders).get(0));
        } catch (Exception e) {
            logger.error("根据ID获取客户拜访活动出现异常", e);
            return ObjectResponse.fail("获取客户拜访活动出现异常");
        }
    }
}
