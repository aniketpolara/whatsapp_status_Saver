package com.console.gbversion.whatsscan.Application.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.preference.PreferenceManager;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/* loaded from: classes2.dex */
public class Helpers {
    public static void transfer(Context context, File file) throws IOException {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String string = defaultSharedPreferences.getString("storage", Environment.getExternalStorageDirectory().toString() + "/Download/Story Saver/Whatsapp/");
        copyFile(file, new File(string + file.getName()));
        MediaScannerConnection.scanFile(context, new String[]{string + file.getName()}, null, null);
    }

    private static void copyFile(File file, File file2) throws IOException {
        FileUtils.copyFile(file, file2, false);
    }
}
