package com.console.gbversion.whatsscan.Application.retrofit;

/* loaded from: classes2.dex */
public interface IActivityCommunication {
    void ApiResult(BaseResponse baseResponse, String str);

    void callApiResult(BaseResponse baseResponse);

    void onApiErrorResult(String str);
}
