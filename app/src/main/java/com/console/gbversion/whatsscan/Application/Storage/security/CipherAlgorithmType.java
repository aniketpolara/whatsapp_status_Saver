package com.console.gbversion.whatsscan.Application.Storage.security;

/* loaded from: classes2.dex */
public enum CipherAlgorithmType {
    AES("AES"),
    DES("DES"),
    DESede("DESede"),
    RSA("RSA");
    
    private String mName;

    CipherAlgorithmType(String str) {
        this.mName = str;
    }

    public String getAlgorithmName() {
        return this.mName;
    }
}
