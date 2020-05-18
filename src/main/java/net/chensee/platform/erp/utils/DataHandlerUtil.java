package net.chensee.platform.erp.utils;

import static net.chensee.platform.erp.common.CommonVariables.DECIMAL_ENLARGE_FACTOR;

/**
 * @author : shibo
 * @date : 2019/6/17 10:12
 */
public class DataHandlerUtil {

    public static Integer enlargeFactor(Double d) {
        if(d == null) {
            return null;
        }
        return enlarge(d);
    }

    public static Integer enlarge(double d) {
        float f = (float) d;
        return (int)(f * DECIMAL_ENLARGE_FACTOR);
    }

    public static Double narrowFactor(Double i) {
        if(i == null) {
            return null;
        }
        return i / DECIMAL_ENLARGE_FACTOR;
    }
}
