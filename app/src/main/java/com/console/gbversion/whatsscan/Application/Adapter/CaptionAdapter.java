package com.console.gbversion.whatsscan.Application.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.console.gbversion.whatsscan.Application.Activity.FullCaptionActivity;
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
public class CaptionAdapter extends RecyclerView.Adapter<CaptionAdapter.ViewHolder> {
    String[] CaptionType;
    private Activity context;
    private int positions;

    public CaptionAdapter(Activity activity, String[] strArr) {
        this.context = activity;
        this.CaptionType = strArr;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.caption_items, viewGroup, false));
    }

    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        viewHolder.txtshayri.setText(this.CaptionType[i]);
        viewHolder.cv.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Adapter.CaptionAdapter.1
            public void onClick(View view) {
                CaptionAdapter.this.positions = viewHolder.getAdapterPosition();
                        CaptionAdapter.this.CallIntent(CaptionAdapter.this.context, FullCaptionActivity.class, 1);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.CaptionType.length;
    }

    /* loaded from: classes2.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        public TextView txtshayri;

        public ViewHolder(View view) {
            super(view);
            this.txtshayri = (TextView) view.findViewById(R.id.txtshayri);
            this.cv = (CardView) view.findViewById(R.id.cv);
        }
    }

    public void CallIntent(Activity activity, Class<?> cls, int i) {
        if (i == 1) {
            Intent intent = new Intent(activity, FullCaptionActivity.class);
            intent.putExtra("FULL_CAPTION", this.CaptionType[this.positions]);
            intent.putExtra("CAPTION_ARRAY", this.CaptionType);
            intent.putExtra("POSITION", this.positions);
            intent.setFlags(67108864);
            activity.startActivity(intent);
        }
    }
}
