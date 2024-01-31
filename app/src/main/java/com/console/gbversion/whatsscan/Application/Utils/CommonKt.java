package com.console.gbversion.whatsscan.Application.Utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import com.console.gbversion.whatsscan.R;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class CommonKt {
    public static final boolean copyText(String str, Context context) {
        Intrinsics.checkNotNullParameter(str, "copyText");
        Intrinsics.checkNotNullParameter(context, "context");
        if (str.length() <= 0) {
            return false;
        }
        Object systemService = context.getSystemService("clipboard");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
        ((ClipboardManager) systemService).setPrimaryClip(ClipData.newPlainText(context.getResources().getString(R.string.copied_text), str));
        return true;
    }

    public static final boolean shareText(String str, Context context) {
        Intrinsics.checkNotNullParameter(str, "shareText");
        Intrinsics.checkNotNullParameter(context, "context");
        if (str.length() <= 0) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/*");
        intent.putExtra("android.intent.extra.TEXT", str);
        context.startActivity(Intent.createChooser(intent, context.getResources().getString(R.string.share_title)));
        return true;
    }
}
