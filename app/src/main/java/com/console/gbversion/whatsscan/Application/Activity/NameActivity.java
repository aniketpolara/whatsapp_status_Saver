package com.console.gbversion.whatsscan.Application.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.console.gbversion.whatsscan.Application.Adapter.NameAdapter;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.Application.Utils.ItemClickSupport;
import com.console.gbversion.whatsscan.R;

/* loaded from: classes2.dex */
public class NameActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private NameAdapter adapter;
    private String[] list;
    private int positions;
    private SearchView search_view;
    private String text = "Preview";
    private Toolbar toolbar;

    @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
    public boolean onQueryTextSubmit(String str) {
        return false;
    }

    public int getItemCount() {
        return this.list.length;
    }

    @SuppressLint("NewApi")
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_name);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        new AsynTaskBase().execute(new Void[0]);
        SearchView searchView = (SearchView) findViewById(R.id.search_view);
        this.search_view = searchView;
        searchView.setQueryHint("Type your text here");
        EditText editText = (EditText) this.search_view.findViewById(R.id.search_src_text);
        editText.setHintTextColor(getResources().getColor(R.color.gray, null));
        editText.setTextColor(getResources().getColor(R.color.white, null));
        this.search_view.setOnQueryTextListener(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void CallIntent(Activity activity, final Class<?> cls, int i) {
        if (i == 1) {
            final Intent intent = new Intent(this, NameDetailsActivity.class);
            intent.putExtra("POSITION", this.positions);
            intent.putExtra("TEXT", this.text);
            intent.putExtra("SIZE", getItemCount());
                    NameActivity.this.startActivity(intent);
        } else if (i == 2) {
                    NameActivity.this.startActivity(new Intent(NameActivity.this, cls));
        }
    }

    @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
    public boolean onQueryTextChange(String str) {
        Log.d("BHOLOHH", "onSearchConfirmed: " + str);
        if (str.equals("")) {
            this.text = "Preview";
        } else {
            this.text = str;
        }
        this.adapter.filter(str);
        return true;
    }

    /* loaded from: classes2.dex */
    private class AsynTaskBase extends AsyncTask<Void, Void, Void> {
        private AsynTaskBase() {
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            super.onPreExecute();
        }

        public Void doInBackground(Void... voidArr) {
            NameActivity nameActivity = NameActivity.this;
            nameActivity.list = nameActivity.getResources().getStringArray(R.array.name_style);
            return null;
        }

        @SuppressLint("WrongConstant")
        public void onPostExecute(Void r5) {
            super.onPostExecute(r5);
            NameActivity nameActivity = NameActivity.this;
            nameActivity.adapter = new NameAdapter(nameActivity, nameActivity.list);
            RecyclerView recyclerView = (RecyclerView) NameActivity.this.findViewById(R.id.nameRecyclerId);
            recyclerView.setLayoutManager(new LinearLayoutManager(NameActivity.this, 1, false));
            recyclerView.setAdapter(NameActivity.this.adapter);
            ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.NameActivity.AsynTaskBase.1
                @Override // com.console.gbversion.whatsscan.Application.Utils.ItemClickSupport.OnItemClickListener
                public void onItemClicked(RecyclerView recyclerView2, int i, View view) {
                    NameActivity nameActivity2 = NameActivity.this;
                    nameActivity2.CallIntent(nameActivity2, NameDetailsActivity.class, 1);
                }
            });
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
                NameActivity.this.finish();
    }
}
