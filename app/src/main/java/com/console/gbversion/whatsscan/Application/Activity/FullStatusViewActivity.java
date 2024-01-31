package com.console.gbversion.whatsscan.Application.Activity;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.R;

/* loaded from: classes2.dex */
public class FullStatusViewActivity extends AppCompatActivity {
    private String PathImage;
    private ImageView img_view;
    private Toolbar toolbar;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_full_view);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.PathImage = getIntent().getStringExtra("PathImage");
        this.img_view = (ImageView) findViewById(R.id.img_view);
        Glide.with((FragmentActivity) this).load(this.PathImage).into(this.img_view);
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
                FullStatusViewActivity.this.finish();
    }
}
