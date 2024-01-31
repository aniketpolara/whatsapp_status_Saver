package com.console.gbversion.whatsscan.Application.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.console.gbversion.whatsscan.Application.DirectChat.DirectChatMainActivity;
import com.console.gbversion.whatsscan.Application.Profile.SearchProfileMainActivity;
import com.console.gbversion.whatsscan.Application.Storage.Storage;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.R;

import java.io.File;

/* loaded from: classes2.dex */
public class WaToolsActivity extends AppCompatActivity {
    private ImageView ll_clear;
    private ImageView ll_code;
    private LinearLayout ll_delete;
    private ImageView ll_direct;
    private ImageView ll_scanner;
    private ImageView profile;
    private ImageView chat;
    private ImageView wpcleaner;
    private Toolbar toolbar;


    private void checkFolder() {
        Storage storage = new Storage(this);
        StringBuilder sb = new StringBuilder();
        sb.append(storage.getExternalStorageDirectory());
        String str = File.separator;
        sb.append(str);
        sb.append(".WA Status Saver");
        sb.append(str);
        storage.createDirectory(sb.toString());
    }

    @Override
    // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_startscreen);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
//        this.toolbar = toolbar;
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.ll_scanner = findViewById(R.id.ll_scanner);
        this.ll_code = findViewById(R.id.ll_code);
        this.ll_clear = findViewById(R.id.ll_clear);
        this.ll_direct = findViewById(R.id.ll_direct);
        this.ll_delete = findViewById(R.id.ll_delete);
        this.profile = findViewById(R.id.profile);
        this.chat = findViewById(R.id.chat);
        checkFolder();
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constant.CallIntent(WaToolsActivity.this, DirectChatActivity.class);
            }
        });
        this.ll_delete.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.WaToolsActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                startActivity(new Intent(WaToolsActivity.this, Message_Recover_Activity.class));
            }
        });
        this.profile.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.WaToolsActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Constant.CallIntent(WaToolsActivity.this, SearchProfileMainActivity.class);
            }
        });
        this.ll_direct.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.WaToolsActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
//                Constant.CallIntent(WaToolsActivity.this, DirectChatActivity.class);
                startActivity(new Intent(WaToolsActivity.this, DirectChatMainActivity.class));

            }
        });
        this.ll_code.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.WaToolsActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Constant.CallIntent(WaToolsActivity.this, QRCodeScanActivity.class);
            }
        });
        this.ll_clear.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.WaToolsActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Constant.CallIntent(WaToolsActivity.this, WhatsappCleanerActivity.class);
            }
        });
        this.ll_scanner.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.WaToolsActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Constant.CallIntent(WaToolsActivity.this, WhatsappWebActivity.class);
            }
        });
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        WaToolsActivity.this.finish();
    }
}
