package com.console.gbversion.whatsscan.Application.Utils;

import com.console.gbversion.whatsscan.Application.Model.IdsModel;
import retrofit2.Call;
import retrofit2.http.GET;

/* loaded from: classes2.dex */
public interface ApiCall {
    @GET("/Gbwhat/gbwhat_api.php")
    Call<IdsModel> getAllValues();
}
