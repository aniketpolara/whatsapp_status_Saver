package com.console.gbversion.whatsscan.Application.Adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
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
public class NameAdapter extends RecyclerView.Adapter<NameAdapter.ViewHolder> {
    Context context;
    String[] list;
    String text = "Preview";

    /* loaded from: classes2.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_copy;
        ImageView img_share;
        TextView txtItemName;

        public ViewHolder(NameAdapter nameAdapter, View view) {
            super(view);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.llItemClickId);
            this.txtItemName = (TextView) view.findViewById(R.id.txtItemTextId);
            this.img_copy = (ImageView) view.findViewById(R.id.img_copy);
            this.img_share = (ImageView) view.findViewById(R.id.img_share);
        }
    }

    public NameAdapter(Context context, String[] strArr) {
        this.context = context;
        this.list = strArr;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(this, LayoutInflater.from(this.context).inflate(R.layout.item_text, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String str = this.list[i];
        final StringBuilder sb = new StringBuilder(str);
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(this.text);
        sb2.append(sb.reverse().toString());
        viewHolder.txtItemName.setText(sb2.toString());
        viewHolder.img_copy.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Adapter.NameAdapter.1
            public void onClick(View view) {
                ((ClipboardManager) NameAdapter.this.context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text", sb));
                Toast.makeText(NameAdapter.this.context, "Copy to Clipboard..", 0).show();
            }
        });
        viewHolder.img_share.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Adapter.NameAdapter.2
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.TEXT", sb2.toString());
                NameAdapter.this.context.startActivity(Intent.createChooser(intent, "share via"));
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.length;
    }

    public void filter(String str) {
        this.text = "";
        if (str.length() == 0) {
            this.text = "Preview";
        } else {
            this.text = str;
        }
        notifyDataSetChanged();
    }
}
