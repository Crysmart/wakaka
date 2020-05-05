package com.wakaka.basis.reflect;

/**
 * 反射创建对象
 *
 * @author Wang.hm
 * @date Created in 15:32 2020/5/5
 */
public class CreateBean {
    public static void main(String[] args) {
        String strBean = "username:admin|password:123|age:18|birthday:1999-09-09";
        String[] propertyKeyValue = strBean.split("[|]");
        for (int i = 0; i < propertyKeyValue.length; i++) {
            String[] keyValue = propertyKeyValue[i].split("[:]");
            String key = BeanUtils.convertBeanPropertyName(keyValue[0]);
            String value = keyValue[1];
            System.out.println("key="+key+" value="+value);
            BeanUtils.createBean(JavaBean.class);
        }
    }



}

