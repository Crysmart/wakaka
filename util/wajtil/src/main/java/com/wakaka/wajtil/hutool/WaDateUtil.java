package com.wakaka.wajtil.hutool;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author Crysmart
 * @date 2021/4/2 13:41
 */
public class WaDateUtil {
    private static final String LATEST = "23:59:59";
    private static final String EARLIEST = "00:00:00";
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取下一年上个月最后一天的指定时间
     * @param startDate 开始时间
     * @return
     */
    public static Date getBeforeMonthLastDayTime(Date startDate,String format){
        //当前时间发生推移12月(366)
        DateTime nextEventTime = DateUtil.offsetMonth(startDate, 11);
        int field1 = nextEventTime.getField(DateField.DAY_OF_MONTH);
        if (field1 != 1){
            //当前时间发生推移12月(365)
            nextEventTime = DateUtil.offsetDay(nextEventTime.toJdkDate(), -1);
        }
        DateTime dateTime = DateUtil.endOfMonth(nextEventTime.toJdkDate());
        int field = dateTime.getField(DateField.DAY_OF_MONTH);
        String formatTime = DateUtil.format(nextEventTime.toJdkDate(), "yyyy-MM");
        String assemblyDateStr = formatTime+"-"+field+" "+ LATEST;
        DateTime parseDate = DateUtil.parse(assemblyDateStr, format);
        return parseDate.toJdkDate();
    }

}
