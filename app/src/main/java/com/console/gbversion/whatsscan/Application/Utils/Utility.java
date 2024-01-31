package com.console.gbversion.whatsscan.Application.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Environment;
import android.widget.Toast;
import java.io.File;

/* loaded from: classes2.dex */
public class Utility {
    private static Context context;

    static {
        new File(Environment.DIRECTORY_DOWNLOADS + "/Story Saver/Facebook/");
        new File(Environment.DIRECTORY_DOWNLOADS + "/Story Saver/Instagram/");
    }

    public Utility(Context context2) {
        context = context2;
        context2.getSharedPreferences("myPrefs", 0).edit();
        context2.getSharedPreferences("myPrefs", 0);
        new ProgressDialog(context2);
    }

    public void showToastFromResource(int i) {
        Context context2 = context;
        Toast.makeText(context2, "" + i, 0).show();
    }

    public String getStringFromResource(int i) {
        return context.getResources().getString(i);
    }
}
