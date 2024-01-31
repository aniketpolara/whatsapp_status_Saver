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
import com.console.gbversion.whatsscan.Application.Adapter.RecentAdapter;
import com.console.gbversion.whatsscan.Application.Model.StatusModel;
import com.console.gbversion.whatsscan.Application.Utils.SharedPrefs;
import com.console.gbversion.whatsscan.Application.Utils.Utils;
import com.console.gbversion.whatsscan.R;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class RecentWappBus extends Fragment implements RecentAdapter.OnCheckboxListener {
    LinearLayout actionLay;
    loadDataAsync async;
    LinearLayout deleteIV;
    LinearLayout downloadIV;
    RelativeLayout emptyLay;
    GridView imageGrid;
    RelativeLayout loaderLay;
    RecentAdapter myAdapter;
    LinearLayout sAccessBtn;
    CheckBox selectAll;
    SwipeRefreshLayout swipeToRefresh;
    int REQUEST_ACTION_OPEN_DOCUMENT_TREE = 1001;
    ArrayList<StatusModel> f = new ArrayList<>();
    ArrayList<StatusModel> filesToDelete = new ArrayList<>();

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.recent_fragment, viewGroup, false);
        this.loaderLay = (RelativeLayout) inflate.findViewById(R.id.loaderLay);
        this.emptyLay = (RelativeLayout) inflate.findViewById(R.id.emptyLay);
        this.imageGrid = (GridView) inflate.findViewById(R.id.WorkImageGrid);
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.id.swipeToRefresh);
        this.swipeToRefresh = swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.console.gbversion.whatsscan.Application.Fragment.RecentWappBus$$ExternalSyntheticLambda5

            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                lambda$onCreateView$0();
            }
        });
        this.actionLay = (LinearLayout) inflate.findViewById(R.id.actionLay);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.deleteIV);
        this.deleteIV = linearLayout;
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Fragment.RecentWappBus$$ExternalSyntheticLambda3

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                lambda$onCreateView$2(view);
            }
        });
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.downloadIV);
        this.downloadIV = linearLayout2;
        linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Fragment.RecentWappBus$$ExternalSyntheticLambda1

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                lambda$onCreateView$3(view);
            }
        });
        CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.selectAll);
        this.selectAll = checkBox;
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.console.gbversion.whatsscan.Application.Fragment.RecentWappBus$$ExternalSyntheticLambda4

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                lambda$onCreateView$4(compoundButton, z);
            }
        });
        LinearLayout linearLayout3 = (LinearLayout) inflate.findViewById(R.id.sAccessBtn);
        this.sAccessBtn = linearLayout3;
        linearLayout3.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Fragment.RecentWappBus$$ExternalSyntheticLambda2

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                lambda$onCreateView$5(view);
            }
        });
        if (!SharedPrefs.getWBTree(getActivity()).equals("")) {
            populateGrid();
        }
        return inflate;
    }

    public void lambda$onCreateView$0() {
        if (!SharedPrefs.getWBTree(getActivity()).equals("")) {
            Iterator<StatusModel> it = this.filesToDelete.iterator();
            while (it.hasNext()) {
                ArrayList<StatusModel> arrayList = this.f;
                it.next().selected = false;
                arrayList.contains(Boolean.FALSE);
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

    public void lambda$onCreateView$2(View view) {
        if (!this.filesToDelete.isEmpty()) {
            new AlertDialog.Builder(getContext()).setMessage(getResources().getString(R.string.delete_alert)).setCancelable(true).setNegativeButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Fragment.RecentWappBus$$ExternalSyntheticLambda0

                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    lambda$onCreateView$1(dialogInterface, i);
                }
            }).setPositiveButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Fragment.RecentWappBus.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).create().show();
        }
    }

    public void lambda$onCreateView$1(DialogInterface dialogInterface, int i) {
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
        this.actionLay.setVisibility(View.GONE);
        this.selectAll.setChecked(false);
    }

    public void lambda$onCreateView$3(View view) {
        if (!this.filesToDelete.isEmpty()) {
            char c = 65535;
            ArrayList arrayList = new ArrayList();
            Iterator<StatusModel> it = this.filesToDelete.iterator();
            while (it.hasNext()) {
                StatusModel next = it.next();
                if (!DocumentFile.fromSingleUri(getActivity(), Uri.parse(next.getFilePath())).exists() || !Utils.download(getActivity(), next.getFilePath())) {
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
                ArrayList<StatusModel> arrayList2 = this.f;
                ((StatusModel) it2.next()).selected = false;
                arrayList2.contains(Boolean.FALSE);
            }
            this.myAdapter.notifyDataSetChanged();
            if (c == 0) {
                Toast.makeText(getContext(), getResources().getString(R.string.save_error), 0).show();
            } else if (c == 1) {
                Toast.makeText(getActivity(), getResources().getString(R.string.save_success), 0).show();
            }
            this.actionLay.setVisibility(View.GONE);
        }
    }

    public void lambda$onCreateView$4(CompoundButton compoundButton, boolean z) {
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
                this.actionLay.setVisibility(View.GONE);
            }
            this.myAdapter.notifyDataSetChanged();
        }
    }

    public void lambda$onCreateView$5(View view) {
        Intent intent;
        if (Utils.appInstalledOrNot(getActivity(), "com.whatsapp.w4b")) {
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
        Toast.makeText(getActivity(), "Please Install WhatsApp Business For Download Status!!!!!", 0).show();
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
            this.actionLay.setVisibility(View.GONE);
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
            SharedPrefs.setWBTree(getActivity(), data.toString());
            populateGrid();
        }
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

    /* loaded from: classes2.dex */
    public class loadDataAsync extends AsyncTask<Void, Void, Void> {
        DocumentFile[] allFiles;

        loadDataAsync() {
//            RecentWappBus.this = r1;
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            super.onPreExecute();
            RecentWappBus.this.loaderLay.setVisibility(0);
            RecentWappBus.this.imageGrid.setVisibility(8);
            RecentWappBus.this.sAccessBtn.setVisibility(8);
            RecentWappBus.this.emptyLay.setVisibility(8);
        }

        public Void doInBackground(Void... voidArr) {
            this.allFiles = null;
            RecentWappBus.this.f = new ArrayList<>();
            DocumentFile[] fromSdcard = RecentWappBus.this.getFromSdcard();
            this.allFiles = fromSdcard;
            Arrays.sort(fromSdcard, new Comparator<DocumentFile>() { // from class: com.console.gbversion.whatsscan.Application.Fragment.RecentWappBus.loadDataAsync.1
                public int compare(DocumentFile documentFile, DocumentFile documentFile2) {
                    return Long.compare(documentFile2.lastModified(), documentFile.lastModified());
                }
            });
            int i = 0;
            while (true) {
                DocumentFile[] documentFileArr = this.allFiles;
                if (i >= documentFileArr.length - 1) {
                    return null;
                }
                if (!documentFileArr[i].getUri().toString().contains(".nomedia")) {
                    RecentWappBus.this.f.add(new StatusModel(this.allFiles[i].getUri().toString()));
                }
                i++;
            }
        }

        public int lambda$doInBackground$0(DocumentFile documentFile, DocumentFile documentFile2) {
            return Long.compare(documentFile2.lastModified(), documentFile.lastModified());
        }

        public void onPostExecute(Void r4) {
            super.onPostExecute(r4);
            new Handler().postDelayed(new RecentWappBus$loadDataAsync$$ExternalSyntheticLambda0(this), 1000);
        }

        public void lambda$onPostExecute$1() {
            if (RecentWappBus.this.getActivity() != null) {
                ArrayList<StatusModel> arrayList = RecentWappBus.this.f;
                if (!(arrayList == null || arrayList.size() == 0)) {
                    RecentWappBus recentWappBus = RecentWappBus.this;
                    recentWappBus.myAdapter = new RecentAdapter(recentWappBus, recentWappBus.f, recentWappBus);
                    RecentWappBus recentWappBus2 = RecentWappBus.this;
                    recentWappBus2.imageGrid.setAdapter((ListAdapter) recentWappBus2.myAdapter);
                    RecentWappBus.this.imageGrid.setVisibility(0);
                }
                RecentWappBus.this.loaderLay.setVisibility(8);
            }
            ArrayList<StatusModel> arrayList2 = RecentWappBus.this.f;
            if (arrayList2 == null || arrayList2.size() == 0) {
                RecentWappBus.this.emptyLay.setVisibility(0);
            } else {
                RecentWappBus.this.emptyLay.setVisibility(8);
            }
        }
    }

    public DocumentFile[] getFromSdcard() {
        DocumentFile fromTreeUri = DocumentFile.fromTreeUri(requireContext().getApplicationContext(), Uri.parse(SharedPrefs.getWBTree(getActivity())));
        if (fromTreeUri == null || !fromTreeUri.exists() || !fromTreeUri.isDirectory() || !fromTreeUri.canRead() || !fromTreeUri.canWrite()) {
            return null;
        }
        return fromTreeUri.listFiles();
    }

    public String getWhatsupFolder() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory());
        String str = File.separator;
        sb.append(str);
        sb.append("Android/media/com.whatsapp.w4b/WhatsApp Business");
        sb.append(str);
        sb.append("Media");
        sb.append(str);
        sb.append(".Statuses");
        return new File(sb.toString()).isDirectory() ? "Android%2Fmedia%2Fcom.whatsapp.w4b%2FWhatsApp Business%2FMedia%2F.Statuses" : "WhatsApp Business%2FMedia%2F.Statuses";
    }

    @Override // com.console.gbversion.whatsscan.Application.Adapter.RecentAdapter.OnCheckboxListener
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
