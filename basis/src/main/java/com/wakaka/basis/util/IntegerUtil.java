package com.wakaka.basis.util;

/**
 * @author Crysmart
 * @date 2020/8/31 17:49
 */
public class IntegerUtil {

    /**
     * 判断Integer类型是否为空
     * @param integer
     * @return
     */
    public static boolean isBlank(Integer integer) {
        if (integer == null || integer == 0){
            return true;
        }
        return false;
    }
}
