package net.chensee.platform.erp.action.business.service;

import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.business.po.BusinessRolePo;

public interface BusService {

    /**
     * 获取所有业务信息
     * @return
     */
    ObjectResponse getAllBusiness();
}
