package com.console.gbversion.whatsscan.Application.Activity;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.console.gbversion.whatsscan.Application.Adapter.VideosAdapter;
import com.console.gbversion.whatsscan.Application.Model.Imagemodel;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.R;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class WaVideoActivity extends AppCompatActivity {
    private TextView noimage;
    private String path;
    private RecyclerView recycler;
    private String title;
    private Toolbar toolbar;
    ArrayList<Imagemodel> video_list = new ArrayList<>();

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

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_video);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.noimage = (TextView) findViewById(R.id.noimage);
        this.recycler = (RecyclerView) findViewById(R.id.recycler);
        this.path = getIntent().getStringExtra("PATH");
        this.title = getIntent().getStringExtra("TITLE");
        removeRefresh();
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void removeRefresh() {
        this.video_list.clear();
        if (getAllFiles(this.path).size() > 0) {
            for (int i = 0; i < getAllFiles(this.path).size(); i++) {
                if (getAllFiles(this.path).get(i).isDirectory()) {
                    List<File> allFiles = getAllFiles(getAllFiles(this.path).get(i).getAbsolutePath());
                    for (int i2 = 0; i2 < allFiles.size(); i2++) {
                        if (allFiles.get(i2).isFile()) {
                            this.video_list.add(new Imagemodel(allFiles.get(i2).getAbsolutePath(), allFiles.get(i2).getName()));
                        }
                    }
                } else {
                    this.video_list.add(new Imagemodel(getAllFiles(this.path).get(i).getAbsolutePath(), getAllFiles(this.path).get(i).getName()));
                }
            }
            this.recycler.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
            if (this.video_list.size() > 0) {
                this.noimage.setVisibility(8);
                this.recycler.setAdapter(new VideosAdapter(this, this.video_list, this.title));
                return;
            }
            this.noimage.setVisibility(0);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
                WaVideoActivity.this.finish();
    }
}
