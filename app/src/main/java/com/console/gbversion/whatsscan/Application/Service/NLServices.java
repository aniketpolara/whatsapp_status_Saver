package com.console.gbversion.whatsscan.Application.Service;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.console.gbversion.whatsscan.Application.Activity.Message_Recover_Activity;
import com.console.gbversion.whatsscan.Application.DB.DBHelper;
import com.console.gbversion.whatsscan.Application.Model.Chats_Pojo;
import com.console.gbversion.whatsscan.Application.Model.Message_Pojo;
import com.console.gbversion.whatsscan.Application.Receiver.AutoStart;
import com.console.gbversion.whatsscan.Application.Receiver.DeleteAlarmReceiver;
import com.console.gbversion.whatsscan.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/* loaded from: classes2.dex */
public class NLServices extends NotificationListenerService {
    Context context;
    DBHelper db;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
        startDELAlarm();
        startServiceAlarm();
    }

    @Override // android.service.notification.NotificationListenerService
    public void onNotificationPosted(StatusBarNotification statusBarNotification) {
        String str;
        CharSequence[] charSequenceArr;
        int i;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8 = "";
        Log.i("Msg", "posted");
        String packageName = statusBarNotification.getPackageName();
        try {
            String charSequence = statusBarNotification.getNotification().tickerText.toString();
            if (packageName.equals("com.whatsapp") && !charSequence.equals("WhatsApp")) {
                Bundle bundle = statusBarNotification.getNotification().extras;
                try {
                    CharSequence charSequence2 = bundle.getCharSequence(NotificationCompat.EXTRA_TEXT);
                    Objects.requireNonNull(charSequence2);
                    str = charSequence2.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                    str = str8;
                }
                try {
                    str8 = bundle.getString(NotificationCompat.EXTRA_TITLE);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                String str9 = null;
                try {
                    charSequenceArr = bundle.getCharSequenceArray(NotificationCompat.EXTRA_TEXT_LINES);
                } catch (Exception e3) {
                    e3.printStackTrace();
                    charSequenceArr = null;
                }
                if (charSequenceArr != null) {
                    str9 = charSequenceArr[charSequenceArr.length - 1].toString();
                    if (charSequence.contains(" @ ") && !str9.contains(" @ ")) {
                        str9 = str8 + " @ " + str9;
                    }
                    Objects.requireNonNull(str8);
                    if (!str8.equals("WhatsApp")) {
                        str9 = str8 + ": " + str9;
                    }
                } else if (charSequence.contains(" @ ")) {
                    str9 = str8 + " @ " + str;
                } else {
                    try {
                        str9 = str8 + ": " + str;
                    } catch (Exception unused) {
                    }
                }
                Integer num = 1;
                if (str9.contains(" @ ") || str9.contains(": ")) {
                    Intent intent = new Intent("Msg");
                    int indexOf = str9.indexOf(": ", 0);
                    if (str9.substring(0, indexOf).contains(" @ ")) {
                        int indexOf2 = str9.indexOf(" @ ", 0);
                        str4 = str9.substring(0, indexOf2);
                        str2 = str9.substring(indexOf2 + 3, indexOf);
                        str3 = str9.substring(indexOf + 2, str9.length());
                        i = 1;
                    } else {
                        str2 = str9.substring(0, indexOf);
                        str3 = str9.substring(indexOf + 2, str9.length());
                        str4 = str2;
                        i = 0;
                    }
                    if (num.equals(1)) {
                        str5 = str4;
                        str6 = str2;
                    } else {
                        str6 = str4;
                        str5 = str2;
                    }
                    this.db = new DBHelper(this.context);
                    if (str3.equals("This round was deleted")) {
                        showNotification();
                        str7 = "Above round was deleted";
                    } else {
                        str7 = str3;
                    }
                    if (!this.db.Checkmsg(str5, str7) && !str5.equals("WhatsApp")) {
                        this.db.createCHAT(new Chats_Pojo(str5, i, getDateTime(), str7));
                        this.db.createMSG(new Message_Pojo(str6, i, str5, str7, getDateTime()));
                        this.db.closeDB();
                        Log.i("Msg", "Notification added");
                        LocalBroadcastManager.getInstance(this.context).sendBroadcast(intent);
                    }
                }
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    @Override // android.service.notification.NotificationListenerService
    public void onNotificationRemoved(StatusBarNotification statusBarNotification) {
        Log.i("Msg", "Notification Removed");
    }

    private String getDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS", Locale.getDefault()).format(new Date());
    }

    public void startDELAlarm() {
        ((AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM)).setInexactRepeating(0, Calendar.getInstance().getTimeInMillis(), 1800000, PendingIntent.getBroadcast(this, 0, new Intent(this, DeleteAlarmReceiver.class), 0));
    }

    public void startServiceAlarm() {
        Intent intent = new Intent(this, AutoStart.class);
        intent.setAction("RestartService");
        ((AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM)).setInexactRepeating(0, Calendar.getInstance().getTimeInMillis(), 300000, PendingIntent.getBroadcast(this, 0, intent, 0));
    }

    private void showNotification() {
        try {
            NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
            if (Build.VERSION.SDK_INT >= 26) {
                NotificationChannel notificationChannel = new NotificationChannel("extricks1", "extricks1", 4);
                notificationChannel.setDescription("YOUR_NOTIFICATION_CHANNEL_DISCRIPTION");
                notificationManager.createNotificationChannel(notificationChannel);
            }
            NotificationCompat.Builder autoCancel = new NotificationCompat.Builder(this, "extricks1").setSmallIcon(R.mipmap.ic_launcher).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)).setContentTitle("New Message Deleted").setContentText("Open to see the round!").setDefaults(-1).setPriority(1).setAutoCancel(true);
            autoCancel.setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, Message_Recover_Activity.class), 134217728));
            notificationManager.notify(0, autoCancel.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
