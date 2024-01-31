package com.console.gbversion.whatsscan.Application.Adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import com.console.gbversion.whatsscan.Application.Utils.AppUtils;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.Application.Utils.Helpers;
import com.console.gbversion.whatsscan.R;
import java.io.File;
import java.io.IOException;

/* loaded from: classes2.dex */
public class StatusVideoActivity extends AppCompatActivity {
    SharedPreferences.Editor editor;
    private String fileName;
    private RelativeLayout ivDownload;
    private RelativeLayout ivShare;
    private String path = "";
    SharedPreferences sharedPreferences;
    private Toolbar toolbar;
    private AppCompatTextView toolbar_text;
    private Uri videoUriPath;
    VideoView videoView;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_fullvideo);
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
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.path = extras.getString("image_path");
            String string = extras.getString("image_filename");
            this.fileName = string;
            this.toolbar_text.setText(string);
        }
        if (this.sharedPreferences.getInt("image_video_count", 1) == 2) {
            this.editor.putInt("image_video_count", 1).apply();
        } else {
            this.editor.putInt("image_video_count", 2).apply();
        }
        this.videoView = (VideoView) findViewById(R.id.videoView);
        this.ivDownload = (RelativeLayout) findViewById(R.id.ivDownload);
        this.ivShare = (RelativeLayout) findViewById(R.id.ivShare);
        this.ivDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Helpers.transfer(StatusVideoActivity.this, new File(StatusVideoActivity.this.path));
                    StatusVideoActivity statusVideoActivity = StatusVideoActivity.this;
                    AppUtils.showSnackbar(statusVideoActivity, statusVideoActivity.getString(R.string.lbl_video_saved_to_gallery));
                } catch (IOException e) {
                    e.printStackTrace();
                    StatusVideoActivity statusVideoActivity2 = StatusVideoActivity.this;
                    AppUtils.showSnackbar(statusVideoActivity2, statusVideoActivity2.getString(R.string.lbl_problem_to_save_status));
                }
            }
        });
        this.ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StatusVideoActivity.this.shareVideo();
            }
        });
        MediaController mediaController = new MediaController(this);
        this.videoUriPath = Uri.parse(this.path);
        this.videoView.setVideoURI(Uri.parse(this.path));
        this.videoView.setMediaController(mediaController);
        mediaController.setAnchorView(this.videoView);
        this.videoView.start();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.videoView.pause();
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void shareVideo() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("video/mp4");
        intent.putExtra("android.intent.extra.STREAM", this.videoUriPath);
        intent.putExtra("android.intent.extra.TEXT", "Download App : http://bit.ly/wtsappstatusimgvdo");
        startActivity(Intent.createChooser(intent, "Share Video Using"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
