package com.console.gbversion.whatsscan.Application.Receiver;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.service.notification.NotificationListenerService;
import com.console.gbversion.whatsscan.Application.Service.NLServices;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/* loaded from: classes2.dex */
public class DeleteAlarmReceiver extends BroadcastReceiver {
    Context mContext;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            this.mContext = context;
            tryReconnectService();
            File file = new File(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp Deleted/.cache/");
            file.mkdirs();
            File[] listFiles = file.listFiles();
            for (File file2 : listFiles) {
                if (file2.exists() && ((int) ((new Date().getTime() - file2.lastModified()) / 60000)) > 68) {
                    try {
                        deleteF(intent + file2.getName(), context);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void deleteF(String str, Context context) throws IOException {
        File file = new File(str);
        file.delete();
        if (file.exists()) {
            file.getCanonicalFile().delete();
            if (file.exists()) {
                context.deleteFile(file.getName());
            }
        }
    }

    public void tryReconnectService() {
        if (Build.VERSION.SDK_INT >= 24) {
            toggleNotificationListenerService();
            NotificationListenerService.requestRebind(new ComponentName(this.mContext.getApplicationContext(), NLServices.class));
        }
    }

    private void toggleNotificationListenerService() {
        PackageManager packageManager = this.mContext.getPackageManager();
        packageManager.setComponentEnabledSetting(new ComponentName(this.mContext, NLServices.class), 2, 1);
        packageManager.setComponentEnabledSetting(new ComponentName(this.mContext, NLServices.class), 1, 1);
    }
}
