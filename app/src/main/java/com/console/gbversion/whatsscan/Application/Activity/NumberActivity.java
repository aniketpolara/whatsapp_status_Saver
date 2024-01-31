package com.console.gbversion.whatsscan.Application.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.console.gbversion.whatsscan.Application.Adapter.NumberAdapter;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.Application.Utils.ItemClickSupport;
import com.console.gbversion.whatsscan.R;

public class NumberActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    NumberAdapter adapter;
    String[] list;
    int positions;
    private SearchView search_view;
    String text = "12345";
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
        setContentView(R.layout.activity_number);
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
        searchView.setQueryHint("Type your numbers here");
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

    public void CallIntent(final Context context, final Class<?> cls, int i) {
        if (i == 1) {
            this.text = this.text.replace(" ", "");
            final Intent intent = new Intent(this, NumberDetailsActivity.class);
            intent.putExtra("position", this.positions);
            intent.putExtra("text", this.text);
            intent.putExtra("size", getItemCount());
                    NumberActivity.this.startActivity(intent);
        } else if (i == 2) {
                    NumberActivity.this.startActivity(new Intent(context, cls));
        }
    }

    @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
    public boolean onQueryTextChange(String str) {
        Log.d("BHOLOHH", "onSearchConfirmed: " + str);
        if (str.equals("")) {
            this.text = "12345";
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
            NumberActivity numberActivity = NumberActivity.this;
            numberActivity.list = numberActivity.getResources().getStringArray(R.array.number_style);
            return null;
        }

        public void onPostExecute(Void r5) {
            super.onPostExecute(r5);
            NumberActivity numberActivity = NumberActivity.this;
            numberActivity.adapter = new NumberAdapter(numberActivity, numberActivity.list);
            RecyclerView recyclerView = (RecyclerView) NumberActivity.this.findViewById(R.id.numberRecyclerId);
            recyclerView.setLayoutManager(new LinearLayoutManager(NumberActivity.this, RecyclerView.VERTICAL, false));
            recyclerView.setAdapter(NumberActivity.this.adapter);
            ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.NumberActivity.AsynTaskBase.1
                @Override // com.console.gbversion.whatsscan.Application.Utils.ItemClickSupport.OnItemClickListener
                public void onItemClicked(RecyclerView recyclerView2, int i, View view) {
                    NumberActivity numberActivity2 = NumberActivity.this;
                    numberActivity2.positions = i;
                    numberActivity2.CallIntent(numberActivity2, NumberDetailsActivity.class, 1);
                }
            });
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
                NumberActivity.this.finish();
    }
}
