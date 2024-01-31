package com.console.gbversion.whatsscan.Application.Adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import com.console.gbversion.whatsscan.Application.Activity.DirectMessage_Activity;
import com.console.gbversion.whatsscan.Application.DB.DBHelper;
import com.console.gbversion.whatsscan.Application.Model.Message_Pojo;
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
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private String chatname;
    DBHelper db;
    private ClickAdapterListener listener;
    private Context mContext;
    private List<Message_Pojo> mmsgList;
    private SparseBooleanArray selectedItems = new SparseBooleanArray();

    /* loaded from: classes2.dex */
    public interface ClickAdapterListener {
        void onRowClicked(int i);

        void onRowLongClicked(int i);
    }

    private void resetCurrentIndex() {
    }

    /* loaded from: classes2.dex */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {
        ConstraintLayout constraintLayout;
        TextView dateTxtV;
        public View layout;
        TextView msgTxtV;
        TextView nameTxtV;
        TextView timeTxtV;

        public ViewHolder(View view) {
            super(view);
            this.layout = view;
            this.msgTxtV = (TextView) view.findViewById(R.id.text_message_body);
            this.nameTxtV = (TextView) view.findViewById(R.id.text_message_name);
            this.timeTxtV = (TextView) view.findViewById(R.id.text_message_time);
            this.dateTxtV = (TextView) view.findViewById(R.id.text_message_date);
            this.constraintLayout = (ConstraintLayout) view.findViewById(R.id.chat_row);
            view.setOnLongClickListener(this);
        }

        public boolean onLongClick(View view) {
            MessageAdapter.this.listener.onRowLongClicked(getAdapterPosition());
            view.performHapticFeedback(0);
            return true;
        }

        public void onClick(View view) {
            if (DirectMessage_Activity.actionMode != null) {
                MessageAdapter.this.listener.onRowClicked(getAdapterPosition());
                view.performHapticFeedback(0);
            }
        }
    }

    public MessageAdapter(List<Message_Pojo> list, Context context, RecyclerView recyclerView, String str, ClickAdapterListener clickAdapterListener) {
        this.mmsgList = list;
        this.mContext = context;
        this.chatname = str;
        this.listener = clickAdapterListener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_chat_row, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Message_Pojo message_Pojo = this.mmsgList.get(i);
        viewHolder.timeTxtV.setText(message_Pojo.getDatetime().substring(11, 16));
        try {
            viewHolder.dateTxtV.setText(new SimpleDateFormat("dd MMM yy", Locale.getDefault()).format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS", Locale.getDefault()).parse(message_Pojo.getDatetime())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (message_Pojo.getCond() == 1) {
            viewHolder.nameTxtV.setText(message_Pojo.getName());
            viewHolder.nameTxtV.setVisibility(0);
        } else {
            viewHolder.nameTxtV.setVisibility(8);
        }
        viewHolder.msgTxtV.setText(message_Pojo.getMsg());
        applyClickEvents(viewHolder, i, message_Pojo);
        if (DirectMessage_Activity.actionMode == null || !getSelectedItems().contains(Integer.valueOf(i))) {
            viewHolder.constraintLayout.setBackgroundColor(0);
        } else {
            viewHolder.constraintLayout.setBackgroundResource(R.color.selected);
        }
    }

    private void applyClickEvents(final ViewHolder viewHolder, final int i, final Message_Pojo message_Pojo) {
        viewHolder.layout.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Adapter.MessageAdapter.1
            public void onClick(View view) {
                if (DirectMessage_Activity.actionMode != null) {
                    MessageAdapter.this.listener.onRowClicked(i);
                    return;
                }
                MessageAdapter messageAdapter = MessageAdapter.this;
                messageAdapter.setClipboard(messageAdapter.mContext, message_Pojo.getMsg());
                Snackbar.make(viewHolder.layout, "Message Copied!", -1).show();
            }
        });
        viewHolder.layout.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.console.gbversion.whatsscan.Application.Adapter.MessageAdapter.2
            public boolean onLongClick(View view) {
                MessageAdapter.this.listener.onRowLongClicked(i);
                view.performHapticFeedback(0);
                return true;
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mmsgList.size();
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
        dBHelper.deleteMsg(this.mmsgList.get(i).getMsg());
        this.mmsgList.remove(i);
        this.db.closeDB();
        resetCurrentIndex();
    }

    public void refresH() {
        DBHelper dBHelper = new DBHelper(this.mContext);
        this.db = dBHelper;
        this.mmsgList = dBHelper.getAllMsgfromchat(this.chatname);
        this.db.closeDB();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setClipboard(Context context, String str) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService("clipboard");
        ClipData newPlainText = ClipData.newPlainText("Copied Text", str);
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(newPlainText);
        }
    }
}
