package com.pfpj.vo;

import java.io.Serializable;

public class ResultStatus implements Serializable {

    public static ResultStatus resultStatus= new ResultStatus();
    private Integer code;
    private String msg;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResultStatus{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    public static ResultStatus resultOk(){
        resultStatus.setCode(0);
        resultStatus.setMsg("OK");
        resultStatus.setData(null);
        return resultStatus;
    }
    public static ResultStatus resultOk(Object data){
        resultStatus.setCode(0);
        resultStatus.setMsg("OK");
        resultStatus.setData(data);
        return resultStatus;
    }
    public static ResultStatus resultFailed(Integer code, String msg){
        resultStatus.setCode(code);
        resultStatus.setMsg(msg);
        return resultStatus;
    }
}
