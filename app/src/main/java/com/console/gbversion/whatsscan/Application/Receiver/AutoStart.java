package com.console.gbversion.whatsscan.Application.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.console.gbversion.whatsscan.Application.Service.MediaListenerService;
import java.util.Objects;

/* loaded from: classes2.dex */
public class AutoStart extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            String action = intent.getAction();
            Objects.requireNonNull(action);
            if (action.equals("RestartService")) {
                context.startService(new Intent(context, MediaListenerService.class));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
