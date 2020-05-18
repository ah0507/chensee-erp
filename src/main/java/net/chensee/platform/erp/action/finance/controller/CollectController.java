package net.chensee.platform.erp.action.finance.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.chensee.base.annotation.CheckFolderAnnontation;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.finance.po.CollectPo;
import net.chensee.platform.erp.action.finance.service.CollectService;
import net.chensee.platform.erp.action.finance.vo.CollectVo;
import net.chensee.platform.erp.utils.DataHandlerUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author : shibo
 * @date : 2019/5/27 18:39
 */
@RestController
@RequestMapping("")
@Slf4j
public class CollectController {

    @Resource
    private CollectService collectService;
    @Value("${pageNumber}")
    private Integer PAGE_NUMBER;
    @Value("${pageSize}")
    private Integer PAGE_SIZE;

    @ApiOperation(value = "获取所有收款记录")
    @ResponseBody
    @RequestMapping(value = "/collect", method = RequestMethod.GET)
    public ObjectResponse getAllCollect(@RequestParam(required = false) Integer pageSize,
                                        @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return collectService.getAllCollect(pageSize, pageNumber);
    }

    @ApiOperation(value = "根据客户名称和收款编号查询收款记录")
    @ResponseBody
    @RequestMapping(value = "/collect/search/condition", method = RequestMethod.GET)
    public ObjectResponse getByCustomerNameAndNumber(@RequestParam(required = false) String customerName,
                                                     @RequestParam(required = false) String number,
                                                     @RequestParam(required = false) Integer pageSize,
                                                     @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return collectService.getByCustomerNameAndNumber(customerName, number, pageSize, pageNumber);
    }

    @ApiOperation(value = "根据ID获取收款记录")
    @RequestMapping(value = "/collect/{id}", method = RequestMethod.GET)
    public BaseResponse getCollectById(@PathVariable Long id) {
        return collectService.getCollectById(id);
    }

    @ApiOperation(value = "添加收款记录")
    @RequestMapping(value = "/collect", method = RequestMethod.POST)
    @CheckFolderAnnontation(0)
    public ObjectResponse addCollect(@RequestBody @Validated CollectVo collectVo) {
        CollectPo collectPo = new CollectPo();
        BeanUtils.copyProperties(collectVo, collectPo);
        collectPo.setAmount(DataHandlerUtil.enlargeFactor(collectVo.getAmount()));
        return collectService.addCollect(collectPo);
    }

    @ApiOperation(value = "修改收款记录")
    @RequestMapping(value = "/collect", method = RequestMethod.PUT)
    @CheckFolderAnnontation(0)
    public BaseResponse updateCollect(@RequestBody @Validated CollectVo collectVo) {
        CollectPo collectPo = new CollectPo();
        BeanUtils.copyProperties(collectVo, collectPo);
        collectPo.setAmount(DataHandlerUtil.enlargeFactor(collectVo.getAmount()));
        return collectService.updateCollect(collectPo);
    }

    @ApiOperation(value = "删除收款记录")
    @RequestMapping(value = "/collect/{id}", method = RequestMethod.DELETE)
    public BaseResponse deleteCollect(@PathVariable Long id) {
        return collectService.deleteCollect(id);
    }

    @ApiOperation(value = "根据合同查询收款记录")
    @ResponseBody
    @RequestMapping(value = "/collect/contract/{contractId}", method = RequestMethod.GET)
    public ObjectResponse getByContractId(@PathVariable Long contractId) {
        return collectService.getByContractId(contractId);
    }
}
