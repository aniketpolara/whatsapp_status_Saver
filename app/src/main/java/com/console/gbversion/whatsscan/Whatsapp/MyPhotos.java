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


public class MyPhotos extends Fragment implements MyStatusAdapter.OnCheckboxListener {
    LinearLayout actionLay;
    LinearLayout deleteIV;
    RelativeLayout emptyLay;
    ArrayList<StatusModel> f = new ArrayList<>();
    ArrayList<StatusModel> filesToDelete = new ArrayList<>();
    GridView imageGrid;
    RelativeLayout loaderLay;
    MyStatusAdapter myAdapter;
    int save = 10;
    CheckBox selectAll;


    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.my_status_fragment, viewGroup, false);
        this.loaderLay = (RelativeLayout) inflate.findViewById(R.id.loaderLay);
        this.emptyLay = (RelativeLayout) inflate.findViewById(R.id.emptyLay);
        this.imageGrid = (GridView) inflate.findViewById(R.id.videoGrid);

        populateGrid();

        this.actionLay = (LinearLayout) inflate.findViewById(R.id.actionLay);
        this.deleteIV = (LinearLayout) inflate.findViewById(R.id.deleteIV);
        this.deleteIV.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (!MyPhotos.this.filesToDelete.isEmpty()) {
                    new AlertDialog.Builder(MyPhotos.this.getContext()).setMessage(MyPhotos.this.getResources().getString(R.string.delete_alert)).setCancelable(true).setNegativeButton(MyPhotos.this.getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {

                        @SuppressLint("WrongConstant")
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ArrayList arrayList = new ArrayList();
                            Iterator<StatusModel> it = MyPhotos.this.filesToDelete.iterator();
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
                            MyPhotos.this.filesToDelete.clear();
                            Iterator it2 = arrayList.iterator();
                            while (it2.hasNext()) {
                                MyPhotos.this.f.remove((StatusModel) it2.next());
                            }
                            MyPhotos.this.myAdapter.notifyDataSetChanged();
                            if (c == 0) {
                                Toast.makeText(MyPhotos.this.getContext(), MyPhotos.this.getResources().getString(R.string.delete_error), 0).show();
                            } else if (c == 1) {
                                Toast.makeText(MyPhotos.this.getActivity(), MyPhotos.this.getResources().getString(R.string.delete_success), 0).show();
                            }
                            MyPhotos.this.actionLay.setVisibility(8);
                            MyPhotos.this.selectAll.setChecked(false);
                        }
                    }).setPositiveButton(MyPhotos.this.getResources().getString(R.string.no), new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create().show();
                }
            }
        });
        this.selectAll = (CheckBox) inflate.findViewById(R.id.selectAll);
        this.selectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (compoundButton.isPressed()) {
                    MyPhotos.this.filesToDelete.clear();
                    int i = 0;
                    while (true) {
                        if (i >= MyPhotos.this.f.size()) {
                            break;
                        } else if (!MyPhotos.this.f.get(i).selected) {
                            z = true;
                            break;
                        } else {
                            i++;
                        }
                    }
                    if (z) {
                        for (int i2 = 0; i2 < MyPhotos.this.f.size(); i2++) {
                            MyPhotos.this.f.get(i2).selected = true;
                            MyPhotos.this.filesToDelete.add(MyPhotos.this.f.get(i2));
                        }
                        MyPhotos.this.selectAll.setChecked(true);
                    } else {
                        for (int i3 = 0; i3 < MyPhotos.this.f.size(); i3++) {
                            MyPhotos.this.f.get(i3).selected = false;
                        }
                        MyPhotos.this.actionLay.setVisibility(8);
                    }
                    MyPhotos.this.myAdapter.notifyDataSetChanged();
                }
            }
        });
        return inflate;
    }

    public void populateGrid() {
        new loadDataAsync().execute(new Void[0]);
    }

    public class loadDataAsync extends AsyncTask<Void, Void, Void> {
        loadDataAsync() {
        }

        @SuppressLint("WrongConstant")
        public void onPreExecute() {
            super.onPreExecute();
            MyPhotos.this.loaderLay.setVisibility(0);
        }

        public Void doInBackground(Void... voidArr) {
            MyPhotos.this.getFromSdcard();
            return null;
        }

        public void onPostExecute(Void r4) {
            super.onPostExecute(r4);
            new Handler().postDelayed(new Runnable() {

                public final void run() {
                    loadDataAsync.this.lambda$onPostExecute$0$MyPhotos$loadDataAsync();
                }
            }, 1000);
        }

        @SuppressLint("WrongConstant")
        public  void lambda$onPostExecute$0$MyPhotos$loadDataAsync() {
            MyPhotos myPhotos = MyPhotos.this;
            MyPhotos myPhotos2 = MyPhotos.this;
            myPhotos.myAdapter = new MyStatusAdapter(myPhotos2, myPhotos2.f, MyPhotos.this);
            MyPhotos.this.imageGrid.setAdapter((ListAdapter) MyPhotos.this.myAdapter);
            MyPhotos.this.loaderLay.setVisibility(8);
            if (MyPhotos.this.f == null || MyPhotos.this.f.size() == 0) {
                MyPhotos.this.emptyLay.setVisibility(0);
            } else {
                MyPhotos.this.emptyLay.setVisibility(8);
            }
        }
    }

    public void getFromSdcard() {
        File file = new File(Environment.getExternalStorageDirectory().toString() + File.separator + "Download" + File.separator + getResources().getString(R.string.app_name) + "/Images");
        this.f = new ArrayList<>();
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            Arrays.sort(listFiles, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
            for (File file2 : listFiles) {
                this.f.add(new StatusModel(file2.getAbsolutePath()));
            }
        }
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.myAdapter.onActivityResult(i, i2, intent);
        int i3 = this.save;
        if (i == i3 && i2 == i3) {
            this.myAdapter.notifyDataSetChanged();
            getFromSdcard();
            MyStatusAdapter myStatusAdapter = new MyStatusAdapter(this, this.f, this);
            this.myAdapter = myStatusAdapter;
            this.imageGrid.setAdapter((ListAdapter) myStatusAdapter);
            this.actionLay.setVisibility(8);
            this.selectAll.setChecked(false);
        }
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onCheckboxListener(View view, List<StatusModel> list) {
        this.filesToDelete.clear();
        for (StatusModel statusModel : list) {
            if (statusModel.isSelected()) {
                this.filesToDelete.add(statusModel);
            }
        }
        if (this.filesToDelete.size() == this.f.size()) {
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