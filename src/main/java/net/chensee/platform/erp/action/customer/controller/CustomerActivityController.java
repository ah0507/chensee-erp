package net.chensee.platform.erp.action.customer.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.chensee.base.annotation.CheckFolderAnnontation;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.customer.po.CustomerActivityPo;
import net.chensee.platform.erp.action.customer.service.CustomerActivityService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author : shibo
 * @date : 2019/5/20 9:25
 */
@RestController
@RequestMapping("")
@Slf4j
public class CustomerActivityController {
    
    @Resource
    private CustomerActivityService customerActivityService;
    @Value("${pageNumber}")
    private Integer PAGE_NUMBER;
    @Value("${pageSize}")
    private Integer PAGE_SIZE;

    @ApiOperation(value = "获取客户活动")
    @RequestMapping(value = "/customer/activity", method = RequestMethod.GET)
    public ObjectResponse getAllCustomerActivity(@RequestParam(required = false) Integer pageSize,
                                                 @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return customerActivityService.getAllCustomerActivity(pageSize, pageNumber);
    }

    @ApiOperation(value = "查找客户活动")
    @RequestMapping(value = "/customer/activity/search/condition", method = RequestMethod.GET)
    public ObjectResponse getByThemeAndNo(@RequestParam(required = false) String theme,
                                          @RequestParam(required = false) String no,
                                          @RequestParam(required = false) Integer pageSize,
                                          @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return customerActivityService.getByThemeAndNo(theme, no, pageSize, pageNumber);
    }

    @ApiOperation(value = "添加客户活动")
    @RequestMapping(value = "/customer/activity", method = RequestMethod.POST)
    @CheckFolderAnnontation(0)
    public ObjectResponse addCustomerActivity(@RequestBody @Validated CustomerActivityPo customerActivityPo) {
        return customerActivityService.addCustomerActivity(customerActivityPo);
    }

    @ApiOperation(value = "修改客户活动")
    @RequestMapping(value = "/customer/activity", method = RequestMethod.PUT)
    @CheckFolderAnnontation(0)
    public BaseResponse updateCustomerActivity(@RequestBody @Validated CustomerActivityPo customerActivityPo) {
        return customerActivityService.updateCustomerActivity(customerActivityPo);
    }

    @ApiOperation(value = "删除客户活动")
    @RequestMapping(value = "/customer/activity/{id}", method = RequestMethod.DELETE)
    public BaseResponse deleteCustomerActivity(@PathVariable Long id) {
        return customerActivityService.deleteCustomerActivity(id);
    }

    @ApiOperation(value = "根据ID获取客户活动")
    @RequestMapping(value = "/customer/activity/{id}", method = RequestMethod.GET)
    public BaseResponse getCustomerActivityById(@PathVariable Long id) {
        return customerActivityService.getCustomerActivityById(id);
    }
}
