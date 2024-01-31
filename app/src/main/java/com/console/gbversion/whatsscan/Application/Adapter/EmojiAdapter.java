package com.console.gbversion.whatsscan.Application.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.ViewHolder> {
    Context context;
    String[] list;

    public EmojiAdapter(Context context, String[] strArr) {
        this.context = context;
        this.list = strArr;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(this, LayoutInflater.from(this.context).inflate(R.layout.item_emoji, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String[] split = this.list[i].split("~~");
        viewHolder.txtEmojiCat.setText(split[0]);
        if (split[0].equalsIgnoreCase("love")) {
            viewHolder.img_emoji.setImageResource(R.drawable.emoji_love);
        } else if (split[0].equalsIgnoreCase("sad")) {
            viewHolder.img_emoji.setImageResource(R.drawable.emoji_sad);
        } else if (split[0].equalsIgnoreCase("sorry")) {
            viewHolder.img_emoji.setImageResource(R.drawable.emoji_sorry);
        } else if (split[0].equalsIgnoreCase("happy")) {
            viewHolder.img_emoji.setImageResource(R.drawable.emoji_happy);
        } else if (split[0].equalsIgnoreCase("surprise")) {
            viewHolder.img_emoji.setImageResource(R.drawable.emoji_surprise);
        } else if (split[0].equalsIgnoreCase("scare")) {
            viewHolder.img_emoji.setImageResource(R.drawable.emoji_scare);
        } else if (split[0].equalsIgnoreCase("music")) {
            viewHolder.img_emoji.setImageResource(R.drawable.emoji_music);
        } else if (split[0].equalsIgnoreCase("smug")) {
            viewHolder.img_emoji.setImageResource(R.drawable.emoji_smug);
        } else if (split[0].equalsIgnoreCase("failure")) {
            viewHolder.img_emoji.setImageResource(R.drawable.emoji_failure);
        } else if (split[0].equalsIgnoreCase("animal")) {
            viewHolder.img_emoji.setImageResource(R.drawable.emoji_animal);
        } else if (split[0].equalsIgnoreCase("evil")) {
            viewHolder.img_emoji.setImageResource(R.drawable.emoji_evil);
        } else if (split[0].equalsIgnoreCase("angry")) {
            viewHolder.img_emoji.setImageResource(R.drawable.emoji_angry);
        } else if (split[0].equalsIgnoreCase("confuse")) {
            viewHolder.img_emoji.setImageResource(R.drawable.emoji_confuse);
        } else if (split[0].equalsIgnoreCase("kiss")) {
            viewHolder.img_emoji.setImageResource(R.drawable.emoji_kiss);
        } else if (split[0].equalsIgnoreCase("shy")) {
            viewHolder.img_emoji.setImageResource(R.drawable.emoji_shy);
        } else if (split[0].equalsIgnoreCase("tired")) {
            viewHolder.img_emoji.setImageResource(R.drawable.emoji_tired);
        } else if (split[0].equalsIgnoreCase("wink")) {
            viewHolder.img_emoji.setImageResource(R.drawable.emoji_wink);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.length;
    }

    /* loaded from: classes2.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_emoji;
        TextView txtEmojiCat;

        public ViewHolder(EmojiAdapter emojiAdapter, View view) {
            super(view);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.llItemEmojiId);
            this.txtEmojiCat = (TextView) view.findViewById(R.id.txt_emojiname);
            this.img_emoji = (ImageView) view.findViewById(R.id.img_emoji);
        }
    }
}
