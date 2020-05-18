package net.chensee.platform.erp.action.customer.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.chensee.base.annotation.CheckFolderAnnontation;

import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.customer.po.LinkmanPo;
import net.chensee.platform.erp.action.customer.service.CustomerLinkmanService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author : shibo
 * @date : 2019/5/20 9:25
 */
@RestController
@RequestMapping("")
@Slf4j
public class CustomerLinkmanController {

    @Resource
    private CustomerLinkmanService customerLinkmanService;
    @Value("${pageNumber}")
    private Integer PAGE_NUMBER;
    @Value("${pageSize}")
    private Integer PAGE_SIZE;

    @ApiOperation(value = "获取所有的客户联系人")
    @RequestMapping(value = "/customer/linkman", method = RequestMethod.GET)
    public ObjectResponse getAllCustomerLinkman(@RequestParam(required = false) Integer pageSize,
                                                @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return customerLinkmanService.getAllCustomerLinkman(pageSize, pageNumber);
    }

    @ApiOperation(value = "根据客户名称和联系人名称查询联系人")
    @RequestMapping(value = "/customer/linkman/search/condition", method = RequestMethod.GET)
    public ObjectResponse getByCustomerNameAndName(@RequestParam(required = false) String customerName,
                                                   @RequestParam(required = false) String name,
                                                   @RequestParam(required = false) Integer pageSize,
                                                   @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return customerLinkmanService.getByCustomerNameAndName(customerName, name, pageSize, pageNumber);
    }

    @ApiOperation(value = "根据客户查询联系人")
    @RequestMapping(value = "/customer/{customerId}/linkman", method = RequestMethod.GET)
    public ObjectResponse getByCustomer(@PathVariable Long customerId, Integer pageSize, Integer pageNumber) {
        return customerLinkmanService.getByCustomer(customerId, pageSize, pageNumber);
    }

    @ApiOperation(value = "添加客户联系人")
    @RequestMapping(value = "/customer/linkman", method = RequestMethod.POST)
    @CheckFolderAnnontation(0)
    public ObjectResponse addCustomerLinkman(@RequestBody @Validated LinkmanPo linkmanPo) {
        return customerLinkmanService.addCustomerLinkman(linkmanPo);
    }

    @ApiOperation(value = "修改客户联系人")
    @RequestMapping(value = "/customer/linkman", method = RequestMethod.PUT)
    @CheckFolderAnnontation(0)
    public BaseResponse updateCustomerLinkman(@RequestBody @Validated LinkmanPo linkmanPo) {
        return customerLinkmanService.updateCustomerLinkman(linkmanPo);
    }

    @ApiOperation(value = "删除客户联系人")
    @RequestMapping(value = "/customer/linkman/{id}", method = RequestMethod.DELETE)
    public BaseResponse deleteCustomerLinkman(@PathVariable Long id) {
        return customerLinkmanService.deleteCustomerLinkman(id);
    }

    @ApiOperation(value = "根据ID获取客户联系人")
    @RequestMapping(value = "/customer/linkman/{id}", method = RequestMethod.GET)
    public ObjectResponse getCustomerLinkmanById(@PathVariable Long id) {
        return customerLinkmanService.getCustomerLinkmanById(id);
    }
}
