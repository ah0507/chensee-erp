**根据代码中vo和po以及数据库中的表**
---
#### 1.销售合同（ContractVo）生成出库（OutputBillVo）信息
target业务所需字段|字段注释|source业务包含的字段|审批中填入字段和类型（*为必填）
|:---|:---|:---|:---|
contractId|合同ID|id|-
contractNumber|合同编号|number|-
orderId|订单ID|orderId|-
outputTime|出库日期|-|outputTime-Date
number|出库单编号|规则生成|-
saleManId|销售人员ID|saleManId|-
employeeId|领用人ID|-|employeeId-Long*
employeeDeptId|领用部门ID|-|employeeDeptId-Long*
chargeManId|负责人ID|-|chargeManId-Long*
sumAmount|合计|根据所有出库商品中金额的总和|-
inputIds|关联入库信息的ID集合|-|inputIds-String
deptId|所属部门ID|deptId|-
**ProductOutputVo**|-|**ContractProductVo**
productId|产品ID|productId|-
unit|单位|-|unit-String(填或者通过产品ID查询)
price|单价|-|price-Double*
amount|产品数量|amounts|-
total|总金额|-|total-Double*(根据单价*数量得出)
strategy|产品出库策略|-|strategy-Integer*
remark|备注|-|remark-String
**补充**|**补充**|**补充**|**补充**

---

#### 2.销售合同（ContractVo）生成收款（CollectVo）信息
target业务所需字段|字段注释|source业务包含的字段|审批中填入字段和类型（*为必填）
|:---|:---|:---|:---|
name|收款名称|-|name-String*
number|收款编号|-|number-String*
customerId|客户ID|otherPartyId|-
orderId|订单ID|orderId|-
payeeId|收款人ID|-|payeeId-Long*
contractId|合同ID|id|-
collectTime|收款日期|-|collectTime-Date
amount|收款金额|amount|-
chargeManId|项目负责人ID|-|chargeManId-Long*
collectMethod|付款方式|-|collectMethod-String
isApprove|是否审批|-|isApprove-Integer(1)
**补充**|**补充**|**补充**|**补充**

----


