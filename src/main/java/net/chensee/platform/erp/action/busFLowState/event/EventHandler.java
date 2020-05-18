package net.chensee.platform.erp.action.busFLowState.event;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wh
 * @version 1.0
 * @date 2020/3/26 15:35
 */
public interface EventHandler {

    String START = "START";
    String END = "END";

    /**
     * 开票信息
     */
    String RECEIVE_BILL = "receiveBill";
    /**
     * 收票信息
     */
    String PAY_BILL = "payBill";
    /**
     * 收款记录
     */
    String RECEIVE = "receive";
    /**
     * 付款记录
     */
    String PAY = "pay";

    default void handler(HttpServletRequest request) {
        if (canHandler(request)) {
            doHandler(request);
        }
    }

    void doHandler(HttpServletRequest request);

    default boolean canHandler(HttpServletRequest request) {
        return true;
    }

    default String getTaskType(HttpServletRequest request) {
        return request.getParameter("taskType");
    }

    default String getBusCode(HttpServletRequest request) {
        return request.getParameter("businessKey").split(",")[0];
    }

    default String getBusId(HttpServletRequest request) {
        return request.getParameter("businessKey").split(",")[1];
    }

    default String getBusKey(HttpServletRequest request) {
        return request.getParameter("businessKey");
    }

    default String getRunFlowId(HttpServletRequest request) {
        return request.getParameter("runFlowId");
    }

    default String getRunTaskId(HttpServletRequest request) {
        return request.getParameter("runTaskId");
    }

    default String getTaskKey(HttpServletRequest request) {
        return request.getParameter("taskKey");
    }

    default String getState(HttpServletRequest request) {
        return request.getParameter("state");
    }
}
