package com.console.gbversion.whatsscan.Application.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.console.gbversion.whatsscan.Application.Adapter.StylishTextAdapter;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.Application.Utils.ItemClickSupport;
import com.console.gbversion.whatsscan.R;

/* loaded from: classes2.dex */
public class StylishTextActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    StylishTextAdapter adapter;
    String[] list;
    private int positions;
    private SearchView search_view;
    String text = "Preview";
    private Toolbar toolbar;

    @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
    public boolean onQueryTextSubmit(String str) {
        return true;
    }

    public int getItemCount() {
        return this.list.length;
    }

    @SuppressLint("NewApi")
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_text_stylish);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        new LoadAsynTask().execute(new Void[0]);
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

    @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
    public boolean onQueryTextChange(String str) {
        if (str.equals("")) {
            this.text = "Preview";
        } else {
            this.text = str;
        }
        this.adapter.filter(str);
        return false;
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void CallIntent(Activity activity, Class<?> cls, int i) {
        if (i == 1) {
            this.list[this.positions].split("~~");
            this.text = this.text.replace(" ", "");
            Intent intent = new Intent(this, TextDetailsActivity.class);
            intent.putExtra("position", this.positions);
            intent.putExtra("text", this.text);
            intent.putExtra("size", getItemCount());
            startActivity(intent);
        } else if (i == 2) {
            startActivity(new Intent(this, cls));
        }
    }

    /* loaded from: classes2.dex */
    private class LoadAsynTask extends AsyncTask<Void, Void, Void> {
        private LoadAsynTask() {
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            super.onPreExecute();
        }

        public Void doInBackground(Void... voidArr) {
            StylishTextActivity stylishTextActivity = StylishTextActivity.this;
            stylishTextActivity.list = stylishTextActivity.getResources().getStringArray(R.array.text_styles);
            return null;
        }

        public void onPostExecute(Void r5) {
            super.onPostExecute(r5);
            StylishTextActivity stylishTextActivity = StylishTextActivity.this;
            stylishTextActivity.adapter = new StylishTextAdapter(stylishTextActivity, stylishTextActivity.list);
            RecyclerView recyclerView = (RecyclerView) StylishTextActivity.this.findViewById(R.id.textRecyclerId);
            recyclerView.setLayoutManager(new LinearLayoutManager(StylishTextActivity.this, RecyclerView.VERTICAL, false));
            recyclerView.setAdapter(StylishTextActivity.this.adapter);
            ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.StylishTextActivity.LoadAsynTask.1
                @Override // com.console.gbversion.whatsscan.Application.Utils.ItemClickSupport.OnItemClickListener
                public void onItemClicked(RecyclerView recyclerView2, final int i, View view) {
                            StylishTextActivity.this.positions = i;
                            StylishTextActivity stylishTextActivity2 = StylishTextActivity.this;
                            stylishTextActivity2.CallIntent(stylishTextActivity2, TextDetailsActivity.class, 1);
                }
            });
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
                StylishTextActivity.this.finish();
    }
}
