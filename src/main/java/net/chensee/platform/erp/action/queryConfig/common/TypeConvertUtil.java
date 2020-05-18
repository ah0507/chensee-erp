package net.chensee.platform.erp.action.queryConfig.common;

import net.chensee.platform.erp.utils.DateUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author ah
 * @title: TypeConvertUtil
 * @date 2019/11/22 14:28
 */
public class TypeConvertUtil {

    /**
     * 根据类型字符串转化java类型
     * @param type
     * @param opr
     * @param value
     * @return
     */
    public static Object convertType(String type,String opr, String value) {
        Object returnVal;
        switch (type) {
            case "int":
                returnVal = getIntVal(opr, value);
                break;
            case "date":
                returnVal = getDateVal(opr, value);
                break;
            default:
                returnVal = getStrVal(opr, value);
                break;
        }
        return returnVal;
    }

    private static Object getDateVal(String opr, String value) {
        Object returnVal;
        if ("in".equals(opr)) {
            List<Date> list = new ArrayList<>();
            for (String str : value.split(",")) {
                list.add(DateUtil.convertStrToDate(str));
            }
            returnVal = list;
        } else {
            returnVal = DateUtil.convertStrToDate(value);
        }
        return returnVal;
    }

    private static Object getIntVal(String opr, String value) {
        Object returnVal;
        if ("in".equals(opr)) {
            List<Integer> list = new ArrayList<>();
            for (String str : value.split(",")) {
                list.add(Integer.parseInt(str));
            }
            returnVal = list;
        } else {
            returnVal = Integer.parseInt(value);
        }
        return returnVal;
    }

    private static Object getStrVal(String opr, String value) {
        Object returnVal;
        if ("in".equals(opr)) {
            returnVal = Arrays.asList(value.split(","));
        } else {
            returnVal = value;
        }
        return returnVal;
    }

    /**
     * 针对特殊情况 like %value
     * @param value
     * @param opr
     * @return
     */
    public static Object convertLikeQueryValue(String opr, Object value) {
        return "like".equals(opr) ? value + "%" : value;
    }
}
