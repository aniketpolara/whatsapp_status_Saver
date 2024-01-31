package com.console.gbversion.whatsscan.Application.Activity;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.console.gbversion.whatsscan.Application.Adapter.VoicesAdapter;
import com.console.gbversion.whatsscan.Application.Model.Imagemodel;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.R;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class WaAudioActivity extends AppCompatActivity {
    ArrayList<Imagemodel> audio_list = new ArrayList<>();
    private TextView no_data;
    private String path;
    private RecyclerView recycler;
    private String title;
    private AppCompatTextView toolbar_text;

    public static List<File> getAllFiles(String str) {
        ArrayList arrayList = new ArrayList();
        File file = new File(str);
        if (file.listFiles() != null) {
            for (File file2 : file.listFiles()) {
                arrayList.add(file2);
            }
        }
        return arrayList;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_audio);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        setSupportActionBar((Toolbar) findViewById(R.id.toolBar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.toolbar_text = (AppCompatTextView) findViewById(R.id.toolbar_text);
        TextView textView = (TextView) findViewById(R.id.no_data);
        this.no_data = textView;
        textView.setText("No Data Found");
        this.recycler = (RecyclerView) findViewById(R.id.recycler);
        this.path = getIntent().getStringExtra("PATH");
        String stringExtra = getIntent().getStringExtra("TITLE");
        this.title = stringExtra;
        this.toolbar_text.setText(stringExtra);
        removeData();
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void removeData() {
        if (getAllFiles(this.path).size() > 0) {
            for (File file : getAllFiles(this.path)) {
                if (file.isFile()) {
                    if (!file.getName().equals(".nomedia")) {
                        this.audio_list.add(new Imagemodel(file.getAbsolutePath(), file.getName()));
                    }
                } else if (file.isDirectory()) {
                    String path = file.getPath();
                    if (getAllFiles(path).size() > 0) {
                        for (File file2 : getAllFiles(path)) {
                            if (!file2.getName().equals(".nomedia")) {
                                this.audio_list.add(new Imagemodel(file2.getAbsolutePath(), file2.getName()));
                            }
                        }
                    }
                }
            }
        }
        this.recycler.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        if (this.audio_list.size() > 0) {
            this.no_data.setVisibility(8);
            this.recycler.setAdapter(new VoicesAdapter(this, this.audio_list, this.title));
            return;
        }
        this.no_data.setVisibility(0);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
                WaAudioActivity.this.finish();
    }
}
