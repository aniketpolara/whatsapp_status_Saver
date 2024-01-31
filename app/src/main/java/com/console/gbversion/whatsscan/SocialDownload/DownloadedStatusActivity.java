package com.console.gbversion.whatsscan.SocialDownload;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.console.gbversion.whatsscan.Application.Adapter.GalleryAdapter;
import com.console.gbversion.whatsscan.Application.Model.GalleryPojo;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.Application.Utils.GalleryFragmentHelper;
import com.console.gbversion.whatsscan.R;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class DownloadedStatusActivity extends AppCompatActivity {
    private final ArrayList<GalleryPojo> filesList = new ArrayList<>();
    private GalleryAdapter galleryAdapter;
    RecyclerView recyclerView;
    RelativeLayout rl_empty;
    SwipeRefreshLayout swipeRecyclerView;
    private Toolbar toolbar;

    public static int getData(File file, File file2) {
        if (file.lastModified() > file2.lastModified()) {
            return -1;
        }
        return file.lastModified() < file2.lastModified() ? 1 : 0;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_download_status);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.swipeRecyclerView = (SwipeRefreshLayout) findViewById(R.id.swipeRecyclerView);
        this.rl_empty = (RelativeLayout) findViewById(R.id.rl_empty);
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        setUpRecyclerView();
        setupSwipeRefresh();
        super.onResume();
    }

    private void setUpRecyclerView() {
        this.filesList.clear();
        this.filesList.addAll(getData());
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        GalleryAdapter galleryAdapter = new GalleryAdapter(this, this.filesList);
        this.galleryAdapter = galleryAdapter;
        this.recyclerView.setAdapter(galleryAdapter);
        RecyclerView.Adapter adapter = this.recyclerView.getAdapter();
        adapter.getClass();
        adapter.notifyDataSetChanged();
    }

    private ArrayList<GalleryPojo> getData() {
        ArrayList<GalleryPojo> arrayList = new ArrayList<>();
        File[] listFiles = new File(Environment.getExternalStorageDirectory().toString() + "/Download/Story Saver/Whatsapp/").listFiles();
        int i = 0;
        if (listFiles != null) {
            if (listFiles.length == 0) {
                this.rl_empty.setVisibility(0);
            } else {
                this.rl_empty.setVisibility(8);
            }
            try {
                Arrays.sort(listFiles, GalleryFragmentHelper.INSTANCE);
                while (i < listFiles.length) {
                    File file = listFiles[i];
                    GalleryPojo galleryPojo = new GalleryPojo();
                    StringBuilder sb = new StringBuilder();
                    sb.append("Saved Stories: ");
                    int i2 = i + 1;
                    sb.append(i2);
                    galleryPojo.setName(sb.toString());
                    galleryPojo.setFilename(file.getName());
                    galleryPojo.setUri(Uri.fromFile(file));
                    galleryPojo.setPath(listFiles[i].getAbsolutePath());
                    arrayList.add(galleryPojo);
                    i = i2;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.rl_empty.setVisibility(0);
        }
        return arrayList;
    }

    private void setupSwipeRefresh() {
        this.swipeRecyclerView.setColorSchemeColors(ContextCompat.getColor(this, R.color.background), ContextCompat.getColor(this, R.color.background));
        this.swipeRecyclerView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.console.gbversion.whatsscan.SocialDownload.DownloadedStatusActivity.1
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                DownloadedStatusActivity.this.lambda$setupSwipeRefresh$2$GalleryFragment();
            }
        });
    }

    public void lambda$setupSwipeRefresh$2$GalleryFragment() {
        this.swipeRecyclerView.setRefreshing(true);
        setUpRecyclerView();
        new Handler().postDelayed(new Runnable() { // from class: com.console.gbversion.whatsscan.SocialDownload.DownloadedStatusActivity.2
            @Override // java.lang.Runnable
            public final void run() {
                DownloadedStatusActivity.this.lambda$null$1$GalleryFragment();
            }
        }, 1500);
    }

    public void lambda$null$1$GalleryFragment() {
        this.swipeRecyclerView.setRefreshing(false);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }
}
