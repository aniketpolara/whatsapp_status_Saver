package com.console.gbversion.whatsscan.Application.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationManagerCompat;

import com.console.gbversion.whatsscan.Application.Fragment.ChatFragment;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.R;

import java.text.DecimalFormat;

/* loaded from: classes2.dex */
public class Message_Recover_Activity extends AppCompatActivity {
    private NetworkChangeReceiver brd;
    private boolean check;
    Toolbar toolbar;

    @Override
    // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_message_recovery);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, new ChatFragment(), ChatFragment.class.getSimpleName()).addToBackStack(null).commit();
        double length = (double) getDatabasePath("deletemesseges").length();
        Double.isNaN(length);
        Double.isNaN(length);
        double d = (length * 1.0d) / 1024.0d;
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        decimalFormat.format(d);
        if (d > 900.0d) {
            decimalFormat.format(d / 1000.0d);
        }
        if (!Constant.isOnline(this)) {
            brodCarst(this);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        if (!checkper()) {
            new AlertDialog.Builder(this).setTitle("Allow Notification Access").setMessage("This app app read your WhatsApp notification and saves them, please allow access.").setPositiveButton("Allow", new C03931()).create().show();
        }
    }

    private boolean checkper() {
        boolean z = false;
        for (String str : NotificationManagerCompat.getEnabledListenerPackages(this)) {
            if (str.equals(getPackageName())) {
                z = true;
            }
        }
        return z;
    }

    @Override
    // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    public void brodCarst(Context context) {
        try {
            this.brd = new NetworkChangeReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            context.registerReceiver(this.brd, intentFilter);
        } catch (Exception unused) {
        }
    }

    /* loaded from: classes2.dex */
    class C03931 implements DialogInterface.OnClickListener {
        C03931() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            Message_Recover_Activity.this.startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
        }
    }

    /* loaded from: classes2.dex */
    public class NetworkChangeReceiver extends BroadcastReceiver {
        boolean c = true;

        public NetworkChangeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (this.c) {
                this.c = false;
                Message_Recover_Activity.this.check = true;
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            @SuppressLint("MissingPermission") NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            @SuppressLint("MissingPermission") NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
            if ((networkInfo.isAvailable() || networkInfo2.isAvailable()) && Constant.isOnline(context)) {
                Message_Recover_Activity.this.check = false;
            }
        }
    }

    @Override
    public void onBackPressed() {
        NetworkChangeReceiver networkChangeReceiver = this.brd;
        if (networkChangeReceiver != null) {
            unregisterReceiver(networkChangeReceiver);
        }
        Message_Recover_Activity.this.finish();
    }
}
