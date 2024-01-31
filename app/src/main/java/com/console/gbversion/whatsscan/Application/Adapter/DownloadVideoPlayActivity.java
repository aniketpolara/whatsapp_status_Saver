package com.console.gbversion.whatsscan.Application.Adapter;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import com.console.gbversion.whatsscan.Application.Utils.AppUtils;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.R;
import java.io.File;

/* loaded from: classes2.dex */
public class DownloadVideoPlayActivity extends AppCompatActivity {
    private String file_name;
    private RelativeLayout ivRepost;
    private RelativeLayout ivShare;
    String path;
    private Toolbar toolbar;
    private AppCompatTextView toolbar_text;
    private Uri videoUriPath;
    VideoView videoView;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_play_video);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        this.toolbar = (Toolbar) findViewById(R.id.toolBar);
        this.toolbar_text = (AppCompatTextView) findViewById(R.id.toolbar_text);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.videoView = (VideoView) findViewById(R.id.videoView);
        this.ivShare = (RelativeLayout) findViewById(R.id.ivShare);
        this.ivRepost = (RelativeLayout) findViewById(R.id.ivRepost);
        this.path = getIntent().getStringExtra("FILEPATH");
        String stringExtra = getIntent().getStringExtra("FILENAME");
        this.file_name = stringExtra;
        this.toolbar_text.setText(stringExtra);
        this.ivShare.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Adapter.DownloadVideoPlayActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DownloadVideoPlayActivity.this.shareVideo();
            }
        });
        this.ivRepost.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Adapter.DownloadVideoPlayActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DownloadVideoPlayActivity.this.setStatus();
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

    /* JADX INFO: Access modifiers changed from: private */
    public void shareVideo() {
        Uri uriForFile = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", new File(this.path));
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.addFlags(1);
        intent.addFlags(2);
        intent.setType("video/mp4");
        intent.putExtra("android.intent.extra.STREAM", uriForFile);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStatus() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setPackage("com.whatsapp");
        intent.setType("video/mp4");
        intent.putExtra("android.intent.extra.STREAM", this.videoUriPath);
        intent.addFlags(1);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            AppUtils.showSnackbar(this, getString(R.string.err_wtsapp_not_installed));
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
