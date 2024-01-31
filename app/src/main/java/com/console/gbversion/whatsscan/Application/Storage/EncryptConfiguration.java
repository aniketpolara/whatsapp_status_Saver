package com.console.gbversion.whatsscan.Application.Storage;

import android.os.Build;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/* loaded from: classes2.dex */
public class EncryptConfiguration {
    private static final String TAG = "EncryptConfiguration";
    private int mChunkSize;
    private boolean mIsEncrypted;
    private byte[] mIvParameter;
    private byte[] mSecretKey;

    private EncryptConfiguration(Builder builder) {
        this.mChunkSize = builder._chunkSize;
        this.mIsEncrypted = builder._isEncrypted;
        this.mIvParameter = builder._ivParameter;
        this.mSecretKey = builder._secretKey;
    }

    public int getChuckSize() {
        return this.mChunkSize;
    }

    public boolean isEncrypted() {
        return this.mIsEncrypted;
    }

    public byte[] getSecretKey() {
        return this.mSecretKey;
    }

    public byte[] getIvParameter() {
        return this.mIvParameter;
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        private static final String UTF_8 = "UTF-8";
        private int _chunkSize = 8192;
        private boolean _isEncrypted = false;
        private byte[] _ivParameter = null;
        private byte[] _secretKey = null;

        public EncryptConfiguration build() {
            return new EncryptConfiguration(this);
        }

        public Builder setChuckSize(int i) {
            this._chunkSize = i;
            return this;
        }

        public Builder setEncryptContent(String str, String str2, byte[] bArr) {
            SecretKeyFactory secretKeyFactory;
            this._isEncrypted = true;
            try {
                this._ivParameter = str.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                Log.e(EncryptConfiguration.TAG, "UnsupportedEncodingException", e);
            }
            try {
                PBEKeySpec pBEKeySpec = new PBEKeySpec(str2.toCharArray(), bArr, 1000, 128);
                if (Build.VERSION.SDK_INT >= 19) {
                    secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1And8bit");
                } else {
                    secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
                }
                this._secretKey = secretKeyFactory.generateSecret(pBEKeySpec).getEncoded();
            } catch (NoSuchAlgorithmException e2) {
                Log.e(EncryptConfiguration.TAG, "NoSuchAlgorithmException", e2);
            } catch (InvalidKeySpecException e3) {
                Log.e(EncryptConfiguration.TAG, "InvalidKeySpecException", e3);
            }
            return this;
        }
    }
}
