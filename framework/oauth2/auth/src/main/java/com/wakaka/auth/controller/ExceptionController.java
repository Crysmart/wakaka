package com.wakaka.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Crysmart
 * @date 2021/1/1 16:18
 */
@ControllerAdvice
public class ExceptionController {

    /**
     * 兜底异常捕捉
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class,Error.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String exceptionHandler(Exception e){
        e.printStackTrace();
        return e.getMessage();
    }
}
