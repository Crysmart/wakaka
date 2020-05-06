package com.wakaka.basis.reflect;

import jdk.nashorn.internal.ir.IfNode;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static void main(String[] args) throws ParseException {
        String s = "1111-11-11 11:11:11";
        boolean matches = s.matches("[\\d]{4}-[\\d]{2}-[\\d]{2} [\\d]{2}:[\\d]{2}:[\\d]{2}");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = simpleDateFormat.parse(s);
    }

    /**
     * setBean
     * @param bean
     * @param key
     * @param value
     */
    public static void setProperty(Object bean, String key, String value) {
        Class<?> aClass = bean.getClass();
        try {
            Method getMethod = aClass.getDeclaredMethod("get" + key);
            Class<?> returnType = getMethod.getReturnType();
            Method setMethod = aClass.getMethod("toString");
            if (returnType.equals(String.class)){
                setMethod = aClass.getDeclaredMethod("set" + key,String.class);
                setMethod.invoke(bean, value);
            } else if (returnType.equals(Long.class)){
                setMethod = aClass.getDeclaredMethod("set" + key,Long.class);
                setMethod.invoke(bean, Long.parseLong(value));
            } else if (returnType.equals(Integer.class)){
                setMethod = aClass.getDeclaredMethod("set" + key,Integer.class);
                setMethod.invoke(bean, Integer.parseInt(value));
            } else if (returnType.equals(Date.class)){
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
                if (value.matches("[\\d]{4}-[\\d]{2}-[\\d]{2}")){
                    simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                } else if (value.matches("[\\d]{4}-[\\d]{2}-[\\d]{2} [\\d]{2}-[\\d]{2}-[\\d]{2}")){
                    simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                }
                Date parse = simpleDateFormat.parse(value);
                setMethod = aClass.getDeclaredMethod("set" + key,Date.class);
                setMethod.invoke(bean, parse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建对象并且赋值
     * @param clz
     * @param args
     * @param regex
     * @param <T>
     * @return
     */
    public static <T> T getBean (Class<?> clz,String args,String regex){
        String[] propertyKeyValue = args.split("["+regex+"]");
        Object bean = BeanUtils.createBean(clz);
        for (int i = 0; i < propertyKeyValue.length; i++) {
            String[] keyValue = propertyKeyValue[i].split("[:]");
            String key = BeanUtils.convertBeanPropertyName(keyValue[0]);
            String value = keyValue[1];
            System.out.println("key="+key+" value="+value);
            if (bean != null){
                BeanUtils.setProperty(bean,key,value);
            }
        }
        return (T) bean;
    }


}
