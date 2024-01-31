package com.console.gbversion.whatsscan.Application.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes2.dex */
public class SharedPrefs {
    private static SharedPreferences mPreferences;

    public static SharedPreferences getInstance(Context context) {
        if (mPreferences == null) {
            mPreferences = context.getApplicationContext().getSharedPreferences("wa_data", 0);
        }
        return mPreferences;
    }

    public static String getLang(Context context) {
        return getInstance(context).getString("language", "en");
    }

    public static void setWATree(Context context, String str) {
        getInstance(context).edit().putString("wa_tree_uri", str).apply();
    }

    public static String getWATree(Context context) {
        return getInstance(context).getString("wa_tree_uri", "");
    }

    public static void setWBTree(Context context, String str) {
        getInstance(context).edit().putString("wb_tree_uri", str).apply();
    }

    public static String getWBTree(Context context) {
        return getInstance(context).getString("wb_tree_uri", "");
    }
}
