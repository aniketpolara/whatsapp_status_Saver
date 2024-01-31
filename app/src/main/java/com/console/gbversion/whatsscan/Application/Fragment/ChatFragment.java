package com.console.gbversion.whatsscan.Application.Fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.console.gbversion.whatsscan.Application.Adapter.ChatAdapter;
import com.console.gbversion.whatsscan.Application.DB.DBHelper;
import com.console.gbversion.whatsscan.Application.Model.Chats_Pojo;
import com.console.gbversion.whatsscan.R;
import java.util.List;

/* loaded from: classes2.dex */
public class ChatFragment extends Fragment implements ChatAdapter.ClickAdapterListener {
    public static ActionMode actionMode;
    private ActionModeCallback actionModeCallback;
    private ChatAdapter adapter;
    List<Chats_Pojo> chat_list;
    DBHelper db;
    private View inflate;
    private BroadcastReceiver onNotice = new C04011();
    TextView txt_empty;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.inflate = layoutInflater.inflate(R.layout.chatfragment, viewGroup, false);
        this.actionModeCallback = new ActionModeCallback();
        this.txt_empty = (TextView) this.inflate.findViewById(R.id.txt_empty);
        DBHelper dBHelper = new DBHelper(getContext());
        this.db = dBHelper;
        this.chat_list = dBHelper.getAllChats();
        this.db.closeDB();
        RecyclerView recyclerView = (RecyclerView) this.inflate.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        if (this.chat_list.size() == 0) {
            this.txt_empty.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
        } else {
            this.txt_empty.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        ChatAdapter chatAdapter = new ChatAdapter(this.chat_list, getActivity(), recyclerView, this);
        this.adapter = chatAdapter;
        recyclerView.setAdapter(chatAdapter);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.onNotice, new IntentFilter("Msg"));
        return this.inflate;
    }

    @Override // com.console.gbversion.whatsscan.Application.Adapter.ChatAdapter.ClickAdapterListener
    public void onRowClicked(int i) {
        enableActionMode(i);
    }

    @Override // com.console.gbversion.whatsscan.Application.Adapter.ChatAdapter.ClickAdapterListener
    public void onRowLongClicked(int i) {
        enableActionMode(i);
    }

    private void enableActionMode(int i) {
        if (actionMode == null) {
            actionMode = ((AppCompatActivity) getActivity()).startSupportActionMode(this.actionModeCallback);
        }
        toggleSelection(i);
    }

    private void toggleSelection(int i) {
        this.adapter.toggleSelection(i);
        int selectedItemCount = this.adapter.getSelectedItemCount();
        if (selectedItemCount == 0) {
            actionMode.finish();
            actionMode = null;
            return;
        }
        actionMode.setTitle(String.valueOf(selectedItemCount));
        actionMode.invalidate();
    }

    public void deleteRows() {
        List<Integer> selectedItems = this.adapter.getSelectedItems();
        for (int size = selectedItems.size() - 1; size >= 0; size--) {
            this.adapter.removeData(selectedItems.get(size).intValue());
        }
        this.adapter.notifyDataSetChanged();
        actionMode = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.onNotice);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class C04011 extends BroadcastReceiver {
        C04011() {
//            ChatFragment.this = r1;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ChatFragment.this.adapter.refresh();
            ChatFragment.this.adapter.notifyDataSetChanged();
        }
    }

    /* loaded from: classes2.dex */
    public class ActionModeCallback implements ActionMode.Callback {
        @Override // androidx.appcompat.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        private ActionModeCallback() {
//            ChatFragment.this = r1;
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.getMenuInflater().inflate(R.menu.con_menu, menu);
            return true;
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            Log.d("API123", "here");
            if (menuItem.getItemId() != R.id.item_delete) {
                return false;
            }
            ChatFragment.this.deleteRows();
            actionMode.finish();
            return true;
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode actionMode) {
            ChatFragment.this.adapter.clearSelections();
            ChatFragment.actionMode = null;
        }
    }
}
