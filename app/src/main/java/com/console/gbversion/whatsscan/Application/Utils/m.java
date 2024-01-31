package com.console.gbversion.whatsscan.Application.Utils;

import java.io.File;
import java.nio.charset.Charset;

/* loaded from: classes2.dex */
public class m {
    static {
        Charset.forName("UTF-8");
    }

    public static long a(File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException(file + " does not exist");
        } else if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            long j = 0;
            if (listFiles == null) {
                return 0;
            }
            for (File file2 : listFiles) {
                if (file2.exists()) {
                    j += file2.isDirectory() ? a(file2) : file2.length();
                } else {
                    throw new IllegalArgumentException(file2 + " does not exist");
                }
            }
            return j;
        } else {
            throw new IllegalArgumentException(file + " is not a directory");
        }
    }
}
