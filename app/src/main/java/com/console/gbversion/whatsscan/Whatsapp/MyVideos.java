package com.console.gbversion.whatsscan.Whatsapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.apache.commons.io.comparator.LastModifiedFileComparator;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.console.gbversion.whatsscan.Application.Model.StatusModel;
import com.console.gbversion.whatsscan.R;

public class MyVideos extends Fragment implements MyStatusAdapter.OnCheckboxListener {
    LinearLayout actionLay;
    MyStatusAdapter adapter;
    LinearLayout deleteIV;
    RelativeLayout emptyLay;
    ArrayList<StatusModel> fileList = null;
    ArrayList<StatusModel> filesToDelete = new ArrayList<>();
    GridView gridView;
    RelativeLayout loaderLay;
    int save = 10;
    CheckBox selectAll;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.my_status_fragment, viewGroup, false);
        this.loaderLay = (RelativeLayout) inflate.findViewById(R.id.loaderLay);
        this.emptyLay = (RelativeLayout) inflate.findViewById(R.id.emptyLay);
        this.gridView = (GridView) inflate.findViewById(R.id.videoGrid);
        populateVideo();
        this.actionLay = (LinearLayout) inflate.findViewById(R.id.actionLay);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.deleteIV);
        this.deleteIV = linearLayout;
        linearLayout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (!MyVideos.this.filesToDelete.isEmpty()) {
                    new AlertDialog.Builder(MyVideos.this.getContext()).setMessage(MyVideos.this.getResources().getString(R.string.delete_alert)).setCancelable(true).setNegativeButton(MyVideos.this.getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {

                        @SuppressLint("WrongConstant")
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ArrayList arrayList = new ArrayList();
                            Iterator<StatusModel> it = MyVideos.this.filesToDelete.iterator();
                            char c = 65535;
                            while (it.hasNext()) {
                                StatusModel next = it.next();
                                File file = new File(next.getFilePath());
                                if (!file.exists() || !file.delete()) {
                                    c = 0;
                                } else {
                                    arrayList.add(next);
                                    if (c != 0) {
                                        c = 1;
                                    } else {
                                        return;
                                    }
                                }
                            }
                            MyVideos.this.filesToDelete.clear();
                            Iterator it2 = arrayList.iterator();
                            while (it2.hasNext()) {
                                MyVideos.this.fileList.remove((StatusModel) it2.next());
                            }
                            MyVideos.this.adapter.notifyDataSetChanged();
                            if (c == 0) {
                                Toast.makeText(MyVideos.this.getContext(), MyVideos.this.getResources().getString(R.string.delete_error), 0).show();
                            } else if (c == 1) {
                                Toast.makeText(MyVideos.this.getActivity(), MyVideos.this.getResources().getString(R.string.delete_success), 0).show();
                            }
                            MyVideos.this.actionLay.setVisibility(8);
                            MyVideos.this.selectAll.setChecked(false);
                        }
                    }).setPositiveButton(MyVideos.this.getResources().getString(R.string.no), new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create().show();
                }
            }
        });
        CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.selectAll);
        this.selectAll = checkBox;
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (compoundButton.isPressed()) {
                    MyVideos.this.filesToDelete.clear();
                    int i = 0;
                    while (true) {
                        if (i >= MyVideos.this.fileList.size()) {
                            break;
                        } else if (!MyVideos.this.fileList.get(i).selected) {
                            z = true;
                            break;
                        } else {
                            i++;
                        }
                    }
                    if (z) {
                        for (int i2 = 0; i2 < MyVideos.this.fileList.size(); i2++) {
                            MyVideos.this.fileList.get(i2).selected = true;
                            MyVideos.this.filesToDelete.add(MyVideos.this.fileList.get(i2));
                        }
                        MyVideos.this.selectAll.setChecked(true);
                    } else {
                        for (int i3 = 0; i3 < MyVideos.this.fileList.size(); i3++) {
                            MyVideos.this.fileList.get(i3).selected = false;
                        }
                        MyVideos.this.actionLay.setVisibility(8);
                    }
                    MyVideos.this.adapter.notifyDataSetChanged();
                }
            }
        });
        return inflate;
    }

    public void populateVideo() {
        new loadDataAsync().execute(new Void[0]);
    }

    public class loadDataAsync extends AsyncTask<Void, Void, Void> {
        loadDataAsync() {
        }

        public void onPreExecute() {
            super.onPreExecute();
            MyVideos.this.loaderLay.setVisibility(0);
        }

        public Void doInBackground(Void... voidArr) {
            MyVideos.this.getFromSdcard();
            return null;
        }

        public void onPostExecute(Void r4) {
            super.onPostExecute(r4);
            new Handler().postDelayed(new Runnable() {

                public final void run() {
                    loadDataAsync.this.lambda$onPostExecute$0$MyVideos$loadDataAsync();
                }
            }, 1000);
        }

        @SuppressLint("WrongConstant")
        public void lambda$onPostExecute$0$MyVideos$loadDataAsync() {
            if (MyVideos.this.fileList != null) {
                MyVideos myVideos = MyVideos.this;
                MyVideos myVideos2 = MyVideos.this;
                myVideos.adapter = new MyStatusAdapter(myVideos2, myVideos2.fileList, MyVideos.this);
                MyVideos.this.gridView.setAdapter((ListAdapter) MyVideos.this.adapter);
            }
            MyVideos.this.loaderLay.setVisibility(8);
            if (MyVideos.this.fileList == null || MyVideos.this.fileList.size() == 0) {
                MyVideos.this.emptyLay.setVisibility(0);
            } else {
                MyVideos.this.emptyLay.setVisibility(8);
            }
        }
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.adapter.onActivityResult(i, i2, intent);
        int i3 = this.save;
        if (i == i3 && i2 == i3) {
            this.adapter.notifyDataSetChanged();
            getFromSdcard();
            if (this.fileList != null) {
                MyStatusAdapter myStatusAdapter = new MyStatusAdapter(this, this.fileList, this);
                this.adapter = myStatusAdapter;
                this.gridView.setAdapter((ListAdapter) myStatusAdapter);
            }
            this.actionLay.setVisibility(8);
            this.selectAll.setChecked(false);
        }
    }

    public void getFromSdcard() {
        File file = new File(Environment.getExternalStorageDirectory().toString() + File.separator + "Download" + File.separator + getResources().getString(R.string.app_name) + "/Videos");
        if (file.isDirectory()) {
            this.fileList = new ArrayList<>();
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                Arrays.sort(listFiles, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
                for (File file2 : listFiles) {
                    this.fileList.add(new StatusModel(file2.getAbsolutePath()));
                }
            }
        }
    }

    @Override // com.nrpgroup.statussaver.adapter.MyStatusAdapter.OnCheckboxListener
    public void onCheckboxListener(View view, List<StatusModel> list) {
        this.filesToDelete.clear();
        for (StatusModel statusModel : list) {
            if (statusModel.isSelected()) {
                this.filesToDelete.add(statusModel);
            }
        }
        if (this.filesToDelete.size() == this.fileList.size()) {
            this.selectAll.setChecked(true);
        }
        if (!this.filesToDelete.isEmpty()) {
            this.actionLay.setVisibility(0);
            return;
        }
        this.selectAll.setChecked(false);
        this.actionLay.setVisibility(8);
    }
}