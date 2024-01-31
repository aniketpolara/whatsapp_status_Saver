package com.console.gbversion.whatsscan.Application.Adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.console.gbversion.whatsscan.Application.Utils.AppUtils;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.Application.Utils.Helpers;
import com.console.gbversion.whatsscan.R;
import java.io.File;
import java.io.IOException;

/* loaded from: classes2.dex */
public class StatusImageActivity extends AppCompatActivity {
    SharedPreferences.Editor editor;
    ImageView imgFull;
    private RelativeLayout ivDownload;
    private RelativeLayout ivShare;
    SharedPreferences sharedPreferences;
    private Toolbar toolbar;
    private AppCompatTextView toolbar_text;
    private String fileName = "";
    private String path = "";

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_fullimage);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        this.toolbar = (Toolbar) findViewById(R.id.toolBar);
        this.toolbar_text = (AppCompatTextView) findViewById(R.id.toolbar_text);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("status_saver_pref", 0);
        this.sharedPreferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
        this.imgFull = (ImageView) findViewById(R.id.imgFull);
        this.ivDownload = (RelativeLayout) findViewById(R.id.ivDownload);
        this.ivShare = (RelativeLayout) findViewById(R.id.ivShare);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.path = extras.getString("image_path");
            this.fileName = extras.getString("image_filename");
            Glide.with((FragmentActivity) this).load((Uri) extras.getParcelable("image_uri")).into(this.imgFull);
            this.toolbar_text.setText(this.fileName);
        }
        if (this.sharedPreferences.getInt("image_video_count", 1) == 2) {
            this.editor.putInt("image_video_count", 1).apply();
        } else {
            this.editor.putInt("image_video_count", 2).apply();
        }
        this.ivDownload.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Adapter.StatusImageActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    Helpers.transfer(StatusImageActivity.this, new File(StatusImageActivity.this.path));
                    StatusImageActivity statusImageActivity = StatusImageActivity.this;
                    AppUtils.showSnackbar(statusImageActivity, statusImageActivity.getString(R.string.lbl_image_saved_to_gallery));
                } catch (IOException e) {
                    e.printStackTrace();
                    StatusImageActivity statusImageActivity2 = StatusImageActivity.this;
                    AppUtils.showSnackbar(statusImageActivity2, statusImageActivity2.getString(R.string.lbl_problem_to_save_status));
                }
            }
        });
        this.ivShare.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Adapter.StatusImageActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                StatusImageActivity.this.shareImage();
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
        Uri parse = Uri.parse(new File(this.path).getAbsolutePath());
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("image/jpeg");
        intent.putExtra("android.intent.extra.STREAM", parse);
        intent.putExtra("android.intent.extra.TEXT", "Download App : http://bit.ly/wtsappstatusimgvdo");
        startActivity(Intent.createChooser(intent, "Share Image Using.."));
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }
}
