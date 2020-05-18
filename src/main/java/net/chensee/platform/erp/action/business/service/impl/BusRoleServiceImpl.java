package net.chensee.platform.erp.action.business.service.impl;


import net.chensee.base.action.folder.vo.WritableFolderVo;
import net.chensee.base.action.role.business.RoleBus;
import net.chensee.base.action.user.vo.UserDetailsAllVo;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.base.utils.UserUtil;
import net.chensee.platform.erp.action.business.mapper.BusRolesDao;
import net.chensee.platform.erp.action.business.po.BusinessRolePo;
import net.chensee.platform.erp.action.business.service.BusRoleService;
import net.chensee.platform.erp.action.business.vo.BusinessRoleVo;
import net.chensee.platform.erp.action.contract.mapper.ContractDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional
public class BusRoleServiceImpl implements BusRoleService {

    private static final Logger logger = LoggerFactory.getLogger(BusRoleServiceImpl.class);

    @Autowired
    private BusRolesDao busRolesDao;
    @Resource
    private RoleBus roleBus;

    @Override
    public ObjectResponse getBusinessRoles(Integer pageNumber, Integer pageSize) {
        try {
            List<BusinessRoleVo> businessRoles = busRolesDao.getBusinessRoles(pageSize * (pageNumber - 1), pageSize);

            List<BusinessRoleVo> bRoles = new ArrayList<>();
            settleData(businessRoles, bRoles);

            Integer countBusRoles = busRolesDao.getCountBusRoles(null);
            Map map = new HashMap();
            map.put("data",bRoles);
            map.put("total",countBusRoles);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("获取业务关联角色异常", e);
            return ObjectResponse.fail("获取业务关联角色异常");
        }
    }

    private void settleData(List<BusinessRoleVo> businessRoles, List<BusinessRoleVo> bRoles) {
        Map<Long, BusinessRoleVo> hashMap = new HashMap<>();
        Set<String> set;
        int indexOf = 0;
        for (BusinessRoleVo businessRoleVo : businessRoles) {
            set = new HashSet<>();
            Long id = businessRoleVo.getId();
            if (hashMap.get(id) == null) {
                if (businessRoleVo.getRoleName() != null){
                    set.add(businessRoleVo.getRoleName());
                }
                businessRoleVo.setRoleNameSet(set);
                businessRoleVo.setRoleName(null);
                hashMap.put(id,businessRoleVo);
                bRoles.add(businessRoleVo);
                indexOf = bRoles.indexOf(businessRoleVo);
            }else {
                BusinessRoleVo bRoleVo = hashMap.get(id);
                indexOf = bRoles.indexOf(bRoleVo);
                set = bRoleVo.getRoleNameSet();
                if (businessRoleVo.getRoleName() != null){
                    set.add(businessRoleVo.getRoleName());
                }
                bRoleVo.setRoleNameSet(set);
                bRoleVo.setRoleName(null);
                hashMap.put(id,bRoleVo);
                bRoles.set(indexOf,bRoleVo);
            }
        }
    }

    @Override
    public ObjectResponse getRolesByName(Integer pageNumber, Integer pageSize, String name) {
        try {
            List<BusinessRoleVo> roles = busRolesDao.getRolesByName(pageSize*(pageNumber-1), pageSize, name);

            List<BusinessRoleVo> bRoles = new ArrayList<>();
            settleData(roles, bRoles);

            Integer countBusRoles = busRolesDao.getCountBusRoles(name);
            Map map = new HashMap();
            map.put("data",bRoles);
            map.put("total",countBusRoles);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("根据名字获取业务关联角色异常", e);
            return ObjectResponse.fail("根据名字获取业务关联角色异常");
        }
    }

    @Override
    public ObjectResponse getRolesByPath(String path) {
        try {
            List<BusinessRoleVo> rolesByPath = busRolesDao.getRolesByPath(path);
            Set<Long> roleSet = new HashSet<>();
            for (BusinessRoleVo businessRoleVo : rolesByPath) {
                Long roleId = businessRoleVo.getRoleId();
                roleSet.add(roleId);
            }
            BusinessRoleVo newBusinessRoleVo = rolesByPath.get(0);
            newBusinessRoleVo.setRoleId(null);
            newBusinessRoleVo.setRoleName(null);
            newBusinessRoleVo.setRoleIdSet(roleSet);
            return ObjectResponse.ok(newBusinessRoleVo);
        } catch (Exception e) {
            logger.error("根据请求路径获取业务关联角色异常", e);
            return ObjectResponse.fail("根据请求路径获取业务关联角色异常");
        }
    }

    @Override
    public BaseResponse addBusinessRole(BusinessRolePo businessRolePo) {
        try {
            //检查待录入业务是否已存在
            //TODO
            businessRolePo.setCreateTime(new Date());
            businessRolePo.setUpdateTime(new Date());
            UserDetailsAllVo currentUser = UserUtil.getCurrentUser();
            businessRolePo.setCreateBy(currentUser.getId());
            businessRolePo.setUpdateBy(currentUser.getId());
            businessRolePo.setIsBuiltIn(BusinessRolePo.IsNotBuiltIn);
            businessRolePo.setStatus(BusinessRolePo.Status_Able);
            busRolesDao.addBusinessRole(businessRolePo);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("添加业务关联角色异常", e);
            return BaseResponse.fail("添加业务关联角色异常");
        }
    }

    @Override
    public BaseResponse updateBusinessRole(BusinessRolePo businessRolePo) {
        try {
            businessRolePo.setUpdateTime(new Date());
            UserDetailsAllVo currentUser = UserUtil.getCurrentUser();
            businessRolePo.setUpdateBy(currentUser.getId());
            busRolesDao.updateBusinessRole(businessRolePo);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("修改业务关联角色异常", e);
            return BaseResponse.fail("修改业务关联角色异常");
        }
    }

    @Override
    public BaseResponse deleteBusinessRole(Long id) {
        try {
            busRolesDao.deleteBusinessRole(id);
            busRolesDao.deleteBusRoleInfos(id);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("删除业务关联角色异常", e);
            return BaseResponse.fail("删除业务关联角色异常");
        }
    }

    @Override
    public BaseResponse allotBusinessRole(Long businessId, String roleIds) {
        try {
            busRolesDao.deleteBusRoleInfos(businessId);
            String[] roleIdArray = roleIds.split(",");
            if (roleIdArray.length != 0) {
                List<Long> roleIdList = new ArrayList<>();
                Long roleId;
                for (String roleIdString : roleIdArray) {
                    roleId = Long.parseLong(roleIdString);
                    roleIdList.add(roleId);
                }
                busRolesDao.allotBusinessRole(businessId,roleIdList);
            }else {
                long roleId = Long.parseLong(roleIds);
                busRolesDao.addBusRoleRelation(businessId,roleId);
            }
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("分配业务角色关联信息异常", e);
            return BaseResponse.fail("分配业务角色关联信息异常");
        }
    }

    @Override
    public BaseResponse addBusRoleRelation(Long busRoleId, Long roleId) {
        try {
            if (roleBus.getRoleById(roleId) != null) {
                busRolesDao.addBusRoleRelation(busRoleId, roleId);
                return BaseResponse.ok();
            }
            return BaseResponse.fail("该角色不存在");
        } catch (Exception e) {
            logger.error("根据业务ID添加角色关联信息异常", e);
            return BaseResponse.fail("根据业务ID添加角色关联信息异常");
        }
    }

    @Override
    public BaseResponse delBusRoleRelation(Long busRoleId,Long roleId) {
        try {
            busRolesDao.delBusRoleRelation(busRoleId,roleId);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("根据业务ID删除角色关联信息异常", e);
            return BaseResponse.fail("根据业务ID删除角色关联信息异常");
        }
    }

    @Override
    public ObjectResponse getBusRoleRelation(Long busRoleId, Integer pageNumber, Integer pageSize) {
        try {
            List<BusinessRoleVo> busRoleRelation =
                    busRolesDao.getBusRoleRelation(busRoleId, pageSize * (pageNumber - 1), pageSize);
            Integer total = busRolesDao.getCountBusRoleRelation(busRoleId);
            Map map = new HashMap<>();
            map.put("data",busRoleRelation);
            map.put("total",total);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("根据业务ID查询角色关联信息异常", e);
            return ObjectResponse.fail("根据业务ID查询角色关联信息异常");
        }
    }


}
