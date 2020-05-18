package net.chensee.platform.erp.action.dispatch.service.impl;

import net.chensee.base.action.user.business.UserBus;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.base.common.po.BaseInfoPo;
import net.chensee.base.utils.CreateAndUpdateInfoUtil;
import net.chensee.base.utils.ResFolderUtils;
import net.chensee.base.utils.UserUtil;
import net.chensee.platform.erp.action.dispatch.mapper.DispatchDao;
import net.chensee.platform.erp.action.dispatch.po.DispatchSheetPo;
import net.chensee.platform.erp.action.dispatch.service.DispatchService;
import net.chensee.platform.erp.action.dispatch.vo.DispatchSheetVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author : shibo
 * @date : 2019/5/17 16:04
 */
@Service
@Transactional
public class DispatchServiceImpl implements DispatchService {
    private static final Logger logger = LoggerFactory.getLogger(DispatchSheetPo.class);
    @Resource
    private DispatchDao dispatchDao;
    @Autowired
    private RestTemplate restTemplate;
    @Resource
    private UserBus userBus;

    @Override
    public ObjectResponse getAllDispatchSheet(Integer pageSize, Integer pageNumber) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            List<DispatchSheetVo> dispatchSheetVos = dispatchDao.getAllDispatchSheet(pageSize * (pageNumber - 1), pageSize, currentFolder);
            for (DispatchSheetVo dispatchSheetVo : dispatchSheetVos) {
                if (dispatchSheetVo.getFileIds() != null) {
                    dispatchSheetVo.setFileIdList(Arrays.asList(dispatchSheetVo.getFileIds().split(",")));
                }
            }
            Integer count = dispatchDao.getCount(null, null, currentFolder);
            Map map = new HashMap();
            map.put("data", dispatchSheetVos);
            map.put("total" ,count);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("获取派工单列表出现异常", e);
            return ObjectResponse.fail("获取派工单列表出现异常");
        }
    }

    @Override
    public ObjectResponse addDispatchSheet(DispatchSheetPo dispatchSheetPo) {
        try {
            Long userId = UserUtil.getCurrentUser().getId();
            if (!userBus.isExistUserInSelectDept(userId, dispatchSheetPo.getDeptId())) {
                return ObjectResponse.fail("当前用户不属于该部门");
            }
            CreateAndUpdateInfoUtil.addCreateInfo(dispatchSheetPo);
            CreateAndUpdateInfoUtil.addUpdateInfo(dispatchSheetPo);
            dispatchSheetPo.setStatus(BaseInfoPo.Status_Able);
            dispatchDao.addDispatchSheet(dispatchSheetPo);
            return ObjectResponse.ok(dispatchSheetPo.getId());
        } catch (Exception e) {
            logger.error("添加派工单出现异常", e);
            return ObjectResponse.fail("添加派工单出现异常");
        }
    }

    @Override
    public BaseResponse updateDispatchSheet(DispatchSheetPo dispatchSheetPo) {
        try {
            Long userId = UserUtil.getCurrentUser().getId();
            if (!userBus.isExistUserInSelectDept(userId, dispatchSheetPo.getDeptId())) {
                return ObjectResponse.fail("当前用户不属于该部门");
            }
            CreateAndUpdateInfoUtil.addUpdateInfo(dispatchSheetPo);
            dispatchDao.updateDispatchSheet(dispatchSheetPo);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("修改派工单出现异常", e);
            return BaseResponse.fail("修改派工单出现异常");
        }
    }

    @Override
    public BaseResponse deleteDispatchSheet(Long id) {
        try {
            dispatchDao.deleteDispatchSheet(id);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("删除派工单出现异常", e);
            return BaseResponse.fail("删除派工单出现异常");
        }
    }

    @Override
    public ObjectResponse getDispatchSheetByCustomers(String customerName, String customerNo, Integer pageSize, Integer pageNumber) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            List<DispatchSheetVo> dispatchSheetVos = dispatchDao.getDispatchSheetByCustomers(customerName, customerNo, pageSize * (pageNumber - 1), pageSize);
            for (DispatchSheetVo dispatchSheetVo : dispatchSheetVos) {
                if (dispatchSheetVo.getFileIds() != null) {
                    dispatchSheetVo.setFileIdList(Arrays.asList(dispatchSheetVo.getFileIds().split(",")));
                }
            }
            Integer count = dispatchDao.getCount(customerName, customerNo, currentFolder);
            Map map = new HashMap();
            map.put("data", dispatchSheetVos);
            map.put("total" ,count);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("获取派工单出现异常", e);
            return ObjectResponse.fail("获取派工单出现异常");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResponse submitFlow(DispatchSheetPo dispatchSheetPo) throws Exception{
        try {
            if(dispatchSheetPo.getId() != null) {
                CreateAndUpdateInfoUtil.addUpdateInfo(dispatchSheetPo);
                dispatchDao.updateDispatchSheet(dispatchSheetPo);
            } else {
                CreateAndUpdateInfoUtil.addCreateInfo(dispatchSheetPo);
                CreateAndUpdateInfoUtil.addUpdateInfo(dispatchSheetPo);
                dispatchSheetPo.setStatus(BaseInfoPo.Status_Able);
                dispatchDao.addDispatchSheet(dispatchSheetPo);
            }
            String url = "http://192.168.2.205:8920/flow/2e5050f2-4d69-4027-8714-cb701a68e054/start";
            Map<String, String> map = new HashMap<>();
            map.put("vals", null);
            map.put("formVals", null);
            ObjectResponse objectResponse = restTemplate.postForObject(url, null, ObjectResponse.class, map);
            if(objectResponse.isOk()) {
                //将运行流程和派工单ID绑定
                return ObjectResponse.ok(dispatchSheetPo.getId());
            }
            return ObjectResponse.ok("流程系统异常",objectResponse.getMsg());
        } catch (Exception e) {
            logger.error("提交流程审批出现异常", e);
            return ObjectResponse.fail("提交流程审批出现异常");
        }
    }

    @Override
    public ObjectResponse getById(Long id) {
        try {
            return ObjectResponse.ok(dispatchDao.getById(id).get(0));
        } catch (Exception e) {
            logger.error("根据ID获取派工单发生异常",e);
            return ObjectResponse.fail("获取派工单发生异常");
        }
    }
}
