package net.chensee.platform.erp.action.busFLowState.event.impl;

import lombok.extern.slf4j.Slf4j;
import net.chensee.base.utils.JsonUtil;
import net.chensee.platform.erp.action.busFLowState.event.EventHandler;
import net.chensee.platform.erp.action.contract.service.ContractService;
import net.chensee.platform.erp.action.contract.vo.ContractPayStageVo;
import net.chensee.platform.erp.action.contract.vo.ContractProductVo;
import net.chensee.platform.erp.action.contract.vo.ContractVo;
import net.chensee.platform.erp.action.finance.po.CollectPo;
import net.chensee.platform.erp.action.finance.service.CollectService;
import net.chensee.platform.erp.action.finance.vo.CollectVo;
import net.chensee.platform.erp.action.flow.mapper.BusColumnValueDao;
import net.chensee.platform.erp.action.flow.po.BusColumnAndValuePo;
import net.chensee.platform.erp.action.product.service.ProductService;
import net.chensee.platform.erp.action.product.vo.OutputBillVo;
import net.chensee.platform.erp.action.product.vo.ProductOutputVo;
import net.chensee.platform.erp.action.product.vo.ProductVo;
import net.chensee.platform.erp.utils.DataHandlerUtil;
import net.sf.json.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author wh
 * @version 1.0
 * @date 2020/4/1 14:36
 */
@Slf4j
@Component
public class SaleContractFlowEndEventHandler implements EventHandler {

    private final String SALE_CONTRACT = "saleContract";
    private final Integer DIRECT = 1;
    private final String STATUS_DONE = "done";

    private final String EMPLOYEE_ID = "employeeId";
    private final String EMPLOYEE_DEPT_ID = "employeeDeptId";
    private final String CHARGE_MAN_ID = "chargeManId";
    private final String OUTPUT_TIME = "outputTime";
    //private final String INPUT_IDS = "inputIds";
    private final String PRICE = "price";
    private final String TOTAL = "total";
    private final String STRATEGY = "strategy";
    private final String REMARK = "remark";

    private final String NAME = "name";
    private final String NUMBER = "number";
    private final String PAYEE_ID = "payeeId";
    private final String COLLECT_TIME = "collectTime";
    private final String COLLECT_METHOD = "collectMethod";
    private final String IS_APPROVE = "isApprove";


    @Autowired
    private ContractService contractService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CollectService collectService;
    @Autowired
    private BusColumnValueDao busColumnValueDao;

    @Override
    public void doHandler(HttpServletRequest request) {
        Long employeeId = -1L;
        Long employeeDeptId = -1L;
        Long chargeManId = -1L;
        Date outputTime = null;
        Double price = -1D;
        Double total = -1D;
        Integer strategy = -1;
        String remark = "";

        String name = "";
        String number = "";
        Long payeeId = -1L;
        Date collectTime = null;
        String collectMethod = "";
        Integer isApprove = 1;


        //获取审批中填入的关联变量
        List<BusColumnAndValuePo> columnValueByBusKey =
                busColumnValueDao.getColumnValueByBusKey(getBusCode(request), Long.parseLong(getBusId(request)));
        if (columnValueByBusKey != null && columnValueByBusKey.size() != 0) {
            for (BusColumnAndValuePo bcavp : columnValueByBusKey) {
                String columnAndValue = bcavp.getColumnAndValue();
                List<String> columnAndValueList = JsonUtil.toList(columnAndValue, String.class);
                for (String cav : columnAndValueList) {
                    Map map = JsonUtil.toObj(cav, Map.class);
                    log.debug("========"+map);
                    String column = (String) map.get("column");
                    switch (column) {
                        case EMPLOYEE_ID:
                            if ("Long".equals((String) map.get("dataType"))) {
                                employeeId = Long.valueOf((String) map.get("value"));
                            }
                            break;
                        case EMPLOYEE_DEPT_ID:
                            if ("Long".equals((String) map.get("dataType"))) {
                                employeeDeptId = Long.valueOf((String) map.get("value"));
                            }
                            break;
                        case CHARGE_MAN_ID:
                            if ("Long".equals((String) map.get("dataType"))) {
                                chargeManId = Long.valueOf((String) map.get("value"));
                            }
                            break;
                        case OUTPUT_TIME:
                            if ("Date".equals((String) map.get("dataType"))) {
                                outputTime = new Date((String) map.get("value"));
                            }
                            break;
                        case PRICE:
                            if ("Double".equals((String) map.get("dataType"))) {
                                price = Double.valueOf((String) map.get("value"));
                            }
                            break;
                        case TOTAL:
                            if ("Double".equals((String) map.get("dataType"))) {
                                total = Double.valueOf((String) map.get("value"));
                            }
                            break;
                        case STRATEGY:
                            if ("Integer".equals((String) map.get("dataType"))) {
                                strategy = Integer.valueOf((String) map.get("value"));
                            }
                            break;
                        case REMARK:
                            if ("String".equals((String) map.get("dataType"))) {
                                remark = (String) map.get("value");
                            }
                            break;
                        case NAME:
                            if ("String".equals((String) map.get("dataType"))) {
                                name = (String) map.get("value");
                            }
                            break;
                        case NUMBER:
                            if ("String".equals((String) map.get("dataType"))) {
                                number = (String) map.get("value");
                            }
                            break;
                        case PAYEE_ID:
                            if ("String".equals((String) map.get("dataType"))) {
                                payeeId = Long.valueOf((String) map.get("value"));
                            }
                            break;
                        case COLLECT_TIME:
                            if ("String".equals((String) map.get("dataType"))) {
                                collectTime = new Date((String) map.get("value"));
                            }
                            break;
                        case COLLECT_METHOD:
                            if ("String".equals((String) map.get("dataType"))) {
                                collectMethod = (String) map.get("value");
                            }
                            break;
                        case IS_APPROVE:
                            if ("String".equals((String) map.get("dataType"))) {
                                isApprove = Integer.valueOf((String) map.get("value"));
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        //1、更改合同状态为  已完成
        ContractVo contractVo =
                (ContractVo)contractService.getById(DIRECT, Long.valueOf(getBusId(request))).getData();
        if (contractVo == null) {
            log.debug("当前"+SALE_CONTRACT+"-"+getBusId(request)+"未找到对应的数据");
            return;
        }
        if (!STATUS_DONE.equals(contractVo.getApprovalStatus())) {
            contractVo.setApprovalStatus(STATUS_DONE);
            contractService.updateContract(contractVo);
        }
        //2、根据采购/销售 变更产品的库存（暂时忽略库存负数情况）
        List<ContractProductVo> productVos = contractVo.getProducts();
        if (productVos != null && productVos.size() != 0) {
            OutputBillVo outputBillVo = new OutputBillVo();
            outputBillVo = initOutputBillVo(contractVo, outputBillVo);
            Integer sum = 0;
            List<ProductOutputVo> productOutputVos = new ArrayList<>();
            ProductOutputVo productOutputVo = new ProductOutputVo();
            for (ContractProductVo contractProductVo : productVos) {
                //TODO   查询表中取出业务关联的 变量  填入
                //例如领用人、领用部门、负责人、关联入库信息的ID集合 【没有设置】
                sum += contractProductVo.getAmounts();
                //根据contractProduct生成productOutput
                productOutputVo = initProductOutput(productOutputVo, contractProductVo);
                //TODO 后续改成合同中获取
                productOutputVo.setPrice(price);
                productOutputVo.setTotal(total);
                productOutputVo.setStrategy(strategy);

                productOutputVos.add(productOutputVo);
            }
            outputBillVo.setEmployeeId(employeeId);
            outputBillVo.setEmployeeDeptId(employeeDeptId);
            outputBillVo.setChargeManId(chargeManId);
            outputBillVo.setOutputTime(outputTime);

            outputBillVo.setSumAmount(Double.valueOf(sum));
            outputBillVo.setOutputVos(productOutputVos);
            //TODO 传入
            outputBillVo.setFolderId(contractVo.getFolderId());
            productService.addProductOutput(outputBillVo);
            log.debug("已根据销售合同进行出库操作"+outputBillVo);
        }
        //3、根据采购/销售  添加 收/付款 记录表
        List<ContractPayStageVo> payStageVos = contractVo.getPayMethods();
        if (payStageVos != null && payStageVos.size() != 0) {
            CollectPo collectPo = new CollectPo();
            //收款名称、收款编号、收款人ID/名称、付款方式、票据ID/编号、billTime
            collectPo = initCollect(contractVo, collectPo);
            for (ContractPayStageVo contractPayStageVo : payStageVos) {
                collectPo.setAmount(DataHandlerUtil.enlargeFactor(contractPayStageVo.getAmount()));
                //set 审批中填入的字段
                collectPo.setName(name);
                collectPo.setNumber(number);
                collectPo.setPayeeId(payeeId);
                collectPo.setCollectTime(collectTime);
                collectPo.setChargeManId(chargeManId);
                collectPo.setCollectMethod(collectMethod);
                collectPo.setIsApprove(isApprove);

                collectService.addCollect(collectPo);
                log.debug("已根据销售合同发起收款单"+collectPo);
            }
        }
    }

    private CollectPo initCollect(ContractVo contractVo, CollectPo collectPo) {
        collectPo.setNumber(UUID.randomUUID().toString().replace("-",""));
        collectPo.setCustomerId(contractVo.getOtherPartyId());
        collectPo.setOrderId(contractVo.getOrderId());
        collectPo.setContractId(contractVo.getId());
        collectPo.setAmount(DataHandlerUtil.enlargeFactor(contractVo.getAmount()));
        collectPo.setCollectTime(contractVo.getContractTime());
        collectPo.setChargeManId(contractVo.getSaleManId());
        //备注 暂时set
        collectPo.setRemark(contractVo.getRemark());
        collectPo.setDeptId(contractVo.getDeptId());
        return collectPo;
    }

    private ProductOutputVo initProductOutput(ProductOutputVo productOutputVo, ContractProductVo contractProductVo) {
        ProductVo productVo =
                (ProductVo) productService.getProductById(contractProductVo.getProductId()).getData();
        productOutputVo.setProductId(productVo.getId());
        productOutputVo.setProductName(productVo.getName());
        productOutputVo.setProductTypeId(productVo.getTypeId());
        productOutputVo.setProductTypeName(productVo.getTypeName());
        productOutputVo.setProductModel(productVo.getModelName());
        productOutputVo.setUnit(productVo.getUnitName());
        productOutputVo.setAmount(contractProductVo.getAmounts());
        productOutputVo.setDeptId(productVo.getDeptId());
        //单价、总金额、备注、出库策略  【没有设置】
        return productOutputVo;
    }

    private OutputBillVo initOutputBillVo(ContractVo contractVo, OutputBillVo outputBillVo) {
        outputBillVo.setContractId(contractVo.getId());
        outputBillVo.setContractNumber(contractVo.getNumber());
        outputBillVo.setOrderId(contractVo.getOrderId());
        outputBillVo.setOutputTime(new Date());
        outputBillVo.setNumber(UUID.randomUUID().toString().replace("-",""));
        outputBillVo.setSaleManId(contractVo.getSaleManId());
        outputBillVo.setSaleManName(contractVo.getSaleManName());
        outputBillVo.setDeptId(contractVo.getDeptId());
        return outputBillVo;
    }

    @Override
    public boolean canHandler(HttpServletRequest request) {
        //判断业务类型
        String taskType = getTaskType(request);
        String busCode = getBusCode(request);
        return END.equals(taskType) && SALE_CONTRACT.equals(busCode);
    }
}
