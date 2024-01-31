package com.console.gbversion.whatsscan.Application.retrofit;

import android.content.Context;
import android.util.Log;
import com.console.gbversion.whatsscan.Application.Security.SecurePreferences;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class Constants {
    public static String SECRET_TOKEN_FOR_PREFERENCE_ENCRIPTION = "92b5a4026af9b6e0d3744ade2831c331ac1cac0dba308f74bc7b3fafe5de6028";
    public static final String IMAGE = "image";
    public static final String VIDEO = "video";
    public static final String AUDIO = "audio";

    public static SecurePreferences getSecurePreferences(Context context) {
        Context applicationContext = context.getApplicationContext();
        return new SecurePreferences(applicationContext, context.getPackageName() + "_preferences", getSecretKeyFrmToken(SECRET_TOKEN_FOR_PREFERENCE_ENCRIPTION), true);
    }

    public static String getSecretKeyFrmToken(String str) {
        try {
            String substring = str.substring(0, 16);
            return substring + str.substring(str.length() - 16, str.length());
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0014 A[Catch: SocketException -> 0x0054, TryCatch #0 {SocketException -> 0x0054, blocks: (B:2:0x0000, B:4:0x000e, B:6:0x0014, B:8:0x0020, B:9:0x0024, B:11:0x0042, B:13:0x004a), top: B:19:0x0000 }] */
    /* Code decompiled incorrectly, please refer to instructions dump */
    public static boolean isConnected() {
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            String str = "";
            while (it.hasNext()) {
                NetworkInterface networkInterface = (NetworkInterface) it.next();
                if (networkInterface.isUp()) {
                    str = networkInterface.getName();
                }
                Log.d("DEBUG", "IFACE NAME: " + str);
                if (str.contains("tun") || str.contains("ppp") || str.contains("pptp")) {
                    return true;
                }
                while (it.hasNext()) {
                }
            }
            return false;
        } catch (SocketException e) {
            e.printStackTrace();
            return false;
        }
    }
}
