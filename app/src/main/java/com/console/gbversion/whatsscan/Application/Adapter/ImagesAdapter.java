package com.console.gbversion.whatsscan.Application.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.console.gbversion.whatsscan.Application.Activity.WaImageActivity;
import com.console.gbversion.whatsscan.Application.Activity.WaShowImageActivity;
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
public class ImagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public ArrayList<Imagemodel> imagelist;
    public Activity mContext;
    private int positions;

    public ImagesAdapter(Activity activity, ArrayList<Imagemodel> arrayList) {
        this.mContext = activity;
        this.imagelist = arrayList;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.imagelist, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {
        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
        final Imagemodel imagemodel = this.imagelist.get(i);
        Glide.with(this.mContext).load(imagemodel.getPath()).into(myViewHolder.imag);
        myViewHolder.docname.setText(imagemodel.getName());
        myViewHolder.tv_filesize.setText(getStringSizeLengthFile(new File(imagemodel.getPath()).length()));
        myViewHolder.mainly.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Adapter.ImagesAdapter.1
            public void onClick(View view) {
                ImagesAdapter.this.positions = viewHolder.getAdapterPosition();
                        ImagesAdapter.this.CallIntent(ImagesAdapter.this.mContext, WaShowImageActivity.class);
            }
        });
        myViewHolder.del.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Adapter.ImagesAdapter.2
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
                ImagesAdapter.this.imagelist.remove(i);
                ImagesAdapter.this.notifyItemRemoved(i);
                Activity activity = ImagesAdapter.this.mContext;
                if (activity instanceof WaImageActivity) {
                    ((WaImageActivity) activity).removeRefresh();
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

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.imagelist.size();
    }

    public void CallIntent(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        intent.putExtra("IMAGE_PATH", this.imagelist.get(this.positions).getPath());
        intent.setFlags(67108864);
        this.mContext.startActivity(intent);
    }

    /* loaded from: classes2.dex */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView del;
        TextView docname;
        private final ImageView imag;
        private final RelativeLayout mainly;
        TextView tv_filesize;

        public MyViewHolder(View view) {
            super(view);
            this.imag = (ImageView) view.findViewById(R.id.image);
            this.del = (ImageView) view.findViewById(R.id.imgDelete);
            this.docname = (TextView) view.findViewById(R.id.docname);
            this.tv_filesize = (TextView) view.findViewById(R.id.tv_filesize);
            this.mainly = (RelativeLayout) view.findViewById(R.id.rl_image);
        }
    }
}
