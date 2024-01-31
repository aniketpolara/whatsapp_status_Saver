package com.console.gbversion.whatsscan.Application.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

/* loaded from: classes2.dex */
public class MyService extends Service {
    Handler mHandler;
    private Runnable myRunnable;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Handler handler = new Handler();
        this.mHandler = handler;
        handler.postDelayed(new Runnable() { // from class: com.console.gbversion.whatsscan.Application.Service.MyService.1
            @Override // java.lang.Runnable
            public void run() {
                MyService myService = MyService.this;
                myService.mHandler.postDelayed(myService.myRunnable, 2000);
            }
        }, 2000);
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.mHandler.removeCallbacks(this.myRunnable);
    }
}
