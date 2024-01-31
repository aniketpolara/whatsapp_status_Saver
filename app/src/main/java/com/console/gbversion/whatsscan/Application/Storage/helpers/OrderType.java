package com.console.gbversion.whatsscan.Application.Storage.helpers;

import java.io.File;
import java.util.Comparator;

/* loaded from: classes2.dex */
public enum OrderType {
    NAME,
    DATE,
    SIZE;

    public Comparator<File> getComparator() {
        int ordinal = ordinal();
        if (ordinal == 0) {
            return new Comparator<File>() { // from class: com.snatik.storage.helpers.OrderType.1
                public int compare(File file, File file2) {
                    return file.getName().compareTo(file2.getName());
                }
            };
        }
        if (ordinal == 1) {
            return new Comparator<File>() { // from class: com.snatik.storage.helpers.OrderType.2
                public int compare(File file, File file2) {
                    return (int) (file2.lastModified() - file.lastModified());
                }
            };
        }
        if (ordinal != 2) {
            return null;
        }
        return new Comparator<File>() { // from class: com.snatik.storage.helpers.OrderType.3
            public int compare(File file, File file2) {
                return (int) (file.length() - file2.length());
            }
        };
    }
}
