package com.console.gbversion.whatsscan.Application.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.console.gbversion.whatsscan.Application.Activity.CaptionActivity;
import com.console.gbversion.whatsscan.R;

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
public class CaptionCatAdapter extends RecyclerView.Adapter<CaptionCatAdapter.ViewHolder> {
    String[] caption_list;
    private Activity context;
    String[] eng_list;
    String language;
    private int positions;

    public CaptionCatAdapter(Activity activity, String[] strArr, String[] strArr2, String str) {
        this.context = activity;
        this.caption_list = strArr;
        this.eng_list = strArr2;
        this.language = str;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowlayout, viewGroup, false));
    }

    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        if (i == 0) {
            viewHolder.img.setImageDrawable(ResourcesCompat.getDrawable(this.context.getResources(), R.drawable.love_icon, null));
        }
        if (i == 1) {
            viewHolder.img.setImageDrawable(ResourcesCompat.getDrawable(this.context.getResources(), R.drawable.sadicon, null));
        }
        if (i == 2) {
            viewHolder.img.setImageDrawable(ResourcesCompat.getDrawable(this.context.getResources(), R.drawable.attitudeicon, null));
        }
        if (i == 3) {
            viewHolder.img.setImageDrawable(ResourcesCompat.getDrawable(this.context.getResources(), R.drawable.romanticicon, null));
        }
        if (i == 4) {
            viewHolder.img.setImageDrawable(ResourcesCompat.getDrawable(this.context.getResources(), R.drawable.aloneicon, null));
        }
        if (i == 5) {
            viewHolder.img.setImageDrawable(ResourcesCompat.getDrawable(this.context.getResources(), R.drawable.andryicon, null));
        }
        if (i == 6) {
            viewHolder.img.setImageDrawable(ResourcesCompat.getDrawable(this.context.getResources(), R.drawable.funnyicon, null));
        }
        viewHolder.textView.setText(this.caption_list[i]);
        viewHolder.txtenglish.setText(this.eng_list[i]);
        viewHolder.cv.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Adapter.CaptionCatAdapter.1
            public void onClick(View view) {
                CaptionCatAdapter.this.positions = viewHolder.getAdapterPosition();
                final Intent intent = new Intent(CaptionCatAdapter.this.context, CaptionActivity.class);
                CaptionCatAdapter captionCatAdapter = CaptionCatAdapter.this;
                intent.putExtra("TYPE", captionCatAdapter.eng_list[captionCatAdapter.positions]);
                intent.putExtra("LANGUAGE", CaptionCatAdapter.this.language);
                        CaptionCatAdapter.this.context.startActivity(intent);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.caption_list.length;
    }

    /* loaded from: classes2.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView img;
        public TextView textView;
        public TextView txtenglish;

        public ViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.textView);
            this.txtenglish = (TextView) view.findViewById(R.id.txtenglish);
            this.img = (ImageView) view.findViewById(R.id.img);
            this.cv = (CardView) view.findViewById(R.id.cv);
        }
    }
}
