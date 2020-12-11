package com.jl.ss.common.exception;


public enum CodeDefined {

    SUCCESS("200", "成功"),
    ERROR("9999", "系统异常,请稍后再试"),

    ERROR_PARAMETER("-1", "参数错误"),
    ERROR_SYNTAX("2201", "请求语法错误");

    /***************************************************************************/

    private String code;

    private String msg;

    CodeDefined(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getValue() {
        return this.code;
    }

    public String getDesc() {
        return this.msg;
    }

}


