package com.console.gbversion.whatsscan.Application.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.console.gbversion.whatsscan.Application.Activity.WaDocsActivity;
import com.console.gbversion.whatsscan.Application.Activity.WaImageActivity;
import com.console.gbversion.whatsscan.Application.Model.Imagemodel;
import com.console.gbversion.whatsscan.R;
import java.io.File;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

/*  JADX ERROR: UnsupportedOperationException in pass: OverrideMethodVisitor
    java.lang.UnsupportedOperationException
    	at jadx.core.utils.ImmutableList.listIterator(ImmutableList.java:198)
    	at java.util.AbstractList.equals(Unknown Source)
    	at jadx.core.dex.visitors.OverrideMethodVisitor.fixMethodArgTypes(OverrideMethodVisitor.java:297)
    	at jadx.core.dex.visitors.OverrideMethodVisitor.processMth(OverrideMethodVisitor.java:71)
    	at jadx.core.dex.visitors.OverrideMethodVisitor.processCls(OverrideMethodVisitor.java:56)
    	at jadx.core.dex.visitors.OverrideMethodVisitor.visit(OverrideMethodVisitor.java:48)
    */
/* loaded from: classes2.dex */
public class DocsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public ArrayList<Imagemodel> doclist;
    public Activity mContext;

    public DocsAdapter(Activity activity, ArrayList<Imagemodel> arrayList) {
        this.mContext = activity;
        this.doclist = arrayList;
    }

    /* loaded from: classes2.dex */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgDelete;
        private final RelativeLayout mainly;
        TextView tv_filesize;
        private final TextView txt_doc;

        public MyViewHolder(View view) {
            super(view);
            this.txt_doc = (TextView) view.findViewById(R.id.txt_doc);
            this.tv_filesize = (TextView) view.findViewById(R.id.tv_filesize);
            this.imgDelete = (ImageView) view.findViewById(R.id.imgDelete);
            this.mainly = (RelativeLayout) view.findViewById(R.id.mm);
            ImageView imageView = (ImageView) view.findViewById(R.id.pc);
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.doc_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
        final Imagemodel imagemodel = this.doclist.get(i);
        myViewHolder.mainly.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Adapter.DocsAdapter.1
            public void onClick(View view) {
                DocsAdapter.this.CallIntent(i);
            }
        });
        myViewHolder.txt_doc.setText(imagemodel.getName());
        myViewHolder.tv_filesize.setText(getStringSizeLengthFile(new File(imagemodel.getPath()).length()));
        myViewHolder.imgDelete.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Adapter.DocsAdapter.2
            public void onClick(View view) {
                File file = new File(imagemodel.getPath());
                if (file.exists()) {
                    if (file.delete()) {
                        PrintStream printStream = System.out;
                        printStream.println("file Deleted :" + imagemodel.getPath());
                    } else {
                        PrintStream printStream2 = System.out;
                        printStream2.println("file not Deleted :" + imagemodel.getPath());
                    }
                }
                DocsAdapter.this.doclist.remove(i);
                DocsAdapter.this.notifyItemRemoved(i);
                DocsAdapter.this.notifyDataSetChanged();
                Activity activity = DocsAdapter.this.mContext;
                if (activity instanceof WaImageActivity) {
                    ((WaDocsActivity) activity).removeRefresh();
                }
            }
        });
    }

    public String getStringSizeLengthFile(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        float f = (float) j;
        if (f < 1048576.0f) {
            return decimalFormat.format((double) (f / 1024.0f)) + " KB";
        } else if (f < 1.07374182E9f) {
            return decimalFormat.format((double) (f / 1048576.0f)) + " MB";
        } else if (f >= 1.09951163E12f) {
            return "";
        } else {
            return decimalFormat.format((double) (f / 1.07374182E9f)) + " GB";
        }
    }

    public void CallIntent(int i) {
        File file = new File(this.doclist.get(i).getPath());
        if (this.doclist.get(i).getPath().contains(".pdf")) {
            Intent intent = new Intent("android.intent.action.VIEW");
            Activity activity = this.mContext;
            intent.setDataAndType(FileProvider.getUriForFile(activity, this.mContext.getPackageName() + ".provider", file), "application/pdf");
            intent.addFlags(1);
            this.mContext.startActivity(intent);
        } else if (this.doclist.get(i).getPath().contains(".jpg") || this.doclist.get(i).getPath().contains(".jpeg") || this.doclist.get(i).getPath().contains(".png")) {
            Intent intent2 = new Intent("android.intent.action.VIEW");
            Activity activity2 = this.mContext;
            intent2.setDataAndType(FileProvider.getUriForFile(activity2, this.mContext.getPackageName() + ".provider", file), "image/*");
            intent2.addFlags(1);
            this.mContext.startActivity(intent2);
        } else if (this.doclist.get(i).getPath().contains(".pdf")) {
            Intent intent3 = new Intent("android.intent.action.VIEW");
            Activity activity3 = this.mContext;
            intent3.setDataAndType(FileProvider.getUriForFile(activity3, this.mContext.getPackageName() + ".provider", file), "video/*");
            intent3.addFlags(1);
            this.mContext.startActivity(intent3);
        } else if (this.doclist.get(i).getPath().contains(".docx")) {
            Intent intent4 = new Intent("android.intent.action.VIEW");
            Activity activity4 = this.mContext;
            intent4.setDataAndType(FileProvider.getUriForFile(activity4, this.mContext.getPackageName() + ".provider", file), "application/*");
            intent4.addFlags(1);
            this.mContext.startActivity(intent4);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.doclist.size();
    }
}
