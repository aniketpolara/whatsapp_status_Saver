package com.console.gbversion.whatsscan.Application.Storage.helpers;

import java.text.DecimalFormat;
import org.apache.commons.io.FileUtils;

/* loaded from: classes2.dex */
public enum SizeUnit {
    B(1),
    KB(1024),
    MB(1048576),
    GB(FileUtils.ONE_GB),
    TB(0);
    
    private static final int BYTES = 1024;
    private long inBytes;

    SizeUnit(long j) {
        this.inBytes = j;
    }

    public long inBytes() {
        return this.inBytes;
    }

    public static String readableSizeUnit(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        SizeUnit sizeUnit = KB;
        if (j < sizeUnit.inBytes()) {
            return decimalFormat.format((double) (((float) j) / ((float) B.inBytes()))) + " B";
        }
        SizeUnit sizeUnit2 = MB;
        if (j < sizeUnit2.inBytes()) {
            return decimalFormat.format((double) (((float) j) / ((float) sizeUnit.inBytes()))) + " KB";
        }
        SizeUnit sizeUnit3 = GB;
        if (j < sizeUnit3.inBytes()) {
            return decimalFormat.format((double) (((float) j) / ((float) sizeUnit2.inBytes()))) + " MB";
        }
        return decimalFormat.format((double) (((float) j) / ((float) sizeUnit3.inBytes()))) + " GB";
    }
}
