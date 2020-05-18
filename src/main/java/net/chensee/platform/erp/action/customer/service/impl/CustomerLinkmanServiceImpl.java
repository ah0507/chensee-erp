package net.chensee.platform.erp.action.customer.service.impl;

import net.chensee.base.action.user.business.UserBus;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.base.common.po.BaseInfoPo;
import net.chensee.base.exception.BusinessRuntimeException;
import net.chensee.base.utils.CreateAndUpdateInfoUtil;
import net.chensee.base.utils.ResFolderUtils;
import net.chensee.base.utils.UserUtil;
import net.chensee.platform.erp.action.customer.mapper.LinkmanDao;
import net.chensee.platform.erp.action.customer.po.LinkmanPo;
import net.chensee.platform.erp.action.customer.service.CustomerLinkmanService;
import net.chensee.platform.erp.action.customer.vo.LinkmanVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author : shibo
 * @date : 2019/5/20 9:28
 */
@Service
@Transactional
public class CustomerLinkmanServiceImpl implements CustomerLinkmanService {
    private static final Logger logger = LoggerFactory.getLogger(LinkmanPo.class);

    @Resource
    private LinkmanDao linkmanDao;
    @Resource
    private UserBus userBus;


    @Override
    public ObjectResponse addCustomerLinkman(LinkmanPo customerLinkmanPo) {
        try {
            Long userId = UserUtil.getCurrentUser().getId();
            if (!userBus.isExistUserInSelectDept(userId, customerLinkmanPo.getDeptId())) {
                return ObjectResponse.fail("当前用户不属于该部门");
            }
            checkParams(customerLinkmanPo);
            customerLinkmanPo.setStatus(BaseInfoPo.Status_Able);
            CreateAndUpdateInfoUtil.addCreateInfo(customerLinkmanPo);
            CreateAndUpdateInfoUtil.addUpdateInfo(customerLinkmanPo);
            linkmanDao.addLinkman(customerLinkmanPo);
            return ObjectResponse.ok(customerLinkmanPo.getId());
        } catch (Exception e) {
            logger.error("添加客户联系人出现异常", e);
            return ObjectResponse.fail("添加客户联系人出现异常");
        }
    }

    private void checkParams(LinkmanPo customerLinkmanPo) {
        if (customerLinkmanPo.getCustomerId() != null && customerLinkmanPo.getSupplierId() == null) {
        }else {
            throw new BusinessRuntimeException(100,"customerId or supplierId");
        }
    }

    @Override
    public BaseResponse updateCustomerLinkman(LinkmanPo customerLinkmanPo) {
        try {
            Long userId = UserUtil.getCurrentUser().getId();
            if (!userBus.isExistUserInSelectDept(userId, customerLinkmanPo.getDeptId())) {
                return ObjectResponse.fail("当前用户不属于该部门");
            }
            CreateAndUpdateInfoUtil.addUpdateInfo(customerLinkmanPo);
            linkmanDao.updateLinkman(customerLinkmanPo);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("修改客户联系人出现异常", e);
            return BaseResponse.fail("修改客户联系人出现异常");
        }
    }

    @Override
    public BaseResponse deleteCustomerLinkman(Long id) {
        try {
            linkmanDao.deleteLinkman(id);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("删除客户联系人出现异常", e);
            return BaseResponse.fail("删除客户联系人出现异常");
        }
    }

    @Override
    public ObjectResponse getAllCustomerLinkman(Integer pageSize, Integer pageNumber) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            List<LinkmanVo> customerLinkmanVos = linkmanDao.getAllCustomerLinkman(pageSize * (pageNumber - 1), pageSize, currentFolder);
            for (LinkmanVo linkmanVo : customerLinkmanVos) {
                if (linkmanVo.getFileIds() != null) {
                    linkmanVo.setFileIdsList(Arrays.asList(linkmanVo.getFileIds().split(",")));
                }
            }
            Integer count = linkmanDao.getCustomerCount(null, null, currentFolder);
            Map map = new HashMap();
            map.put("data", customerLinkmanVos);
            map.put("total" ,count);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("获取客户联系人出现异常", e);
            return ObjectResponse.fail("获取客户联系人出现异常");
        }
    }

    @Override
    public ObjectResponse getByCustomerNameAndName(String customerName, String name, Integer pageSize, Integer pageNumber) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            List<LinkmanVo> customerLinkmanVos = linkmanDao.getByCustomerNameAndName(customerName, name, pageSize * (pageNumber - 1), pageSize, currentFolder);
            for (LinkmanVo linkmanVo : customerLinkmanVos) {
                if (linkmanVo.getFileIds() != null) {
                    linkmanVo.setFileIdsList(Arrays.asList(linkmanVo.getFileIds().split(",")));
                }
            }
            Integer count = linkmanDao.getCustomerCount(customerName, name, currentFolder);
            Map map = new HashMap();
            map.put("data", customerLinkmanVos);
            map.put("total" ,count);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("查找客户联系人出现异常", e);
            return ObjectResponse.fail("查找客户联系人出现异常");
        }
    }

    @Override
    public ObjectResponse getByCustomer(Long customerId, Integer pageSize, Integer pageNumber) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            List<LinkmanVo> customerLinkmanVos = linkmanDao.getByCustomer(customerId, pageSize * (pageNumber - 1), pageSize,currentFolder);
            for (LinkmanVo linkmanVo : customerLinkmanVos) {
                if (linkmanVo.getFileIds() != null) {
                    linkmanVo.setFileIdsList(Arrays.asList(linkmanVo.getFileIds().split(",")));
                }
            }
            Integer count = linkmanDao.getCountByCustomer(customerId,currentFolder);
            Map map = new HashMap();
            map.put("data", customerLinkmanVos);
            map.put("total" ,count);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("查找客户联系人出现异常", e);
            return ObjectResponse.fail("查找客户联系人出现异常");
        }
    }

    @Override
    public ObjectResponse getCustomerLinkmanById(Long id) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            return ObjectResponse.ok(linkmanDao.getCustomerLinkmanById(id,currentFolder).get(0));
        } catch (Exception e) {
            logger.error("根据ID获取客户联系人出现异常", e);
            return ObjectResponse.fail("获取客户联系人出现异常");
        }
    }
}
