package net.chensee.platform.erp.action.dispatch.service.impl;

import net.chensee.base.action.user.business.UserBus;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.base.common.po.BaseInfoPo;
import net.chensee.base.utils.CreateAndUpdateInfoUtil;
import net.chensee.base.utils.ResFolderUtils;
import net.chensee.base.utils.UserUtil;
import net.chensee.platform.erp.action.dispatch.mapper.RequestDispatchDao;
import net.chensee.platform.erp.action.dispatch.po.RequestDispatchPo;
import net.chensee.platform.erp.action.dispatch.service.RequestDispatchService;
import net.chensee.platform.erp.action.dispatch.vo.RequestDispatchVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author : shibo
 * @date : 2019/6/19 10:44
 */
@Service
@Transactional
public class RequestDispatchServiceImpl implements RequestDispatchService {
    private static final Logger logger = LoggerFactory.getLogger(RequestDispatchPo.class);

    @Resource
    private RequestDispatchDao requestDispatchDao;
    @Resource
    private UserBus userBus;

    @Override
    public ObjectResponse getAllRequestDispatch(Integer pageSize, Integer pageNumber) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            List<RequestDispatchVo> dispatchVos = requestDispatchDao.getAllRequestDispatch(pageSize * (pageNumber - 1), pageSize, currentFolder);
            for (RequestDispatchVo requestDispatchVo : dispatchVos) {
                if (requestDispatchVo.getFileIds() != null) {
                    requestDispatchVo.setFileIdList(Arrays.asList(requestDispatchVo.getFileIds().split(",")));
                }
            }
            Integer count = requestDispatchDao.getCount(null, null, currentFolder);
            Map map = new HashMap();
            map.put("data", dispatchVos);
            map.put("total" ,count);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("获取所有派工单发生异常",e);
            return ObjectResponse.fail("获取所有派工单发生异常");
        }
    }

    @Override
    public ObjectResponse addRequestDispatch(RequestDispatchPo requestDispatchPo) {
        try {
            Long userId = UserUtil.getCurrentUser().getId();
            if (!userBus.isExistUserInSelectDept(userId, requestDispatchPo.getDeptId())) {
                return ObjectResponse.fail("当前用户不属于该部门");
            }
            CreateAndUpdateInfoUtil.addCreateInfo(requestDispatchPo);
            CreateAndUpdateInfoUtil.addUpdateInfo(requestDispatchPo);
            requestDispatchPo.setStatus(BaseInfoPo.Status_Able);
            requestDispatchDao.addRequestDispatch(requestDispatchPo);
            return ObjectResponse.ok(requestDispatchPo.getId());
        } catch (Exception e) {
            logger.error("添加请求派工单出现异常", e);
            return ObjectResponse.fail("添加请求派工单出现异常");
        }
    }

    @Override
    public BaseResponse updateRequestDispatch(RequestDispatchPo requestDispatchPo) {
        try {
            Long userId = UserUtil.getCurrentUser().getId();
            if (!userBus.isExistUserInSelectDept(userId, requestDispatchPo.getDeptId())) {
                return ObjectResponse.fail("当前用户不属于该部门");
            }
            CreateAndUpdateInfoUtil.addUpdateInfo(requestDispatchPo);
            requestDispatchDao.updateRequestDispatch(requestDispatchPo);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("修改请求派工单出现异常", e);
            return BaseResponse.fail("修改请求派工单出现异常");
        }
    }

    @Override
    public BaseResponse deleteRequestDispatch(Long id) {
        try {
            requestDispatchDao.deleteRequestDispatch(id);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("删除请求派工单出现异常", e);
            return BaseResponse.fail("删除请求派工单出现异常");
        }
    }

    @Override
    public ObjectResponse getRequestDispatchBySupplier(String supplierName, String supplierNo, Integer pageSize, Integer pageNumber) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            List<RequestDispatchVo> dispatchVos = requestDispatchDao.getRequestDispatchBySupplier(supplierName, supplierNo, pageSize * (pageNumber - 1), pageSize);
            for (RequestDispatchVo requestDispatchVo : dispatchVos) {
                if (requestDispatchVo.getFileIds() != null) {
                    requestDispatchVo.setFileIdList(Arrays.asList(requestDispatchVo.getFileIds().split(",")));
                }
            }
            Integer count = requestDispatchDao.getCount(supplierName, supplierNo, currentFolder);
            Map map = new HashMap();
            map.put("data", dispatchVos);
            map.put("total" ,count);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("根据供应商名称和编号获取派工单发生异常",e);
            return ObjectResponse.fail("根据供应商名称和编号获取派工单发生异常");
        }
    }

    @Override
    public ObjectResponse getById(Long id) {
        try {
            return ObjectResponse.ok(requestDispatchDao.getById(id).get(0));
        } catch (Exception e) {
            logger.error("根据ID获取派工单发生异常",e);
            return ObjectResponse.fail("获取派工单发生异常");
        }
    }
}
