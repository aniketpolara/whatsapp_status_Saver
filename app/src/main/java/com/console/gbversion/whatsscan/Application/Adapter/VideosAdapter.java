package com.console.gbversion.whatsscan.Application.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.console.gbversion.whatsscan.Application.Activity.WaVideoActivity;
import com.console.gbversion.whatsscan.Application.Activity.WaVideoPlayActivity;
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
public class VideosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Activity mContext;
    private int positions;
    public ArrayList<Imagemodel> videolist;

    public VideosAdapter(Activity activity, ArrayList<Imagemodel> arrayList, String str) {
        this.mContext = activity;
        this.videolist = arrayList;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.videolist, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {
        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
        final Imagemodel imagemodel = this.videolist.get(i);
        Glide.with(this.mContext).load(imagemodel.getPath()).into(myViewHolder.ivVideoItem);
        String stringSizeLengthFile = getStringSizeLengthFile(new File(imagemodel.getPath()).length());
        myViewHolder.vid_name.setText(imagemodel.getName());
        myViewHolder.tv_filesize.setText(stringSizeLengthFile);
        myViewHolder.cardItem.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Adapter.VideosAdapter.1
            public void onClick(View view) {
                VideosAdapter.this.positions = viewHolder.getAdapterPosition();
                        VideosAdapter.this.CallIntent(VideosAdapter.this.mContext, WaVideoPlayActivity.class);
            }
        });
        myViewHolder.imgDelete.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Adapter.VideosAdapter.2
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
                VideosAdapter.this.videolist.remove(i);
                VideosAdapter.this.notifyItemRemoved(i);
                ((WaVideoActivity) VideosAdapter.this.mContext).removeRefresh();
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

    public void CallIntent(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        intent.putExtra("uri", this.videolist.get(this.positions).getPath());
        intent.setFlags(67108864);
        this.mContext.startActivity(intent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.videolist.size();
    }

    /* loaded from: classes2.dex */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardItem;
        ImageView imgDelete;
        private final ImageView ivVideoItem;
        TextView tv_filesize;
        TextView vid_name;

        public MyViewHolder(View view) {
            super(view);
            this.ivVideoItem = (ImageView) view.findViewById(R.id.ivVideoItem);
            this.imgDelete = (ImageView) view.findViewById(R.id.imgDelete);
            this.cardItem = (CardView) view.findViewById(R.id.cardItem);
            this.vid_name = (TextView) view.findViewById(R.id.vid_name);
            this.tv_filesize = (TextView) view.findViewById(R.id.tv_filesize);
        }
    }
}
