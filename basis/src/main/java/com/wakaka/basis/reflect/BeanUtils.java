package com.wakaka.basis.reflect;

/**
 * 转换bean工具
 *
 * @author Wang.hm
 * @date Created in 17:26 2020/5/5
 */
public class BeanUtils {

    /**
     * 转换首字母大写
     * @param propertyName
     * @return
     */
    public static String convertBeanPropertyName(String propertyName){
        if (propertyName == null || "".equals(propertyName)){
            throw new NullPointerException("对象为空");
        }
        String substring = propertyName.substring(0, 1);
        String headWord = substring.toUpperCase();
        String otherWord = propertyName.substring(1);
        return headWord+otherWord;
    }

    public static <T> T createBean(Class<?> clz){
        try {
            return (T) clz.getDeclaredConstructor().newInstance();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String qwe = convertBeanPropertyName("123q");
    }
}
