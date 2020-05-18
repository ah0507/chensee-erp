package net.chensee.platform.erp.action.business.service.impl;


import net.chensee.base.action.role.business.RoleBus;
import net.chensee.base.action.user.vo.UserDetailsAllVo;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.base.utils.UserUtil;
import net.chensee.platform.erp.action.business.mapper.BusDao;
import net.chensee.platform.erp.action.business.mapper.BusRolesDao;
import net.chensee.platform.erp.action.business.po.BusinessRolePo;
import net.chensee.platform.erp.action.business.service.BusRoleService;
import net.chensee.platform.erp.action.business.service.BusService;
import net.chensee.platform.erp.action.business.vo.BusinessRoleVo;
import net.chensee.platform.erp.action.business.vo.BusinessVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional
public class BusServiceImpl implements BusService {

    private static final Logger logger = LoggerFactory.getLogger(BusServiceImpl.class);

    @Autowired
    private BusDao busDao;

    @Override
    public ObjectResponse getAllBusiness() {
        try {
            List<BusinessVo> allBusiness = busDao.getAllBusiness();
            return ObjectResponse.ok(allBusiness);
        } catch (Exception e) {
            logger.error("获取所有业务信息异常", e);
            return ObjectResponse.fail("获取所有业务信息异常");
        }
    }
}
