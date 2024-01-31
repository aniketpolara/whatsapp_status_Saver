package com.console.gbversion.whatsscan.Application.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.console.gbversion.whatsscan.Application.Model.y;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.Application.Utils.m;
import com.console.gbversion.whatsscan.R;
import java.io.File;
import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class WhatsappCleanerActivity extends AppCompatActivity {
    public TextView f670d;
    public String[] f672f;
    public long f677k;
    public GridView gridView;
    public ProgressDialog progressDialog;
    private Toolbar toolbar;
    public WhatsappCleanerAdapter whatsappCleanerAdapter;
    public ArrayList<y> yArrayList;

    public final long a(File file) {
        File[] listFiles = file.listFiles();
        int length = listFiles.length;
        long j = 0;
        for (int i = 0; i < length; i++) {
            j += listFiles[i].isFile() ? listFiles[i].length() : a(listFiles[i]);
        }
        return j;
    }

    public String b(long j) {
        if (j <= 0) {
            return "0";
        }
        double d = (double) j;
        int log10 = (int) (Math.log10(d) / Math.log10(1024.0d));
        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.#");
        double pow = Math.pow(1024.0d, (double) log10);
        Double.isNaN(d);
        sb.append(decimalFormat.format(d / pow));
        sb.append(" ");
        sb.append(new String[]{"B", "KB", "MB", "GB", "TB", "PB", "EB"}[log10]);
        return sb.toString();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_clearcache_whatsapp);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
//        this.toolbar = toolbar;
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.yArrayList = new ArrayList<>();
        this.gridView = (GridView) findViewById(R.id.gridviewDatas);
        this.f670d = (TextView) findViewById(R.id.no_data);
        ((ProgressBar) findViewById(R.id.progressbar)).setVisibility(View.GONE);
        ProgressDialog show = ProgressDialog.show(this, "", "Please wait...", true);
        this.progressDialog = show;
        if (show != null) {
            try {
                if (!show.isShowing()) {
                    this.progressDialog.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        new a(this).execute(new Void[0]);
    }

    public void CallIntent1(Context context, Class<?> cls, int i, String str) {
        Intent intent = new Intent(context, cls);
        intent.putExtra("PATH", this.yArrayList.get(i).b);
        intent.putExtra("TITLE", str);
        context.startActivity(intent);
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    /* loaded from: classes2.dex */
    public class a extends AsyncTask<Void, Void, String> {
        public a(WhatsappCleanerActivity whatsappCleanerActivity) {
            new WeakReference(whatsappCleanerActivity);
        }

        public String doInBackground(Void[] voidArr) {
            File file = new File(Environment.getExternalStorageDirectory().toString() + "/WhatsApp/Media/").exists() ? new File(Environment.getExternalStorageDirectory().toString() + "/WhatsApp/Media/") : new File("/storage/emulated/0/Android/media/com.whatsapp/WhatsApp/Media/");
            if (!file.exists()) {
                return null;
            }
            File[] listFiles = file.listFiles();
            long j = 0;
            if (listFiles == null) {
                return null;
            }
            for (File file2 : listFiles) {
                j += m.a(file2);
            }
            WhatsappCleanerActivity whatsappCleanerActivity = WhatsappCleanerActivity.this;
            whatsappCleanerActivity.f677k = j;
            whatsappCleanerActivity.f672f = new String[listFiles.length];
            for (int i = 0; i < listFiles.length; i++) {
                WhatsappCleanerActivity.this.f672f[i] = listFiles[i].getAbsolutePath();
                if (listFiles[i].getAbsolutePath().length() != 0) {
                    WhatsappCleanerActivity whatsappCleanerActivity2 = WhatsappCleanerActivity.this;
                    if (!whatsappCleanerActivity2.b(whatsappCleanerActivity2.a(new File(WhatsappCleanerActivity.this.f672f[i]))).equals("0") && !listFiles[i].getName().contains(".")) {
                        y yVar = new y();
                        yVar.b = listFiles[i].getAbsolutePath();
                        yVar.f438a = listFiles[i].getName();
                        WhatsappCleanerActivity whatsappCleanerActivity3 = WhatsappCleanerActivity.this;
                        yVar.c = whatsappCleanerActivity3.b(whatsappCleanerActivity3.a(new File(WhatsappCleanerActivity.this.f672f[i])));
                        WhatsappCleanerActivity.this.yArrayList.add(yVar);
                    }
                }
            }
            Log.d("ORANGE2121", "doInBackground: " + WhatsappCleanerActivity.this.yArrayList.size());
            return null;
        }

        @SuppressLint("WrongConstant")
        public void onPostExecute(String str) {
            TextView textView;
            int i;
            if (WhatsappCleanerActivity.this.yArrayList.size() == 0) {
                textView = WhatsappCleanerActivity.this.f670d;
                i = 0;
            } else {
                textView = WhatsappCleanerActivity.this.f670d;
                i = 8;
            }
            textView.setVisibility(i);
            WhatsappCleanerActivity whatsappCleanerActivity = WhatsappCleanerActivity.this;
            whatsappCleanerActivity.whatsappCleanerAdapter = new WhatsappCleanerAdapter(whatsappCleanerActivity, whatsappCleanerActivity.yArrayList);
            WhatsappCleanerActivity whatsappCleanerActivity2 = WhatsappCleanerActivity.this;
            whatsappCleanerActivity2.gridView.setAdapter((ListAdapter) whatsappCleanerActivity2.whatsappCleanerAdapter);
            try {
                ProgressDialog progressDialog = WhatsappCleanerActivity.this.progressDialog;
                if (progressDialog != null && progressDialog.isShowing()) {
                    WhatsappCleanerActivity.this.progressDialog.dismiss();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* loaded from: classes2.dex */
    public class WhatsappCleanerAdapter extends BaseAdapter {
        public Activity activity;
        int i3;
        public LayoutInflater layoutInflater;
        public ArrayList<y> yArrayList;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return (long) i;
        }

        public WhatsappCleanerAdapter(Activity activity, ArrayList<y> arrayList) {
            this.activity = activity;
            this.yArrayList = arrayList;
            this.layoutInflater = LayoutInflater.from(activity);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.yArrayList.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return Integer.valueOf(i);
        }

        @Override // android.widget.Adapter
        public View getView(final int i, View view, ViewGroup viewGroup) {
            RequestManager requestManager;
            View inflate = this.layoutInflater.inflate(R.layout.clear_cache_item, (ViewGroup) null, false);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.imageView);
            TextView textView = (TextView) inflate.findViewById(R.id.txt_name);
            TextView textView2 = (TextView) inflate.findViewById(R.id.txt_size);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.mainly);
            final String str = this.yArrayList.get(i).f438a;
            if (str.equals("WhatsApp Audio")) {
                requestManager = Glide.with(this.activity);
                this.i3 = R.drawable.img_audio;
            } else if (str.equals("WhatsApp Voice Notes")) {
                requestManager = Glide.with(this.activity);
                this.i3 = R.drawable.img_voicenote;
            } else if (str.equals("WhatsApp Documents")) {
                requestManager = Glide.with(this.activity);
                this.i3 = R.drawable.img_documents;
            } else if (str.equals("WhatsApp Animated Gifs")) {
                requestManager = Glide.with(this.activity);
                this.i3 = R.drawable.img_gif;
            } else {
                boolean equals = str.equals("WhatsApp Images");
                this.i3 = R.drawable.img_images;
                if (!equals && !str.equals("WhatsApp Profile Photos")) {
                    if (str.equals("WhatsApp Stickers")) {
                        Glide.with(this.activity);
                        this.i3 = R.drawable.img_stickers;
                    } else if (str.equals("WhatsApp Video")) {
                        Glide.with(this.activity);
                        this.i3 = R.drawable.img_videos;
                    }
                }
                requestManager = Glide.with(this.activity);
            }
            requestManager.load(Integer.valueOf(this.i3)).into(imageView);
            textView.setText(str);
            Log.d("BHOLOHH", "getView: " + this.yArrayList.get(i).c);
            textView2.setText(this.yArrayList.get(i).c);
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.WhatsappCleanerActivity.WhatsappCleanerAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (str.equals("WhatsApp Audio")) {
                        WhatsappCleanerActivity.this.CallIntent1(WhatsappCleanerActivity.this, WaAudioActivity.class, i, "Audio");
                    } else if (str.equals("WhatsApp Voice Notes")) {
                        WhatsappCleanerActivity.this.CallIntent1(WhatsappCleanerActivity.this, WaAudioActivity.class, i, "Voice Notes");
                    } else if (str.equals("WhatsApp Documents")) {
                        WhatsappCleanerActivity.this.CallIntent1(WhatsappCleanerActivity.this, WaDocsActivity.class, i, "Document");
                    } else if (str.equals("WhatsApp Animated Gifs")) {
                        WhatsappCleanerActivity.this.CallIntent1(WhatsappCleanerActivity.this, WaVideoActivity.class, i, "Gifs");
                    } else if (str.equals("WhatsApp Profile Photos")) {
                        WhatsappCleanerActivity.this.CallIntent1(WhatsappCleanerActivity.this, WaImageActivity.class, i, "Profile Photos");
                    } else if (str.equals("WhatsApp Stickers")) {
                        WhatsappCleanerActivity.this.CallIntent1(WhatsappCleanerActivity.this, WaImageActivity.class, i, "Stickers");
                    } else if (str.equals("WhatsApp Video")) {
                        WhatsappCleanerActivity.this.CallIntent1(WhatsappCleanerActivity.this, WaVideoActivity.class, i, "Videos");
                    } else if (str.equals("WhatsApp Images")) {
                        WhatsappCleanerActivity.this.CallIntent1(WhatsappCleanerActivity.this, WaImageActivity.class, i, "Images");
                    } else if (str.equals("WallPaper")) {
                        WhatsappCleanerActivity.this.CallIntent1(WhatsappCleanerActivity.this, WaImageActivity.class, i, "Wallpaper");
                    }
                }
            });
            return inflate;
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        WhatsappCleanerActivity.this.finish();
    }
}
