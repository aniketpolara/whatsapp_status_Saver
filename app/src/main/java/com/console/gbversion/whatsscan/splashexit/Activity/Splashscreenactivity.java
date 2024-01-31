package com.console.gbversion.whatsscan.splashexit.Activity;

import android.Manifest;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.console.gbversion.whatsscan.R;

import org.json.JSONObject;

public class Splashscreenactivity extends AppCompatActivity {
    boolean hasAndroidPermissions;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splashscreen);
        getWindow().setFlags(1024, 1024);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!getSharedPreferences(getPackageName(), MODE_PRIVATE).getBoolean("termaccepted", false)) {
                    startActivity(new Intent(Splashscreenactivity.this, Activity_All_Permission.class));
                } else {
                    startActivity(new Intent(Splashscreenactivity.this, Firstactivity.class));
                }
                finish();
            }
        }, 3000);

    }

    @Override
    public void onBackPressed() {
    }
}
