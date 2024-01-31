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
import com.console.gbversion.whatsscan.Application.Fragment.NumberFragment;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.Application.Utils.TransformerOutSlide;
import com.console.gbversion.whatsscan.R;

/* loaded from: classes2.dex */
public class NumberDetailsActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
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
        setContentView(R.layout.activity_number_detail);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        this.toolbar = (Toolbar) findViewById(R.id.toolBar);
        this.toolbar_text = (AppCompatTextView) findViewById(R.id.toolbar_text);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Intent intent = getIntent();
        this.position = intent.getIntExtra("position", 0);
        this.text = intent.getStringExtra("text");
        this.size = intent.getIntExtra("size", 0);
        this.toolbar_text.setText(this.text);
        new AsyncTaskBase().execute(new Void[0]);
        this.img_next = (ImageView) findViewById(R.id.img_next);
        this.img_previous = (ImageView) findViewById(R.id.img_previous);
        this.img_next.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.NumberDetailsActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NumberDetailsActivity.this.viewPager.setCurrentItem(NumberDetailsActivity.this.getItem(1), true);
            }
        });
        this.img_previous.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.NumberDetailsActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NumberDetailsActivity.this.viewPager.setCurrentItem(NumberDetailsActivity.this.getItem(-1), true);
            }
        });
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getItem(int i) {
        return this.viewPager.getCurrentItem() + i;
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
            NumberDetailsActivity numberDetailsActivity = NumberDetailsActivity.this;
            numberDetailsActivity.list = numberDetailsActivity.getResources().getStringArray(R.array.number_style);
            return null;
        }

        public void onPostExecute(Void r3) {
            super.onPostExecute(r3);
            NumberDetailsActivity numberDetailsActivity = NumberDetailsActivity.this;
            numberDetailsActivity.viewPager = (ViewPager) numberDetailsActivity.findViewById(R.id.vp_number);
            NumberDetailsActivity numberDetailsActivity2 = NumberDetailsActivity.this;
            NumberDetailPagerAdapter numberDetailPagerAdapter = new NumberDetailPagerAdapter(numberDetailsActivity2.getSupportFragmentManager());
            NumberDetailsActivity.this.viewPager.setOnPageChangeListener(NumberDetailsActivity.this);
            NumberDetailsActivity.this.viewPager.setAdapter(numberDetailPagerAdapter);
            NumberDetailsActivity.this.viewPager.setPageMargin(20);
            NumberDetailsActivity.this.viewPager.setPageTransformer(true, new TransformerOutSlide());
        }
    }

    /* loaded from: classes2.dex */
    public class NumberDetailPagerAdapter extends FragmentPagerAdapter {
        public NumberDetailPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override // androidx.fragment.app.FragmentPagerAdapter
        public Fragment getItem(int i) {
            NumberDetailsActivity numberDetailsActivity = NumberDetailsActivity.this;
            return NumberFragment.content(numberDetailsActivity.text, numberDetailsActivity.list[numberDetailsActivity.position + i]);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            NumberDetailsActivity numberDetailsActivity = NumberDetailsActivity.this;
            return numberDetailsActivity.size - numberDetailsActivity.position;
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
                NumberDetailsActivity.this.finish();
    }
}
