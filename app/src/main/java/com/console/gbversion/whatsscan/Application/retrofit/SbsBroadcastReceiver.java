package com.console.gbversion.whatsscan.Application.retrofit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.console.gbversion.whatsscan.MyApplication;

/* compiled from: ConnectivityReceiver.java */
/* loaded from: classes2.dex */
class SbsBroadcastReceiver extends BroadcastReceiver {
    protected MyApplication application;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            this.application = (MyApplication) context.getApplicationContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
