package com.console.gbversion.whatsscan.Application.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.R;

/* loaded from: classes2.dex */
public class SettingsActivity extends AppCompatActivity {
    private RelativeLayout rl_home;
    private RelativeLayout rl_more;
    private RelativeLayout rl_privacy;
    private RelativeLayout rl_rate;
    private RelativeLayout rl_share;
    private RelativeLayout rl_tools;
    private TextView txt_version;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_setting);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        this.rl_home = (RelativeLayout) findViewById(R.id.rl_home);
        this.rl_tools = (RelativeLayout) findViewById(R.id.rl_tools);
        this.rl_privacy = (RelativeLayout) findViewById(R.id.rl_privacy);
        this.rl_rate = (RelativeLayout) findViewById(R.id.rl_rate);
        this.rl_share = (RelativeLayout) findViewById(R.id.rl_share);
        this.rl_more = (RelativeLayout) findViewById(R.id.rl_more);
        TextView textView = (TextView) findViewById(R.id.txt_version);
        this.txt_version = textView;
        try {
            textView.setText(getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        this.rl_home.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.SettingsActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                        Constant.CallIntent(SettingsActivity.this, HomeActivity.class);
            }
        });
        this.rl_tools.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.SettingsActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                        Constant.CallIntent(SettingsActivity.this, WaToolsActivity.class);
            }
        });
        this.rl_share.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.SettingsActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("text/plain");
                    intent.putExtra("android.intent.extra.SUBJECT", R.string.app_name);
                    intent.putExtra("android.intent.extra.TEXT", "\nLet me recommend you this application\n\nhttps://play.google.com/store/apps/details?id=com.console.gbversion.whatsscan\n\n");
                    SettingsActivity.this.startActivity(Intent.createChooser(intent, "choose one"));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        this.rl_more.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.SettingsActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
//                try {
//                    SettingsActivity settingsActivity = SettingsActivity.this;
//                    settingsActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pub:" + GlobalAppsMedia.app_acc_link)));
//                } catch (ActivityNotFoundException unused) {
//                    SettingsActivity settingsActivity2 = SettingsActivity.this;
//                    settingsActivity2.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/developer?id=" + GlobalAppsMedia.app_acc_link)));
//                }
            }
        });
        this.rl_rate.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.SettingsActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    SettingsActivity settingsActivity = SettingsActivity.this;
                    settingsActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + SettingsActivity.this.getPackageName())));
                } catch (ActivityNotFoundException unused) {
                    Toast.makeText(SettingsActivity.this, " unable to find market app", 0).show();
                }
            }
        });
        this.rl_privacy.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.SettingsActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
                SettingsActivity.this.finish();
    }
}
