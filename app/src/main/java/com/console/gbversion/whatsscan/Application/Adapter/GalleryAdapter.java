package com.console.gbversion.whatsscan.Application.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.console.gbversion.whatsscan.Application.Model.GalleryPojo;
import com.console.gbversion.whatsscan.R;

import java.io.File;
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
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    private Activity activity;
    private ArrayList<GalleryPojo> filesList;
    private int positions;

    public GalleryAdapter(Activity activity, ArrayList<GalleryPojo> arrayList) {
        this.activity = activity;
        this.filesList = arrayList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_gallery_row, (ViewGroup) null, false));
    }

    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final GalleryPojo galleryPojo = this.filesList.get(i);
        new File(Uri.parse(galleryPojo.getUri().toString()).getPath());
        if (galleryPojo.getUri().toString().endsWith(".mp4")) {
            viewHolder.img_play.setVisibility(0);
        } else if (galleryPojo.getUri().toString().endsWith(".jpg") || galleryPojo.getUri().toString().endsWith(".png")) {
            viewHolder.img_play.setVisibility(8);
        }
        Glide.with(this.activity).load(galleryPojo.getUri()).into(viewHolder.ivMainImage);
        viewHolder.cv_item.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Adapter.GalleryAdapter.1
            public void onClick(View view) {
                GalleryAdapter.this.positions = viewHolder.getAdapterPosition();
                if (galleryPojo.getUri().toString().endsWith(".mp4")) {
                    GalleryAdapter.this.CallIntent(GalleryAdapter.this.activity, DownloadVideoPlayActivity.class, 1);
                } else if (galleryPojo.getUri().toString().endsWith(".jpg") || galleryPojo.getUri().toString().endsWith(".png")) {
                    GalleryAdapter.this.CallIntent(GalleryAdapter.this.activity, DownloadImgDisplayActivity.class, 2);
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.filesList.size();
    }

    /* loaded from: classes2.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv_item;
        ImageView img_play;
        AppCompatImageView ivMainImage;

        ViewHolder(GalleryAdapter galleryAdapter, View view) {
            super(view);
            this.ivMainImage = (AppCompatImageView) view.findViewById(R.id.ivMainImage);
            AppCompatImageView appCompatImageView = (AppCompatImageView) view.findViewById(R.id.ivDelete);
            this.img_play = (ImageView) view.findViewById(R.id.img_play);
            this.cv_item = (CardView) view.findViewById(R.id.cv_item);
        }
    }

    public void CallIntent(final Activity activity, Class<?> cls, int i) {
        if (i == 1) {
            final Intent intent = new Intent(activity, cls);
            intent.putExtra("FILEPATH", this.filesList.get(this.positions).getPath());
            intent.putExtra("FILENAME", this.filesList.get(this.positions).getFilename());
            activity.startActivity(intent);
        } else if (i == 2) {
            final Intent intent2 = new Intent(activity, DownloadImgDisplayActivity.class);
            intent2.putExtra("FILEPATH", this.filesList.get(this.positions).getPath());
            intent2.putExtra("FILENAME", this.filesList.get(this.positions).getFilename());
            activity.startActivity(intent2);
        }
    }
}
