package net.chensee.platform.erp.action.supplier.service.impl;

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
import net.chensee.platform.erp.action.customer.vo.LinkmanVo;
import net.chensee.platform.erp.action.supplier.service.SupplierLinkmanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author : shibo
 * @date : 2019/6/20 9:20
 */
@Service
@Transactional
public class SupplierLinkmanServiceImpl implements SupplierLinkmanService {
    private static final Logger logger = LoggerFactory.getLogger(LinkmanPo.class);

    @Resource
    private LinkmanDao linkmanDao;
    @Resource
    private UserBus userBus;

    @Override
    public ObjectResponse addSupplierLinkman(LinkmanPo linkmanPo) {
        try {
            Long userId = UserUtil.getCurrentUser().getId();
            if (!userBus.isExistUserInSelectDept(userId, linkmanPo.getDeptId())) {
                return ObjectResponse.fail("当前用户不属于该部门");
            }
            checkParams(linkmanPo);
            linkmanPo.setStatus(BaseInfoPo.Status_Able);
            CreateAndUpdateInfoUtil.addCreateInfo(linkmanPo);
            CreateAndUpdateInfoUtil.addUpdateInfo(linkmanPo);
            linkmanDao.addLinkman(linkmanPo);
            return ObjectResponse.ok(linkmanPo.getId());
        } catch (Exception e) {
            logger.error("添加供应商联系人出现异常", e);
            return ObjectResponse.fail();
        }
    }

    private void checkParams(LinkmanPo supplierLinkmanPo) {
        if (supplierLinkmanPo.getCustomerId() == null && supplierLinkmanPo.getSupplierId() != null) {
        }else {
            throw new BusinessRuntimeException(100,"customerId or supplierId");
        }
    }

    @Override
    public BaseResponse updateSupplierLinkman(LinkmanPo linkmanPo) {
        try {
            Long userId = UserUtil.getCurrentUser().getId();
            if (!userBus.isExistUserInSelectDept(userId, linkmanPo.getDeptId())) {
                return ObjectResponse.fail("当前用户不属于该部门");
            }
            CreateAndUpdateInfoUtil.addUpdateInfo(linkmanPo);
            linkmanDao.updateLinkman(linkmanPo);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("修改供应商联系人出现异常", e);
        }
        return BaseResponse.fail();
    }

    @Override
    public BaseResponse deleteSupplierLinkman(Long id) {
        try {
            linkmanDao.deleteLinkman(id);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("删除供应商联系人出现异常", e);
        }
        return BaseResponse.fail();
    }

    @Override
    public ObjectResponse getAllSupplierLinkman(Integer pageSize, Integer pageNumber) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            List<LinkmanVo> linkmanVos = linkmanDao.getAllSupplierLinkman(pageSize * (pageNumber - 1), pageSize,currentFolder);
            Integer count = linkmanDao.getSupplierCount(null, null,currentFolder);
            Map map = new HashMap();
            map.put("data", linkmanVos);
            map.put("total" ,count);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("获取供应商联系人出现异常", e);
        }
        return ObjectResponse.fail();
    }

    @Override
    public ObjectResponse getBySupplierNameAndName(String supplierName, String name, Integer pageSize, Integer pageNumber) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            List<LinkmanVo> linkmanVos = linkmanDao.getBySupplierNameAndName(supplierName, name, pageSize * (pageNumber - 1), pageSize,currentFolder);
            Integer count = linkmanDao.getSupplierCount(supplierName, name,currentFolder);
            Map map = new HashMap();
            map.put("data", linkmanVos);
            map.put("total" ,count);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("查找供应商联系人出现异常", e);
        }
        return ObjectResponse.fail();
    }

    @Override
    public ObjectResponse getBySupplier(Long supplierId, Integer pageSize, Integer pageNumber) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            List<LinkmanVo> linkmanVos = linkmanDao.getBySupplier(supplierId, pageSize * (pageNumber - 1), pageSize,currentFolder);
            Integer count = linkmanDao.getCountBySupplier(supplierId,currentFolder);
            Map map = new HashMap();
            map.put("data", linkmanVos);
            map.put("total" ,count);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("查找供应商联系人出现异常", e);
            return ObjectResponse.fail();
        }
    }

    @Override
    public ObjectResponse getSupplierLinkmanById(Long id) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            return ObjectResponse.ok(linkmanDao.getSupplierLinkmanById(id,currentFolder).get(0));
        } catch (Exception e) {
            logger.error("根据ID查找供应商联系人出现异常", e);
            return ObjectResponse.fail();
        }
    }
}
