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
public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.ViewHolder> {
    Context context;
    String[] list;
    private StringBuilder sb;
    String text = "12345";

    public NumberAdapter(Context context, String[] strArr) {
        this.context = context;
        this.list = strArr;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(this, LayoutInflater.from(this.context).inflate(R.layout.item_text, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        try {
            this.sb = new StringBuilder();
            String[] split = this.list[i].split(" ");
            for (int i2 = 0; i2 < this.text.length(); i2++) {
                this.sb.append(split[Character.digit(this.text.charAt(i2), 10)]);
            }
            viewHolder.txtItemNumber.setText(this.sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        viewHolder.img_copy.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Adapter.NumberAdapter.1
            public void onClick(View view) {
                ((ClipboardManager) NumberAdapter.this.context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text", NumberAdapter.this.sb.toString()));
                Toast.makeText(NumberAdapter.this.context, "Copy to Clipboard..", 0).show();
            }
        });
        viewHolder.img_share.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Adapter.NumberAdapter.2
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.TEXT", NumberAdapter.this.sb.toString());
                NumberAdapter.this.context.startActivity(Intent.createChooser(intent, "share via"));
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
            this.text = "12345";
        } else {
            this.text = str;
        }
        notifyDataSetChanged();
    }

    /* loaded from: classes2.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_copy;
        ImageView img_share;
        TextView txtItemNumber;

        public ViewHolder(NumberAdapter numberAdapter, View view) {
            super(view);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.llItemClickId);
            this.txtItemNumber = (TextView) view.findViewById(R.id.txtItemTextId);
            this.img_copy = (ImageView) view.findViewById(R.id.img_copy);
            this.img_share = (ImageView) view.findViewById(R.id.img_share);
        }
    }
}
