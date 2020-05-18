package net.chensee.platform.erp.action.product.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import net.chensee.base.annotation.CheckFolderAnnontation;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.product.po.ProductPo;
import net.chensee.platform.erp.action.product.service.ProductService;
import net.chensee.platform.erp.action.product.vo.InputBillVo;
import net.chensee.platform.erp.action.product.vo.OutputBillVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("")
@Slf4j
public class ProductController {

	@Resource
	private ProductService productService;
	@Value("${pageNumber}")
	private Integer PAGE_NUMBER;
	@Value("${pageSize}")
	private Integer PAGE_SIZE;

	@ApiOperation(value = "获取所有产品")
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public BaseResponse getAllProducts(@RequestParam(required = false) Integer pageSize,
									   @RequestParam(required = false) Integer pageNumber) {
		if (pageNumber == null) {
			pageNumber = PAGE_NUMBER;
		}
		if (pageSize == null) {
			pageSize = PAGE_SIZE;
		}
		return productService.getAllProducts(pageSize, pageNumber);
	}

	@ApiOperation(value = "根据ID获取产品")
	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public BaseResponse getProductById(@PathVariable Long id) {
		return productService.getProductById(id);
	}

	@ApiOperation(value = "添加产品")
	@RequestMapping(value = "/product", method = RequestMethod.POST)
	@CheckFolderAnnontation(0)
	public ObjectResponse addProduct(@RequestBody @Validated ProductPo productPo) {
		return productService.addProduct(productPo);
	}

	@ApiOperation(value = "修改产品")
	@RequestMapping(value = "/product", method = RequestMethod.PUT)
	@CheckFolderAnnontation(0)
	public BaseResponse updateProduct(@RequestBody @Validated ProductPo productPo) {
		return productService.updateProduct(productPo);
	}

	@ApiOperation(value = "删除产品")
	@RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
	public BaseResponse deleteProduct(@PathVariable Long id) {
		return productService.deleteProduct(id);
	}

	@ApiOperation(value = "通过名称和类型查询产品")
	@RequestMapping(value = "/product/search/condition", method = RequestMethod.GET)
	public BaseResponse getByNameAndType(@RequestParam(required = false) String name,
										 @RequestParam(required = false) String typeName,
										 @RequestParam(required = false) Integer pageSize,
										 @RequestParam(required = false) Integer pageNumber) {
		if (pageNumber == null) {
			pageNumber = PAGE_NUMBER;
		}
		if (pageSize == null) {
			pageSize = PAGE_SIZE;
		}
		return productService.getByNameAndType(name, typeName, pageSize, pageNumber);
	}

	@ApiOperation(value = "获取所有产品入库信息")
	@RequestMapping(value = "/product/input", method = RequestMethod.GET)
	public BaseResponse getAllProductInputs(@RequestParam(required = false) Integer pageSize,
											@RequestParam(required = false) Integer pageNumber) {
		if (pageNumber == null) {
			pageNumber = PAGE_NUMBER;
		}
		if (pageSize == null) {
			pageSize = PAGE_SIZE;
		}
		return productService.getAllProductInputs(pageSize, pageNumber);
	}

	@ApiOperation(value = "通过条件查询产品入库信息")
	@RequestMapping(value = "/product/input/search/condition", method = RequestMethod.GET)
	public BaseResponse getInputByCondition(@RequestParam(required = false) String contractNumber,
											@RequestParam(required = false) String purchaserDeptName,
											@RequestParam(required = false)
												@DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime,
											@RequestParam(required = false)
												@DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime,
											@RequestParam(required = false) Integer pageSize,
											@RequestParam(required = false) Integer pageNumber) {
		if (pageNumber == null) {
			pageNumber = PAGE_NUMBER;
		}
		if (pageSize == null) {
			pageSize = PAGE_SIZE;
		}
		return productService.getInputByCondition(contractNumber, purchaserDeptName, startTime, endTime, pageSize, pageNumber);
	}

	@ApiOperation(value = "根据ID获取产品入库信息")
	@RequestMapping(value = "/product/input/{id}", method = RequestMethod.GET)
	public BaseResponse getProductInputById(@PathVariable Long id) {
		return productService.getProductInputById(id);
	}

	@ApiOperation(value = "产品入库")
	@RequestMapping(value = "/product/input", method = RequestMethod.POST)
	@CheckFolderAnnontation(0)
	public ObjectResponse inputProduct(@RequestBody @Validated InputBillVo inputBillVo) throws Exception{
		return productService.addProductInput(inputBillVo);
	}

	@ApiOperation(value = "删除产品入库信息")
	@RequestMapping(value = "/product/input/{id}", method = RequestMethod.DELETE)
	public BaseResponse deleteProductInput(@PathVariable Long id) throws Exception{
		return productService.deleteProductInput(id);
	}

	@ApiOperation(value = "获取所有产品出库信息")
	@RequestMapping(value = "/product/output", method = RequestMethod.GET)
	public BaseResponse getAllProductOutput(@RequestParam(required = false) Integer pageSize,
											@RequestParam(required = false) Integer pageNumber) {
		if (pageNumber == null) {
			pageNumber = PAGE_NUMBER;
		}
		if (pageSize == null) {
			pageSize = PAGE_SIZE;
		}
		return productService.getAllProductOutput(pageSize, pageNumber);
	}

	@ApiOperation(value = "通过条件查询产品出库信息")
	@RequestMapping(value = "/product/output/search/condition", method = RequestMethod.GET)
	public BaseResponse getOutputByCondition(@RequestParam(required = false) String contractNumber,
											 @RequestParam(required = false) String employeeDeptName,
											 @RequestParam(required = false)
												 @DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime,
											 @RequestParam(required = false)
												 @DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime,
											 @RequestParam(required = false) Integer pageSize,
											 @RequestParam(required = false) Integer pageNumber) {
		if (pageNumber == null) {
			pageNumber = PAGE_NUMBER;
		}
		if (pageSize == null) {
			pageSize = PAGE_SIZE;
		}
		return productService.getOutputByCondition(contractNumber, employeeDeptName, startTime, endTime, pageSize, pageNumber);
	}

	@ApiOperation(value = "根据ID获取产品出库信息")
	@RequestMapping(value = "/product/output/{id}", method = RequestMethod.GET)
	public BaseResponse getProductOutputById(@PathVariable Long id) {
		return productService.getProductOutputById(id);
	}

	@ApiOperation(value = "产品出库")
	@RequestMapping(value = "/product/output", method = RequestMethod.POST)
	@CheckFolderAnnontation(0)
	public BaseResponse outputProduct(@RequestBody @Validated OutputBillVo outputBillVo) throws Exception{
		return productService.addProductOutput(outputBillVo);
	}

	@ApiOperation(value = "删除产品出库信息")
	@RequestMapping(value = "/product/output/{id}", method = RequestMethod.DELETE)
	public BaseResponse deleteProductOutput(@PathVariable Long id) throws Exception{
		return productService.deleteProductOutput(id);
	}

	@ApiOperation(value = "获取产品库存数量")
	@RequestMapping(value = "/product/allStockAmount", method = RequestMethod.GET)
	public BaseResponse getAllProductStockAmount(@RequestParam(required = false) Integer pageSize,
												 @RequestParam(required = false) Integer pageNumber){
		if (pageNumber == null) {
			pageNumber = PAGE_NUMBER;
		}
		if (pageSize == null) {
			pageSize = PAGE_SIZE;
		}
		return productService.getAllProductStockAmount(pageSize,pageNumber);
	}

	@ApiOperation(value = "条件查询获取产品库存数量")
	@RequestMapping(value = "/product/allStockAmount/condition", method = RequestMethod.GET)
	public BaseResponse getAllProductStockAmountByCondition(@RequestParam(value = "name",required = false) String name,
															@RequestParam(value = "typeName",required = false) String typeName,
															@RequestParam(required = false) Integer pageSize,
															@RequestParam(required = false) Integer pageNumber){
		if (pageNumber == null) {
			pageNumber = PAGE_NUMBER;
		}
		if (pageSize == null) {
			pageSize = PAGE_SIZE;
		}
		return productService.getAllProductStockAmountByCondition(name, typeName, pageSize, pageNumber);
	}
}
