package com.console.gbversion.whatsscan.Application.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;
import androidx.viewpager.widget.ViewPager;
import com.console.gbversion.whatsscan.Application.Adapter.PreviewAdapter;
import com.console.gbversion.whatsscan.Application.Model.StatusModel;
import com.console.gbversion.whatsscan.Application.Utils.SharedPrefs;
import com.console.gbversion.whatsscan.Application.Utils.Utils;
import com.console.gbversion.whatsscan.R;
import java.io.File;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class PreviewActivity extends AppCompatActivity {
    ImageView backIV;
    private View.OnClickListener clickListener = new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.PreviewActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.backIV:
                    PreviewActivity.this.onBackPressed();
                    return;
                case R.id.deleteIV:
                    if (PreviewActivity.this.imageList.size() > 0) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(PreviewActivity.this);
                        builder.setTitle(R.string.confirm);
                        builder.setMessage(R.string.del_status);
                        builder.setPositiveButton(PreviewActivity.this.getResources().getString(R.string.yes), new DialogInterface.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.PreviewActivity.1.1
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                if (PreviewActivity.this.statusDownload.equals("download")) {
                                    PreviewActivity previewActivity = PreviewActivity.this;
                                    File file = new File(previewActivity.imageList.get(previewActivity.viewPager.getCurrentItem()).getFilePath());
                                    if (file.exists()) {
                                        file.delete();
                                        PreviewActivity.this.delete(0);
                                        return;
                                    }
                                    return;
                                }
                                PreviewActivity previewActivity2 = PreviewActivity.this;
                                DocumentFile fromSingleUri = DocumentFile.fromSingleUri(previewActivity2, Uri.parse(previewActivity2.imageList.get(previewActivity2.viewPager.getCurrentItem()).getFilePath()));
                                if (fromSingleUri.exists()) {
                                    fromSingleUri.delete();
                                    PreviewActivity.this.delete(0);
                                }
                            }
                        });
                        builder.setNegativeButton(PreviewActivity.this.getResources().getString(R.string.no), new DialogInterface.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.PreviewActivity.1.2
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        builder.show();
                        return;
                    }
                    PreviewActivity.this.finish();
                    return;
                case R.id.downloadIV:
                    if (PreviewActivity.this.imageList.size() > 0) {
                        try {
                            PreviewActivity previewActivity = PreviewActivity.this;
                            Utils.download(previewActivity, previewActivity.imageList.get(previewActivity.viewPager.getCurrentItem()).getFilePath());
                            PreviewActivity previewActivity2 = PreviewActivity.this;
                            Toast.makeText(previewActivity2, previewActivity2.getResources().getString(R.string.saved_success), 0).show();
                            return;
                        } catch (Exception unused) {
                            Toast.makeText(PreviewActivity.this, "Sorry we can't move file.try with other file.", 1).show();
                            return;
                        }
                    } else {
                        PreviewActivity.this.finish();
                        return;
                    }
                case R.id.repostIV:
                    PreviewActivity previewActivity3 = PreviewActivity.this;
                    boolean isVideoFile = Utils.isVideoFile(previewActivity3, previewActivity3.imageList.get(previewActivity3.viewPager.getCurrentItem()).getFilePath());
                    PreviewActivity previewActivity4 = PreviewActivity.this;
                    Utils.repostWhatsApp(previewActivity3, isVideoFile, previewActivity4.imageList.get(previewActivity4.viewPager.getCurrentItem()).getFilePath());
                    return;
                case R.id.shareIV:
                    if (PreviewActivity.this.imageList.size() > 0) {
                        PreviewActivity previewActivity5 = PreviewActivity.this;
                        boolean isVideoFile2 = Utils.isVideoFile(previewActivity5, previewActivity5.imageList.get(previewActivity5.viewPager.getCurrentItem()).getFilePath());
                        PreviewActivity previewActivity6 = PreviewActivity.this;
                        Utils.shareFile(previewActivity5, isVideoFile2, previewActivity6.imageList.get(previewActivity6.viewPager.getCurrentItem()).getFilePath());
                        return;
                    }
                    PreviewActivity.this.finish();
                    return;
                default:
                    return;
            }
        }
    };
    LinearLayout deleteIV;
    LinearLayout downloadIV;
    ArrayList<StatusModel> imageList;
    int position;
    PreviewAdapter previewAdapter;
    LinearLayout shareIV;
    String statusDownload;
    ViewPager viewPager;
    LinearLayout wAppIV;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_preview);
        getWindow().setFlags(1024, 1024);
        Utils.setLanguage(this, SharedPrefs.getLang(this));
        this.backIV = (ImageView) findViewById(R.id.backIV);
        this.viewPager = (ViewPager) findViewById(R.id.viewPager);
        this.shareIV = (LinearLayout) findViewById(R.id.shareIV);
        this.downloadIV = (LinearLayout) findViewById(R.id.downloadIV);
        this.deleteIV = (LinearLayout) findViewById(R.id.deleteIV);
        this.wAppIV = (LinearLayout) findViewById(R.id.repostIV);
        this.imageList = getIntent().getParcelableArrayListExtra("images");
        this.position = getIntent().getIntExtra("position", 0);
        String stringExtra = getIntent().getStringExtra("statusdownload");
        this.statusDownload = stringExtra;
        if (stringExtra.equals("download")) {
            this.downloadIV.setVisibility(8);
        } else {
            this.downloadIV.setVisibility(0);
        }
        PreviewAdapter previewAdapter = new PreviewAdapter(this, this.imageList);
        this.previewAdapter = previewAdapter;
        this.viewPager.setAdapter(previewAdapter);
        this.viewPager.setCurrentItem(this.position);
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.PreviewActivity.2
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
            }
        });
        this.downloadIV.setOnClickListener(this.clickListener);
        this.shareIV.setOnClickListener(this.clickListener);
        this.deleteIV.setOnClickListener(this.clickListener);
        this.backIV.setOnClickListener(this.clickListener);
        this.wAppIV.setOnClickListener(this.clickListener);
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void delete(int i) {
        if (this.imageList.size() > 0 && this.viewPager.getCurrentItem() < this.imageList.size()) {
            i = this.viewPager.getCurrentItem();
        }
        this.imageList.remove(this.viewPager.getCurrentItem());
        PreviewAdapter previewAdapter = new PreviewAdapter(this, this.imageList);
        this.previewAdapter = previewAdapter;
        this.viewPager.setAdapter(previewAdapter);
        setResult(10, new Intent());
        if (this.imageList.size() > 0) {
            this.viewPager.setCurrentItem(i);
        } else {
            finish();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
                PreviewActivity.this.finish();
    }
}
