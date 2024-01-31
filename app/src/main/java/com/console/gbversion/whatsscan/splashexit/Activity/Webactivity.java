package com.console.gbversion.whatsscan.splashexit.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.console.gbversion.whatsscan.R;
import com.console.gbversion.whatsscan.splashexit.Glob;

/* loaded from: classes3.dex */
public class Webactivity extends AppCompatActivity {
    private WebView webPrivacyPolicy;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_web);
        getWindow().setFlags(1024, 1024);
        WebView webView = (WebView) findViewById(R.id.wvPrivacyPolicy);
        this.webPrivacyPolicy = webView;
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        this.webPrivacyPolicy.setWebViewClient(new WebViewClient() { // from class: com.console.globalcall.xvideocall.splashexit.Activity.royaldi_Webactivity.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView2, String str) {
                if (str.startsWith("http:") || str.startsWith("https:")) {
                    return false;
                }
                Webactivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView2, int i, String str, String str2) {
                Toast.makeText(Webactivity.this, str, 0).show();
            }
        });
        this.webPrivacyPolicy.loadUrl(Glob.privacy_link);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        finish();
    }
}
