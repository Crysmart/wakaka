package com.wakaka.basis.reflect;

/**
 * 反射创建对象
 *
 * @author Wang.hm
 * @date Created in 15:32 2020/5/5
 */
public class CreateBean {
    public static void main(String[] args) {
        String strBean = "username:admin|password:123|age:18|birthday:1999-09-09 12-12-12";
        JavaBean bean = BeanUtils.getBean(JavaBean.class, strBean, "|");
        System.out.println(bean.toString());
    }



}

