package net.chensee.platform.erp.action.product.service.impl;

import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.base.common.po.BaseInfoPo;
import net.chensee.base.utils.FolderUtil;
import net.chensee.base.utils.ResFolderUtils;
import net.chensee.base.utils.UserUtil;
import net.chensee.platform.erp.action.product.mapper.ProductTypeUnitDao;
import net.chensee.platform.erp.action.product.po.ProductTypeUnitPo;
import net.chensee.platform.erp.action.product.service.ProductTypeUnitService;
import net.chensee.platform.erp.action.product.vo.ProductTypeUnitVo;
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
public class ProductTypeUnitServiceImpl implements ProductTypeUnitService {
    private static final Logger logger = LoggerFactory.getLogger(ProductTypeUnitPo.class);

    @Resource
    private ProductTypeUnitDao productTypeUnitDao;

    @Override
    public ObjectResponse getAllProductTypeUnitsPagination(Integer pageSize, Integer pageNumber) {
        Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
        List<ProductTypeUnitVo> productTypeUnitVos = productTypeUnitDao.getAllProductTypeUnitsPagination(pageSize * (pageNumber - 1), pageSize, currentFolder);
        Integer count = productTypeUnitDao.getCount(null,null,currentFolder);
        Map map = new HashMap();
        map.put("data", productTypeUnitVos);
        map.put("total" ,count);
        return ObjectResponse.ok(map);
    }

    @Override
    public ObjectResponse getAllProductTypeUnits() {
        Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
        List<ProductTypeUnitVo> productTypeUnitVos = productTypeUnitDao.getAllProductTypeUnits(currentFolder);
        return ObjectResponse.ok(productTypeUnitVos);
    }

    @Override
    public ObjectResponse getByNameAndType(String name, String typeName, Integer pageSize, Integer pageNumber) {
        Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
        List<ProductTypeUnitVo> productTypeUnitVos = productTypeUnitDao.getByNameAndType(name, typeName, pageSize * (pageNumber - 1), pageSize, currentFolder);
        Integer count = productTypeUnitDao.getCount(name, typeName, currentFolder);
        Map map = new HashMap();
        map.put("data", productTypeUnitVos);
        map.put("total" ,count);
        return ObjectResponse.ok(map);
    }

    @Override
    public ObjectResponse getProductTypeUnitById(Long id) {
        Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
        List<ProductTypeUnitVo> productTypeUnitVos = productTypeUnitDao.getProductTypeUnitById(id, currentFolder);
        return ObjectResponse.ok(productTypeUnitVos.get(0));
    }

    @Override
    public ObjectResponse addProductTypeUnit(ProductTypeUnitPo productTypeUnitPo) {
        try {
            Date date = new Date();
            productTypeUnitPo.setCreateTime(date);
            productTypeUnitPo.setUpdateTime(date);
            Long userId = UserUtil.getCurrentUser().getId();
            productTypeUnitPo.setCreateBy(userId);
            productTypeUnitPo.setUpdateBy(userId);
            productTypeUnitPo.setStatus(BaseInfoPo.Status_Able);
            productTypeUnitDao.addProductTypeUnit(productTypeUnitPo);
            return ObjectResponse.ok(productTypeUnitPo);
        } catch (Exception e) {
            logger.error("添加产品类型单位出现异常", e);
            e.printStackTrace();
        }
        return ObjectResponse.fail();
    }

    @Override
    public BaseResponse updateProductTypeUnit(ProductTypeUnitPo productTypeUnitPo) {
        try {
            Date date = new Date();
            productTypeUnitPo.setUpdateTime(date);
            Long userId = UserUtil.getCurrentUser().getId();
            productTypeUnitPo.setUpdateBy(userId);
            productTypeUnitDao.updateProductTypeUnit(productTypeUnitPo);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("修改产品类型单位出现异常", e);
            e.printStackTrace();
        }
        return BaseResponse.fail();
    }

    @Override
    public BaseResponse deleteProductTypeUnit(Long id) {
        try {
            productTypeUnitDao.deleteProductTypeUnit(id);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("删除产品类型单位出现异常", e);
            e.printStackTrace();
        }
        return BaseResponse.fail();
    }


}
