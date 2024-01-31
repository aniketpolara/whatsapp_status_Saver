package com.console.gbversion.whatsscan.Application.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Window;
import androidx.core.content.ContextCompat;
import com.console.gbversion.whatsscan.R;

/* loaded from: classes2.dex */
public class Constant {
    public static String SAVE_TO = "QR and Barcode";

    public static void CallIntent(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        intent.setFlags(67108864);
        context.startActivity(intent);
    }

    public static boolean isOnline(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public static Boolean getguide(Context context) {
        try {
            return Boolean.valueOf(context.getSharedPreferences("shp_Admob_addata", 0).getBoolean("IS_GUIDE", false));
        } catch (Exception unused) {
            return Boolean.FALSE;
        }
    }

    public static String getGuidScreen_Visibility(Context context) {
        try {
            return context.getSharedPreferences("guidscreen", 0).getString("color", "false");
        } catch (Exception unused) {
            return "false";
        }
    }

    @SuppressLint("ResourceType")
    public static void setStatusBarGradiant(Activity activity, int i) {
        Window window = activity.getWindow();
        Drawable drawable = ContextCompat.getDrawable(activity, R.drawable.status_bar);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(ContextCompat.getColor(activity, 17170445));
        window.setNavigationBarColor(ContextCompat.getColor(activity, 17170445));
        window.setBackgroundDrawable(drawable);
    }
}
