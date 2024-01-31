package com.console.gbversion.whatsscan.Application.Storage.security;

import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes2.dex */
public class SecurityUtil {
    private static final String TAG = "SecurityUtil";

    public static byte[] encrypt(byte[] bArr, int i, byte[] bArr2, byte[] bArr3) {
        if (bArr2.length == 16 && bArr3.length == 16) {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, CipherAlgorithmType.AES.getAlgorithmName());
                IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
                Cipher instance = Cipher.getInstance(CipherTransformationType.AES_CBC_PKCS5Padding);
                instance.init(i, secretKeySpec, ivParameterSpec);
                return instance.doFinal(bArr);
            } catch (InvalidAlgorithmParameterException e) {
                Log.e(TAG, "Failed to encrypt/descrypt - Invalid Algorithm Parameter", e);
                return null;
            } catch (InvalidKeyException e2) {
                Log.e(TAG, "Failed to encrypt/descrypt - Invalid Key", e2);
                return null;
            } catch (NoSuchAlgorithmException e3) {
                Log.e(TAG, "Failed to encrypt/descrypt - Unknown Algorithm", e3);
                return null;
            } catch (BadPaddingException e4) {
                Log.e(TAG, "Failed to encrypt/descrypt", e4);
                return null;
            } catch (IllegalBlockSizeException e5) {
                Log.e(TAG, "Failed to encrypt/descrypt", e5);
                return null;
            } catch (NoSuchPaddingException e6) {
                Log.e(TAG, "Failed to encrypt/descrypt- Unknown Padding", e6);
                return null;
            }
        } else {
            Log.w(TAG, "Set the encryption parameters correctly. The must be 16 length long each");
            return null;
        }
    }

    public String xor(String str, String str2) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            byte[] bytes2 = str2.getBytes("UTF-8");
            byte[] bArr = new byte[bytes.length];
            for (int i = 0; i < bytes.length; i++) {
                bArr[i] = (byte) (bytes[i] ^ bytes2[i % bytes2.length]);
            }
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }
}
