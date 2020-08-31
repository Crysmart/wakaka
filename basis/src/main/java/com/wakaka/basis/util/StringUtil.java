package com.wakaka.basis.util;

import java.io.IOException;
import java.util.Date;

public class StringUtil {
    public static void main(String[] args) throws IOException {

    }

    /**
     * 日期和时间的调试
     * @param date
     * @return
     */
    public static String dateFormat(Date date){
        //详见format转换符
        //return String.format("hello %tc", date);
        return String.format("%,.2f", 10000.0/3.0);
    }

    /**
     * 索引区分，'$' 符号必须紧跟%符号后, '%'符号后为参数下标，最少为1
     * @return
     */
    public static String indexFormat(){
        return String.format("%2$s:%1$s", "hello",new Date());
    }


}
