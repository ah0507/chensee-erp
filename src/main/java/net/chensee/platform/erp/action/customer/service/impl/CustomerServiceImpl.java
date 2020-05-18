package net.chensee.platform.erp.action.customer.service.impl;

import net.chensee.base.action.user.business.UserBus;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.base.common.po.BaseInfoPo;
import net.chensee.base.exception.BusinessRuntimeException;
import net.chensee.base.utils.CreateAndUpdateInfoUtil;
import net.chensee.base.utils.ResFolderUtils;
import net.chensee.base.utils.UserUtil;
import net.chensee.platform.erp.action.customer.mapper.CustomerDao;
import net.chensee.platform.erp.action.customer.mapper.FinancialInfoDao;
import net.chensee.platform.erp.action.customer.po.FinancialInfoPo;
import net.chensee.platform.erp.action.customer.po.CustomerPo;
import net.chensee.platform.erp.action.customer.service.CustomerService;
import net.chensee.platform.erp.action.customer.vo.CustomerVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author : shibo
 * @date : 2019/5/17 17:41
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerPo.class);

    @Resource
    private CustomerDao customerDao;
    @Resource
    private FinancialInfoDao financialInfoDao;
    @Resource
    private UserBus userBus;

    @Override
    public ObjectResponse getAllCustomers(Integer pageSize, Integer pageNumber) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            List<CustomerVo> customerVos = customerDao.getAllCustomers(pageSize * (pageNumber - 1), pageSize, currentFolder);
            Integer count = customerDao.getCount(null, null, currentFolder);
            Map map = new HashMap();
            map.put("data", customerVos);
            map.put("total" ,count);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("获取所有客户发生异常",e);
            return ObjectResponse.fail("获取所有客户发生异常");
        }
    }

    @Override
    public ObjectResponse getByNameAndNo(String name, String no, Integer pageSize, Integer pageNumber) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            List<CustomerVo> customerVos = customerDao.getByNameAndNo(name, no, pageSize * (pageNumber - 1), pageSize, currentFolder);
            Integer count = customerDao.getCount(name, no, currentFolder);
            Map map = new HashMap();
            map.put("data", customerVos);
            map.put("total" ,count);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("根据客户名称和编号查询客户发生异常",e);
            return ObjectResponse.fail("根据客户名称和编号查询客户发生异常");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResponse addCustomer(CustomerVo customerVo) throws Exception{
        try {
            Long userId = UserUtil.getCurrentUser().getId();
            if (!userBus.isExistUserInSelectDept(userId, customerVo.getDeptId())) {
                return ObjectResponse.fail("当前用户不属于该部门");
            }
            CustomerPo customerPo = new CustomerPo();
            FinancialInfoPo financialPo = new FinancialInfoPo();
            this.copyData(customerPo, financialPo, customerVo);
            customerPo.setStatus(BaseInfoPo.Status_Able);
            customerPo.setFolderId(customerVo.getFolderId());
            CreateAndUpdateInfoUtil.addCreateInfo(customerPo);
            CreateAndUpdateInfoUtil.addUpdateInfo(customerPo);
            customerDao.addCustomer(customerPo);
            financialPo.setCustomerId(customerPo.getId());
            financialPo.setStatus(BaseInfoPo.Status_Able);
            financialPo.setFolderId(customerVo.getFolderId());
            checkParam(financialPo);
            CreateAndUpdateInfoUtil.addCreateInfo(financialPo);
            CreateAndUpdateInfoUtil.addUpdateInfo(financialPo);
            financialInfoDao.addFinancialInfo(financialPo);
            return ObjectResponse.ok(customerPo.getId());
        } catch (Exception e) {
            logger.error("添加客户异常", e);
            return ObjectResponse.fail("添加客户异常");
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
    public BaseResponse updateCustomer(CustomerVo customerVo) throws Exception{
        try {
            Long userId = UserUtil.getCurrentUser().getId();
            if (!userBus.isExistUserInSelectDept(userId, customerVo.getDeptId())) {
                return ObjectResponse.fail("当前用户不属于该部门");
            }
            CustomerPo customerPo = new CustomerPo();
            FinancialInfoPo financialPo = new FinancialInfoPo();
            this.copyData(customerPo, financialPo, customerVo);
            CreateAndUpdateInfoUtil.addUpdateInfo(customerPo);
            customerDao.updateCustomer(customerPo);
            financialPo.setCustomerId(customerPo.getId());
            //查找财务表中是否有该客户ID对应的财务信息，如果有就更新，没有就新增
            FinancialInfoPo financialPoByCustomerId = financialInfoDao.getByCustomerId(customerPo.getId());
            if(financialPoByCustomerId == null) {
                financialPo.setStatus(BaseInfoPo.Status_Able);
                financialPo.setFolderId(customerVo.getFolderId());
                CreateAndUpdateInfoUtil.addUpdateInfo(financialPo);
                CreateAndUpdateInfoUtil.addCreateInfo(financialPo);
                financialInfoDao.addFinancialInfo(financialPo);
            } else {
                CreateAndUpdateInfoUtil.addUpdateInfo(financialPo);
                financialInfoDao.updateFinancialInfo(financialPo);
            }
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("修改客户异常", e);
            return BaseResponse.fail("修改客户异常");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse deleteCustomer(Long id) throws Exception{
        try {
            customerDao.deleteCustomer(id);
            financialInfoDao.deleteByCustomerId(id);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("删除客户异常", e);
            return BaseResponse.fail("删除客户异常");
        }
    }

    @Override
    public ObjectResponse getById(Long id) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            List<CustomerVo> customerVos = customerDao.getById(id, currentFolder);
            if (customerVos != null && customerVos.size() !=0) {
                return ObjectResponse.ok(customerVos.get(0));
            }
            return ObjectResponse.ok("没有获取到该客户");
        } catch (Exception e) {
            logger.error("根据ID查询客户发生异常",e);
            return ObjectResponse.fail("查询客户发生异常");
        }
    }

    private void copyData(CustomerPo customerPo, FinancialInfoPo financialPo, CustomerVo customerVo) {
        BeanUtils.copyProperties(customerVo, customerPo);
        financialPo.setId(customerVo.getFinancialId());
        financialPo.setAddrPhone(customerVo.getFinancialAddrPhone());
        financialPo.setName(customerVo.getFinancialName());
        financialPo.setTaxpayerCode(customerVo.getFinancialTaxpayerCode());
        financialPo.setBankAccount(customerVo.getFinancialBankAccount());
    }
}
