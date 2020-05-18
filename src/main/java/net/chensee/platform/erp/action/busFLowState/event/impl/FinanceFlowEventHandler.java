package net.chensee.platform.erp.action.busFLowState.event.impl;

import lombok.extern.slf4j.Slf4j;
import net.chensee.platform.erp.action.busFLowState.event.EventHandler;
import net.chensee.platform.erp.action.finance.po.CollectPo;
import net.chensee.platform.erp.action.finance.po.PaymentPo;
import net.chensee.platform.erp.action.finance.service.BillService;
import net.chensee.platform.erp.action.finance.service.CollectService;
import net.chensee.platform.erp.action.finance.service.PaymentService;
import net.chensee.platform.erp.action.finance.vo.BillVo;
import net.chensee.platform.erp.action.finance.vo.CollectVo;
import net.chensee.platform.erp.action.finance.vo.PaymentVo;
import net.chensee.platform.erp.utils.DataHandlerUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ah
 * @title: FinancePayFlowEventHandler
 * @date 2020/3/31 10:03
 */
@Slf4j
@Component
public class FinanceFlowEventHandler implements EventHandler {

    @Autowired
    private BillService billService;
    @Autowired
    private CollectService collectService;
    @Autowired
    private PaymentService paymentService;

    @Override
    public void doHandler(HttpServletRequest request) {
        String busId = getBusId(request);
        String busCode = getBusCode(request);
        switch (busCode) {
            case RECEIVE_BILL:
                //先开票后收款
                handlePayBillReceive(busId);
                break;
            case PAY:
                //先付款后收票
                handlePayReceiveBill(busId);
                break;
            case PAY_BILL:
                //先收票后付款
                handleReceiveBillPay(busId);
                break;
            case RECEIVE:
                //先收款后开票
                handleReceivePayBill(busId);
                break;
            default:
                break;
        }
    }

    private void handlePayBillReceive(String busId) {
        BillVo billVo = (BillVo) billService.getReceiveById(Long.valueOf(busId)).getData();
        if (billVo.getIsApprove() == 0) {
            return;
        }
        CollectPo collectPo = new CollectPo();
        BeanUtils.copyProperties(billVo, collectPo);
        collectPo.setCustomerId(billVo.getFirstPartyId());
        collectPo.setBillId(billVo.getId());
        collectPo.setCollectTime(billVo.getBillTime());
        collectPo.setAmount(DataHandlerUtil.enlargeFactor(billVo.getTotalAmount()));
        try {
            collectService.addCollect(collectPo);
            log.debug("=======开票信息" + busId + "发起审批成功=====");
        } catch (Exception e) {
            log.error("开票信息" + busId + "发起审批异常!", e);
        }
    }

    private void handleReceivePayBill(String busId) {
        CollectVo collectVo = (CollectVo) collectService.getCollectById(Long.valueOf(busId)).getData();
        if (collectVo.getIsApprove() == 0) {
            return;
        }
        BillVo billVo = new BillVo();
        BeanUtils.copyProperties(collectVo, billVo);
        billVo.setFirstPartyId(collectVo.getCustomerId());
        billVo.setId(collectVo.getBillId());
        billVo.setBillTime(collectVo.getCollectTime());
        billVo.setTotalAmount(collectVo.getAmount());
        try {
            billService.addBill(billVo);
            log.debug("=======收款记录" + busId + "发起审批成功=====");
        } catch (Exception e) {
            log.error("收款记录" + busId + "发起审批异常!", e);
        }
    }

    private void handleReceiveBillPay(String busId) {
        BillVo billVo = (BillVo) billService.getPayById(Long.valueOf(busId)).getData();
        if (billVo.getIsApprove() == 0) {
            return;
        }
        PaymentPo paymentPo = new PaymentPo();
        BeanUtils.copyProperties(billVo, paymentPo);
        paymentPo.setSupplierId(billVo.getSecondPartyId());
        paymentPo.setBillId(billVo.getId());
        paymentPo.setPaymentTime(billVo.getBillTime());
        paymentPo.setAmount(DataHandlerUtil.enlargeFactor(billVo.getTotalAmount()));
        try {
            paymentService.addPayment(paymentPo);
            log.debug("=======收票信息" + busId + "发起审批成功=====");
        } catch (Exception e) {
            log.error("收票信息" + busId + "发起审批异常!", e);
        }
    }

    private void handlePayReceiveBill(String busId) {
        PaymentVo paymentVo = (PaymentVo) paymentService.getPaymentById(Long.valueOf(busId)).getData();
        if (paymentVo.getIsApprove() == 0) {
            return;
        }
        BillVo billVo = new BillVo();
        BeanUtils.copyProperties(paymentVo, billVo);
        billVo.setSecondPartyId(paymentVo.getSupplierId());
        billVo.setId(paymentVo.getBillId());
        billVo.setBillTime(paymentVo.getPaymentTime());
        billVo.setTotalAmount(paymentVo.getAmount());
        try {
            billService.addBill(billVo);
            log.debug("=======付款记录" + busId + "发起审批成功=====");
        } catch (Exception e) {
            log.error("付款记录" + busId + "发起审批异常!", e);
        }
    }
}
