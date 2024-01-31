package com.console.gbversion.whatsscan.Application.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.console.gbversion.whatsscan.Application.Colorgenerator.TextDrawable;
import com.console.gbversion.whatsscan.Application.Colorgenerator.util.ColorGenerator;
import com.console.gbversion.whatsscan.Application.Activity.DirectMessage_Activity;
import com.console.gbversion.whatsscan.Application.DB.DBHelper;
import com.console.gbversion.whatsscan.Application.Fragment.ChatFragment;
import com.console.gbversion.whatsscan.Application.Model.Chats_Pojo;
import com.console.gbversion.whatsscan.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private static List<Chats_Pojo> chat_list;
    private DBHelper db;
    private ClickAdapterListener listener;
    private Activity mContext;
    ColorGenerator generator = ColorGenerator.MATERIAL;
    private SparseBooleanArray selectedItems = new SparseBooleanArray();

    /* loaded from: classes2.dex */
    public interface ClickAdapterListener {
        void onRowClicked(int i);

        void onRowLongClicked(int i);
    }

    public ChatAdapter(List<Chats_Pojo> list, Activity activity, RecyclerView recyclerView, ClickAdapterListener clickAdapterListener) {
        chat_list = list;
        this.mContext = activity;
        this.listener = clickAdapterListener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Chats_Pojo chats_Pojo = chat_list.get(i);
        viewHolder.txt_name.setText(chats_Pojo.getName());
        viewHolder.txt_message.setText(chats_Pojo.getLastmsg());
        viewHolder.txt_time.setText(chats_Pojo.getDatetime().substring(11, 16));
        TextDrawable buildRound = TextDrawable.builder().buildRound(String.valueOf(chat_list.get(i).getName().substring(0, 1)), this.generator.getRandomColor());
        try {
            viewHolder.txt_date.setText(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS", Locale.getDefault()).parse(chats_Pojo.getDatetime())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (chats_Pojo.getCond() == 1) {
            viewHolder.img_profile.setImageResource(R.drawable.group);
        } else {
            viewHolder.img_profile.setImageDrawable(buildRound);
        }
        applyClickEvents(viewHolder, i, chats_Pojo);
        if (ChatFragment.actionMode != null) {
            if (getSelectedItems().contains(Integer.valueOf(i))) {
                viewHolder.img_profile.setImageResource(R.drawable.check2);
            } else if (chats_Pojo.getCond() == 1) {
                viewHolder.img_profile.setImageResource(R.drawable.group);
            } else {
                viewHolder.img_profile.setImageDrawable(buildRound);
            }
        }
    }

    private void applyClickEvents(ViewHolder viewHolder, final int i, Chats_Pojo chats_Pojo) {
        viewHolder.layout.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Adapter.ChatAdapter.1
            public void onClick(View view) {
                        ChatAdapter.this.CallIntent(ChatAdapter.this.mContext, DirectMessage_Activity.class, i);
            }
        });
        viewHolder.layout.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.console.gbversion.whatsscan.Application.Adapter.ChatAdapter.2
            public boolean onLongClick(View view) {
                ChatAdapter.this.listener.onRowLongClicked(i);
                view.performHapticFeedback(0);
                return true;
            }
        });
    }

    public void CallIntent(Context context, Class<?> cls, int i) {
        Intent intent = new Intent(context, cls);
        intent.putExtra("CHAT_NAME", chat_list.get(i).getName());
        intent.putExtra("cond", chat_list.get(i).getCond());
        intent.setFlags(67108864);
        context.startActivity(intent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return chat_list.size();
    }

    public void toggleSelection(int i) {
        if (this.selectedItems.get(i, false)) {
            this.selectedItems.delete(i);
        } else {
            this.selectedItems.put(i, true);
        }
        notifyItemChanged(i);
    }

    public void clearSelections() {
        this.selectedItems.clear();
        notifyDataSetChanged();
    }

    public int getSelectedItemCount() {
        return this.selectedItems.size();
    }

    public List<Integer> getSelectedItems() {
        ArrayList arrayList = new ArrayList(this.selectedItems.size());
        for (int i = 0; i < this.selectedItems.size(); i++) {
            arrayList.add(Integer.valueOf(this.selectedItems.keyAt(i)));
        }
        return arrayList;
    }

    public void removeData(int i) {
        DBHelper dBHelper = new DBHelper(this.mContext);
        this.db = dBHelper;
        dBHelper.deleteChat(chat_list.get(i).getName());
        chat_list.remove(i);
        this.db.closeDB();
    }

    public void refresh() {
        DBHelper dBHelper = new DBHelper(this.mContext);
        this.db = dBHelper;
        chat_list = dBHelper.getAllChats();
        this.db.closeDB();
    }

    /* loaded from: classes2.dex */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {
        ImageView img_profile;
        View layout;
        TextView txt_date;
        TextView txt_message;
        TextView txt_name;
        TextView txt_time;

        ViewHolder(View view) {
            super(view);
            this.layout = view;
            this.txt_name = (TextView) view.findViewById(R.id.txt_name);
            this.txt_date = (TextView) view.findViewById(R.id.txt_date);
            this.txt_time = (TextView) view.findViewById(R.id.txt_time);
            this.txt_message = (TextView) view.findViewById(R.id.txt_message);
            this.img_profile = (ImageView) view.findViewById(R.id.img_profile);
            view.setOnLongClickListener(this);
        }

        public boolean onLongClick(View view) {
            ChatAdapter.this.listener.onRowLongClicked(getAdapterPosition());
            view.performHapticFeedback(0);
            return true;
        }

        public void onClick(View view) {
            if (ChatFragment.actionMode != null) {
                ChatAdapter.this.listener.onRowClicked(getAdapterPosition());
                view.performHapticFeedback(0);
            }
        }
    }
}
