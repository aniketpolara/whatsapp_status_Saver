package com.console.gbversion.whatsscan.Application.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.console.gbversion.whatsscan.Application.Fragment.NameFragment;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.Application.Utils.TransformerOutSlide;
import com.console.gbversion.whatsscan.R;

/* loaded from: classes2.dex */
public class NameDetailsActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ImageView img_next;
    private ImageView img_previous;
    String[] list;
    int position = 0;
    int size;
    String text;
    private Toolbar toolbar;
    private AppCompatTextView toolbar_text;
    private ViewPager viewPager;

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_name_detail);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        this.toolbar = (Toolbar) findViewById(R.id.toolBar);
        this.toolbar_text = (AppCompatTextView) findViewById(R.id.toolbar_text);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Intent intent = getIntent();
        this.position = intent.getIntExtra("POSITION", 0);
        this.text = intent.getStringExtra("TEXT");
        this.size = intent.getIntExtra("SIZE", 0);
        new AsyncTaskBase().execute(new Void[0]);
        this.toolbar_text.setText(this.text);
        this.img_next = (ImageView) findViewById(R.id.img_next);
        this.img_previous = (ImageView) findViewById(R.id.img_previous);
        this.img_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NameDetailsActivity.this.viewPager.setCurrentItem(NameDetailsActivity.this.getItem(1), true);
            }
        });
        this.img_previous.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.NameDetailsActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NameDetailsActivity.this.viewPager.setCurrentItem(NameDetailsActivity.this.getItem(-1), true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getItem(int i) {
        return this.viewPager.getCurrentItem() + i;
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
            NameDetailsActivity nameDetailsActivity = NameDetailsActivity.this;
            nameDetailsActivity.list = nameDetailsActivity.getResources().getStringArray(R.array.name_style);
            return null;
        }

        public void onPostExecute(Void r3) {
            super.onPostExecute(r3);
            NameDetailsActivity nameDetailsActivity = NameDetailsActivity.this;
            nameDetailsActivity.viewPager = (ViewPager) nameDetailsActivity.findViewById(R.id.vp_name);
            NameDetailsActivity nameDetailsActivity2 = NameDetailsActivity.this;
            NameDetailPagerAdapter nameDetailPagerAdapter = new NameDetailPagerAdapter(nameDetailsActivity2.getSupportFragmentManager());
            NameDetailsActivity.this.viewPager.addOnPageChangeListener(NameDetailsActivity.this);
            NameDetailsActivity.this.viewPager.setAdapter(nameDetailPagerAdapter);
            NameDetailsActivity.this.viewPager.setPageMargin(20);
            NameDetailsActivity.this.viewPager.setPageTransformer(true, new TransformerOutSlide());
        }
    }

    /* loaded from: classes2.dex */
    public class NameDetailPagerAdapter extends FragmentPagerAdapter {
        public NameDetailPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager, 1);
        }

        @Override
        public Fragment getItem(int i) {
            NameDetailsActivity nameDetailsActivity = NameDetailsActivity.this;
            return NameFragment.content(nameDetailsActivity.text, nameDetailsActivity.list[nameDetailsActivity.position + i]);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            NameDetailsActivity nameDetailsActivity = NameDetailsActivity.this;
            return nameDetailsActivity.size - nameDetailsActivity.position;
        }
    }

    @Override
    public void onBackPressed() {
                NameDetailsActivity.this.finish();
    }
}
