package com.maqueezu.el.pojo;

import com.google.gson.annotations.SerializedName;
import com.maqueezu.utils.pojo.HttpResult;

import java.io.Serializable;

/**
 *  def api 响应model  可根据实际需要进行修改
 * @param <T>
 */
public class DefApiModel<T> implements Serializable,HttpResult {
    /**
     * 请求成功api返回的code
     */
    public static final int successCode = 1;


    @SerializedName(value = "ResultCode", alternate = {"code"})
    private int ResultCode = -1;
    @SerializedName(value = "Message", alternate = {"message","ResultMsg"})
    private String Message = "操作失败，请检查您的网络";
    @SerializedName(value = "TotalCount", alternate = {"count"})
    private int TotalCount;
    private String Code;
    @SerializedName(value = "CMS", alternate = {"ResultData","ads"})
    private T ResultData;

    public T getResultData() {
        return ResultData;
    }

    public void setResultData(T resultData) {
        ResultData = resultData;
    }

    public int getResultCode() {
        return ResultCode;
    }

    public void setResultCode(int resultCode) {
        ResultCode = resultCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(int totalCount) {
        TotalCount = totalCount;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    @Override
    public String toString() {
        return "DefApiModel{" +
                "ResultCode=" + ResultCode +
                ", Message='" + Message + '\'' +
                ", TotalCount=" + TotalCount +
                ", Code='" + Code + '\'' +
                ", CMS=" + ResultData +
                '}';
    }

    @Override
    public void setSuperResultMessage(String message) {
        setMessage(message);
    }

    @Override
    public void setSuperResultCode(String code) {
        try{
            setResultCode(Integer.parseInt(code));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
