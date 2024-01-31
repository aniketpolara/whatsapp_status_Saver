package com.console.gbversion.whatsscan.Whatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import com.console.gbversion.whatsscan.Application.Utils.SharedPrefs;
import com.console.gbversion.whatsscan.Application.Utils.Utils;
import com.console.gbversion.whatsscan.R;

public class DownloadedStatus_new extends AppCompatActivity {
    ImageView backIV;
    TabLayout tabLayout;
    String[] tabs;
    ViewPager viewPager;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloaded_status_new);
        context = DownloadedStatus_new.this;
        Utils.setLanguage(context, SharedPrefs.getLang(context));

        this.viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(this.viewPager);
        String[] strArr = new String[2];
        this.tabs = strArr;
        strArr[0] = getResources().getString(R.string.photos);
        this.tabs[1] = getResources().getString(R.string.videos);
        this.tabLayout = (TabLayout) findViewById(R.id.tablayout);
        this.tabLayout.setupWithViewPager(this.viewPager);

        for (int i = 0; i < this.tabLayout.getTabCount(); i++) {
            this.tabLayout.getTabAt(i).setCustomView(getTabViewUn(i));
        }

        setupTabIcons();
        this.tabLayout.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                TabLayout.Tab tabAt = tabLayout.getTabAt(tab.getPosition());
                tabAt.setCustomView((View) null);
                tabAt.setCustomView(DownloadedStatus_new.this.getTabView(tab.getPosition()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TabLayout.Tab tabAt = DownloadedStatus_new.this.tabLayout.getTabAt(tab.getPosition());
                tabAt.setCustomView((View) null);
                tabAt.setCustomView(DownloadedStatus_new.this.getTabViewUn(tab.getPosition()));
            }
        });


    }
    public View getTabView(int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.custom_tab, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.tab);
        textView.setText(this.tabs[i]);
        textView.setTextColor(getResources().getColor(R.color.tab_txt_press));
        textView.setBackgroundResource(R.drawable.press_tab);
        textView.setLayoutParams(new FrameLayout.LayoutParams((getResources().getDisplayMetrics().widthPixels * 438) / 1080, (getResources().getDisplayMetrics().heightPixels * 140) / 1920));
        return inflate;
    }

    private void setupTabIcons() {
        View inflate = LayoutInflater.from(context).inflate(R.layout.custom_tab, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.tab);
        textView.setText(this.tabs[0]);
        textView.setTextColor(getResources().getColor(R.color.tab_txt_press));
        textView.setBackgroundResource(R.drawable.press_tab);
        textView.setLayoutParams(new FrameLayout.LayoutParams((getResources().getDisplayMetrics().widthPixels * 438) / 1080, (getResources().getDisplayMetrics().heightPixels * 140) / 1920));
        TabLayout.Tab tabAt = this.tabLayout.getTabAt(0);
        tabAt.setCustomView((View) null);
        tabAt.setCustomView(inflate);
    }

    public View getTabViewUn(int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.custom_tab, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.tab);
        textView.setText(this.tabs[i]);
        textView.setTextColor(getResources().getColor(R.color.white));
        textView.setBackgroundResource(R.drawable.unpress_tab);
        textView.setLayoutParams(new FrameLayout.LayoutParams((getResources().getDisplayMetrics().widthPixels * 438) / 1080, (getResources().getDisplayMetrics().heightPixels * 140) / 1920));
        return inflate;
    }

    private void setupViewPager(ViewPager viewPager2) {
        DownloadedStatus_new.ViewPagerAdapter viewPagerAdapter = new DownloadedStatus_new.ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new MyPhotos(), "Photos");
        viewPagerAdapter.addFragment(new MyVideos(), "Videos");
        viewPager2.setAdapter(viewPagerAdapter);
    }

    public static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList();
        private final List<String> mFragmentTitleList = new ArrayList();

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int i) {
            return this.mFragmentList.get(i);
        }

        @Override
        public int getCount() {
            return this.mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String str) {
            this.mFragmentList.add(fragment);
            this.mFragmentTitleList.add(str);
        }

        @Override
        public CharSequence getPageTitle(int i) {
            return this.mFragmentTitleList.get(i);
        }
    }

//    private void setActionBar() {
//        setHasOptionsMenu(true);
//        ActionBar supportActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
//        supportActionBar.show();
//        supportActionBar.setDisplayShowHomeEnabled(false);
//        supportActionBar.setDisplayShowTitleEnabled(true);
//        supportActionBar.setDisplayShowCustomEnabled(false);
//        supportActionBar.setTitle("Whatsapp Saved Status");
//    }

}