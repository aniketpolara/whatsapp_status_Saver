package com.console.gbversion.whatsscan;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.multidex.MultiDex;

public class MyApplication extends Application implements LifecycleObserver, Application.ActivityLifecycleCallbacks {
    private static MyApplication _instance;
    public static Activity currentActivity;

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStopped(Activity activity) {
    }

    /* access modifiers changed from: protected */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onMoveToForeground() {
    }

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public void onCreate() {
        super.onCreate();
//        new AppOpenManager(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        registerActivityLifecycleCallbacks(this);
        MultiDex.install(this);
        _instance = this;
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
        getSharedPreferences("MY_PREFS_NAME", 0).edit();
//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            /* class gbwhats.gbwhatsapp.gblatestversion.Application.Splash.Utils.MyApplication.AnonymousClass1 */
//
//            @Override // com.google.android.gms.ads.initialization.OnInitializationCompleteListener
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });
    }

    public static synchronized MyApplication getInstance() {
        MyApplication myApplication;
        synchronized (MyApplication.class) {
            synchronized (MyApplication.class) {
                myApplication = _instance;
            }
            return myApplication;
        }
    }

    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    public static boolean hasNetwork() {
        return _instance.checkIfHasNetwork();
    }

    public boolean checkIfHasNetwork() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public void onActivityStarted(Activity activity) {
//        if (!FirstSplashActivity.isSplash) {
//            currentActivity = activity;
//        }
    }

    public void onActivityPaused(Activity activity) {
        Activity activity2;
//        if (!FirstSplashActivity.isSplash && (activity2 = currentActivity) != null) {
//            AppOpenManager.AppOpenAdManager.loadAd(activity2);
//        }
    }
}