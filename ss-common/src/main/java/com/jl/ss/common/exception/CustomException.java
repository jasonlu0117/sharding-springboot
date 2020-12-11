package com.jl.ss.common.exception;

/**
 * 自定义异常
 */
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String code = CodeDefined.ERROR.getValue();
    private String msg;

    public CustomException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public CustomException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public CustomException(String msg, String code) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public CustomException(String msg, String code, Throwable e) {
        super(msg, e);
        this.code = code;
        this.msg = msg;
    }

    public CustomException(CodeDefined codeDefined) {
        super(codeDefined.getDesc());
        this.code = codeDefined.getValue();
        this.msg = codeDefined.getDesc();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
