package com.console.gbversion.whatsscan.Application.retrofit;

import okhttp3.ResponseBody;

/* loaded from: classes2.dex */
public class BaseResponse<T> {
    public T data;
    public ResponseBody responseBody;
    public String statusCode;

    public ResponseBody getResponseBody() {
        return this.responseBody;
    }

    public void setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String str) {
        this.statusCode = str;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T t) {
        this.data = t;
    }
}
