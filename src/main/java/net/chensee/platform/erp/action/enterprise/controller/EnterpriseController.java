package net.chensee.platform.erp.action.enterprise.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.chensee.base.annotation.CheckFolderAnnontation;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.enterprise.po.EnterprisePo;
import net.chensee.platform.erp.action.enterprise.service.EnterpriseService;
import net.chensee.platform.erp.action.queryConfig.vo.QueryConfigParamVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : shibo
 * @date : 2019/6/18 9:14
 */
@RestController
@RequestMapping("")
@Slf4j
public class EnterpriseController {
    
    @Resource
    private EnterpriseService enterpriseService;
    @Value("${pageNumber}")
    private Integer PAGE_NUMBER;
    @Value("${pageSize}")
    private Integer PAGE_SIZE;

    @ApiOperation(value = "获取所有公司")
    @RequestMapping(value = "/enterprise", method = RequestMethod.GET)
    public ObjectResponse getAllEnterprise(@RequestParam(required = false) Integer pageSize,
                                           @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return enterpriseService.getAllEnterprise(pageSize, pageNumber);
    }

    @ApiOperation(value = "根据公司名称和编号查询公司")
    @RequestMapping(value = "/enterprise/search/condition", method = RequestMethod.GET)
    public ObjectResponse getByNameAndNo(@RequestParam(required = false) String name,
                                         @RequestParam(required = false) String no,
                                         @RequestParam(required = false) Integer pageSize,
                                         @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return enterpriseService.getByNameAndNo(name, no, pageSize, pageNumber);
    }

    @ApiOperation(value = "根据ID查询公司")
    @RequestMapping(value = "/enterprise/{id}", method = RequestMethod.GET)
    public ObjectResponse getById(@PathVariable Long id) {
        return enterpriseService.getById(id);
    }

    @ApiOperation(value = "根据高级配置查询公司")
    @RequestMapping(value = "/enterprise/query/config", method = RequestMethod.POST)
    public ObjectResponse getEnterpriseByConfig(@RequestBody List<QueryConfigParamVo> queryConfigParamVos,
                                                @RequestParam(value = "pageSize")Integer pageSize,
                                                @RequestParam(value = "pageNumber")Integer pageNumber) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String className = this.getClass().getName();
        String classMethodName = className + "." + methodName;
        return enterpriseService.getEnterpriseByConfig(queryConfigParamVos,classMethodName, pageSize, pageNumber);
    }

    @ApiOperation(value = "添加公司")
    @RequestMapping(value = "/enterprise", method = RequestMethod.POST)
    @CheckFolderAnnontation(0)
    public ObjectResponse addEnterprise(@RequestBody @Validated EnterprisePo enterprisePo) {
        return enterpriseService.addEnterprise(enterprisePo);
    }

    @ApiOperation(value = "修改公司")
    @RequestMapping(value = "/enterprise", method = RequestMethod.PUT)
    @CheckFolderAnnontation(0)
    public BaseResponse updateEnterprise(@RequestBody @Validated EnterprisePo enterprisePo) {
        return enterpriseService.updateEnterprise(enterprisePo);
    }

    @ApiOperation(value = "删除公司")
    @RequestMapping(value = "/enterprise/{id}", method = RequestMethod.DELETE)
    public BaseResponse deleteEnterprise(@PathVariable Long id) {
        return enterpriseService.deleteEnterprise(id);
    }

}
