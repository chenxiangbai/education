package com.education.bean;

/**
 * @author admin
 * @version 1.0
 * @date 2020-06-03 15:02
 * @description 错误码
 */
@SuppressWarnings("ALL")
public enum ResultCode {
    /**
     * 成功标志
     */
    SUCCESS("success", "成功"),

    /**
     * 错误标志
     */
    ERROR("error", "错误"),

    /**
     * 异常标志
     */
    EXCEPTION("exception", "异常"),

    /**
     * 警告标志
     */
    WARNING("warning", "警告"),

    /**
     * 提示标志
     */
    INFO("info", "提示"),

    /**
     * 登录失败标志
     */
    NOT_LOGIN("not_login", "登录失败"),

    /**
     * 不能为空标志
     */
    NOT_LOGIN_CODE_IS_NULL("not_login_code_is_null","login_code 不能为空"),

    /**
     * 不能为空标志
     */
    NOT_PASSWORD_IS_NULL("not_password_is_null","password 不能为空"),

    /**
     * 错误标志
     */
    ERROR_PASSWORD("error_password","password 错误"),

    /**
     * 错误标志
     */
    ERROR_LOGIN_CODE("error_login_code","账号 错误"),

    /**
     * 注册失败标志
     */
    ERROR_REGISTER("error_register","注册失败"),

    /**
     * 错误标志
     */
    ERROR_LOGIN_CODE_REPEAT("error_login_code_repeat","login_code重复"),

    /**
     * 作为网关或者代理工作的服务器尝试执行请求时，从上游服务器接收到无效的响应。
     */
    ERROR_BAD_GATEWAY("error_Bad_Gateway_502","作为网关或者代理工作的服务器尝试执行请求时，从上游服务器接收到无效的响应。");

    private String code;
    private String msg;

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Result result(){
        return new Result(this.code,this.msg);
    }

    public Result result(String msg){
        return new Result(this.code,msg);
    }


}
