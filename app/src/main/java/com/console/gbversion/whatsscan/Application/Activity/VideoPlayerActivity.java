package com.console.gbversion.whatsscan.Application.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import com.console.gbversion.whatsscan.Application.Utils.Utils;
import com.console.gbversion.whatsscan.R;

/* loaded from: classes2.dex */
public class VideoPlayerActivity extends AppCompatActivity {
    ImageView backIV;
    VideoView displayVV;
    String videoPath;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_video_player2);
        getWindow().setFlags(1024, 1024);
        getWindow().setFlags(1024, 1024);
        ImageView imageView = (ImageView) findViewById(R.id.backIV);
        this.backIV = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.VideoPlayerActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        this.videoPath = Utils.mPath;
        VideoView videoView = (VideoView) findViewById(R.id.displayVV);
        this.displayVV = videoView;
        videoView.setVideoPath(this.videoPath);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(this.displayVV);
        this.displayVV.setMediaController(mediaController);
        this.displayVV.start();
    }

    public void lambda$onCreate$0(View view) {
        onBackPressed();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.displayVV.setVideoPath(this.videoPath);
        this.displayVV.start();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
                VideoPlayerActivity.this.finish();
    }
}
