package com.xufangfang.o2o.dto;

import lombok.Data;

/**
 *  封装json对象，所有返回结果都是用它
 * @param <T>
 */
@Data
public class Result<T> {
    private boolean success;//是否成功标志
    private T data;//成功时返回的数据
    private String errorMsg;//错误信息
    private int errorCode;

    public Result(){

    }

    /**
     * 成功时的构造器
     * @param success
     * @param data
     */
    public Result(boolean success,T data){
          this.success=success;
          this.data=data;
    }

    /**
     * 错误时的构造器
     */
    public Result(boolean success,int errorCode,String errorMsg){
        this.success=success;
        this.errorCode=errorCode;
        this.errorMsg=errorMsg;
    }
}
