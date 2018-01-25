package com.icexxx.icewx.util;

/**
 * icewx &nbsp; 返回消息体
 * 
 * @author IceWater
 * @version 1.0.0
 */
public class Result {
    private String errcode;
    private String errmsg;

    public Result() {

    }

    public Result(String errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @Override
    public String toString() {
        return "Result [errcode=" + errcode + ", errmsg=" + errmsg + "]";
    }
}
