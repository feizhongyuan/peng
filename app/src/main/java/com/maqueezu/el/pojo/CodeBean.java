package com.maqueezu.el.pojo;

/**
 * Created by fei .
 * Created by Date 2019/6/4 15:21
 */

public class CodeBean {

    /**
     * result : 0
     * message : 您操作过快，请稍后重试！
     * data : null
     */

    private int result;
    private String message;
    private Object data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CodeBean{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
