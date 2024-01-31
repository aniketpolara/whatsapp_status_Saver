package com.console.gbversion.whatsscan.Application.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.console.gbversion.whatsscan.Application.Adapter.MessageAdapter;
import com.console.gbversion.whatsscan.Application.DB.DBHelper;
import com.console.gbversion.whatsscan.Application.Model.Message_Pojo;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.R;
import java.util.List;
import java.util.Objects;

/* loaded from: classes2.dex */
public class DirectMessage_Activity extends AppCompatActivity implements MessageAdapter.ClickAdapterListener {
    public static ActionMode actionMode;
    private ActionModeCallback actionModeCallback;
    String chat_name;
    DBHelper dbHelper;
    private MessageAdapter messageAdapter;
    private BroadcastReceiver onNotice = new C04021();
    private RecyclerView rv_chat;
    private Toolbar toolbar;
    private AppCompatTextView toolbar_text;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_directmsg);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Intent intent = getIntent();
        this.chat_name = intent.getStringExtra("CHAT_NAME");
        Bundle extras = intent.getExtras();
        Objects.requireNonNull(extras);
        extras.getInt("cond");
        this.actionModeCallback = new ActionModeCallback();
        this.rv_chat = (RecyclerView) findViewById(R.id.rv_chat);
        AppCompatTextView appCompatTextView = (AppCompatTextView) findViewById(R.id.toolbar_text);
        this.toolbar_text = appCompatTextView;
        appCompatTextView.setText(this.chat_name);
        this.rv_chat.setHasFixedSize(true);
        this.rv_chat.setLayoutManager(new LinearLayoutManager(this, 1, true));
        DBHelper dBHelper = new DBHelper(getApplicationContext());
        this.dbHelper = dBHelper;
        List<Message_Pojo> allMsgfromchat = dBHelper.getAllMsgfromchat(this.chat_name);
        this.dbHelper.closeDB();
        MessageAdapter messageAdapter = new MessageAdapter(allMsgfromchat, this, this.rv_chat, this.chat_name, this);
        this.messageAdapter = messageAdapter;
        this.rv_chat.setAdapter(messageAdapter);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.onNotice, new IntentFilter("Msg"));
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override // com.console.gbversion.whatsscan.Application.Adapter.MessageAdapter.ClickAdapterListener
    public void onRowClicked(int i) {
        enableActionMode(i);
    }

    @Override // com.console.gbversion.whatsscan.Application.Adapter.MessageAdapter.ClickAdapterListener
    public void onRowLongClicked(int i) {
        enableActionMode(i);
    }

    private void enableActionMode(int i) {
        if (actionMode == null) {
            actionMode = startSupportActionMode(this.actionModeCallback);
        }
        toggleSelection(i);
    }

    private void toggleSelection(int i) {
        this.messageAdapter.toggleSelection(i);
        int selectedItemCount = this.messageAdapter.getSelectedItemCount();
        if (selectedItemCount == 0) {
            actionMode.finish();
            actionMode = null;
            return;
        }
        actionMode.setTitle(String.valueOf(selectedItemCount));
        actionMode.invalidate();
    }

    public void deleteRows() {
        List<Integer> selectedItems = this.messageAdapter.getSelectedItems();
        for (int size = selectedItems.size() - 1; size >= 0; size--) {
            this.messageAdapter.removeData(selectedItems.get(size).intValue());
        }
        this.messageAdapter.notifyDataSetChanged();
        actionMode = null;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.onNotice);
    }

    /* loaded from: classes2.dex */
    class C04021 extends BroadcastReceiver {
        C04021() {
//            DirectMessage_Activity.this = r1;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            DirectMessage_Activity.this.messageAdapter.refresH();
            DirectMessage_Activity.this.messageAdapter.notifyDataSetChanged();
        }
    }

    /* loaded from: classes2.dex */
    public class ActionModeCallback implements ActionMode.Callback {
        @Override // androidx.appcompat.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        private ActionModeCallback() {
//            DirectMessage_Activity.this = r1;
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.getMenuInflater().inflate(R.menu.con_menu, menu);
            return true;
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            if (menuItem.getItemId() != R.id.item_delete) {
                return false;
            }
            DirectMessage_Activity.this.deleteRows();
            actionMode.finish();
            return true;
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode actionMode) {
            DirectMessage_Activity.this.messageAdapter.clearSelections();
            DirectMessage_Activity.actionMode = null;
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
                DirectMessage_Activity.this.finish();
    }
}
