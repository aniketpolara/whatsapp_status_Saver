package com.console.gbversion.whatsscan.Application.Utils;

import android.database.Cursor;

/* loaded from: classes2.dex */
public class ContentUtill {
    public static String getLong(Cursor cursor) {
        return "" + cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
    }
}
