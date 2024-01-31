package com.console.gbversion.whatsscan.Application.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.ConsoleMessage;
import android.webkit.DownloadListener;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.R;

import java.util.Arrays;
import java.util.Locale;

/* loaded from: classes2.dex */
public class WhatsappWebActivity extends AppCompatActivity {
    public static final String[] VIDEO_PERMISSION = {"android.permission.CAMERA", "android.permission.RECORD_AUDIO"};
    private static final String WHATSAPP_WEB_URL = "https://web.whatsapp.com/🌐/" + Locale.getDefault().getLanguage();
    private NetworkChangeReceiver brd;
    private boolean check;
    public PermissionRequest currentPermissionRequest;
    boolean keyboardEnabled;
    public ValueCallback<Uri[]> mUploadMessage;
    MyWebView mWebView;
    private ViewGroup mainView;
    private SharedPreferences prefs;
    public final Activity activity = this;
    Toast clickReminder = null;
    private long lastTouchClick = 0;
    private float lastXClick = 0.0f;

    private void showSnackbar(String str) {
    }

    public void showToast(String str) {
    }

    static {
        String str = Build.VERSION.RELEASE;
    }

    public static boolean lambda$onCreate$1(WhatsappWebActivity whatsappWebActivity, View view, MotionEvent motionEvent) {
        Toast toast = whatsappWebActivity.clickReminder;
        if (toast != null) {
            toast.cancel();
            whatsappWebActivity.clickReminder = null;
        }
        whatsappWebActivity.lastTouchClick = System.currentTimeMillis();
        whatsappWebActivity.lastXClick = motionEvent.getX();
        motionEvent.getY();
        if (whatsappWebActivity.mainView.getDescendantFocusability() == 393216 && motionEvent.getAction() == 0 && Math.abs(motionEvent.getY() - ((float) whatsappWebActivity.mWebView.getHeight())) < 160.0f) {
            if (System.currentTimeMillis() - whatsappWebActivity.lastTouchClick >= 1300) {
                whatsappWebActivity.lastTouchClick = System.currentTimeMillis();
                whatsappWebActivity.lastXClick = motionEvent.getX();
                motionEvent.getY();
                return false;
            } else if (Math.abs(whatsappWebActivity.lastXClick - motionEvent.getX()) < 180.0f) {
                whatsappWebActivity.showSnackbar("Use the keyboard button on top to type");
                whatsappWebActivity.lastTouchClick = 0;
                return false;
            } else {
                whatsappWebActivity.lastTouchClick = System.currentTimeMillis();
                whatsappWebActivity.lastXClick = motionEvent.getX();
                motionEvent.getY();
            }
        }
        return false;
    }

    @Override
    // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_whatscan_main);
        getWindow().setFlags(1024, 1024);
        this.prefs = getSharedPreferences(getPackageName(), 0);
        getWindow().setSoftInputMode(16);
        getWindow().setFlags(1024, 1024);
        this.mainView = (ViewGroup) findViewById(R.id.layout);
        MyWebView myWebView = (MyWebView) findViewById(R.id.webview);
        this.mWebView = myWebView;
        myWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setAllowContentAccess(true);
        this.mWebView.getSettings().setAllowFileAccess(true);
        this.mWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        this.mWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        this.mWebView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        this.mWebView.getSettings().setDomStorageEnabled(true);
        this.mWebView.getSettings().setAppCacheEnabled(true);
        this.mWebView.getSettings().setAppCachePath(getCacheDir().getAbsolutePath());
        this.mWebView.getSettings().setCacheMode(-1);
        this.mWebView.setScrollBarStyle(33554432);
        this.mWebView.setScrollbarFadingEnabled(true);
        this.mWebView.setWebChromeClient(new WebChromeClient() { // from class: com.console.gbversion.whatsscan.Application.Activity.WhatsappWebActivity.1
            @Override // android.webkit.WebChromeClient
            public void onPermissionRequest(PermissionRequest permissionRequest) {
                if (permissionRequest.getResources()[0].equals("android.webkit.resource.VIDEO_CAPTURE")) {
                    if (ContextCompat.checkSelfPermission(WhatsappWebActivity.this.activity, "android.permission.CAMERA") == -1 && ContextCompat.checkSelfPermission(WhatsappWebActivity.this.activity, "android.permission.RECORD_AUDIO") == -1) {
                        ActivityCompat.requestPermissions(WhatsappWebActivity.this.activity, WhatsappWebActivity.VIDEO_PERMISSION, 203);
                        WhatsappWebActivity.this.currentPermissionRequest = permissionRequest;
                    } else if (ContextCompat.checkSelfPermission(WhatsappWebActivity.this.activity, "android.permission.CAMERA") == -1) {
                        ActivityCompat.requestPermissions(WhatsappWebActivity.this.activity, new String[]{"android.permission.CAMERA"}, 201);
                        WhatsappWebActivity.this.currentPermissionRequest = permissionRequest;
                    } else if (ContextCompat.checkSelfPermission(WhatsappWebActivity.this.activity, "android.permission.RECORD_AUDIO") == -1) {
                        ActivityCompat.requestPermissions(WhatsappWebActivity.this.activity, new String[]{"android.permission.RECORD_AUDIO"}, 202);
                        WhatsappWebActivity.this.currentPermissionRequest = permissionRequest;
                    } else {
                        permissionRequest.grant(permissionRequest.getResources());
                    }
                } else if (!permissionRequest.getResources()[0].equals("android.webkit.resource.AUDIO_CAPTURE")) {
                    try {
                        permissionRequest.grant(permissionRequest.getResources());
                    } catch (RuntimeException e) {
                        Log.d("Whats Web", "Granting permissions failed", e);
                    }
                } else if (ContextCompat.checkSelfPermission(WhatsappWebActivity.this.activity, "android.permission.RECORD_AUDIO") == 0) {
                    permissionRequest.grant(permissionRequest.getResources());
                } else {
                    ActivityCompat.requestPermissions(WhatsappWebActivity.this.activity, new String[]{"android.permission.RECORD_AUDIO"}, 202);
                    WhatsappWebActivity.this.currentPermissionRequest = permissionRequest;
                }
            }

            @Override // android.webkit.WebChromeClient
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.d("Whats Web", "WebView console message: " + consoleMessage.message());
                return super.onConsoleMessage(consoleMessage);
            }

            @Override // android.webkit.WebChromeClient
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {
                WhatsappWebActivity whatsappWebActivity = WhatsappWebActivity.this;
                whatsappWebActivity.mUploadMessage = valueCallback;
                whatsappWebActivity.startActivityForResult(fileChooserParams.createIntent(), 200);
                Toast.makeText(WhatsappWebActivity.this, "aditya", 0).show();
                return true;
            }
        });
        this.mWebView.setWebViewClient(new WebViewClient() { // from class: com.console.gbversion.whatsscan.Application.Activity.WhatsappWebActivity.2
            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                webView.scrollTo(0, 0);
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                if (Build.VERSION.SDK_INT < 24) {
                    return super.shouldOverrideUrlLoading(webView, webResourceRequest);
                }
                Log.d("Whats Web", webResourceRequest.getUrl().toString());
                if (webResourceRequest.getUrl().toString().contains("web.whatsapp.com")) {
                    return false;
                }
                if (webResourceRequest.getUrl().toString().contains("www.whatsapp.com")) {
                    WhatsappWebActivity.this.loadWhatsapp();
                    return true;
                }
                WhatsappWebActivity.this.startActivity(new Intent("android.intent.action.VIEW", webResourceRequest.getUrl()));
                return true;
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (Build.VERSION.SDK_INT >= 24) {
                    return super.shouldOverrideUrlLoading(webView, str);
                }
                if (str.equalsIgnoreCase("web.whatsapp.com")) {
                    return false;
                }
                if (str.equalsIgnoreCase("www.whatsapp.com")) {
                    WhatsappWebActivity.this.loadWhatsapp();
                    return true;
                }
                WhatsappWebActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                Log.d("Whats Web", String.format("Error: %s - %s", Integer.valueOf(webResourceError.getErrorCode()), webResourceError.getDescription()));
            }

            @Override // android.webkit.WebViewClient
            public void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
                Log.d("Whats Web", "Unhandled key event: " + keyEvent.toString());
            }
        });
        this.mWebView.setDownloadListener(new DownloadListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.WhatsappWebActivity.3
            @Override // android.webkit.DownloadListener
            public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                WhatsappWebActivity.this.showToast("Downloading is not supported yet.");
            }
        });
        this.mWebView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Win64; x64; rv:46.0) Gecko/20100101 Firefox/60.0");
        if (bundle == null) {
            loadWhatsapp();
        } else {
            Log.d("Whats Web", "savedInstanceState is present");
        }
        this.mWebView.setOnTouchListener(new View.OnTouchListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.WhatsappWebActivity.4
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return WhatsappWebActivity.lambda$onCreate$1(WhatsappWebActivity.this, view, motionEvent);
            }
        });
        if (!Constant.isOnline(this)) {
            brodCarst(this);
        }
    }

    public void loadWhatsapp() {
        this.mWebView.loadUrl(WHATSAPP_WEB_URL);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.mWebView.onResume();
        this.keyboardEnabled = this.prefs.getBoolean("keyboardEnabled", false);
        setNavbarEnabled(this.prefs.getBoolean("navbarEnabled", true));
        if (!this.keyboardEnabled) {
            setKeyboardEnabled(false);
        }
        showIntroInfo();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.mWebView.onPause();
    }

    @Override
    // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        switch (i) {
            case 201:
            case 202:
                if (iArr.length > 0 && iArr[0] == 0) {
                    try {
                        PermissionRequest permissionRequest = this.currentPermissionRequest;
                        permissionRequest.grant(permissionRequest.getResources());
                        break;
                    } catch (RuntimeException e) {
                        Log.e("Whats Web", "Granting permissions failed", e);
                        break;
                    }
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Permission not granted, can't use ");
                    sb.append(i == 201 ? "camera" : "microphone");
                    showSnackbar(sb.toString());
                    this.currentPermissionRequest.deny();
                    break;
                }
            case 203:
                if (strArr.length != 2 || iArr[0] != 0 || iArr[1] != 0) {
                    showSnackbar("Permission not granted, can't use video.");
                    this.currentPermissionRequest.deny();
                    break;
                } else {
                    try {
                        PermissionRequest permissionRequest2 = this.currentPermissionRequest;
                        permissionRequest2.grant(permissionRequest2.getResources());
                        break;
                    } catch (RuntimeException e2) {
                        Log.e("Whats Web", "Granting permissions failed", e2);
                        break;
                    }
                }
            case 204:
                if (iArr.length <= 0 || iArr[0] != 0) {
                    showSnackbar("Permission not granted, can't download to storage");
                    break;
                }
                break;
            default:
                Log.d("Whats Web", "Got permission result with unknown request code " + i + " - " + Arrays.asList(strArr).toString());
                break;
        }
        this.currentPermissionRequest = null;
    }

    @Override
    // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mWebView.saveState(bundle);
    }

    @Override // android.app.Activity
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.mWebView.restoreState(bundle);
    }

    @Override
    // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.mUploadMessage == null) {
            return;
        }
        if (i != 200) {
            Log.d("Whats Web", "Got activity result with unknown request code " + i + " - " + intent.toString());
        } else if (i2 == 0 || intent.getData() == null) {
            this.mUploadMessage.onReceiveValue(null);
        } else {
            this.mUploadMessage.onReceiveValue(new Uri[]{intent.getData()});
        }
    }

    private void showIntroInfo() {
        if (!this.prefs.getBoolean("introShown", false)) {
            showPopupDialog(R.string.introInfo);
            this.prefs.edit().putBoolean("introShown", true).apply();
        }
    }

    private void showPopupDialog(int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(i).setCancelable(false).setPositiveButton("Ok", (DialogInterface.OnClickListener) null);
        builder.create().show();
    }

    private void setKeyboardEnabled(boolean z) {
        this.keyboardEnabled = z;
        InputMethodManager inputMethodManager = (InputMethodManager) this.activity.getSystemService("input_method");
        if (z && this.mainView.getDescendantFocusability() == 393216) {
            this.mainView.setDescendantFocusability(262144);
            showSnackbar("Keyboard is unblocked.");
        } else if (!z) {
            this.mainView.setDescendantFocusability(393216);
            this.mWebView.getRootView().requestFocus();
            showSnackbar("Keyboard is blocked.");
            inputMethodManager.hideSoftInputFromWindow(this.activity.getCurrentFocus().getWindowToken(), 0);
        }
        this.prefs.edit().putBoolean("keyboardEnabled", z).apply();
    }

    private void setNavbarEnabled(boolean z) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            if (z) {
                supportActionBar.show();
            } else {
                supportActionBar.hide();
            }
            this.prefs.edit().putBoolean("navbarEnabled", z).apply();
        }
    }

    public void brodCarst(Context context) {
        try {
            this.brd = new NetworkChangeReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            context.registerReceiver(this.brd, intentFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* loaded from: classes2.dex */
    public class NetworkChangeReceiver extends BroadcastReceiver {
        boolean c = true;

        public NetworkChangeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (this.c) {
                this.c = false;
                WhatsappWebActivity.this.check = true;
            }
            @SuppressLint("MissingPermission") NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            if (activeNetworkInfo.getType() == 1) {
                if (Constant.isOnline(context)) {
                    WhatsappWebActivity.this.check = false;
                }
            } else if (activeNetworkInfo.getType() == 0 && Constant.isOnline(context)) {
                WhatsappWebActivity.this.check = false;
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        NetworkChangeReceiver networkChangeReceiver = this.brd;
        if (networkChangeReceiver != null) {
            unregisterReceiver(networkChangeReceiver);
        }
        WhatsappWebActivity.this.finish();
    }
}
