package com.console.gbversion.whatsscan.Application.Activity;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.R;
import java.io.File;

/* loaded from: classes2.dex */
public class WaVideoPlayActivity extends AppCompatActivity {
    AlertDialog.Builder builder;
    ImageView imgDelete;
    private Toolbar toolbar;
    private AppCompatTextView toolbar_text;
    private String videopath;
    private VideoView videoview;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.playvideo);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        this.imgDelete = (ImageView) findViewById(R.id.imgDelete);
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.videoview = (VideoView) findViewById(R.id.videoview);
        this.toolbar_text = (AppCompatTextView) findViewById(R.id.toolbar_text);
        this.videopath = getIntent().getStringExtra("uri");
        this.toolbar_text.setText(new File(this.videopath).getName().split("\\.")[0]);
        this.videoview.setVideoURI(Uri.parse(this.videopath));
        this.videoview.requestFocus();
        this.videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.WaVideoPlayActivity.1
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.stop();
            }
        });
        this.videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.WaVideoPlayActivity.2
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                WaVideoPlayActivity.this.videoview.start();
                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.WaVideoPlayActivity.2.1
                    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
                    public void onVideoSizeChanged(MediaPlayer mediaPlayer2, int i, int i2) {
                        MediaController mediaController = new MediaController(WaVideoPlayActivity.this);
                        WaVideoPlayActivity.this.videoview.setMediaController(mediaController);
                        mediaController.setAnchorView(WaVideoPlayActivity.this.videoview);
                    }
                });
            }
        });
        this.imgDelete.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.WaVideoPlayActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WaVideoPlayActivity waVideoPlayActivity = WaVideoPlayActivity.this;
                waVideoPlayActivity.builder = new AlertDialog.Builder(waVideoPlayActivity);
                WaVideoPlayActivity.this.builder.setTitle("Delete File");
                WaVideoPlayActivity.this.builder.setMessage("Do you want to delete this file?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.WaVideoPlayActivity.3.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new File(WaVideoPlayActivity.this.videopath).delete();
                        WaVideoPlayActivity.this.finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.WaVideoPlayActivity.3.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                WaVideoPlayActivity.this.builder.create().show();
            }
        });
        this.videoview.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.WaVideoPlayActivity.4
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                return false;
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
                WaVideoPlayActivity.this.finish();
    }
}
