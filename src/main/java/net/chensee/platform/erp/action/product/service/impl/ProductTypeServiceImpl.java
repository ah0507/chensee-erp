package net.chensee.platform.erp.action.product.service.impl;

import net.chensee.base.action.user.vo.UserDetailsAllVo;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.base.common.po.BaseInfoPo;
import net.chensee.base.utils.CreateAndUpdateInfoUtil;
import net.chensee.base.utils.ResFolderUtils;
import net.chensee.base.utils.UserUtil;
import net.chensee.platform.erp.action.product.mapper.ProductTypeDao;
import net.chensee.platform.erp.action.product.po.ProductTypeModelPo;
import net.chensee.platform.erp.action.product.po.ProductTypePo;
import net.chensee.platform.erp.action.product.service.ProductTypeService;
import net.chensee.platform.erp.action.product.vo.ProductTypeModelVo;
import net.chensee.platform.erp.action.product.vo.ProductTypeVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author : shibo
 * @date : 2019/5/21 14:50
 */
@Service
@Transactional
public class ProductTypeServiceImpl implements ProductTypeService {
    private static final Logger logger = LoggerFactory.getLogger(ProductTypePo.class);

    @Resource
    private ProductTypeDao productTypeDao;

    @Override
    public ObjectResponse getAllProductTypes(Integer pageNumber, Integer pageSize) {
        Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
        List<ProductTypeVo> allProductTypes =
                productTypeDao.getAllProductTypes(pageSize * (pageNumber - 1), pageSize, currentFolder);

        List<ProductTypeVo> pTypes = new ArrayList<>();
        settleData(allProductTypes, pTypes);

        Integer total = productTypeDao.getCountProductTypes(null,null,null,currentFolder);
        Map map = new HashMap<>();
        map.put("data",pTypes);
        map.put("total",total);
        return ObjectResponse.ok(map);
    }

    private void settleData(List<ProductTypeVo> allProductTypes, List<ProductTypeVo> pTypes) {
        Map<Long, ProductTypeVo> hashMap = new HashMap<>();
        Set<String> set;
        int indexOf = 0;
        for (ProductTypeVo productTypeVo : allProductTypes) {
            set = new HashSet<>();
            Long id = productTypeVo.getId();
            if (hashMap.get(id) == null) {
                if (productTypeVo.getModelName() != null){
                    set.add(productTypeVo.getModelName());
                }
                productTypeVo.setModelNameSet(set);
                productTypeVo.setModelName(null);
                hashMap.put(id,productTypeVo);
                pTypes.add(productTypeVo);
                indexOf = pTypes.indexOf(productTypeVo);
            }else {
                ProductTypeVo pTypeVo = hashMap.get(id);
                indexOf = pTypes.indexOf(pTypeVo);
                set = pTypeVo.getModelNameSet();
                if (productTypeVo.getModelName() != null){
                    set.add(productTypeVo.getModelName());
                }
                pTypeVo.setModelNameSet(set);
                pTypeVo.setModelName(null);
                hashMap.put(id,pTypeVo);
                pTypes.set(indexOf,pTypeVo);
            }
        }
    }

    @Override
    public ObjectResponse getProductTypesByCondition(String name, String parentName, String unitName,
                                                     Integer pageNumber, Integer pageSize) {
        Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
        List<ProductTypeVo> types = productTypeDao.getProductTypesByCondition(name, parentName, unitName,
                pageSize * (pageNumber - 1), pageSize, currentFolder);

        List<ProductTypeVo> pTypes = new ArrayList<>();
        settleData(types, pTypes);

        Integer total = productTypeDao.getCountProductTypes(name, parentName, unitName, currentFolder);
        Map map = new HashMap<>();
        map.put("data",pTypes);
        map.put("total",total);
        return ObjectResponse.ok(map);
    }

    @Override
    public ObjectResponse getProductTypeById(Long id) {
        Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
        return ObjectResponse.ok(productTypeDao.getProductTypeById(id,currentFolder).get(0));
    }

    @Override
    public BaseResponse addProductTypeModel(Long id, ProductTypeModelPo productTypeModelPo) {
        try {
            UserDetailsAllVo user = UserUtil.getCurrentUser();
            Date date = new Date();
            productTypeModelPo.setTypeId(id);
            productTypeModelPo.setCreateBy(user.getId());
            productTypeModelPo.setCreateTime(date);
            productTypeModelPo.setUpdateBy(user.getId());
            productTypeModelPo.setUpdateTime(date);
            productTypeModelPo.setStatus(ProductTypeModelPo.Status_Able);
            productTypeDao.addProductTypeModel(productTypeModelPo);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("根据类型ID添加产品型号异常", e);
        }
        return BaseResponse.fail("根据类型ID添加产品型号异常");
    }

    @Override
    public ObjectResponse getProductTypeModel(Long id,Integer pageNumber,Integer pageSize) {
        try {
            List<ProductTypeModelVo> productTypeModel = productTypeDao.getProductTypeModel(id, pageSize * (pageNumber - 1), pageSize);
            Integer total = productTypeDao.getCountModelByTypeId(id);
            Map map = new HashMap<>();
            map.put("data",productTypeModel);
            map.put("total",total);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("根据类型ID获取产品型号异常", e);
        }
        return ObjectResponse.fail("根据类型ID获取产品型号异常");
    }

    @Override
    public BaseResponse deleteProductTypeModel(Long id) {
        try {
            productTypeDao.deleteProductTypeModel(id);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("根据类型ID删除产品型号异常", e);
        }
        return BaseResponse.fail("根据类型ID删除产品型号异常");
    }

    @Override
    public ObjectResponse addProductType(ProductTypePo productTypePo) {
        try {
            CreateAndUpdateInfoUtil.addCreateInfo(productTypePo);
            CreateAndUpdateInfoUtil.addUpdateInfo(productTypePo);
            productTypePo.setStatus(BaseInfoPo.Status_Able);
            productTypeDao.addProductType(productTypePo);
            return ObjectResponse.ok(productTypePo.getId());
        } catch (Exception e) {
            logger.error("添加产品类型出现异常", e);
        }
        return ObjectResponse.fail();
    }

    @Override
    public BaseResponse updateProductType(ProductTypePo productTypePo) {
        try {
            CreateAndUpdateInfoUtil.addUpdateInfo(productTypePo);
            productTypeDao.updateProductType(productTypePo);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("修改产品类型出现异常", e);
        }
        return BaseResponse.fail();
    }

    @Override
    public BaseResponse deleteProductType(Long id) {
        try {
            this.deleteChildrenTypes(id);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("删除产品类型出现异常", e);
        }
        return BaseResponse.fail();
    }

    private void deleteChildrenTypes(Long id) {
        productTypeDao.deleteProductType(id);
        List<Long> ids = productTypeDao.getChildrenIds(id);
        if(ids != null && ids.size() > 0) {
            for (Long s : ids) {
                this.deleteChildrenTypes(s);
            }
        }
    }
}
