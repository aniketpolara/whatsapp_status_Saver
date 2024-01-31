package com.console.gbversion.whatsscan.Application.Utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import com.google.android.material.snackbar.Snackbar;
import com.console.gbversion.whatsscan.R;
import java.io.File;

/* loaded from: classes2.dex */
public class AppUtils {
    public static void showToast(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    public static void share(Activity activity, String str) {
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("image/*");
//        intent.setType("image/jpeg");
        intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(str)));
        activity.startActivity(Intent.createChooser(intent, "Share via"));
    }

    public static void copyToClipboard(Context context, String str) {
        ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", str));
        showToast(context, context.getResources().getString(R.string.copied));
    }

    public static void showSnackbar(Activity activity, String str) {
        Snackbar make = Snackbar.make(activity.findViewById(16908290), str, 0);
        View view = make.getView();
        view.setBackgroundColor(ContextCompat.getColor(activity, R.color.download));
        ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(ContextCompat.getColor(activity, 17170443));
        make.show();
    }
}
