package net.chensee.platform.erp.action.supplier.service.impl;

import net.chensee.base.action.user.business.UserBus;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.base.common.po.BaseInfoPo;
import net.chensee.base.exception.BusinessRuntimeException;
import net.chensee.base.utils.CreateAndUpdateInfoUtil;
import net.chensee.base.utils.ResFolderUtils;
import net.chensee.base.utils.UserUtil;
import net.chensee.platform.erp.action.customer.mapper.FinancialInfoDao;
import net.chensee.platform.erp.action.customer.po.FinancialInfoPo;
import net.chensee.platform.erp.action.supplier.mapper.SupplierDao;
import net.chensee.platform.erp.action.supplier.po.SupplierPo;
import net.chensee.platform.erp.action.supplier.service.SupplierService;
import net.chensee.platform.erp.action.supplier.vo.SupplierVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author : shibo
 * @date : 2019/6/18 13:46
 */
@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {
    private static final Logger logger = LoggerFactory.getLogger(SupplierPo.class);

    @Resource
    private SupplierDao supplierDao;
    @Resource
    private FinancialInfoDao financialInfoDao;
    @Resource
    private UserBus userBus;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResponse addSupplier(SupplierVo supplierVo) throws Exception{
        try {
            Long userId = UserUtil.getCurrentUser().getId();
            if (!userBus.isExistUserInSelectDept(userId, supplierVo.getDeptId())) {
                return ObjectResponse.fail("当前用户不属于该部门");
            }
            SupplierPo supplierPo = new SupplierPo();
            FinancialInfoPo financialPo = new FinancialInfoPo();
            this.copyData(supplierPo, financialPo, supplierVo);
            supplierPo.setStatus(BaseInfoPo.Status_Able);
            supplierPo.setFolderId(supplierVo.getFolderId());
            CreateAndUpdateInfoUtil.addCreateInfo(supplierPo);
            CreateAndUpdateInfoUtil.addUpdateInfo(supplierPo);
            supplierDao.addSupplier(supplierPo);
            financialPo.setSupplierId(supplierPo.getId());
            financialPo.setFolderId(supplierVo.getFolderId());
            financialPo.setStatus(BaseInfoPo.Status_Able);
            checkParam(financialPo);
            CreateAndUpdateInfoUtil.addCreateInfo(financialPo);
            CreateAndUpdateInfoUtil.addUpdateInfo(financialPo);
            financialInfoDao.addFinancialInfo(financialPo);
            return ObjectResponse.ok(supplierPo.getId());
        } catch (Exception e) {
            logger.error("添加供应商出现异常", e);
            return ObjectResponse.fail();
        }
    }

    private void checkParam(FinancialInfoPo financialPo) {
        if (financialPo.getName() == "" || financialPo.getName() == null) {
            throw new BusinessRuntimeException(100,"name");
        }
        if (financialPo.getTaxpayerCode() == "" || financialPo.getTaxpayerCode() == null) {
            throw new BusinessRuntimeException(100,"taxpayerCode");
        }
        if (financialPo.getAddrPhone() == "" || financialPo.getAddrPhone() == null) {
            throw new BusinessRuntimeException(100,"addrPhone");
        }
        if (financialPo.getBankAccount() == "" || financialPo.getBankAccount() == null) {
            throw new BusinessRuntimeException(100,"bankAccount");
        }
        if (financialPo.getCustomerId() == null && financialPo.getSupplierId() == null) {
            throw new BusinessRuntimeException(100,"customerId or supplierId");
        }
        if (financialPo.getCustomerId() != null && financialPo.getSupplierId() != null) {
            throw new BusinessRuntimeException(100,"customerId or supplierId");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse updateSupplier(SupplierVo supplierVo) throws Exception{
        try {
            Long userId = UserUtil.getCurrentUser().getId();
            if (!userBus.isExistUserInSelectDept(userId, supplierVo.getDeptId())) {
                return ObjectResponse.fail("当前用户不属于该部门");
            }
            SupplierPo supplierPo = new SupplierPo();
            FinancialInfoPo financialPo = new FinancialInfoPo();
            this.copyData(supplierPo, financialPo, supplierVo);
            CreateAndUpdateInfoUtil.addUpdateInfo(supplierPo);
            supplierDao.updateSupplier(supplierPo);
            financialPo.setSupplierId(supplierPo.getId());
            CreateAndUpdateInfoUtil.addUpdateInfo(financialPo);
            financialInfoDao.updateFinancialInfo(financialPo);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("修改供应商出现异常", e);
            return BaseResponse.fail();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse deleteSupplier(Long id) throws Exception{
        try {
            supplierDao.deleteSupplier(id);
            financialInfoDao.deleteBySupplierId(id);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("删除供应商出现异常", e);
            return BaseResponse.fail();
        }
    }

    @Override
    public ObjectResponse getAllSupplier(Integer pageSize, Integer pageNumber) {
        Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
        List<SupplierVo> supplierVos = supplierDao.getAllSupplier(pageSize * (pageNumber - 1), pageSize, currentFolder);
        Integer count = supplierDao.getCount(null, null, currentFolder);
        Map map = new HashMap();
        map.put("data", supplierVos);
        map.put("total" ,count);
        return ObjectResponse.ok(map);
    }

    @Override
    public ObjectResponse getByNameAndNo(String name, String number, Integer pageSize, Integer pageNumber) {
        Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
        List<SupplierVo> supplierVos = supplierDao.getByNameAndNo(name, number, pageSize * (pageNumber - 1), pageSize);
        Integer count = supplierDao.getCount(name, number, currentFolder);
        Map map = new HashMap();
        map.put("data", supplierVos);
        map.put("total" ,count);
        return ObjectResponse.ok(map);
    }

    @Override
    public ObjectResponse getById(Long supplierId) {
        Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
        List<SupplierVo> supplierVos = supplierDao.getById(supplierId, currentFolder);
        if (supplierVos != null && supplierVos.size() !=0) {
            return ObjectResponse.ok(supplierVos.get(0));
        }
        return ObjectResponse.fail("没有找到该供应商信息");
    }

    private void copyData(SupplierPo supplierPo, FinancialInfoPo financialPo, SupplierVo supplierVo) {
        BeanUtils.copyProperties(supplierVo, supplierPo);
        financialPo.setId(supplierVo.getFinancialId());
        financialPo.setAddrPhone(supplierVo.getFinancialAddrPhone());
        financialPo.setName(supplierVo.getFinancialName());
        financialPo.setTaxpayerCode(supplierVo.getFinancialTaxpayerCode());
        financialPo.setBankAccount(supplierVo.getFinancialBankAccount());
    }
}
