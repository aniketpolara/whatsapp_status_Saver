package com.console.gbversion.whatsscan.Application.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Environment;
import android.util.DisplayMetrics;

import androidx.core.content.FileProvider;
import androidx.documentfile.provider.DocumentFile;
import androidx.exifinterface.media.ExifInterface;
import com.console.gbversion.whatsscan.R;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public class Utils {
    public static String mPath;
    public static final String WA_API_BASE_URL = "https://api.whatsapp.com/send?phone=";


    public static String getBack(String str, String str2) {
        Matcher matcher = Pattern.compile(str2).matcher(str);
        return matcher.find() ? matcher.group(1) : "";
    }

    public static boolean download(Context context, String str) {
        return copyFileInSavedDir(context, str);
    }

    public static boolean isVideoFile(Context context, String str) {
        if (str.startsWith("content")) {
            String type = DocumentFile.fromSingleUri(context, Uri.parse(str)).getType();
            return type != null && type.startsWith("video");
        }
        String guessContentTypeFromName = URLConnection.guessContentTypeFromName(str);
        return guessContentTypeFromName != null && guessContentTypeFromName.startsWith("video");
    }

    public static boolean copyFileInSavedDir(Context context, String str) {
        String str2;
        if (isVideoFile(context, str)) {
            str2 = getDir(context, "Videos").getAbsolutePath();
        } else {
            str2 = getDir(context, "Images").getAbsolutePath();
        }
        Uri fromFile = Uri.fromFile(new File(str2 + File.separator + new File(str).getName()));
        try {
            InputStream openInputStream = context.getContentResolver().openInputStream(Uri.parse(str));
            OutputStream openOutputStream = context.getContentResolver().openOutputStream(fromFile, "w");
            byte[] bArr = new byte[1024];
            while (true) {
                int read = openInputStream.read(bArr);
                if (read > 0) {
                    openOutputStream.write(bArr, 0, read);
                } else {
                    openInputStream.close();
                    openOutputStream.flush();
                    openOutputStream.close();
                    Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                    intent.setData(fromFile);
                    context.sendBroadcast(intent);
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    static File getDir(Context context, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().toString());
        String str2 = File.separator;
        sb.append(str2);
        sb.append("Download");
        sb.append(str2);
        sb.append(context.getResources().getString(R.string.app_name));
        sb.append(str2);
        sb.append(str);
        File file = new File(sb.toString());
        file.mkdirs();
        return file;
    }

    public static void setLanguage(Context context, String str) {
        Locale locale = new Locale(str);
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, displayMetrics);
    }

    public static boolean appInstalledOrNot(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static void shareFile(Context context, boolean z, String str) {
        Uri uri;
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        if (z) {
            intent.setType("Video/*");
        } else {
            intent.setType("image/*");
        }
        if (str.startsWith("content")) {
            uri = Uri.parse(str);
        } else {
            uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", new File(str));
        }
        intent.putExtra("android.intent.extra.STREAM", uri);
        context.startActivity(intent);
    }

    public static void repostWhatsApp(Context context, boolean z, String str) {
        Uri uri;
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        if (z) {
            intent.setType("Video/*");
        } else {
            intent.setType("image/*");
        }
        if (str.startsWith("content")) {
            uri = Uri.parse(str);
        } else {
            uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", new File(str));
        }
        intent.putExtra("android.intent.extra.STREAM", uri);
        intent.setPackage("com.whatsapp");
        context.startActivity(intent);
    }

    public static int getRerutnCharacter(String str) {
        if (str.equals("a")) {
            return 0;
        }
        if (str.equals("b")) {
            return 1;
        }
        if (str.equals("c")) {
            return 2;
        }
        if (str.equals("d")) {
            return 3;
        }
        if (str.equals("e")) {
            return 4;
        }
        if (str.equals("f")) {
            return 5;
        }
        if (str.equals("g")) {
            return 6;
        }
        if (str.equals("h")) {
            return 7;
        }
        if (str.equals("i")) {
            return 8;
        }
        if (str.equals("j")) {
            return 9;
        }
        if (str.equals("k")) {
            return 10;
        }
        if (str.equals("l")) {
            return 11;
        }
        if (str.equals("m")) {
            return 12;
        }
        if (str.equals("n")) {
            return 13;
        }
        if (str.equals("o")) {
            return 14;
        }
        if (str.equals("p")) {
            return 15;
        }
        if (str.equals("q")) {
            return 16;
        }
        if (str.equals("r")) {
            return 17;
        }
        if (str.equals("s")) {
            return 18;
        }
        if (str.equals("t")) {
            return 19;
        }
        if (str.equals("u")) {
            return 20;
        }
        if (str.equals("v")) {
            return 21;
        }
        if (str.equals("w")) {
            return 22;
        }
        if (str.equals("x")) {
            return 23;
        }
        if (str.equals("y")) {
            return 24;
        }
        if (str.equals("z")) {
            return 25;
        }
        if (str.equals(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS)) {
            return 26;
        }
        if (str.equals("B")) {
            return 27;
        }
        if (str.equals("C")) {
            return 28;
        }
        if (str.equals("D")) {
            return 29;
        }
        if (str.equals(ExifInterface.LONGITUDE_EAST)) {
            return 30;
        }
        if (str.equals("F")) {
            return 31;
        }
        if (str.equals("G")) {
            return 32;
        }
        if (str.equals("H")) {
            return 33;
        }
        if (str.equals("I")) {
            return 34;
        }
        if (str.equals("J")) {
            return 35;
        }
        if (str.equals("K")) {
            return 36;
        }
        if (str.equals("L")) {
            return 37;
        }
        if (str.equals("M")) {
            return 38;
        }
        if (str.equals("N")) {
            return 39;
        }
        if (str.equals("O")) {
            return 40;
        }
        if (str.equals("P")) {
            return 41;
        }
        if (str.equals("Q")) {
            return 42;
        }
        if (str.equals("R")) {
            return 43;
        }
        if (str.equals(ExifInterface.LATITUDE_SOUTH)) {
            return 44;
        }
        if (str.equals("T")) {
            return 45;
        }
        if (str.equals("U")) {
            return 46;
        }
        if (str.equals(ExifInterface.GPS_MEASUREMENT_INTERRUPTED)) {
            return 47;
        }
        if (str.equals(ExifInterface.LONGITUDE_WEST)) {
            return 48;
        }
        if (str.equals("X")) {
            return 49;
        }
        if (str.equals("Y")) {
            return 50;
        }
        return str.equals("Z") ? 51 : -1;
    }

    public static boolean isWhatsAppInstall(Context context) {
        context.getPackageManager();
        try {
            return isAppInstalled(context, "com.whatsapp");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean isAppInstalled(Context context, String str) {
        try {
            for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(new Intent("android.intent.action.MAIN", (Uri) null), 0)) {
                if (resolveInfo.activityInfo.packageName.equals(str)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isBusinessWhatsAppInstall(Context context) {
        try {
            return isAppInstalled(context, "com.whatsapp.w4b");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
