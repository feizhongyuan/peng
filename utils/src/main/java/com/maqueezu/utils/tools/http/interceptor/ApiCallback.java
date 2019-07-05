package com.maqueezu.utils.tools.http.interceptor;

import android.text.TextUtils;

import com.maqueezu.utils.pojo.HttpResult;
import com.maqueezu.utils.tools.LogUtil;
import com.maqueezu.utils.tools.http.HttpCodeMessage;
import com.maqueezu.utils.tools.http.OnResultListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 这个类中统一处理 api 接口返回响应的处理
 * Created by xianlai on 2017/1/13 0013.
 */
public class ApiCallback<T extends HttpResult> implements Callback<T> {
    private OnResultListener<T> listener;
    private Class<T> clz;
    private String requestUrl;

    public ApiCallback(Call<T> call, Class<T> clz, OnResultListener<T> listener) {
        this.listener = listener;
        this.clz = clz;
    }

    /**
     * Invoked for a received HTTP response.
     * <p/>
     * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
     * Call {@link Response#isSuccessful()} to determine if the response indicates success.
     *
     * @param call
     * @param response
     */
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        T t = null;
        if (response != null) {
          t = response.body();
        }

        if (t == null) {
            t = createObj(response);
            if (listener != null) {
                listener.onResult(t);
            }
        } else if (response.isSuccessful()) {
            if (listener != null) {
                listener.onResult(t);
            }
        }
    }

    /**
     * Invoked when a network exception occurred talking to the server or when an unexpected
     * exception occurred creating the request or processing the response.
     *
     * @param call
     * @param t
     */
    @Override
    public void onFailure(Call<T> call, Throwable t) {
        T body = createObj(null);
        String message = HttpCodeMessage.exceptionToMessage(t);
        if (!TextUtils.isEmpty(message)) {
            body.setSuperResultMessage(message);
        }
        if (listener != null) {
            listener.onResult(body);
        }
        LogUtil.print(t.getLocalizedMessage() + "  " + message);
    }

    /**
     * 根据字节码创建出对象
     *
     * @return
     */
    private T createObj(Response<T> response) {
        T obj = null;
        try {
            obj = clz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (response != null) {
            int code = response.code();
            String message = new HttpCodeMessage().codeHandle(code);
            obj.setSuperResultMessage(message);
            obj.setSuperResultCode(code + "");
        }
        return obj;
    }
}
