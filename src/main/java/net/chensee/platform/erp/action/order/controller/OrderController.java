package net.chensee.platform.erp.action.order.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.chensee.base.annotation.CheckFolderAnnontation;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.order.service.OrderService;
import net.chensee.platform.erp.action.order.vo.OrderVo;
import net.chensee.platform.erp.utils.OrderCheckParamUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("")
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;
    @Value("${pageNumber}")
    private Integer PAGE_NUMBER;
    @Value("${pageSize}")
    private Integer PAGE_SIZE;

    @ApiOperation(value = "获取所有销售订单")
    @RequestMapping(value = "/order/sale", method = RequestMethod.GET)
    public ObjectResponse getAllSaleOrders(@RequestParam(required = false) Integer pageSize,
                                           @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return orderService.getAllOrders(pageSize, pageNumber, 1);
    }

    @ApiOperation(value = "获取所有采购订单")
    @RequestMapping(value = "/order/purchase", method = RequestMethod.GET)
    public ObjectResponse getAllPurchaseOrders(@RequestParam(required = false) Integer pageSize,
                                               @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return orderService.getAllOrders(pageSize, pageNumber, -1);
    }

    @ApiOperation(value = "根据名称和编号查询销售订单")
    @RequestMapping(value = "/order/sale/search/condition", method = RequestMethod.GET)
    public BaseResponse getSaleOrderByNameAndNo(@RequestParam(required = false) String name,
                                                @RequestParam(required = false) String no,
                                                @RequestParam(required = false) Integer pageSize,
                                                @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return orderService.getByNameAndNo(name, no, pageSize, pageNumber, 1);
    }

    @ApiOperation(value = "根据名称和编号查询采购订单")
    @RequestMapping(value = "/order/purchase/search/condition", method = RequestMethod.GET)
    public BaseResponse getPurcahseOrderByNameAndNo(@RequestParam(required = false) String name,
                                                    @RequestParam(required = false) String no,
                                                    @RequestParam(required = false) Integer pageSize,
                                                    @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return orderService.getByNameAndNo(name, no, pageSize, pageNumber, -1);
    }

    @ApiOperation(value = "根据ID查询销售订单")
    @RequestMapping(value = "/order/sale/{orderId}", method = RequestMethod.GET)
    public BaseResponse getSaleOrderByNameAndNo(@PathVariable Long orderId) {
        return orderService.getById(1, orderId);
    }

    @ApiOperation(value = "根据ID查询采购订单")
    @RequestMapping(value = "/order/purchase/{orderId}", method = RequestMethod.GET)
    public BaseResponse getPurchaseOrderByNameAndNo(@PathVariable Long orderId) {
        return orderService.getById(-1, orderId);
    }

    @ApiOperation(value = "添加销售订单")
    @RequestMapping(value = "/order/sale", method = RequestMethod.POST)
    @CheckFolderAnnontation(0)
    public BaseResponse addSaleOrder(@RequestBody @Validated OrderVo orderVo) throws Exception{
        orderVo.setDirect(1);
        OrderCheckParamUtil.checkSaleOrderParam(orderVo);

        return orderService.addOrder(orderVo);
    }

    @ApiOperation(value = "添加采购订单")
    @RequestMapping(value = "/order/purchase", method = RequestMethod.POST)
    @CheckFolderAnnontation(0)
    public BaseResponse addPurchaseOrder(@RequestBody @Validated OrderVo orderVo) throws Exception{
        orderVo.setDirect(-1);
        OrderCheckParamUtil.checkPurchaseOrderParam(orderVo);

        return orderService.addOrder(orderVo);
    }

    @ApiOperation(value = "修改订单")
    @RequestMapping(value = "/order", method = RequestMethod.PUT)
    @CheckFolderAnnontation(0)
    public BaseResponse updateOrder(@RequestBody @Validated OrderVo orderVo) throws Exception{
        Integer direct = orderVo.getDirect();
        if (direct == 1) {
            OrderCheckParamUtil.checkSaleOrderParam(orderVo);
        }else if (direct == -1) {
            OrderCheckParamUtil.checkPurchaseOrderParam(orderVo);
        }
        return orderService.updateOrder(orderVo);
    }

    @ApiOperation(value = "删除订单")
    @RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE)
    public BaseResponse deleteOrder(@PathVariable Long id) throws Exception{
        return orderService.deleteOrder(id);
    }

}
