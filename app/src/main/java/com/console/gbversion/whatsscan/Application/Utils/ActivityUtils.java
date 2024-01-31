package com.console.gbversion.whatsscan.Application.Utils;

import android.app.Activity;
import android.content.Intent;

/* loaded from: classes2.dex */
public class ActivityUtils {
    private static ActivityUtils sActivityUtils;

    public static ActivityUtils getInstance() {
        if (sActivityUtils == null) {
            sActivityUtils = new ActivityUtils();
        }
        return sActivityUtils;
    }

    public void invokeActivity(Activity activity, Class<?> cls, boolean z) {
        activity.startActivity(new Intent(activity, cls));
        if (z) {
            activity.finish();
        }
    }
}
