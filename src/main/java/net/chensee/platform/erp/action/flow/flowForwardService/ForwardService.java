package net.chensee.platform.erp.action.flow.flowForwardService;

import net.chensee.base.action.role.vo.RoleVo;
import net.chensee.base.action.user.business.UserBus;
import net.chensee.base.action.userGroup.vo.UserGroupVo;
import net.chensee.base.utils.JsonUtil;
import net.chensee.platform.erp.action.flow.vo.MsgVo;
import net.chensee.platform.erp.utils.RestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wh
 * @version 1.0
 * @date 2020/3/17 11:14
 */
@Service
@Transactional
public class ForwardService {

    @Value("${flow.request.url}")
    private String urlPre;

    @Value("${logMsgUrl}")
    private String logMsgUrl;

    @Resource
    private UserBus userBus;

    @Value("${flow.tenant}")
    private String TENANT;

    private static final Logger logger = LoggerFactory.getLogger(ForwardService.class);

    @Autowired
    private RestTemplate restTemplate;

    public String startFlow(String flowId, String businessKey, Map params) {
        String url = urlPre + "/flow/start/"+flowId;
        return RestUtil.sendPostRequestWithMap(url,params);
    }

    public ResponseEntity<Object> getFormsByTaskId(String businessKey) {
        String formsDataUrl = urlPre + "/form/data/pre/form/business/" + businessKey;
        return restTemplate.getForEntity(formsDataUrl, Object.class);
    }

    public ResponseEntity<Object> getFormByDoneTaskId(String taskId) {
        String formDataUrl = urlPre + "/form/data/his/task/current/form/business/" + taskId;
        return restTemplate.getForEntity(formDataUrl, Object.class);
    }

    public String submitForm(Long formId, String businessKey, Long userId, String data) {
        String formDataurl = urlPre + "/form/data/" + formId + "/" + businessKey + "/id/" + userId;
        return RestUtil.sendPostRequestWithString(formDataurl, data);
    }

    public String completeCurNode(String businessKey, String data) {
        String nodeCompleteUrl = urlPre + "/flow/complete/" + businessKey;
        return RestUtil.sendPostRequestWithString(nodeCompleteUrl, data);
    }

    public ResponseEntity<Object> getAlreadyDoneTask(Long userId) {
        String hisTaskUrl = urlPre + "/flow/list/task/his/tenant/" + TENANT + "/done/" + userId;
        return restTemplate.getForEntity(hisTaskUrl, Object.class);
    }

    public String handleData(String businessKey, Long formId, String data) {
        String handleDataUrl =
                urlPre + "/form/list/flow/run/task/business/"+businessKey+"/data/"+formId;
        return RestUtil.sendPostRequestWithString(handleDataUrl, data);
    }

    public String getBusData(String businessKey, Long formId, String data) {
        String busDataUrl =
                urlPre + "/form/list/flow/run/task/business/"+businessKey+"/bus/data/"+formId;
        return RestUtil.sendPostRequestWithString(busDataUrl, data);
    }

    public ResponseEntity<Object> getToDoTask(Long userId) {
        String hisTaskUrl =
                urlPre + "/flow/list/task/run/tenant/" + TENANT + "/todo/" + userId +"?extraInfo={extraInfo}";
        //根据userId获取用户所在的role和group
        List<RoleVo> roleVos = userBus.getRolesByUserId(userId);
        List<UserGroupVo> userGroupVos = userBus.getUserGroupsByUserId(userId);
        //反复新建，解决？
        //TODO
        Map<String, List<Long>> extraInfo = new HashMap<>();
        List<Long> roleIdList = new ArrayList<>();
        List<Long> groupIdList = new ArrayList<>();

        for (RoleVo roleVo : roleVos) {
            roleIdList.add(roleVo.getId());
        }
        for (UserGroupVo userGroupVo : userGroupVos) {
            groupIdList.add(userGroupVo.getId());
        }
        extraInfo.put("role",roleIdList);
        extraInfo.put("userGroup",groupIdList);
        String extraInfoStr = JsonUtil.toStr(extraInfo);

        return restTemplate.getForEntity(hisTaskUrl, Object.class, extraInfoStr);
    }

    public void signTodoTask(Long userId, String taskId) {
        String signTaskUrl =
                urlPre + "/flow/list/task/run/tenant/"+TENANT+"/sign/"+userId+"?taskId={taskId}";
        restTemplate.put(signTaskUrl,String.class,taskId);
    }

    public String getRunTaskById(String taskId) {
        String getTaskUrl =
                urlPre + "/flow/list/task/run/businessKey/"+taskId;
        return restTemplate.getForObject(getTaskUrl, String.class, taskId);
    }

    /**
     * 发送消息接口
     * @param msgVo
     * @return
     */
    public String sendMsg(MsgVo msgVo) {
        String msgUrl = logMsgUrl + "/msg";
        String json = JsonUtil.toStr(msgVo);
        return RestUtil.sendPostRequestWithString(msgUrl, json);
    }

    /**
     * 根据rfId获取流程关联的所有表单
     * @param runFlowId
     * @param flowState
     * @return
     */
    public ResponseEntity<Object> getFormDataByRfId(String runFlowId, Integer flowState) {
        String formDataUrl = urlPre + "/form/list/flow/" + runFlowId + "/form/data?flowState=" + flowState;
        return restTemplate.getForEntity(formDataUrl, Object.class);
    }

    public ResponseEntity<Object> getAllFlows() {
        String flowsUrl = urlPre + "/flow/list/flow/" + TENANT;
        return restTemplate.getForEntity(flowsUrl, Object.class);
    }

    public String getBusKey(String taskId) {
        String getBusKeyUrl = urlPre + "/flow/list/task/run/businessKey/flow/" + taskId;
        return restTemplate.getForObject(getBusKeyUrl, String.class);
    }

    //TODO 整合
    public String submitAndCompleteNode(String businessKey, Long formId, String data) {
        return null;
    }


}