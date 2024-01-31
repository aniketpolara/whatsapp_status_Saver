package com.console.gbversion.whatsscan.Application.Activity;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.R;
import com.console.gbversion.whatsscan.Whatsapp.WhatsappstatusActivity;
import com.console.gbversion.whatsscan.splashexit.Activity.Firstactivity;

/* loaded from: classes2.dex */
public class HomeActivity extends AppCompatActivity {
    private ImageView ll_chat_style;
    private ImageView ll_status;
    private ImageView ll_wa_tool;

    @Override
    // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_home);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        this.ll_wa_tool = findViewById(R.id.ll_wa_tool);
        this.ll_status = findViewById(R.id.ll_status);
        this.ll_chat_style = findViewById(R.id.ll_chat_style);
        if (!isConnected()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View inflate = LayoutInflater.from(getApplicationContext()).inflate(R.layout.internet_dialog, (ViewGroup) findViewById(16908290), false);
            builder.setView(inflate);
            ((Button) inflate.findViewById(R.id.buttonOk)).setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.HomeActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    HomeActivity.this.finishAffinity();
                }
            });
            AlertDialog create = builder.create();
            create.setCanceledOnTouchOutside(false);
            create.show();
        }
        this.ll_status.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.HomeActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Constant.CallIntent(HomeActivity.this, WhatsappstatusActivity.class);
            }
        });
        this.ll_chat_style.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.HomeActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Constant.CallIntent(HomeActivity.this, ChatStyleActivity.class);
            }
        });
        this.ll_wa_tool.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.HomeActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Constant.CallIntent(HomeActivity.this, WaToolsActivity.class);
            }
        });
    }

    public boolean isConnected() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                return false;
            }
            if (!activeNetworkInfo.isConnected()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
            return false;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    public void gotoStoreRate() {
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + getPackageName())));
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(getApplicationContext(), "You don't have Google Play installed", 1).show();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
//                AdsManager.backPressed(HomeActivity.this);
        startActivity(new Intent(HomeActivity.this, Firstactivity.class));
    }
}
