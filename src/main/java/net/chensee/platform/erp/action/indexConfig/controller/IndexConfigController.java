package net.chensee.platform.erp.action.indexConfig.controller;

import io.swagger.annotations.ApiOperation;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.indexConfig.service.IndexConfigService;
import net.chensee.platform.erp.action.indexConfig.vo.IndexConfigVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ah
 * @title: indexConfigController
 * @date 2019/12/5 10:24
 */
@RestController
@RequestMapping(value = "/indexConfig")
public class IndexConfigController {

    @Autowired
    private IndexConfigService indexConfigService;

    @ApiOperation(value = "添加首页信息及配置")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ObjectResponse saveIndexConfig(@RequestBody IndexConfigVo indexConfigVo) throws Exception {
        return indexConfigService.saveIndexConfig(indexConfigVo);
    }

    @ApiOperation(value = "保存用户对应的首页")
    @RequestMapping(value = "/{indexId}/user", method = RequestMethod.POST)
    public ObjectResponse saveUserIndex(@PathVariable Long indexId) throws Exception {
        return indexConfigService.saveUserIndex(indexId);
    }

    @ApiOperation(value = "获取用户个人的所有首页信息")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ObjectResponse getIndexInfosByUser() {
        return indexConfigService.getIndexInfosByUser();
    }

    @ApiOperation(value = "获取用户对应角色的所有首页信息")
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public ObjectResponse getIndexInfosByRole() {
        return indexConfigService.getIndexInfosByRole();
    }

    @ApiOperation(value = "获取用户对应部门的所有首页信息")
    @RequestMapping(value = "/dept", method = RequestMethod.GET)
    public ObjectResponse getIndexInfosByDept() {
        return indexConfigService.getIndexInfosByDept();
    }


    @ApiOperation(value = "分页获得用户对应的首页及配置")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ObjectResponse getIndexInfoConfigPage(@RequestParam(required = false) Integer pageSize,
                                             @RequestParam(required = false) Integer pageNumber) {
        return indexConfigService.getIndexInfoConfigPage(pageSize,pageNumber);
    }

    @ApiOperation(value = "获得用户对应的首页及配置")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ObjectResponse getUserIndexConfig() {
        return indexConfigService.getUserIndexConfig();
    }

    @ApiOperation(value = "删除首页的信息及配置")
    @RequestMapping(value = "/{indexId}", method = RequestMethod.DELETE)
    public ObjectResponse delIndexConfig(@PathVariable Long indexId) throws Exception {
        return indexConfigService.delIndexConfig(indexId);
    }

}
