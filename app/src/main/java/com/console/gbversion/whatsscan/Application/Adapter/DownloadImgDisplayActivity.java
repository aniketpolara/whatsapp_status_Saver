package com.console.gbversion.whatsscan.Application.Adapter;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.console.gbversion.whatsscan.Application.Utils.AppUtils;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.R;
import java.io.File;

/* loaded from: classes2.dex */
public class DownloadImgDisplayActivity extends AppCompatActivity {
    private RelativeLayout ivRepost;
    private RelativeLayout ivShare;
    ImageView iv_img;
    String path;
    private Toolbar toolbar;
    private AppCompatTextView toolbar_text;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_display_image);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.iv_img = (ImageView) findViewById(R.id.iv_img);
        this.toolbar_text = (AppCompatTextView) findViewById(R.id.toolbar_text);
        this.path = getIntent().getStringExtra("FILEPATH");
        this.toolbar_text.setText(getIntent().getStringExtra("FILENAME"));
        Glide.with((FragmentActivity) this).load(this.path).into(this.iv_img);
        this.ivShare = (RelativeLayout) findViewById(R.id.ivShare);
        this.ivRepost = (RelativeLayout) findViewById(R.id.ivRepost);
        this.ivShare.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Adapter.DownloadImgDisplayActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DownloadImgDisplayActivity.this.shareImage();
            }
        });
        this.ivRepost.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Adapter.DownloadImgDisplayActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DownloadImgDisplayActivity.this.setStatus();
            }
        });
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void shareImage() {
        Uri uriForFile = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", new File(this.path));
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.addFlags(1);
        intent.addFlags(2);
        intent.setType("image/jpeg");
        intent.putExtra("android.intent.extra.STREAM", uriForFile);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStatus() {
        Uri parse = Uri.parse(new File(this.path).getAbsolutePath());
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setAction("android.intent.action.SEND");
        intent.setPackage("com.whatsapp");
        intent.putExtra("android.intent.extra.STREAM", parse);
        intent.setType("image/jpeg");
        intent.addFlags(1);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            AppUtils.showSnackbar(this, getString(R.string.err_wtsapp_not_installed));
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }
}
