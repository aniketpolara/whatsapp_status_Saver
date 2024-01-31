package com.console.gbversion.whatsscan.Application.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.console.gbversion.whatsscan.Application.Fragment.ArtFragment;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.Application.Utils.TransformerOutSlide;
import com.console.gbversion.whatsscan.R;

/* loaded from: classes2.dex */
public class ArtDetailsActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    LinearLayout banner_container;
    private ImageView img_next;
    private ImageView img_previous;
    String[] list;
    private Toolbar toolbar;
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
        setContentView(R.layout.activity_art_detail);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        new AsyncTaskBase().execute(new Integer[0]);
        this.img_next = (ImageView) findViewById(R.id.img_next);
        this.img_previous = (ImageView) findViewById(R.id.img_previous);
        this.img_next.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.ArtDetailsActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ArtDetailsActivity.this.viewPager.setCurrentItem(ArtDetailsActivity.this.getItem(1), true);
            }
        });
        this.img_previous.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.ArtDetailsActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ArtDetailsActivity.this.viewPager.setCurrentItem(ArtDetailsActivity.this.getItem(-1), true);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
//        if (AdsManager.adView != null) {
//            AdsManager.adView.pause();
//        }
        super.onPause();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
//        if (AdsManager.adView != null) {
//            AdsManager.adView.resume();
//        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
//        if (AdsManager.adView != null) {
//            AdsManager.adView.destroy();
//        }
        super.onDestroy();
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
    public class ArtPagerAdapter extends FragmentPagerAdapter {
        public ArtPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override // androidx.fragment.app.FragmentPagerAdapter
        public Fragment getItem(int i) {
            return ArtFragment.content(ArtDetailsActivity.this.list[i]);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return ArtDetailsActivity.this.list.length;
        }
    }

    /* loaded from: classes2.dex */
    private class AsyncTaskBase extends AsyncTask<Integer, Void, Void> {
        private AsyncTaskBase() {
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            super.onPreExecute();
        }

        public Void doInBackground(Integer... numArr) {
            ArtDetailsActivity artDetailsActivity = ArtDetailsActivity.this;
            artDetailsActivity.list = artDetailsActivity.getResources().getStringArray(R.array.art_style);
            return null;
        }

        public void onPostExecute(Void r3) {
            super.onPostExecute(r3);
            ArtDetailsActivity artDetailsActivity = ArtDetailsActivity.this;
            artDetailsActivity.viewPager = (ViewPager) artDetailsActivity.findViewById(R.id.pgrArtDetailId);
            ArtDetailsActivity artDetailsActivity2 = ArtDetailsActivity.this;
            ArtPagerAdapter artPagerAdapter = new ArtPagerAdapter(artDetailsActivity2.getSupportFragmentManager());
            ArtDetailsActivity.this.viewPager.addOnPageChangeListener(ArtDetailsActivity.this);
            ArtDetailsActivity.this.viewPager.setAdapter(artPagerAdapter);
            ArtDetailsActivity.this.viewPager.setPageMargin(20);
            ArtDetailsActivity.this.viewPager.setPageTransformer(true, new TransformerOutSlide());
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
                ArtDetailsActivity.this.finish();
    }
}
