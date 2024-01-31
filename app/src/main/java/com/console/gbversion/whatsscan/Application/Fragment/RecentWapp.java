package com.console.gbversion.whatsscan.Application.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.storage.StorageManager;
import android.util.Log;
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
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.console.gbversion.whatsscan.Application.Adapter.RecentAdapter;
import com.console.gbversion.whatsscan.Application.Model.StatusModel;
import com.console.gbversion.whatsscan.Application.Utils.SharedPrefs;
import com.console.gbversion.whatsscan.Application.Utils.Utils;
import com.console.gbversion.whatsscan.R;

public class RecentWapp extends Fragment implements RecentAdapter.OnCheckboxListener {
    int REQUEST_ACTION_OPEN_DOCUMENT_TREE = 101;
    LinearLayout actionLay;
    loadDataAsync async;
    LinearLayout deleteIV;
    LinearLayout downloadIV;
    RelativeLayout emptyLay;
    ArrayList<StatusModel> f = new ArrayList<>();
    ArrayList<StatusModel> filesToDelete = new ArrayList<>();
    GridView imageGrid;
    RelativeLayout loaderLay;
    RecentAdapter myAdapter;
    LinearLayout sAccessBtn;
    CheckBox selectAll;
    SwipeRefreshLayout swipeToRefresh;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.recent_fragment, viewGroup, false);
        this.loaderLay = (RelativeLayout) inflate.findViewById(R.id.loaderLay);
        this.emptyLay = (RelativeLayout) inflate.findViewById(R.id.emptyLay);
        this.imageGrid = (GridView) inflate.findViewById(R.id.WorkImageGrid);
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.id.swipeToRefresh);
        this.swipeToRefresh = swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public final void onRefresh() {
                RecentWapp.this.lambda$onCreateView$0$RecentWapp();
            }
        });
        this.actionLay = (LinearLayout) inflate.findViewById(R.id.actionLay);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.deleteIV);
        this.deleteIV = linearLayout;
        linearLayout.setOnClickListener(new View.OnClickListener() {

            public final void onClick(View view) {
                RecentWapp.this.lambda$onCreateView$2$RecentWapp(view);
            }
        });

        this.downloadIV = (LinearLayout) inflate.findViewById(R.id.downloadIV);
        this.downloadIV.setOnClickListener(new View.OnClickListener() {

            public final void onClick(View view) {
                RecentWapp.this.lambda$onCreateView$3$RecentWapp(view);
            }
        });

        this.selectAll = (CheckBox) inflate.findViewById(R.id.selectAll);
        this.selectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                RecentWapp.this.lambda$onCreateView$4$RecentWapp(compoundButton, z);
            }
        });
        this.sAccessBtn = (LinearLayout) inflate.findViewById(R.id.sAccessBtn);
        this.sAccessBtn.setOnClickListener(new View.OnClickListener() {

            public final void onClick(View view) {
                RecentWapp.this.lambda$onCreateView$5$RecentWapp(view);
            }
        });
        if (!SharedPrefs.getWATree(getActivity()).equals("")) {
            populateGrid();
        }
        return inflate;
    }

    @SuppressLint("WrongConstant")
    public void lambda$onCreateView$0$RecentWapp() {
        if (!SharedPrefs.getWATree(getActivity()).equals("")) {
            Iterator<StatusModel> it = this.filesToDelete.iterator();
            while (it.hasNext()) {
                ArrayList<StatusModel> arrayList = this.f;
                it.next().selected = false;
                arrayList.contains(false);
            }
            RecentAdapter recentAdapter = this.myAdapter;
            if (recentAdapter != null) {
                recentAdapter.notifyDataSetChanged();
            }
            this.filesToDelete.clear();
            this.selectAll.setChecked(false);
            this.actionLay.setVisibility(8);
            populateGrid();
        }
        this.swipeToRefresh.setRefreshing(false);
    }

    public void lambda$onCreateView$2$RecentWapp(View view) {
        if (!this.filesToDelete.isEmpty()) {
            new AlertDialog.Builder(getContext()).setMessage(getResources().getString(R.string.delete_alert)).setCancelable(true).setNegativeButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {

                public final void onClick(DialogInterface dialogInterface, int i) {
                    RecentWapp.this.lambda$onCreateView$1$RecentWapp(dialogInterface, i);
                }
            }).setPositiveButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).create().show();
        }
    }

    @SuppressLint("WrongConstant")
    public  void lambda$onCreateView$1$RecentWapp(DialogInterface dialogInterface, int i) {
        ArrayList arrayList = new ArrayList();
        Iterator<StatusModel> it = this.filesToDelete.iterator();
        char c = 65535;
        while (it.hasNext()) {
            StatusModel next = it.next();
            DocumentFile fromSingleUri = DocumentFile.fromSingleUri(getActivity(), Uri.parse(next.getFilePath()));
            if (!fromSingleUri.exists() || !fromSingleUri.delete()) {
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
        this.filesToDelete.clear();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            this.f.remove((StatusModel) it2.next());
        }
        this.myAdapter.notifyDataSetChanged();
        if (c == 0) {
            Toast.makeText(getContext(), getResources().getString(R.string.delete_error), 0).show();
        } else if (c == 1) {
            Toast.makeText(getActivity(), getResources().getString(R.string.delete_success), 0).show();
        }
        this.actionLay.setVisibility(8);
        this.selectAll.setChecked(false);
    }

    @SuppressLint("WrongConstant")
    public void lambda$onCreateView$3$RecentWapp(View view) {
//        AdController.adCounter++;
//        AdController.showInterAd(getActivity(), (Intent) null, 0);
        if (!this.filesToDelete.isEmpty()) {
            char c = 65535;
            ArrayList arrayList = new ArrayList();
            Iterator<StatusModel> it = this.filesToDelete.iterator();
            while (it.hasNext()) {
                StatusModel next = it.next();
                if (DocumentFile.fromSingleUri(getActivity(), Uri.parse(next.getFilePath())).exists()) {
                    Log.e("again: ", new File(next.getFilePath()).getAbsolutePath());
                    if (Utils.download(getActivity(), next.getFilePath())) {
                        arrayList.add(next);
                        if (c != 0) {
                            c = 1;
                        } else {
                            return;
                        }
                    }
                }
            }
            this.filesToDelete.clear();
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                ArrayList<StatusModel> arrayList2 = this.f;
                ((StatusModel) it2.next()).selected = false;
                arrayList2.contains(false);
            }
            this.myAdapter.notifyDataSetChanged();
            if (c == 0) {
                Toast.makeText(getContext(), getResources().getString(R.string.save_error), 0).show();
            } else if (c == 1) {
                Toast.makeText(getActivity(), getResources().getString(R.string.save_success), 0).show();
            }
            this.actionLay.setVisibility(8);
            this.selectAll.setChecked(false);
        }
    }


    @SuppressLint("WrongConstant")
    public void lambda$onCreateView$4$RecentWapp(CompoundButton compoundButton, boolean z) {
//        AdController.adCounter++;
//        if (AdController.isLoadIronSourceAd) {
//            AdController.ironShowInterstitial(getActivity(), (Intent) null, 0);
//        } else {
//            AdController.showInterAd(getActivity(), (Intent) null, 0);
//        }
        if (compoundButton.isPressed()) {
            this.filesToDelete.clear();
            int i = 0;
            while (true) {
                if (i >= this.f.size()) {
                    break;
                } else if (!this.f.get(i).selected) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            if (z) {
                for (int i2 = 0; i2 < this.f.size(); i2++) {
                    this.f.get(i2).selected = true;
                    this.filesToDelete.add(this.f.get(i2));
                }
                this.selectAll.setChecked(true);
            } else {
                for (int i3 = 0; i3 < this.f.size(); i3++) {
                    this.f.get(i3).selected = false;
                }
                this.actionLay.setVisibility(8);
            }
            this.myAdapter.notifyDataSetChanged();
        }
    }

    @SuppressLint("WrongConstant")
    public  void lambda$onCreateView$5$RecentWapp(View view) {
        Intent intent;
        if (Utils.appInstalledOrNot(getActivity(), "com.whatsapp")) {
            StorageManager storageManager = (StorageManager) getActivity().getSystemService("storage");
            String whatsupFolder = getWhatsupFolder();
            if (Build.VERSION.SDK_INT >= 29) {
                intent = storageManager.getPrimaryStorageVolume().createOpenDocumentTreeIntent();
                String replace = ((Uri) intent.getParcelableExtra("android.provider.extra.INITIAL_URI")).toString().replace("/root/", "/document/");
                intent.putExtra("android.provider.extra.INITIAL_URI", Uri.parse(replace + "%3A" + whatsupFolder));
            } else {
                intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
                intent.putExtra("android.provider.extra.INITIAL_URI", Uri.parse("content://com.android.externalstorage.documents/document/primary%3A" + whatsupFolder));
            }
            intent.addFlags(2);
            intent.addFlags(1);
            intent.addFlags(128);
            intent.addFlags(64);
            startActivityForResult(intent, this.REQUEST_ACTION_OPEN_DOCUMENT_TREE);
            return;
        }
        Toast.makeText(getActivity(), "Please Install WhatsApp For Download Status!!!!!", 0).show();
    }

    public void populateGrid() {
        loadDataAsync loaddataasync = new loadDataAsync();
        this.async = loaddataasync;
        loaddataasync.execute(new Void[0]);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        loadDataAsync loaddataasync = this.async;
        if (loaddataasync != null) {
            loaddataasync.cancel(true);
        }
    }

    public class loadDataAsync extends AsyncTask<Void, Void, Void> {
        DocumentFile[] allFiles;

        loadDataAsync() {
        }

        @SuppressLint("WrongConstant")
        public void onPreExecute() {
            super.onPreExecute();
            RecentWapp.this.loaderLay.setVisibility(0);
            RecentWapp.this.imageGrid.setVisibility(8);
            RecentWapp.this.sAccessBtn.setVisibility(8);
            RecentWapp.this.emptyLay.setVisibility(8);
        }

        public Void doInBackground(Void... voidArr) {
            this.allFiles = null;
            RecentWapp.this.f = new ArrayList<>();
            DocumentFile[] fromSdcard = RecentWapp.this.getFromSdcard();
            this.allFiles = fromSdcard;
            Arrays.sort(fromSdcard, $$Lambda$RecentWapp$loadDataAsync$LQ2rgjWuRszdn5szn_GVJq1GMwA.INSTANCE);
            int i = 0;
            while (true) {
                DocumentFile[] documentFileArr = this.allFiles;
                if (i >= documentFileArr.length - 1) {
                    return null;
                }
                if (!documentFileArr[i].getUri().toString().contains(".nomedia")) {
                    RecentWapp.this.f.add(new StatusModel(this.allFiles[i].getUri().toString()));
                }
                i++;
            }
        }

        public void onPostExecute(Void r4) {
            super.onPostExecute(r4);
            new Handler().postDelayed(new Runnable() {

                public final void run() {
                    RecentWapp.loadDataAsync.this.lambda$onPostExecute$1$RecentWapp$loadDataAsync();
                }
            }, 1000);
        }

        @SuppressLint("WrongConstant")
        public void lambda$onPostExecute$1$RecentWapp$loadDataAsync() {
            if (RecentWapp.this.getActivity() != null) {
                if (!(RecentWapp.this.f == null || RecentWapp.this.f.size() == 0)) {
                    RecentWapp recentWapp = RecentWapp.this;
                    RecentWapp recentWapp2 = RecentWapp.this;
                    recentWapp.myAdapter = new RecentAdapter(recentWapp2, recentWapp2.f, RecentWapp.this);
                    RecentWapp.this.imageGrid.setAdapter((ListAdapter) RecentWapp.this.myAdapter);
                    RecentWapp.this.imageGrid.setVisibility(0);
                }
                RecentWapp.this.loaderLay.setVisibility(8);
            }
            if (RecentWapp.this.f == null || RecentWapp.this.f.size() == 0) {
                RecentWapp.this.emptyLay.setVisibility(0);
            } else {
                RecentWapp.this.emptyLay.setVisibility(8);
            }
        }
    }

    private DocumentFile[] getFromSdcard() {
        DocumentFile fromTreeUri = DocumentFile.fromTreeUri(requireContext().getApplicationContext(), Uri.parse(SharedPrefs.getWATree(getActivity())));
        if (fromTreeUri == null || !fromTreeUri.exists() || !fromTreeUri.isDirectory() || !fromTreeUri.canRead() || !fromTreeUri.canWrite()) {
            return null;
        }
        return fromTreeUri.listFiles();
    }

    public String getWhatsupFolder() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory());
        sb.append(File.separator);
        sb.append("Android/media/com.whatsapp/WhatsApp");
        sb.append(File.separator);
        sb.append("Media");
        sb.append(File.separator);
        sb.append(".Statuses");
        return new File(sb.toString()).isDirectory() ? "Android%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia%2F.Statuses" : "WhatsApp%2FMedia%2F.Statuses";
    }

    @SuppressLint("WrongConstant")
    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        RecentAdapter recentAdapter = this.myAdapter;
        if (recentAdapter != null) {
            recentAdapter.onActivityResult(i, i2, intent);
        }
        if (i == 10 && i2 == 10) {
            this.f = new ArrayList<>();
            DocumentFile[] fromSdcard = getFromSdcard();
            for (int i3 = 0; i3 < fromSdcard.length - 1; i3++) {
                if (!fromSdcard[i3].getUri().toString().contains(".nomedia")) {
                    this.f.add(new StatusModel(fromSdcard[i3].getUri().toString()));
                }
            }
            RecentAdapter recentAdapter2 = new RecentAdapter(this, this.f, this);
            this.myAdapter = recentAdapter2;
            this.imageGrid.setAdapter((ListAdapter) recentAdapter2);
            this.actionLay.setVisibility(8);
            this.selectAll.setChecked(false);
        }
        if (i == this.REQUEST_ACTION_OPEN_DOCUMENT_TREE && i2 == -1) {
            Uri data = intent.getData();
            Log.e("onActivityResult: ", "" + intent.getData());
            try {
                if (Build.VERSION.SDK_INT >= 19) {
                    requireContext().getContentResolver().takePersistableUriPermission(data, 3);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            SharedPrefs.setWATree(getActivity(), data.toString());
            populateGrid();
        }
    }

    @SuppressLint("WrongConstant")
    @Override // com.nrpgroup.statussaver.adapter.RecentAdapter.OnCheckboxListener
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