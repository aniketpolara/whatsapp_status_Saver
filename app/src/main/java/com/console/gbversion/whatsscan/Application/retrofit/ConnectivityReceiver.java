package com.console.gbversion.whatsscan.Application.retrofit;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.console.gbversion.whatsscan.MyApplication;

/* loaded from: classes2.dex */
public class ConnectivityReceiver extends SbsBroadcastReceiver {
    private static MyApplication application;
    public static ConnectivityReceiverListener connectivityReceiverListener;

    /* loaded from: classes2.dex */
    public interface ConnectivityReceiverListener {
        void onNetworkConnectionChanged(boolean z);
    }

    public ConnectivityReceiver() {
        application = this.application;
    }

    @Override // com.console.gbversion.whatsscan.Application.retrofit.SbsBroadcastReceiver, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        boolean z = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
        ConnectivityReceiverListener connectivityReceiverListener2 = connectivityReceiverListener;
        if (connectivityReceiverListener2 != null) {
            connectivityReceiverListener2.onNetworkConnectionChanged(z);
        }
    }

    public static boolean isConnected() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) application.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
