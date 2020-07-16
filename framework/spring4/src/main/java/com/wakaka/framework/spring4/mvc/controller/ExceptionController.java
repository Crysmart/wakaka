package com.wakaka.framework.spring4.mvc.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 异常控制器
 * @author Crysmart
 * @date 2020/7/16 16:02
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = {})
    public void function(){

    }
}
