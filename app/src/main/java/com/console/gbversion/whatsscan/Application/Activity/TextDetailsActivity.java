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
import com.console.gbversion.whatsscan.Application.Fragment.TextFragment;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.Application.Utils.TransformerOutSlide;
import com.console.gbversion.whatsscan.R;

/* loaded from: classes2.dex */
public class TextDetailsActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
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
        setContentView(R.layout.activity_textstylish_details);
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
        new AsyncTaskBase().execute(new Void[0]);
        this.toolbar_text.setText(this.text);
        this.img_next = (ImageView) findViewById(R.id.img_next);
        this.img_previous = (ImageView) findViewById(R.id.img_previous);
        this.img_next.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.TextDetailsActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TextDetailsActivity.this.viewPager.setCurrentItem(TextDetailsActivity.this.getItem(1), true);
            }
        });
        this.img_previous.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.TextDetailsActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TextDetailsActivity.this.viewPager.setCurrentItem(TextDetailsActivity.this.getItem(-1), true);
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
            TextDetailsActivity textDetailsActivity = TextDetailsActivity.this;
            textDetailsActivity.list = textDetailsActivity.getResources().getStringArray(R.array.text_styles);
            return null;
        }

        public void onPostExecute(Void r3) {
            super.onPostExecute(r3);
            TextDetailsActivity textDetailsActivity = TextDetailsActivity.this;
            textDetailsActivity.viewPager = (ViewPager) textDetailsActivity.findViewById(R.id.vp_text);
            TextDetailsActivity textDetailsActivity2 = TextDetailsActivity.this;
            TextPagerAdapter textPagerAdapter = new TextPagerAdapter(textDetailsActivity2.getSupportFragmentManager());
            TextDetailsActivity.this.viewPager.addOnPageChangeListener(TextDetailsActivity.this);
            TextDetailsActivity.this.viewPager.setAdapter(textPagerAdapter);
            TextDetailsActivity.this.viewPager.setPageMargin(20);
            TextDetailsActivity.this.viewPager.setPageTransformer(true, new TransformerOutSlide());
        }
    }

    /* loaded from: classes2.dex */
    public class TextPagerAdapter extends FragmentPagerAdapter {
        public TextPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override // androidx.fragment.app.FragmentPagerAdapter
        public Fragment getItem(int i) {
            TextDetailsActivity textDetailsActivity = TextDetailsActivity.this;
            return TextFragment.content(textDetailsActivity.text, textDetailsActivity.list[textDetailsActivity.position + i]);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            TextDetailsActivity textDetailsActivity = TextDetailsActivity.this;
            return textDetailsActivity.size - textDetailsActivity.position;
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
                TextDetailsActivity.this.finish();
    }
}
