package com.paicheya.hammer.newtransapp.core;

/**
 * 网络返回基类 支持泛型
 * Created by cly on 17/2/2.
 */

public class BaseResponse <T> {
    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public boolean isOk(){
        if(this.getCode() == 202){
            return  true;
        }
        return false;
    }
}
