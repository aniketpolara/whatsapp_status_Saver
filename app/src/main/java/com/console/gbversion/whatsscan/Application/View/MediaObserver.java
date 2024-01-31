package com.console.gbversion.whatsscan.Application.View;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.FileObserver;
import android.os.Handler;
import android.os.Looper;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.console.gbversion.whatsscan.Application.Activity.Message_Recover_Activity;
import com.console.gbversion.whatsscan.R;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes2.dex */
public class MediaObserver extends FileObserver {
    private Context mContext;
    private String mpath;
    private final String pathToSave = Environment.getExternalStorageDirectory().toString() + "/WhatsApp Deleted/.cache/";
    private final String pathToSave1 = Environment.getExternalStorageDirectory().toString() + "/WhatsApp Deleted/";

    public MediaObserver(String str, Context context) {
        super(str, 4095);
        this.mpath = str;
        this.mContext = context;
    }

    @Override // android.os.FileObserver
    public void onEvent(int i, final String str) {
        if ((i & 128) != 0) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.console.gbversion.whatsscan.Application.View.MediaObserver.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        MediaObserver mediaObserver = MediaObserver.this;
                        File file = new File(MediaObserver.this.mpath + str);
                        mediaObserver.CopyFile(file, new File(MediaObserver.this.pathToSave + str + ".tmp"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
        }
        if ((i & 512) != 0) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.console.gbversion.whatsscan.Application.View.MediaObserver.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        MediaObserver mediaObserver = MediaObserver.this;
                        File file = new File(MediaObserver.this.pathToSave + str + ".tmp");
                        StringBuilder sb = new StringBuilder();
                        sb.append(MediaObserver.this.pathToSave1);
                        sb.append(str);
                        mediaObserver.CopyFile(file, new File(sb.toString()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    try {
                        MediaObserver mediaObserver2 = MediaObserver.this;
                        mediaObserver2.deleteF(MediaObserver.this.pathToSave + str + ".tmp");
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    MediaObserver.this.showNotification(str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void CopyFile(File file, File file2) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read > 0) {
                fileOutputStream.write(bArr, 0, read);
            } else {
                fileOutputStream.close();
                fileInputStream.close();
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteF(String str) throws IOException {
        File file = new File(str);
        file.delete();
        if (file.exists()) {
            file.getCanonicalFile().delete();
            if (file.exists()) {
                this.mContext.deleteFile(file.getName());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNotification(String str) {
        try {
            NotificationManager notificationManager = (NotificationManager) this.mContext.getSystemService("notification");
            if (Build.VERSION.SDK_INT >= 26) {
                NotificationChannel notificationChannel = new NotificationChannel("extricks1", "extricks1", 4);
                notificationChannel.setDescription("YOUR_NOTIFICATION_CHANNEL_DISCRIPTION");
                notificationManager.createNotificationChannel(notificationChannel);
            }
            NotificationCompat.Builder autoCancel = new NotificationCompat.Builder(this.mContext, "extricks1").setSmallIcon(R.mipmap.ic_launcher).setLargeIcon(BitmapFactory.decodeResource(this.mContext.getResources(), R.mipmap.ic_launcher)).setContentTitle("New Message Deleted").setContentText(str).setDefaults(-1).setPriority(1).setAutoCancel(true);
            Intent intent = new Intent(this.mContext, Message_Recover_Activity.class);
            intent.putExtra("mF", "mediaF");
            autoCancel.setContentIntent(PendingIntent.getActivity(this.mContext, 0, intent, 134217728));
            notificationManager.notify(0, autoCancel.build());
            LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(new Intent("Media"));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
