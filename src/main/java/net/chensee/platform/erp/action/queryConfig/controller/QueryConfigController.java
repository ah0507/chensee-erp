package net.chensee.platform.erp.action.queryConfig.controller;

import io.swagger.annotations.ApiOperation;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.queryConfig.po.QueryConfigPo;
import net.chensee.platform.erp.action.queryConfig.service.QueryConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ah
 * @title: QueryConfigController
 * @date 2019/11/26 10:02
 */
@RestController
@RequestMapping(value = "/queryConfig")
public class QueryConfigController {

    @Autowired
    private QueryConfigService queryConfigService;

    @ApiOperation(value = "添加高级查询配置")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ObjectResponse addQueryConfig(@RequestBody QueryConfigPo queryConfigPo) throws Exception {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String className = this.getClass().getName();
        String classMethodName = className + "." + methodName;
        return queryConfigService.addQueryConfig(queryConfigPo,classMethodName);
    }

    @ApiOperation(value = "删除高级查询配置")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ObjectResponse delQueryConfig(@PathVariable(value = "id") Long id) throws Exception {
        return queryConfigService.delQueryConfig(id);
    }

    @ApiOperation(value = "修改高级查询配置")
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ObjectResponse updateQueryConfig(@RequestBody QueryConfigPo queryConfigPo) throws Exception {
        return queryConfigService.updateQueryConfig(queryConfigPo);
    }

    @ApiOperation(value = "查询已用高级查询配置")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ObjectResponse getQueryConfigPage(@RequestParam(value = "pageSize") Integer pageSize,
                                             @RequestParam(value = "pageNumber") Integer pageNumber) {
        return queryConfigService.getQueryConfigPage(pageSize,pageNumber);
    }

    @ApiOperation(value = "查询所有高级查询配置")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ObjectResponse getQueryConfigAllPage(@RequestParam(value = "pageSize") Integer pageSize,
                                             @RequestParam(value = "pageNumber") Integer pageNumber) {
        return queryConfigService.getQueryConfigAllPage(pageSize,pageNumber);
    }
}
