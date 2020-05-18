package net.chensee.platform.erp.action.flow.service.impl;

import net.chensee.base.action.role.mapper.RoleDao;
import net.chensee.base.action.user.vo.UserVo;
import net.chensee.base.action.userGroup.mapper.UserGroupDao;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.busFLowState.mapper.BusFlowDao;
import net.chensee.platform.erp.action.busFLowState.vo.BusFlowVo;
import net.chensee.platform.erp.action.business.mapper.BusDao;
import net.chensee.platform.erp.action.business.po.BusinessFlowLogPo;
import net.chensee.platform.erp.action.business.vo.BusinessVo;
import net.chensee.platform.erp.action.flow.flowForwardService.ForwardService;
import net.chensee.platform.erp.action.flow.mapper.BusColumnValueDao;
import net.chensee.platform.erp.action.flow.mapper.FlowDao;
import net.chensee.platform.erp.action.flow.po.FlowBatchBusPo;
import net.chensee.platform.erp.action.flow.po.FlowPo;
import net.chensee.platform.erp.action.flow.service.FlowService;
import net.chensee.platform.erp.action.flow.vo.FlowVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class FlowServiceImpl implements FlowService {

    private static final Logger logger = LoggerFactory.getLogger(FlowServiceImpl.class);

    @Autowired
    private FlowDao flowDao;
    @Autowired
    private BusDao busDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserGroupDao userGroupDao;
    @Autowired
    private ForwardService forwardService;
    @Autowired
    private BusFlowDao busFlowDao;
    @Autowired
    private BusColumnValueDao busColumnValueDao;

    @Override
    public ObjectResponse getFlowBusRelations() {
        try {
            List<FlowVo> flowVos = flowDao.getFlowBusRelations();
            return ObjectResponse.ok(flowVos);
        } catch (Exception e) {
            logger.error("获取业务流程关联信息异常", e);
            return ObjectResponse.fail("获取业务流程关联信息异常");
        }
    }

    @Override
    public ObjectResponse getFlowBusRelationById(Long id) {
        try {
            List<FlowVo> flowVos = flowDao.getFlowBusRelationById(id);
            return ObjectResponse.ok(flowVos);
        } catch (Exception e) {
            logger.error("根据ID获取业务流程关联信息异常", e);
            return ObjectResponse.fail("获取业务流程关联信息异常");
        }
    }

    @Override
    public BaseResponse addFlowBusRelation(FlowPo flowPo) {
        try {
            flowDao.addFlowBusRelation(flowPo);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("添加业务流程关联信息异常", e);
            return BaseResponse.fail("添加业务流程关联信息异常");
        }
    }

    @Override
    public BaseResponse deleteFlowBusRelation(Long id) {
        try {
            flowDao.deleteFlowBusRelation(id);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("删除业务流程关联信息异常", e);
            return BaseResponse.fail("删除业务流程关联信息异常");
        }
    }

    @Override
    public ObjectResponse getBusinessByFlowId(String flowId) {
        try {
            return ObjectResponse.ok(flowDao.getBusinessByFlowId(flowId));
        } catch (Exception e) {
            logger.error("根据流程ID获取关联的业务信息异常", e);
            return ObjectResponse.fail("获取关联的业务信息异常");
        }
    }

    @Override
    public BaseResponse addBatchFlowBusRelations(FlowBatchBusPo flowBatchBusPo) {
        try {
            String[] busIdArray = flowBatchBusPo.getBusIds().split(",");
            List<Long> busIdList = new ArrayList<>();
            for (String busIdString : busIdArray) {
                if (busIdString != "") {
                    Long busId = Long.parseLong(busIdString);
                    busIdList.add(busId);
                }
            }
            List<FlowVo> flowVos = flowDao.getFlowBusRelations();
            List<Long> list = new ArrayList<>();
            for (FlowVo flowVo : flowVos) {
                if (busIdList.contains(flowVo.getBusinessId())) {
                    list.add(flowVo.getBusinessId());
                }
            }
            if (list != null && list.size() != 0) {
                flowDao.deleteRelationByBusId(list);
            }
            flowDao.addBatchFlowBusRelations(flowBatchBusPo.getFlowId(),busIdList);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("批量添加流程业务关联信息异常", e);
            return BaseResponse.fail("批量添加流程业务关联信息异常");
        }
    }

    @Override
    public ObjectResponse launchFlow(String busCode,Long busId) {
        try {
            String state = busFlowDao.getFlowStateByBusCodeId(busCode, busId);
            if (state != null) {
                return ObjectResponse.fail("该业务审批已存在，请勿重复发起审批");
            }
            List<BusinessVo> business = busDao.getBusinessByCode(busCode);
            if (business.size() !=1 ) {
                return ObjectResponse.fail("该业务不存在或者不符合要求");
            }
            List<FlowVo> flowVos = flowDao.getFlowBusRelationByBusId(business.get(0).getId());
            if (flowVos.size() !=1 ) {
                return ObjectResponse.fail("该业务对应的流程不存在或者不符合要求");
            }
            String flowId = flowVos.get(0).getFlowId();
            String businessKey = busCode + "," + busId;
            Map<String, String> params = new HashMap<>();
            params.put("num","2");
            params.put("businessKey",businessKey);
            String result = forwardService.startFlow(flowId, businessKey, params);

            saveBusFlowLog(busCode, busId, flowId);

            return ObjectResponse.ok(result);
        } catch (Exception e) {
            logger.error("发起流程异常", e);
            return ObjectResponse.fail("发起流程异常");
        }
    }

    private void saveBusFlowLog(String busCode, Long busId, String flowId) {
        BusinessFlowLogPo businessFlowLogPo = new BusinessFlowLogPo();
        businessFlowLogPo.setBusCode(busCode);
        businessFlowLogPo.setBusId(busId);
        businessFlowLogPo.setFlowId(flowId);
        businessFlowLogPo.setCreateTime(new Date());
        busDao.insertBusFlowInfo(businessFlowLogPo);
    }

    @Override
    public ObjectResponse getFormsByTaskId(String businessKey) {
        try {
            ResponseEntity<Object> formDataEntity = forwardService.getFormsByTaskId(businessKey);
            return ObjectResponse.ok(formDataEntity.getBody());
        } catch (Exception e) {
            logger.error("根据TaskId查找当前及之前节点对应的所有表单发生异常", e);
            return ObjectResponse.fail("查找当前及之前节点对应的所有表单发生异常");
        }
    }

    @Override
    public ObjectResponse getFormByDoneTaskId(String taskId) {
        try {
            ResponseEntity<Object> formDataEntity = forwardService.getFormByDoneTaskId(taskId);
            return ObjectResponse.ok(formDataEntity.getBody());
        } catch (Exception e) {
            logger.error("通过已完成任务的taskId获取关联的表单发生异常", e);
            return ObjectResponse.fail("通过已完成任务的taskId获取关联的表单发生异常");
        }
    }

    @Override
    public BaseResponse submitFormData(Long formId, String taskId, Long userId, String data) {
        try {
            forwardService.submitForm(formId, taskId, userId, data);
            //根据form中的isFlow属性判断表单中的data是否需要代入流程
            String flowData = forwardService.handleData(taskId, formId, data);
            //获取表单中填入的字段和值
            String busData1 = forwardService.getBusData(taskId, formId, data);
            logger.debug("======bus变量======"+busData1);
            //把变量存入数据库中
            String busKey = forwardService.getBusKey(taskId);
            if (busKey == null || "".equals(busKey.trim())) {
                logger.error("taskID为"+taskId);
                return BaseResponse.fail("业务Code和业务ID为空");
            }
            busColumnValueDao.addBusColumnsAndValues(busKey.split(",")[0],
                    Long.valueOf(busKey.split(",")[1]),busData1);
            forwardService.completeCurNode(taskId, flowData);
            //TODO   整合
            //String busData = forwardService.submitAndCompleteNode(taskId, formId, data);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("完成当前节点并提交当前表单发生异常", e);
            return BaseResponse.fail("完成当前节点并提交当前表单发生异常");
        }
    }

    @Override
    public ObjectResponse getAlreadyDoneTask(Long userId) {
        try {
            ResponseEntity<Object> entity = forwardService.getAlreadyDoneTask(userId);
            return ObjectResponse.ok(entity.getBody());
        } catch (Exception e) {
            logger.error("获取当前用户已办任务发生异常", e);
            return ObjectResponse.fail("获取当前用户已办任务发生异常");
        }
    }

    @Override
    public ObjectResponse getToDoTask(Long userId) {
        try {
            ResponseEntity<Object> entity = forwardService.getToDoTask(userId);
            return ObjectResponse.ok(entity.getBody());
        } catch (Exception e) {
            logger.error("获取当前用户待办任务发生异常", e);
            return ObjectResponse.fail("获取当前用户待办任务发生异常");
        }
    }

    @Override
    public BaseResponse signTodoTask(Long userId, String taskId) {
        try {
            boolean flag = false;
            //获取taskId的任务的owner
            String owner = forwardService.getRunTaskById(taskId);
            //检查当前用户的签收权限  是否在owner的范围内
            String[] ownerArr = owner.split(":");
            if (ownerArr.length == 1) {
                //转换为Long或者不符合owner设置标准
                if (Long.valueOf(owner).equals(userId)) {
                    flag = true;
                }else {
                    logger.error("无法签收该任务");
                }
            }else if (ownerArr.length == 2) {
                if (ownerArr[0].equals("role")) {
                    flag = ifCanSign(userId, Long.valueOf(ownerArr[1]), null);
                }else if (ownerArr[0].equals("userGroup")) {
                    flag = ifCanSign(userId, null, Long.valueOf(ownerArr[1]));
                }else if (ownerArr[0].equals("user")) {
                    flag = Long.valueOf(ownerArr[1]).equals(userId);
                }else {
                    logger.error("owner信息有误："+owner);
                }
            }
            if (flag) {
                forwardService.signTodoTask(userId, taskId);
            }else {
                return BaseResponse.fail("签收失败");
            }
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("签收待完成任务异常", e);
            return BaseResponse.fail("签收待完成任务异常");
        }
    }

    @Override
    public BaseResponse saveOrUpdateBusFlowState(String runFlowId, String businessKey) {
        try {
            String[] strings = businessKey.split(",");
            String busCode = strings[0];
            String busId = strings[1];
            boolean flowState;
            String state = busDao.getFlowStateByBusCodeId(busCode, Long.valueOf(busId));
            if (state == null ) {
                flowState = true;
                busDao.saveOrUpdateBusFlowState(busCode,Long.valueOf(busId),runFlowId,flowState);
            }else if (Integer.valueOf(state)== 1) {
                flowState = false;
                busDao.saveOrUpdateBusFlowState(busCode,Long.valueOf(busId),runFlowId,flowState);
            }
            return ObjectResponse.ok();
        } catch (Exception e) {
            logger.error("保存或更新业务发起的流程状态发生异常", e);
            return ObjectResponse.fail("保存或更新业务发起的流程状态发生异常");
        }
    }

    @Override
    public ObjectResponse getBusFormData(String busCode, String busId) {
        try {
            BusFlowVo busFlowVo = busFlowDao.getFlow(busCode, Long.parseLong(busId));
            Object body = forwardService.getFormDataByRfId(busFlowVo.getRunFlowId(), busFlowVo.getFlowState()).getBody();
            return ObjectResponse.ok(body);
        } catch (Exception e) {
            logger.error("查询具体业务对应的流程发生异常", e);
            return ObjectResponse.fail("查询具体业务对应的流程发生异常");
        }
    }

    @Override
    public ObjectResponse getAllFlows() {
        try {
            ResponseEntity<Object> allFlows = forwardService.getAllFlows();
            return ObjectResponse.ok(allFlows.getBody());
        } catch (Exception e) {
            logger.error("查询所有流程发生异常", e);
            return ObjectResponse.fail("查询所有流程发生异常");
        }
    }

    private boolean ifCanSign(Long userId, Long roleId, Long userGroupId) {
        if (roleId != null && userGroupId == null) {
            List<UserVo> userByRoleId = roleDao.getUserByRoleId(roleId);
            for (UserVo userVo : userByRoleId) {
                if (userVo.getId() == userId) {
                    return true;
                }
            }
        }
        if (userGroupId != null && roleId == null) {
            List<UserVo> userByUserGroupId = userGroupDao.getUserByUserGroupId(userGroupId);
            for (UserVo userVo : userByUserGroupId) {
                if (userVo.getId() == userId) {
                    return true;
                }
            }
        }
        return false;
    }
}
