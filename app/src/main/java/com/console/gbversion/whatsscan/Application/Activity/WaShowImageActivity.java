package com.console.gbversion.whatsscan.Application.Activity;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.R;
import java.io.File;

/* loaded from: classes2.dex */
public class WaShowImageActivity extends AppCompatActivity {
    private String imagepath;
    private ImageView img_show;
    private Toolbar toolbar;
    private AppCompatTextView toolbar_text;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_show_image);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.img_show = (ImageView) findViewById(R.id.img_show);
        this.toolbar_text = (AppCompatTextView) findViewById(R.id.toolbar_text);
        this.imagepath = getIntent().getStringExtra("IMAGE_PATH");
        this.toolbar_text.setText(new File(this.imagepath).getName().split("\\.")[0]);
        Glide.with((FragmentActivity) this).load(this.imagepath).into(this.img_show);
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
                WaShowImageActivity.this.finish();
    }
}
