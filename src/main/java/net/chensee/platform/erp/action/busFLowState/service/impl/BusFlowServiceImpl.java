package net.chensee.platform.erp.action.busFLowState.service.impl;

import net.chensee.base.action.role.mapper.RoleDao;
import net.chensee.base.action.user.vo.UserVo;
import net.chensee.base.action.userGroup.mapper.UserGroupDao;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.busFLowState.mapper.BusFlowDao;
import net.chensee.platform.erp.action.busFLowState.service.BusFlowService;
import net.chensee.platform.erp.action.busFLowState.vo.BusFlowVo;
import net.chensee.platform.erp.action.business.mapper.BusDao;
import net.chensee.platform.erp.action.business.po.BusinessFlowLogPo;
import net.chensee.platform.erp.action.business.vo.BusinessVo;
import net.chensee.platform.erp.action.flow.flowForwardService.ForwardService;
import net.chensee.platform.erp.action.flow.mapper.FlowDao;
import net.chensee.platform.erp.action.flow.po.FlowBatchBusPo;
import net.chensee.platform.erp.action.flow.po.FlowPo;
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
public class BusFlowServiceImpl implements BusFlowService {

    private static final Logger logger = LoggerFactory.getLogger(BusFlowServiceImpl.class);

    @Autowired
    private BusFlowDao busFlowDao;
    @Autowired
    private ForwardService forwardService;

    @Override
    public BaseResponse saveOrUpdateBusFlowState(String runFlowId, String businessKey, String state) {
        try {
            String[] strings = businessKey.split(",");
            String busCode = strings[0];
            String busId = strings[1];
            int flowState;
            String itemState = busFlowDao.getFlowStateByBusCodeId(busCode, Long.valueOf(busId));
            if ((itemState == null || "0".equals(itemState)) && "created".equals(state)) {
                flowState = 1;
                busFlowDao.saveOrUpdateBusFlowState(busCode,Long.valueOf(busId),runFlowId,flowState);
            }else if ("1".equals(itemState) && "post".equals(state)) {
                flowState = 2;
                busFlowDao.saveOrUpdateBusFlowState(busCode,Long.valueOf(busId),runFlowId,flowState);
            }
            return ObjectResponse.ok();
        } catch (Exception e) {
            logger.error("保存或更新业务发起的流程状态发生异常", e);
            return ObjectResponse.fail("保存或更新业务发起的流程状态发生异常");
        }
    }

    @Override
    public ObjectResponse getBusFlowState(String busCode, String busIds) {
        try {
            List<String> busIdList = Arrays.asList(busIds.split(","));
            List<BusFlowVo> busFlowVoList = busFlowDao.getFlowStates(busCode, busIdList);
            Map<String, String> map = new HashMap<>();
            for (BusFlowVo busFlowVo : busFlowVoList) {
                map.put(String.valueOf(busFlowVo.getBusId()),String.valueOf(busFlowVo.getFlowState()));
            }
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("查询具体业务对应的流程状态发生异常", e);
            return ObjectResponse.fail("查询具体业务对应的流程状态发生异常");
        }
    }



}
