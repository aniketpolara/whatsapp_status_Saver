package com.console.gbversion.whatsscan.Application.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.R;

/* loaded from: classes2.dex */
public class ChatStyleActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ll_art;
    private ImageView ll_emotion;
    private ImageView ll_mirror;
    private ImageView ll_name;
    private ImageView ll_numeric;
    private ImageView ll_style_txt;
    private ImageView rl_caption;
    private Toolbar toolbar;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_chat_style);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        findViewById();
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void findViewById() {
        this.ll_style_txt = findViewById(R.id.ll_style_txt);
        this.ll_name =  findViewById(R.id.ll_name);
        this.ll_numeric = findViewById(R.id.ll_numeric);
        this.ll_emotion = findViewById(R.id.ll_emotion);
        this.ll_art = findViewById(R.id.ll_art);
        this.ll_mirror =  findViewById(R.id.ll_mirror);
        this.rl_caption = findViewById(R.id.ll_caption);
        this.ll_style_txt.setOnClickListener(this);
        this.ll_name.setOnClickListener(this);
        this.ll_numeric.setOnClickListener(this);
        this.ll_emotion.setOnClickListener(this);
        this.ll_art.setOnClickListener(this);
        this.ll_mirror.setOnClickListener(this);
        this.rl_caption.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_art:
                        Constant.CallIntent(ChatStyleActivity.this, ArtDetailsActivity.class);
                return;
            case R.id.ll_emotion:
                        Constant.CallIntent(ChatStyleActivity.this, EmojisActivity.class);
                return;
            case R.id.ll_mirror:
                        Constant.CallIntent(ChatStyleActivity.this, MirrorTextActivity.class);
                return;
            case R.id.ll_name:
                        Constant.CallIntent(ChatStyleActivity.this, NameActivity.class);
                return;
            case R.id.ll_numeric:
                        Constant.CallIntent(ChatStyleActivity.this, NumberActivity.class);
                return;
            case R.id.ll_style_txt:
                        Constant.CallIntent(ChatStyleActivity.this, StylishTextActivity.class);
                return;
            case R.id.ll_caption:
                        Constant.CallIntent(ChatStyleActivity.this, CaptionLanguageActivity.class);
                return;
            default:
                return;
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
                ChatStyleActivity.this.finish();
    }
}
