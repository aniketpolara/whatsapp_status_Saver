package com.console.gbversion.whatsscan.Application.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.R;

/* loaded from: classes2.dex */
public class CaptionLanguageActivity extends AppCompatActivity {
    private ImageView English;
    private ImageView Gujarati;
    private ImageView Hindi;
    private ImageView Marathi;
    private ImageView Punjabi;
    private ImageView Tamil;
    private ImageView Telugu;
    private ImageView Urdu;
    private Toolbar toolbar;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_language_caption);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.Gujarati =  findViewById(R.id.gujarati);
        this.English = findViewById(R.id.english);
        this.Hindi = findViewById(R.id.hindi);
        this.Punjabi = findViewById(R.id.panjabi);
        this.Urdu = findViewById(R.id.urdu);
        this.Tamil = findViewById(R.id.tamil);
        this.Telugu = findViewById(R.id.telugu);
        this.Marathi = findViewById(R.id.marathi);
        this.English.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.CaptionLanguageActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                final Intent intent = new Intent(CaptionLanguageActivity.this, CaptionTypesActivity.class);
                intent.putExtra("LANGUAGE", "ENGLISH");
                        CaptionLanguageActivity.this.startActivity(intent);
            }
        });
        this.Gujarati.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.CaptionLanguageActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                final Intent intent = new Intent(CaptionLanguageActivity.this, CaptionTypesActivity.class);
                intent.putExtra("LANGUAGE", "GUJARATI");
                        CaptionLanguageActivity.this.startActivity(intent);
            }
        });
        this.Hindi.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.CaptionLanguageActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                final Intent intent = new Intent(CaptionLanguageActivity.this, CaptionTypesActivity.class);
                intent.putExtra("LANGUAGE", "HINDI");
                        CaptionLanguageActivity.this.startActivity(intent);
            }
        });
        this.Punjabi.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.CaptionLanguageActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                final Intent intent = new Intent(CaptionLanguageActivity.this, CaptionTypesActivity.class);
                intent.putExtra("LANGUAGE", "PUNJABI");
                        CaptionLanguageActivity.this.startActivity(intent);
            }
        });
        this.Tamil.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.CaptionLanguageActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                final Intent intent = new Intent(CaptionLanguageActivity.this, CaptionTypesActivity.class);
                intent.putExtra("LANGUAGE", "TAMIL");
                        CaptionLanguageActivity.this.startActivity(intent);
            }
        });
        this.Telugu.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.CaptionLanguageActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                final Intent intent = new Intent(CaptionLanguageActivity.this, CaptionTypesActivity.class);
                intent.putExtra("LANGUAGE", "TELUGU");
                        CaptionLanguageActivity.this.startActivity(intent);
            }
        });
        this.Urdu.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.CaptionLanguageActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                final Intent intent = new Intent(CaptionLanguageActivity.this, CaptionTypesActivity.class);
                intent.putExtra("LANGUAGE", "URDU");
                        CaptionLanguageActivity.this.startActivity(intent);
            }
        });
        this.Marathi.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.CaptionLanguageActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                final Intent intent = new Intent(CaptionLanguageActivity.this, CaptionTypesActivity.class);
                intent.putExtra("LANGUAGE", "MARATHI");
                        CaptionLanguageActivity.this.startActivity(intent);
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
                CaptionLanguageActivity.this.finish();
    }
}
