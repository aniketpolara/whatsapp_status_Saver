package com.console.gbversion.whatsscan.Application.Fragment;

import androidx.documentfile.provider.DocumentFile;

import java.util.Comparator;

public final class $$Lambda$RecentWapp$loadDataAsync$LQ2rgjWuRszdn5szn_GVJq1GMwA implements Comparator {
    public static final  $$Lambda$RecentWapp$loadDataAsync$LQ2rgjWuRszdn5szn_GVJq1GMwA INSTANCE = new $$Lambda$RecentWapp$loadDataAsync$LQ2rgjWuRszdn5szn_GVJq1GMwA();

    private $$Lambda$RecentWapp$loadDataAsync$LQ2rgjWuRszdn5szn_GVJq1GMwA() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return Long.compare(((DocumentFile) obj2).lastModified(), ((DocumentFile) obj).lastModified());
    }
}