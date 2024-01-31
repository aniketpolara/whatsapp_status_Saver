package com.console.gbversion.whatsscan.Application.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class Preferences {
    public static SharedPreferences.Editor editor;
    private static Context mContext;
    private static Preferences preferences;
    private static SharedPreferences sharedPreferences;

    public Preferences(Context context) {
    }

    public static Preferences getInstance(Context context) {
        if (preferences == null) {
            mContext = context;
            preferences = new Preferences(context);
        }
        PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences sharedPreferences2 = mContext.getSharedPreferences("qr_bar", 0);
        sharedPreferences = sharedPreferences2;
        editor = sharedPreferences2.edit();
        return preferences;
    }

    public void setString(String str, String str2) {
        editor.putString(str, str2);
        editor.commit();
    }

    public String getString(String str) {
        return sharedPreferences.getString(str, null);
    }

    public void setBoolean(String str, boolean z) {
        editor.putBoolean(str, z);
        editor.commit();
    }

    public Boolean getBoolean(String str, boolean z) {
        return Boolean.valueOf(sharedPreferences.getBoolean(str, z));
    }

    public void setInteger(String str, int i) {
        editor.putInt(str, i);
        editor.commit();
    }

    public int getInteger(String str) {
        return sharedPreferences.getInt(str, -1);
    }

    public void setStringArray(String str, ArrayList<String> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator<String> it = arrayList.iterator();
            String str2 = "";
            while (it.hasNext()) {
                String next = it.next();
                if (str2.isEmpty()) {
                    str2 = next;
                } else {
                    str2 = str2 + "," + next;
                }
            }
            setString(str, str2);
        }
    }

    public ArrayList<String> getStringArray(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        String string = getString(str);
        return string != null ? new ArrayList<>(Arrays.asList(string.split(","))) : arrayList;
    }
}
