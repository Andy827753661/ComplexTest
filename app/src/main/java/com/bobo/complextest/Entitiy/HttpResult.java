
package com.bobo.complextest.Entitiy;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/11/14.
 */

public class HttpResult<T> {
    public String requestTime;

    public int code;

    @SerializedName("data")
    public T mData;

    public HttpResult(String requestTime, int code, T data) {
        this.requestTime = requestTime;
        this.code = code;
        this.mData = data;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return mData;
    }

    public void setData(T mData) {
        this.mData = mData;
    }
}
