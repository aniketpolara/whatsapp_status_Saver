package com.console.gbversion.whatsscan.Application.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.console.gbversion.whatsscan.Application.Adapter.EmojiAdapter;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.Application.Utils.ItemClickSupport;
import com.console.gbversion.whatsscan.R;

/* loaded from: classes2.dex */
public class EmojisActivity extends AppCompatActivity {
    private EmojiAdapter adapter;
    private String[] list;
    private int positions;
    private RecyclerView rv_emoji;
    private String[] split;
    private Toolbar toolbar;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_emojis);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        new AsyncTaskBase().execute(new Void[0]);
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void CallIntent(Context context, Class<?> cls, int i) {
        if (i == 1) {
            Intent intent = new Intent(context, cls);
            intent.putExtra("title", this.split[0]);
            intent.putExtra("position", this.positions);
            context.startActivity(intent);
        } else if (i == 2) {
            startActivity(new Intent(context, cls));
        }
    }

    /* loaded from: classes2.dex */
    private class AsyncTaskBase extends AsyncTask<Void, Void, Void> {
        private AsyncTaskBase() {
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            super.onPreExecute();
        }

        public Void doInBackground(Void... voidArr) {
            EmojisActivity emojisActivity = EmojisActivity.this;
            emojisActivity.list = emojisActivity.getResources().getStringArray(R.array.cat_name);
            return null;
        }

        public void onPostExecute(Void r5) {
            super.onPostExecute(r5);
            EmojisActivity emojisActivity = EmojisActivity.this;
            emojisActivity.adapter = new EmojiAdapter(emojisActivity, emojisActivity.list);
            final EmojisActivity emojisActivity2 = EmojisActivity.this;
            emojisActivity2.rv_emoji = (RecyclerView) emojisActivity2.findViewById(R.id.rv_emoji);
            EmojisActivity.this.rv_emoji.setLayoutManager(new GridLayoutManager(EmojisActivity.this, 2));
            EmojisActivity.this.rv_emoji.setAdapter(EmojisActivity.this.adapter);
            ItemClickSupport.addTo(EmojisActivity.this.rv_emoji).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.EmojisActivity.AsyncTaskBase.1
                @Override // com.console.gbversion.whatsscan.Application.Utils.ItemClickSupport.OnItemClickListener
                public void onItemClicked(RecyclerView recyclerView, int i, View view) {
                    EmojisActivity emojisActivity3 = EmojisActivity.this;
                    emojisActivity3.split = emojisActivity3.list[i].split("~~");
                    EmojisActivity.this.positions = i;
                            emojisActivity2.CallIntent(emojisActivity2, EmojiDetailsActivity.class, 1);
                }
            });
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
                EmojisActivity.this.finish();
    }
}
