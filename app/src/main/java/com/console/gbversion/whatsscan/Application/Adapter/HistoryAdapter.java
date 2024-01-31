package com.console.gbversion.whatsscan.Application.Adapter;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.console.gbversion.whatsscan.R;
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
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private ClickListener clickListener;
    private ArrayList<String> mList;

    /* loaded from: classes2.dex */
    public interface ClickListener {
        void onItemClicked(int i);
    }

    /* loaded from: classes2.dex */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView result;

        public ViewHolder(View view, int i) {
            super(view);
            this.result = (TextView) view.findViewById(R.id.result);
            ((ImageButton) view.findViewById(R.id.copyButton)).setOnClickListener(this);
        }

        public void onClick(View view) {
            if (HistoryAdapter.this.clickListener != null) {
                HistoryAdapter.this.clickListener.onItemClicked(getLayoutPosition());
            }
        }
    }

    public HistoryAdapter(Context context, ArrayList<String> arrayList) {
        this.mList = arrayList;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_history, viewGroup, false), i);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (Build.VERSION.SDK_INT >= 24) {
            viewHolder.result.setText(Html.fromHtml(this.mList.get(i), 0));
        } else {
            viewHolder.result.setText(Html.fromHtml(this.mList.get(i)));
        }
        viewHolder.result.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }
}
