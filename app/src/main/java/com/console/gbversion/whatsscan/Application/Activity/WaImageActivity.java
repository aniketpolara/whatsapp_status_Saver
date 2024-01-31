package com.console.gbversion.whatsscan.Application.Activity;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.console.gbversion.whatsscan.Application.Adapter.ImagesAdapter;
import com.console.gbversion.whatsscan.Application.Model.Imagemodel;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.R;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class WaImageActivity extends AppCompatActivity {
    ArrayList<Imagemodel> imagelist = new ArrayList<>();
    private TextView noimage;
    private String path;
    private RecyclerView recycler;
    private String title;
    private Toolbar toolbar;
    TextView toolbar_text;

    public static List<File> getAllFiles(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.clear();
        File file = new File(str);
        if (file.listFiles() != null) {
            File[] listFiles = file.listFiles();
            for (File file2 : listFiles) {
                if (!file2.getName().equalsIgnoreCase(".nomedia")) {
                    arrayList.add(file2);
                }
            }
        }
        return arrayList;
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_image);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.noimage = (TextView) findViewById(R.id.noimage);
        this.toolbar_text = (TextView) findViewById(R.id.toolbar_text);
        this.recycler = (RecyclerView) findViewById(R.id.recycler);
        this.path = getIntent().getStringExtra("PATH");
        String stringExtra = getIntent().getStringExtra("TITLE");
        this.title = stringExtra;
        this.toolbar_text.setText(stringExtra);
        removeRefresh();
    }

    public void removeRefresh() {
        this.imagelist.clear();
        if (getAllFiles(this.path).size() > 0) {
            for (File file : getAllFiles(this.path)) {
                if (file.isFile()) {
                    String str = this.path;
                    str.substring(str.lastIndexOf(".") + 1);
                    this.imagelist.add(new Imagemodel(file.getAbsolutePath(), file.getName()));
                }
            }
            this.recycler.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
            if (this.imagelist.size() > 0) {
                this.noimage.setVisibility(8);
                this.recycler.setAdapter(new ImagesAdapter(this, this.imagelist));
                return;
            }
            this.noimage.setVisibility(0);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
                WaImageActivity.this.finish();
    }
}
