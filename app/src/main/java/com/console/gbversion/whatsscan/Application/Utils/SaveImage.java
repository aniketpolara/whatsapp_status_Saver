package com.console.gbversion.whatsscan.Application.Utils;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;

/* loaded from: classes2.dex */
public class SaveImage extends AsyncTask<Void, Void, String> {
    private Bitmap bitmap;
    private String name;
    private SaveListener saveListener;

    /* loaded from: classes2.dex */
    public interface SaveListener {
        void onSaved(String str);
    }

    public SaveImage(String str, Bitmap bitmap) {
        this.name = str;
        this.bitmap = bitmap;
    }

    public void setSaveListener(SaveListener saveListener) {
        this.saveListener = saveListener;
    }

    public String doInBackground(Void... voidArr) {
        return saveImageFile(this.bitmap, this.name);
    }

    public void onPostExecute(String str) {
        super.onPostExecute(str);
        SaveListener saveListener = this.saveListener;
        if (saveListener != null) {
            saveListener.onSaved(str);
        }
    }

    private String saveImageFile(Bitmap bitmap, String str) {
        String filename = getFilename(str);
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(filename));
            return filename;
        }
        catch (Exception e) {
            try {
                e.printStackTrace();
                return filename;
            }
            catch (Exception e2) {
                e2.printStackTrace();
                return filename;
            }
        }
    }

    private String getFilename(String str) {
        File file = new File(Environment.getExternalStorageDirectory().getPath(), Constant.SAVE_TO);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (str.contains("/")) {
            str = str.replace("/", "\\");
        }
        return file.getAbsolutePath() + "/" + str + ".png";
    }
}
