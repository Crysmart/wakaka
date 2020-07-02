package com.szb.platform.workflow.engine.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 转换占位符
 *
 * @author Wang.hm
 * @date Created in 21:23 2020/2/9
 */
public class StringTempUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(StringTempUtil.class);

    /**
     * 占位前缀: "${"
     */
    public static final String PLACEHOLDER_PREFIX = "${";
    /**
     * 占位后缀: "}"
     */
    public static final String PLACEHOLDER_SUFFIX = "}";

    /**
     * text ${占位符} 替换
     * @param text
     * @param parameter
     * @return
     */
    public static String resolvePlaceholders(String text, Map<String, Object> parameter) {

        if (parameter == null || parameter.isEmpty()) {
            return text;
        }
        StringBuffer buf = new StringBuffer(text);
        //找到前缀“${”作为起始位置
        int startIndex = buf.indexOf(PLACEHOLDER_PREFIX);
        //如果存在前缀“${”
        while (startIndex != -1) {
            //确定对应后缀"}"的结束位置
            int endIndex = buf.indexOf(PLACEHOLDER_SUFFIX, startIndex + PLACEHOLDER_PREFIX.length());
            //如果存在后缀"}"
            if (endIndex != -1) {
                //截取${}里面的key
                String placeholder = buf.substring(startIndex + PLACEHOLDER_PREFIX.length(), endIndex);
                //确定下一个查询的开始位置
                int nextIndex = endIndex + PLACEHOLDER_SUFFIX.length();
                try {
                    Object o = parameter.get(placeholder);
                    if (o != null) {
                        //从map中获取key的值
                        String propVal;
                        if (!(o instanceof String)) {
                            propVal = o.toString();
                        } else{
                            propVal = (String) o;
                        }

                        //通过map对应的值替代${}以及里面的值
                        buf.replace(startIndex, endIndex + PLACEHOLDER_SUFFIX.length(), propVal);
                        //确定替换后的下一个查询起始位置
                        nextIndex = startIndex + propVal.length();
                    } else {
                        LOGGER.warn("parameter参数传递失败: '" + placeholder + "' in [" + text + "] ");
                    }
                } catch (Exception ex) {
                    LOGGER.warn("占位解析异常: '" + placeholder + "' in [" + text + "]: " + ex);
                }
                //将开始位置移动到替换后的查询位置
                startIndex = buf.indexOf(PLACEHOLDER_PREFIX, nextIndex);
            } else {
                startIndex = -1;
            }
        }
        //返回替换后的结果
        return buf.toString();
    }
}
