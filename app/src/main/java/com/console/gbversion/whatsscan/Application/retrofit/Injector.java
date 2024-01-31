package com.console.gbversion.whatsscan.Application.retrofit;

import android.util.Log;
import com.console.gbversion.whatsscan.MyApplication;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/* loaded from: classes2.dex */
public class Injector {
    public static final String CACHE_CONTROL = "Cache-Control";
    public static final int CACHE_SIZE = 31457280;
    public static final int MAX_AGE = 0;
    public static final int MAX_STALE = 7;

    public static Retrofit provideRetrofit(String str) {
        return new Retrofit.Builder().baseUrl(str).client(provideOkHttpClient()).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder().addInterceptor(provideHttpLoggingInterceptor()).addInterceptor(provideOfflineCacheInterceptor()).addNetworkInterceptor(provideCacheInterceptor()).cache(provideCache()).build();
    }

    public static Cache provideCache() {
        try {
            return new Cache(new File(MyApplication.getInstance().getCacheDir(), "http-cache"), 31457280);
        } catch (Exception unused) {
            Log.e("Injector", "Could not create Cache!");
            return null;
        }
    }

    public static Interceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() { // from class: com.console.gbversion.whatsscan.Application.retrofit.Injector.1
            @Override // okhttp3.logging.HttpLoggingInterceptor.Logger
            public void log(String str) {
                Log.e("Injector", str);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        return httpLoggingInterceptor;
    }

    public static Interceptor provideCacheInterceptor() {
        return new Interceptor() { // from class: com.console.gbversion.whatsscan.Application.retrofit.Injector.2
            @Override // okhttp3.Interceptor
            public Response intercept(Interceptor.Chain chain) throws IOException {
                return chain.proceed(chain.request()).newBuilder().removeHeader("Pragma").removeHeader(Injector.CACHE_CONTROL).header(Injector.CACHE_CONTROL, new CacheControl.Builder().maxAge(0, TimeUnit.MINUTES).build().toString()).build();
            }
        };
    }

    public static Interceptor provideOfflineCacheInterceptor() {
        return new Interceptor() { // from class: com.console.gbversion.whatsscan.Application.retrofit.Injector.3
            @Override // okhttp3.Interceptor
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request request = chain.request();
                if (!MyApplication.hasNetwork()) {
                    request = request.newBuilder().removeHeader("Pragma").removeHeader(Injector.CACHE_CONTROL).cacheControl(new CacheControl.Builder().maxStale(7, TimeUnit.DAYS).build()).build();
                }
                return chain.proceed(request);
            }
        };
    }
}
