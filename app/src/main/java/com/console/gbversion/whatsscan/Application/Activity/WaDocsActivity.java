package com.console.gbversion.whatsscan.Application.Activity;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.console.gbversion.whatsscan.Application.Adapter.DocsAdapter;
import com.console.gbversion.whatsscan.Application.Model.Imagemodel;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.R;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class WaDocsActivity extends AppCompatActivity {
    ArrayList<Imagemodel> docs_list = new ArrayList<>();
    private String path;
    private RecyclerView recycler;
    private Toolbar toolbar;
    private TextView txt_empty;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_documents);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.txt_empty = (TextView) findViewById(R.id.txt_empty);
        this.recycler = (RecyclerView) findViewById(R.id.recycler);
        this.path = getIntent().getStringExtra("PATH");
        removeRefresh();
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public static List<File> getAllFiles(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.clear();
        File file = new File(str);
        if (file.listFiles() != null) {
            for (File file2 : file.listFiles()) {
                arrayList.add(file2);
            }
        }
        return arrayList;
    }

    public void removeRefresh() {
        this.docs_list.clear();
        if (getAllFiles(this.path).size() > 0) {
            for (File file : getAllFiles(this.path)) {
                if (file.isFile()) {
                    String str = this.path;
                    str.substring(str.lastIndexOf(".") + 1);
                    this.docs_list.add(new Imagemodel(file.getAbsolutePath(), file.getName()));
                }
            }
            this.recycler.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
            if (this.docs_list.size() > 0) {
                this.txt_empty.setVisibility(8);
                this.recycler.setAdapter(new DocsAdapter(this, this.docs_list));
                return;
            }
            this.txt_empty.setVisibility(0);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
                WaDocsActivity.this.finish();
    }
}
