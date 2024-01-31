package com.console.gbversion.whatsscan.Application.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import de.hdodenhof.circleimageview.CircleImageView;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.Application.Utils.ContentUtill;
import com.console.gbversion.whatsscan.R;
import java.io.File;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class WaPlayAudioActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    ImageButton btnPlayVideo;
    ImageView img_thumbnail;
    Animation rotateAnimation;
    SeekBar seekVideo;
    private RelativeLayout share;
    TextView tvEndVideo;
    TextView tvStartVideo;
    VideoView videoview;
    int duration = 0;
    Handler handler = new Handler();
    boolean isPlay = false;
    View.OnClickListener onclickplayvideo = new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.WaPlayAudioActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            WaPlayAudioActivity waPlayAudioActivity = WaPlayAudioActivity.this;
            if (waPlayAudioActivity.isPlay) {
                waPlayAudioActivity.videoview.pause();
                WaPlayAudioActivity waPlayAudioActivity2 = WaPlayAudioActivity.this;
                waPlayAudioActivity2.handler.removeCallbacks(waPlayAudioActivity2.runnable);
                WaPlayAudioActivity.this.img_thumbnail.clearAnimation();
                WaPlayAudioActivity.this.btnPlayVideo.setBackgroundResource(R.drawable.ic_baseline_play_circle_24);
            } else {
                waPlayAudioActivity.rotateAnimation = AnimationUtils.loadAnimation(waPlayAudioActivity, R.anim.anim);
                WaPlayAudioActivity waPlayAudioActivity3 = WaPlayAudioActivity.this;
                waPlayAudioActivity3.img_thumbnail.startAnimation(waPlayAudioActivity3.rotateAnimation);
                WaPlayAudioActivity waPlayAudioActivity4 = WaPlayAudioActivity.this;
                waPlayAudioActivity4.videoview.seekTo(waPlayAudioActivity4.seekVideo.getProgress());
                WaPlayAudioActivity.this.videoview.start();
                WaPlayAudioActivity waPlayAudioActivity5 = WaPlayAudioActivity.this;
                waPlayAudioActivity5.img_thumbnail.startAnimation(waPlayAudioActivity5.rotateAnimation);
                WaPlayAudioActivity waPlayAudioActivity6 = WaPlayAudioActivity.this;
                waPlayAudioActivity6.handler.postDelayed(waPlayAudioActivity6.runnable, 500);
                WaPlayAudioActivity.this.videoview.setVisibility(0);
                WaPlayAudioActivity.this.btnPlayVideo.setBackgroundResource(R.drawable.ic_baseline_pause_circle_24);
            }
            WaPlayAudioActivity waPlayAudioActivity7 = WaPlayAudioActivity.this;
            waPlayAudioActivity7.isPlay = !waPlayAudioActivity7.isPlay;
        }
    };
    Runnable runnable = new Runnable() { // from class: com.console.gbversion.whatsscan.Application.Activity.WaPlayAudioActivity.2
        @Override // java.lang.Runnable
        public void run() {
            if (WaPlayAudioActivity.this.videoview.isPlaying()) {
                int currentPosition = WaPlayAudioActivity.this.videoview.getCurrentPosition();
                WaPlayAudioActivity.this.seekVideo.setProgress(currentPosition);
                try {
                    TextView textView = WaPlayAudioActivity.this.tvStartVideo;
                    textView.setText("" + WaPlayAudioActivity.formatTimeUnit((long) currentPosition));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                WaPlayAudioActivity waPlayAudioActivity = WaPlayAudioActivity.this;
                if (currentPosition == waPlayAudioActivity.duration) {
                    waPlayAudioActivity.seekVideo.setProgress(0);
                    WaPlayAudioActivity.this.btnPlayVideo.setBackgroundResource(R.drawable.ic_baseline_play_circle_24);
                    WaPlayAudioActivity.this.tvStartVideo.setText("00:00");
                    WaPlayAudioActivity waPlayAudioActivity2 = WaPlayAudioActivity.this;
                    waPlayAudioActivity2.handler.removeCallbacks(waPlayAudioActivity2.runnable);
                    return;
                }
                waPlayAudioActivity.handler.postDelayed(waPlayAudioActivity.runnable, 500);
                return;
            }
            WaPlayAudioActivity waPlayAudioActivity3 = WaPlayAudioActivity.this;
            waPlayAudioActivity3.seekVideo.setProgress(waPlayAudioActivity3.duration);
            try {
                TextView textView2 = WaPlayAudioActivity.this.tvStartVideo;
                textView2.setText("" + WaPlayAudioActivity.formatTimeUnit((long) WaPlayAudioActivity.this.duration));
            } catch (ParseException e2) {
                e2.printStackTrace();
            }
            WaPlayAudioActivity waPlayAudioActivity4 = WaPlayAudioActivity.this;
            waPlayAudioActivity4.handler.removeCallbacks(waPlayAudioActivity4.runnable);
        }
    };
    String videoPath = "";

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public static String formatTimeUnit(long j) throws ParseException {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        return String.format("%02d:%02d", Long.valueOf(timeUnit.toMinutes(j)), Long.valueOf(timeUnit.toSeconds(j) - TimeUnit.MINUTES.toSeconds(timeUnit.toMinutes(j))));
    }

    @SuppressLint("NewApi")
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_play_audio);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        setSupportActionBar((Toolbar) findViewById(R.id.toolBar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                StrictMode.class.getMethod("disableDeathOnFileUriExposure", new Class[0]).invoke(null, new Object[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.btnPlayVideo = (ImageButton) findViewById(R.id.btnPlayVideo);
        this.img_thumbnail = (ImageView) findViewById(R.id.img_thumbnail);
        this.share = (RelativeLayout) findViewById(R.id.ivShare);
        this.videoPath = getIntent().getStringExtra("AUDIO_PATH");
        try {
            ((AppCompatTextView) findViewById(R.id.toolbar_text)).setText(new File(this.videoPath).getName().split("\\.")[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        SeekBar seekBar = (SeekBar) findViewById(R.id.sbVideo);
        this.seekVideo = seekBar;
        seekBar.setOnSeekBarChangeListener(this);
        this.tvStartVideo = (TextView) findViewById(R.id.tvStartVideo);
        this.tvEndVideo = (TextView) findViewById(R.id.tvEndVideo);
        VideoView videoView = (VideoView) findViewById(R.id.vvScreen);
        this.videoview = videoView;
        videoView.setVideoPath(this.videoPath);
        this.btnPlayVideo.setOnClickListener(this.onclickplayvideo);
        this.videoview.setOnErrorListener(new C04152(this));
        this.videoview.setOnPreparedListener(new C04163());
        this.videoview.setOnCompletionListener(new C04174());
        this.seekVideo.getProgressDrawable().setColorFilter(getResources().getColor(R.color.download, null), PorterDuff.Mode.SRC_IN);
        this.seekVideo.getThumb().setColorFilter(getResources().getColor(R.color.download, null), PorterDuff.Mode.SRC_IN);
        this.share.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.WaPlayAudioActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Uri parse = Uri.parse(WaPlayAudioActivity.this.videoPath);
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("audio/*");
                intent.putExtra("android.intent.extra.STREAM", parse);
                WaPlayAudioActivity.this.startActivity(Intent.createChooser(intent, "Share Sound File"));
            }
        });
        ThumbAudio(this, this.videoPath);
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        int progress = seekBar.getProgress();
        this.videoview.seekTo(progress);
        try {
            TextView textView = this.tvStartVideo;
            textView.setText("" + formatTimeUnit((long) progress));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void ThumbAudio(Context context, String str) {
        Cursor query = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "_id"}, "_data  like ?", new String[]{"%" + str + "%"}, " _id DESC");
        int count = query.getCount();
        if (count > 0) {
            query.moveToFirst();
            for (int i = 0; i < count; i++) {
                Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, ContentUtill.getLong(query));
                query.moveToNext();
            }
        }
    }

    /* loaded from: classes2.dex */
    class C04152 implements MediaPlayer.OnErrorListener {
        @Override // android.media.MediaPlayer.OnErrorListener
        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            return true;
        }

        C04152(WaPlayAudioActivity waPlayAudioActivity) {
        }
    }

    /* loaded from: classes2.dex */
    class C04163 implements MediaPlayer.OnPreparedListener {
        C04163() {
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            WaPlayAudioActivity waPlayAudioActivity = WaPlayAudioActivity.this;
            waPlayAudioActivity.duration = waPlayAudioActivity.videoview.getDuration();
            WaPlayAudioActivity waPlayAudioActivity2 = WaPlayAudioActivity.this;
            waPlayAudioActivity2.seekVideo.setMax(waPlayAudioActivity2.duration);
            WaPlayAudioActivity.this.tvStartVideo.setText("00:00");
            try {
                TextView textView = WaPlayAudioActivity.this.tvEndVideo;
                textView.setText("" + WaPlayAudioActivity.formatTimeUnit((long) WaPlayAudioActivity.this.duration));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /* loaded from: classes2.dex */
    class C04174 implements MediaPlayer.OnCompletionListener {
        C04174() {
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            WaPlayAudioActivity.this.videoview.setVisibility(8);
            WaPlayAudioActivity.this.btnPlayVideo.setBackgroundResource(R.drawable.ic_baseline_play_circle_24);
            WaPlayAudioActivity.this.videoview.seekTo(0);
            WaPlayAudioActivity.this.seekVideo.setProgress(0);
            WaPlayAudioActivity.this.tvStartVideo.setText("00:00");
            WaPlayAudioActivity waPlayAudioActivity = WaPlayAudioActivity.this;
            waPlayAudioActivity.handler.removeCallbacks(waPlayAudioActivity.runnable);
            WaPlayAudioActivity.this.isPlay = false;
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
                WaPlayAudioActivity.this.finish();
    }
}
