package net.chensee.platform.erp.utils;

import net.chensee.base.exception.BusinessRuntimeException;
import net.chensee.platform.erp.action.order.vo.OrderVo;

public class OrderCheckParamUtil {

    public static void checkSaleOrderParam(OrderVo orderVo) {
        if (orderVo.getFirstPartyId() == null) {
            throw new BusinessRuntimeException(100,"firstPartyId");
        }
        if (orderVo.getFirstPartyName() == null || orderVo.getFirstPartyName() == "") {
            throw new BusinessRuntimeException(100,"firstPartyName");
        }
        if (orderVo.getFirstPartyManId() == null) {
            throw new BusinessRuntimeException(100,"firstPartyManId");
        }
        if (orderVo.getFirstPartyManName() == null || orderVo.getFirstPartyManName() == "") {
            throw new BusinessRuntimeException(100,"firstPartyManName");
        }
    }

    public static void checkPurchaseOrderParam(OrderVo orderVo) {
        if (orderVo.getSecondPartyId() == null) {
            throw new BusinessRuntimeException(100,"secondPartyId");
        }
        if (orderVo.getSecondPartyName() == null || orderVo.getSecondPartyName() == "") {
            throw new BusinessRuntimeException(100,"secondPartyName");
        }
        if (orderVo.getSecondPartyManId() == null) {
            throw new BusinessRuntimeException(100,"secondPartyManId");
        }
        if (orderVo.getSecondPartyManName() == null || orderVo.getSecondPartyManName() == "") {
            throw new BusinessRuntimeException(100,"secondPartyManName");
        }
    }
}
