package com.console.gbversion.whatsscan.Application.Storage.security;

/* loaded from: classes2.dex */
public enum CipherPaddingType {
    NoPadding("NoPadding"),
    PKCS5Padding("PKCS5Padding"),
    PKCS1Padding("PKCS1Padding"),
    OAEPWithSHA_1AndMGF1Padding("OAEPWithSHA-1AndMGF1Padding"),
    OAEPWithSHA_256AndMGF1Padding("OAEPWithSHA-256AndMGF1Padding");
    
    private String mName;

    CipherPaddingType(String str) {
        this.mName = str;
    }

    public String getAlgorithmName() {
        return this.mName;
    }
}
