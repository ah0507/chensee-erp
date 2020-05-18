package net.chensee.platform.erp.action.order.service.impl;

import net.chensee.base.common.po.BaseInfoPo;
import net.chensee.base.utils.CreateAndUpdateInfoUtil;
import net.chensee.platform.erp.action.order.mapper.OrderContractDao;
import net.chensee.platform.erp.action.order.po.OrderContractPo;
import net.chensee.platform.erp.action.order.service.OrderContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author : shibo
 * @date : 2019/5/20 17:02
 */
@Service
@Transactional
public class OrderContractServiceImpl implements OrderContractService {
    private static final Logger logger = LoggerFactory.getLogger(OrderContractPo.class);

    @Resource
    private OrderContractDao orderContractDao;

    @Override
    public Long addOrderContract(OrderContractPo orderContractPo) {
        try {
            CreateAndUpdateInfoUtil.addCreateInfo(orderContractPo);
            CreateAndUpdateInfoUtil.addUpdateInfo(orderContractPo);
            orderContractPo.setStatus(BaseInfoPo.Status_Able);
            orderContractDao.addOrderContract(orderContractPo);
            return orderContractPo.getId();
        } catch (Exception e) {
            logger.error("添加订单可生成合同出现异常", e);
        }
        return 0L;
    }

    @Override
    public void updateOrderContract(OrderContractPo orderContractPo) {
        try {
            CreateAndUpdateInfoUtil.addUpdateInfo(orderContractPo);
            orderContractDao.updateOrderContract(orderContractPo);
        } catch (Exception e) {
            logger.error("修改订单可生成合同出现异常", e);
        }
    }

    @Override
    public void deleteOrderContract(Long id) {
        try {
            orderContractDao.deleteOrderContract(id);
        } catch (Exception e) {
            logger.error("删除订单可生成合同出现异常");
        }
    }
}
