package com.console.gbversion.whatsscan.Application.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import com.console.gbversion.whatsscan.Application.View.MediaObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* loaded from: classes2.dex */
public class MediaListenerService extends Service {
    List<MediaObserver> observers = new ArrayList();
    String[] paths = new String[7];

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        String[] strArr = this.paths;
        strArr[0] = Environment.getExternalStorageDirectory().toString() + "/WhatsApp/Media/WhatsApp Images/";
        String[] strArr2 = this.paths;
        strArr2[1] = Environment.getExternalStorageDirectory().toString() + "/WhatsApp/Media/WhatsApp Video/";
        String[] strArr3 = this.paths;
        strArr3[2] = Environment.getExternalStorageDirectory().toString() + "/WhatsApp/Media/WhatsApp Documents/";
        String[] strArr4 = this.paths;
        strArr4[3] = Environment.getExternalStorageDirectory().toString() + "/WhatsApp/Media/WhatsApp Audio/";
        String[] strArr5 = this.paths;
        strArr5[4] = Environment.getExternalStorageDirectory().toString() + "/WhatsApp/Media/WhatsApp Animated Gifs/";
        String[] strArr6 = this.paths;
        strArr6[5] = Environment.getExternalStorageDirectory().toString() + "/WhatsApp/Media/WhatsApp Stickers/";
        String[] strArr7 = this.paths;
        strArr7[6] = Environment.getExternalStorageDirectory().toString() + "/WhatsApp/Media/WhatsApp Voice Notes/";
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        List<MediaObserver> list = this.observers;
        if (list != null) {
            for (MediaObserver mediaObserver : list) {
                if (mediaObserver != null) {
                    mediaObserver.stopWatching();
                }
            }
        }
        String[] strArr = this.paths;
        for (String str : strArr) {
            List<MediaObserver> list2 = this.observers;
            Objects.requireNonNull(list2);
            list2.add(new MediaObserver(str, this));
        }
        List<MediaObserver> list3 = this.observers;
        Objects.requireNonNull(list3);
        for (MediaObserver mediaObserver2 : list3) {
            mediaObserver2.startWatching();
        }
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        sendBroadcast(new Intent("RestartService"));
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
        sendBroadcast(new Intent("RestartService"));
    }
}
