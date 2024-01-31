package com.console.gbversion.whatsscan.Application.Storage.security;

/* loaded from: classes2.dex */
public enum CipherModeType {
    CBC("CBC"),
    ECB("ECB");
    
    private String mName;

    CipherModeType(String str) {
        this.mName = str;
    }

    public String getAlgorithmName() {
        return this.mName;
    }
}
