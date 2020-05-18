package net.chensee.platform.erp.action.contract.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.chensee.base.annotation.CheckFolderAnnontation;

import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.contract.service.ContractService;
import net.chensee.platform.erp.action.contract.vo.ContractVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("")
@Slf4j
public class ContractController {

    @Resource
    private ContractService contractService;
    @Value("${pageNumber}")
    private Integer PAGE_NUMBER;
    @Value("${pageSize}")
    private Integer PAGE_SIZE;

    @ApiOperation(value = "获取所有销售合同")
    @RequestMapping(value = "/contract/sale", method = RequestMethod.GET)
    public ObjectResponse getAllSaleContracts(@RequestParam(required = false) Integer pageSize,
                                              @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return contractService.getAllContracts(pageSize, pageNumber, 1);
    }

    @ApiOperation(value = "获取所有采购合同")
    @RequestMapping(value = "/contract/purchase", method = RequestMethod.GET)
    public ObjectResponse getAllPurchaseContracts(@RequestParam(required = false) Integer pageSize,
                                                  @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return contractService.getAllContracts(pageSize, pageNumber, -1);
    }

    @ApiOperation(value = "获取对方所有销售合同")
    @RequestMapping(value = "/contract/sale/otherParty/{id}", method = RequestMethod.GET)
    public ObjectResponse getSaleOrderByOtherPartyId(@PathVariable Long id,
                                                     @RequestParam(required = false) Integer pageSize,
                                                     @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return contractService.getByOtherPartyId(id, pageSize, pageNumber, 1);
    }

    @ApiOperation(value = "获取对方所有采购合同")
    @RequestMapping(value = "/contract/purchase/otherParty/{id}", method = RequestMethod.GET)
    public ObjectResponse getPurchaseOrderByOtherPartyId(@PathVariable Long id,
                                                         Integer pageSize,
                                                         Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return contractService.getByOtherPartyId(id, pageSize, pageNumber, -1);
    }

    @ApiOperation(value = "根据订单名称和合同编号查询销售合同")
    @RequestMapping(value = "/contract/sale/search/condition", method = RequestMethod.GET)
    public ObjectResponse getSaleByOrderNameAndContractNo(@RequestParam(required = false) String orderName,
                                                          @RequestParam(required = false) String no,
                                                          @RequestParam(required = false) Integer pageSize,
                                                          @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return contractService.getByOrderNameAndContractNo(orderName, no, pageSize, pageNumber, 1);
    }

    @ApiOperation(value = "根据订单名称和合同编号查询采购合同")
    @RequestMapping(value = "/contract/purchase/search/condition", method = RequestMethod.GET)
    public ObjectResponse getPurchaseByOrderNameAndContractNo(@RequestParam(required = false) String orderName,
                                                              @RequestParam(required = false) String no,
                                                              @RequestParam(required = false) Integer pageSize,
                                                              @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return contractService.getByOrderNameAndContractNo(orderName, no, pageSize, pageNumber, -1);
    }

    @ApiOperation(value = "根据合同ID查询销售合同")
    @RequestMapping(value = "/contract/sale/{contractId}", method = RequestMethod.GET)
    public ObjectResponse getSaleOrderById( @PathVariable Long contractId) {
        return contractService.getById(1,contractId);
    }

    @ApiOperation(value = "根据合同ID查询采购合同")
    @RequestMapping(value = "/contract/purchase/{contractId}", method = RequestMethod.GET)
    public ObjectResponse getPurchaseOrderById(@PathVariable Long contractId) {
        return contractService.getById(-1,contractId);
    }

    @ApiOperation(value = "根据销售订单中合同ID生成销售合同")
    @RequestMapping(value = "/contract/sale/{orderContractId}", method = RequestMethod.POST)
    public BaseResponse addSaleContract(@PathVariable Long orderContractId) throws Exception{
        return contractService.addContract(orderContractId, 1);
    }

    @ApiOperation(value = "根据采购订单中合同ID生成采购合同")
    @RequestMapping(value = "/contract/purchase/{orderContractId}", method = RequestMethod.POST)
    public BaseResponse addPurchaseContract(@PathVariable Long orderContractId) throws Exception{
        return contractService.addContract(orderContractId, -1);
    }

    @ApiOperation(value = "修改合同")
    @CheckFolderAnnontation(0)
    @RequestMapping(value = "/contract", method = RequestMethod.PUT)
    public BaseResponse updateContract(@RequestBody @Validated ContractVo contractVo) throws Exception{
        Integer direct = contractVo.getDirect();
        return contractService.updateContract(contractVo);
    }

    @ApiOperation(value = "删除合同")
    @RequestMapping(value = "/contract/{id}", method = RequestMethod.DELETE)
    public BaseResponse deleteContract(@PathVariable Long id) throws Exception{
        return contractService.deleteContract(id);
    }
}
