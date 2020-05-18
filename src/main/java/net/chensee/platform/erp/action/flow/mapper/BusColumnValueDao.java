package net.chensee.platform.erp.action.flow.mapper;

import net.chensee.platform.erp.action.flow.po.BusColumnAndValuePo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusColumnValueDao {

    /**
     * 添加审批过程中业务填入的变量与值
     * @param busCode
     * @param busId
     * @param columnAndValue
     * @return
     */
    void addBusColumnsAndValues(String busCode, Long busId, String columnAndValue);

    /**
     * 根据bus信息获取列与值
     * @param busCode
     * @param busId
     * @return
     */
    List<BusColumnAndValuePo> getColumnValueByBusKey(String busCode, Long busId);
}
