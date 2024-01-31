package com.console.gbversion.whatsscan.Application.Utils;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import androidx.core.view.ViewCompat;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class CodeGenerator extends AsyncTask<Void, Void, Bitmap> {
    private static int TYPE;
    private String input;
    private ResultListener resultListener;

    /* loaded from: classes2.dex */
    public interface ResultListener {
        void onResult(Bitmap bitmap);
    }

    public void generateQRFor(String str) {
        this.input = str;
        TYPE = 0;
    }

    public void generateBarFor(String str) {
        this.input = str;
        TYPE = 1;
    }

    public void setResultListener(ResultListener resultListener) {
        this.resultListener = resultListener;
    }

    public Bitmap doInBackground(Void... voidArr) {
        try {
            if (TYPE == 0) {
                return createQRCode(this.input);
            }
            return createBarcode(this.input);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        ResultListener resultListener = this.resultListener;
        if (resultListener != null) {
            resultListener.onResult(bitmap);
        }
    }

    private Bitmap createQRCode(String str) throws WriterException {
        try {
            BitMatrix encode = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, 1080, 1080, null);
            int width = encode.getWidth();
            int height = encode.getHeight();
            int[] iArr = new int[width * height];
            for (int i = 0; i < height; i++) {
                int i2 = i * width;
                for (int i3 = 0; i3 < width; i3++) {
                    iArr[i2 + i3] = encode.get(i3, i) ? ViewCompat.MEASURED_STATE_MASK : -1;
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            createBitmap.setPixels(iArr, 0, 1080, 0, 0, width, height);
            return createBitmap;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    private Bitmap createBarcode(String str) throws WriterException {
        BitMatrix encode = new MultiFormatWriter().encode(Uri.encode(str), BarcodeFormat.CODE_128, 1080, 1);
        int width = encode.getWidth();
        Bitmap createBitmap = Bitmap.createBitmap(width, 640, Bitmap.Config.ARGB_8888);
        for (int i = 0; i < width; i++) {
            int[] iArr = new int[640];
            Arrays.fill(iArr, encode.get(i, 0) ? ViewCompat.MEASURED_STATE_MASK : -1);
            createBitmap.setPixels(iArr, 0, 1, i, 0, 1, 640);
        }
        return createBitmap;
    }
}
