package com.education.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author admin
 * @version 1.0
 * @date 2020-06-03 15:00
 * @description 统一返回
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {
    private String code;
    private String msg;
    private String exception;
    private T data;

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(T t){
        this(ResultCode.SUCCESS);
        this.data = t;
    }
    public Result(ResultCode resultCode, String msg) {
        this(resultCode.getCode(),msg);
    }

    public Result(ResultCode resultCode) {
        this(resultCode.getCode(),resultCode.getMsg());
    }

    public Result(T data, ResultCode resultCode) {
        this(resultCode);
        this.data = data;
    }

    public Result(T data,Boolean bool){
        this(bool);
        this.data = data;
    }

    public Result(T data, Boolean bool, ResultCode resultCode){
        this(resultCode);
        if(bool){
            this.code = ResultCode.SUCCESS.getCode();
            this.msg = ResultCode.SUCCESS.getMsg();
        }
        this.data = data;
    }

    public Result(Boolean bool){
        if(bool){
            this.code = ResultCode.SUCCESS.getCode();
            this.msg = ResultCode.SUCCESS.getMsg();
        }else {
            this.code = ResultCode.ERROR.getCode();
            this.msg = ResultCode.ERROR.getMsg();
        }
    }
}
