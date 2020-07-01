package com.wakaka.framework.spring4.aop;

import com.wakaka.framework.spring4.JavaBean;
import org.springframework.stereotype.Component;

/**
 * @author Crysmart
 * @date 2020/7/1 15:01
 */
@Component
public class MainDemo {
    public void function(){
        System.out.println("进无参方法了");
    }
    public void function(int count){
        System.out.printf("进有参方法了%d\n",count);
    }
    public void function(JavaBean javaBean){
        System.out.println("进JavaBean有参方法了"+javaBean.toString());
    }
}
