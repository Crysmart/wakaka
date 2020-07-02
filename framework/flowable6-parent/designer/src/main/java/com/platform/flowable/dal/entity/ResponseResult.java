package com.platform.flowable.dal.entity;

/**
 * 相应结果
 * @author wang.hm
 * @date 2020/3/9 10:56
 */
public class ResponseResult {
    /** 响应代码 */
    private Integer code;
    /** 响应信息 */
    private String message;
    /** 响应对象 */
    private Object object;
    public ResponseResult(){}
    public ResponseResult(Integer code,String message,Object object){
        this.code = code;
        this.message = message;
        this.object = object;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
