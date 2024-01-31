package com.console.gbversion.whatsscan.Application.Activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.R;

/* loaded from: classes2.dex */
public class FullCaptionActivity extends AppCompatActivity {
    String[] CaptionArray;
    private RelativeLayout copy;
    ImageView img_next;
    ImageView img_previous;
    int pos;
    private RelativeLayout share;
    private Toolbar toolbar;
    TextView txt_full;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_fullcaption);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.txt_full = (TextView) findViewById(R.id.txt_full);
        this.img_next = (ImageView) findViewById(R.id.img_next);
        this.img_previous = (ImageView) findViewById(R.id.img_previous);
        this.share = (RelativeLayout) findViewById(R.id.ivShare);
        this.copy = (RelativeLayout) findViewById(R.id.ivCopy);
        final String stringExtra = getIntent().getStringExtra("FULL_CAPTION");
        this.pos = getIntent().getIntExtra("POSITION", 0);
        this.txt_full.setText(stringExtra);
        setTitle("Captions");
        this.CaptionArray = getIntent().getStringArrayExtra("CAPTION_ARRAY");
        this.share.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.FullCaptionActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.SUBJECT", "Whats Web");
                intent.putExtra("android.intent.extra.TEXT", stringExtra);
                FullCaptionActivity.this.startActivity(Intent.createChooser(intent, "Share via"));
            }
        });
        this.copy.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.FullCaptionActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Toast.makeText(FullCaptionActivity.this, "Status Copy to Clipboard", 0).show();
                ((ClipboardManager) FullCaptionActivity.this.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Status", stringExtra));
            }
        });
        int i = this.pos;
        if (i == 0) {
            this.img_previous.setEnabled(false);
        } else if (i == this.CaptionArray.length - 1) {
            this.img_next.setEnabled(false);
        }
        this.img_next.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.FullCaptionActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FullCaptionActivity fullCaptionActivity = FullCaptionActivity.this;
                int i2 = fullCaptionActivity.pos + 1;
                fullCaptionActivity.pos = i2;
                if (i2 < fullCaptionActivity.CaptionArray.length - 1) {
                    fullCaptionActivity.img_previous.setEnabled(true);
                    FullCaptionActivity fullCaptionActivity2 = FullCaptionActivity.this;
                    fullCaptionActivity2.txt_full.setText(fullCaptionActivity2.CaptionArray[fullCaptionActivity2.pos]);
                    Log.d("POS", "onClick: " + FullCaptionActivity.this.pos);
                    return;
                }
                fullCaptionActivity.img_next.setEnabled(false);
            }
        });
        this.img_previous.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.FullCaptionActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FullCaptionActivity fullCaptionActivity = FullCaptionActivity.this;
                int i2 = fullCaptionActivity.pos - 1;
                fullCaptionActivity.pos = i2;
                if (i2 > -1) {
                    fullCaptionActivity.img_next.setEnabled(true);
                    FullCaptionActivity fullCaptionActivity2 = FullCaptionActivity.this;
                    fullCaptionActivity2.txt_full.setText(fullCaptionActivity2.CaptionArray[fullCaptionActivity2.pos]);
                    Log.d("POS", "onClick: " + FullCaptionActivity.this.pos);
                    return;
                }
                fullCaptionActivity.img_previous.setEnabled(false);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
                FullCaptionActivity.this.finish();
    }
}
