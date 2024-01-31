package com.console.gbversion.whatsscan.Application.Utils;

import com.console.gbversion.whatsscan.SocialDownload.DownloadedStatusActivity;
import java.io.File;
import java.util.Comparator;

/* loaded from: classes2.dex */
public final class GalleryFragmentHelper implements Comparator {
    public static final GalleryFragmentHelper INSTANCE = new GalleryFragmentHelper();

    public GalleryFragmentHelper() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return DownloadedStatusActivity.getData((File) obj, (File) obj2);
    }
}
