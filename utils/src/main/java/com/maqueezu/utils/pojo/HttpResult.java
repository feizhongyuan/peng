package com.maqueezu.utils.pojo;

/**
 * Created by admin on 2019/3/20 0020.
 *
 * 这个类主要用来再统一的callback中处理不同服务器响应的 code message 赋值
 *
 * 其他的服务器响应model都必须要实现这个接口
 *
 */

public interface HttpResult {

    /**
     * 设置api响应消息
     * @param message
     */
    void setSuperResultMessage(String message);

    /**
     * 设置api响应code
     * @param code
     */
    void setSuperResultCode(String code);
}
