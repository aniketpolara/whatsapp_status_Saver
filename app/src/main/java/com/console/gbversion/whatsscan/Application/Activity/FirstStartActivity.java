package com.console.gbversion.whatsscan.Application.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.console.gbversion.whatsscan.R;

/* loaded from: classes2.dex */
public class FirstStartActivity extends AppCompatActivity {
    LinearLayout banner_container;
    CardView btnSkip;
    CardView imgPrivacy;
    CardView imgRate;
    CardView imgShare;
    RelativeLayout rvBannerApp;

    @Override
    // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.first_activity);
        getWindow().setFlags(1024, 1024);
        this.banner_container = (LinearLayout) findViewById(R.id.banner_container);
        this.imgRate = (CardView) findViewById(R.id.imgRate);
        this.imgShare = (CardView) findViewById(R.id.imgShare);
        this.imgPrivacy = (CardView) findViewById(R.id.imgPrivacy);
        this.btnSkip = (CardView) findViewById(R.id.btnSkip);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rvBannerApp);
        this.rvBannerApp = relativeLayout;
        this.btnSkip.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.FirstStartActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                final Intent intent;
                intent = new Intent(FirstStartActivity.this, HomeActivity.class);
                FirstStartActivity.this.startActivity(intent);
            }
        });
        this.imgRate.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.FirstStartActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    FirstStartActivity firstStartActivity = FirstStartActivity.this;
                    firstStartActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + FirstStartActivity.this.getPackageName())));
                } catch (ActivityNotFoundException unused) {
                    Toast.makeText(FirstStartActivity.this.getApplicationContext(), "You don't have Google Play installed", 1).show();
                }
            }
        });
        this.imgShare.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.FirstStartActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("text/plain");
                    intent.putExtra("android.intent.extra.SUBJECT", R.string.app_name);
                    intent.putExtra("android.intent.extra.TEXT", "\nLet me recommend you this application\n\nhttps://play.google.com/store/apps/details?id=com.console.gbversion.whatsscan\n\n");
                    FirstStartActivity.this.startActivity(Intent.createChooser(intent, "choose one"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.imgPrivacy.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.FirstStartActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
//                FirstStartActivity.this.startActivity(new Intent(FirstStartActivity.this, PrivacyPolicyActivity.class));
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
//                AdsManager.backPressed(FirstStartActivity.this);
    }
}
