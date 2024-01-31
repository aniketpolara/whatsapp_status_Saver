package com.console.gbversion.whatsscan.Application.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.console.gbversion.whatsscan.Application.Activity.WaAudioActivity;
import com.console.gbversion.whatsscan.Application.Activity.WaPlayAudioActivity;
import com.console.gbversion.whatsscan.Application.Model.Imagemodel;
import com.console.gbversion.whatsscan.R;
import java.io.File;
import java.io.PrintStream;
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
public class VoicesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public ArrayList<Imagemodel> audiolist;
    public Activity mContext;
    private int positions;
    private final String titlename;

    public VoicesAdapter(Activity activity, ArrayList<Imagemodel> arrayList, String str) {
        this.mContext = activity;
        this.audiolist = arrayList;
        this.titlename = str;
        Log.d("MyData", "MyGridAdapter: " + arrayList.size());
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.voicelist, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int i) {
        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
        final Imagemodel imagemodel = this.audiolist.get(i);
        if (this.titlename.equalsIgnoreCase("Audio")) {
            Glide.with(this.mContext).load(Integer.valueOf((int) R.drawable.img_audio)).into(myViewHolder.img_icon);
        } else {
            Glide.with(this.mContext).load(Integer.valueOf((int) R.drawable.img_voicenote)).into(myViewHolder.img_icon);
        }
        myViewHolder.rl_main.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Adapter.VoicesAdapter.1
            public void onClick(View view) {
                VoicesAdapter.this.positions = viewHolder.getAdapterPosition();
                        VoicesAdapter.this.CallIntent(VoicesAdapter.this.mContext, WaPlayAudioActivity.class);
            }
        });
        myViewHolder.songname.setText(imagemodel.getName().split("\\.")[0]);
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(String.valueOf(Uri.parse(imagemodel.getPath())));
            long parseLong = Long.parseLong(mediaMetadataRetriever.extractMetadata(9));
            String valueOf = String.valueOf((parseLong % 60000) / 1000);
            Log.v("seconds", valueOf);
            String valueOf2 = String.valueOf(parseLong / 60000);
            String str = "0" + valueOf2 + ":" + valueOf;
            if (valueOf.length() == 1) {
                myViewHolder.songtime.setText("0" + valueOf2 + ":0" + valueOf);
            } else {
                myViewHolder.songtime.setText("0" + valueOf2 + ":" + valueOf);
            }
            Log.v("out", str);
            mediaMetadataRetriever.release();
        } catch (NullPointerException | NumberFormatException e) {
            e.printStackTrace();
        }
        myViewHolder.del.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Adapter.VoicesAdapter.2
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
                VoicesAdapter.this.audiolist.remove(i);
                VoicesAdapter.this.notifyItemRemoved(i);
                ((WaAudioActivity) VoicesAdapter.this.mContext).removeData();
            }
        });
    }

    public void CallIntent(final Context context, Class<?> cls) {
        final Intent intent = new Intent(context, cls);
        intent.putExtra("AUDIO_PATH", this.audiolist.get(this.positions).getPath());
        intent.setFlags(67108864);
                context.startActivity(intent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.audiolist.size();
    }

    /* loaded from: classes2.dex */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView del;
        ImageView img_icon;
        private final RelativeLayout rl_main;
        private final TextView songname;
        private final TextView songtime;

        public MyViewHolder(View view) {
            super(view);
            this.songname = (TextView) view.findViewById(R.id.songname);
            this.rl_main = (RelativeLayout) view.findViewById(R.id.rl_main);
            this.songtime = (TextView) view.findViewById(R.id.songtime);
            this.del = (ImageView) view.findViewById(R.id.imgDelete);
            this.img_icon = (ImageView) view.findViewById(R.id.img_icon);
        }
    }
}
