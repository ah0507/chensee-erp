package net.chensee.platform.erp.action.customer.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.chensee.base.annotation.CheckFolderAnnontation;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.customer.service.CustomerService;
import net.chensee.platform.erp.action.customer.vo.CustomerVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author : shibo
 * @date : 2019/5/17 18:02
 */
@RestController
@RequestMapping("")
@Slf4j
public class CustomerController {

    @Resource
    private CustomerService customerService;
    @Value("${pageNumber}")
    private Integer PAGE_NUMBER;
    @Value("${pageSize}")
    private Integer PAGE_SIZE;

    @ApiOperation(value = "获取所有客户")
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public ObjectResponse getAllCustomers(@RequestParam(required = false) Integer pageSize,
                                          @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return customerService.getAllCustomers(pageSize, pageNumber);
    }

    @ApiOperation(value = "根据客户名称和编号查询客户")
    @RequestMapping(value = "/customer/search/condition", method = RequestMethod.GET)
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
        return customerService.getByNameAndNo(name, no, pageSize, pageNumber);
    }

    @ApiOperation(value = "添加客户")
    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    @CheckFolderAnnontation(0)
    public ObjectResponse addCustomer(@RequestBody @Validated CustomerVo customerVo) throws Exception{
        return customerService.addCustomer(customerVo);
    }

    @ApiOperation(value = "修改客户")
    @CheckFolderAnnontation(0)
    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    public BaseResponse updateCustomer(@RequestBody @Validated CustomerVo customerVo) throws Exception{
        return customerService.updateCustomer(customerVo);
    }

    @ApiOperation(value = "删除客户")
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    public BaseResponse deleteCustomer(@PathVariable Long id) throws Exception{
        return customerService.deleteCustomer(id);
    }

    @ApiOperation(value = "根据客户ID查询客户")
    @RequestMapping(value = "/customer/id/{id}", method = RequestMethod.GET)
    public ObjectResponse getById(@PathVariable Long id) {
        return customerService.getById(id);
    }
}
