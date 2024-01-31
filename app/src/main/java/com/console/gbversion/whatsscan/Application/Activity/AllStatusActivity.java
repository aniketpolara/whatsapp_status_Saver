package com.console.gbversion.whatsscan.Application.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import com.console.gbversion.whatsscan.Application.Fragment.RecentWapp;
import com.console.gbversion.whatsscan.Application.Fragment.RecentWappBus;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.Application.Utils.SharedPrefs;
import com.console.gbversion.whatsscan.R;

import java.util.ArrayList;
import java.util.List;

public class AllStatusActivity extends AppCompatActivity {
    ViewPagerAdapter adapter;
    boolean isOpenWapp = false;
    boolean isOpenWbApp = false;
    TabLayout tabLayout;
    String[] tabs;
    private Toolbar toolbar;
    ViewPager viewPager;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_all_status);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        this.viewPager = viewPager;
        setupViewPager(viewPager);
        String[] strArr = new String[2];
        this.tabs = strArr;
        strArr[0] = getResources().getString(R.string.wapp);
        this.tabs[1] = getResources().getString(R.string.wbapp);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        this.tabLayout = tabLayout;
        tabLayout.setupWithViewPager(this.viewPager);
        for (int i = 0; i < this.tabLayout.getTabCount(); i++) {
            this.tabLayout.getTabAt(i).setCustomView(getTabViewUn(i));
        }
        setupTabIcons();
        this.tabLayout.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) new TabLayout.OnTabSelectedListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.AllStatusActivity.1
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                AllStatusActivity.this.viewPager.setCurrentItem(tab.getPosition());
                TabLayout.Tab tabAt = AllStatusActivity.this.tabLayout.getTabAt(tab.getPosition());
                tabAt.setCustomView((View) null);
                tabAt.setCustomView(AllStatusActivity.this.getTabView(tab.getPosition()));
                if (tab.getPosition() == 0) {
                    AllStatusActivity allStatusActivity = AllStatusActivity.this;
                    if (allStatusActivity.isOpenWapp) {
                        allStatusActivity.isOpenWapp = false;
                        if (!SharedPrefs.getWATree(allStatusActivity).equals("")) {
                            FragmentManager supportFragmentManager = AllStatusActivity.this.getSupportFragmentManager();
                            ((RecentWapp) supportFragmentManager.findFragmentByTag("android:switcher:" + AllStatusActivity.this.viewPager.getId() + ":" + tab.getPosition())).populateGrid();
                        }
                    }
                }
                if (tab.getPosition() == 1) {
                    AllStatusActivity allStatusActivity2 = AllStatusActivity.this;
                    if (allStatusActivity2.isOpenWbApp) {
                        allStatusActivity2.isOpenWbApp = false;
                        if (!SharedPrefs.getWBTree(allStatusActivity2).equals("")) {
                            FragmentManager supportFragmentManager2 = AllStatusActivity.this.getSupportFragmentManager();
                            ((RecentWappBus) supportFragmentManager2.findFragmentByTag("android:switcher:" + AllStatusActivity.this.viewPager.getId() + ":" + tab.getPosition())).populateGrid();
                        }
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TabLayout.Tab tabAt = AllStatusActivity.this.tabLayout.getTabAt(tab.getPosition());
                tabAt.setCustomView((View) null);
                tabAt.setCustomView(AllStatusActivity.this.getTabViewUn(tab.getPosition()));
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        this.adapter = viewPagerAdapter;
        viewPagerAdapter.addFragment(new RecentWapp(), "Whatsapp");
        this.adapter.addFragment(new RecentWappBus(), "WA Business");
        viewPager.setAdapter(this.adapter);
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

    private void setupTabIcons() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.custom_tab, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.tab);
        textView.setText(this.tabs[0]);
        textView.setTextColor(getResources().getColor(R.color.tab_txt_press));
        textView.setBackgroundResource(R.drawable.press_tab);
        textView.setLayoutParams(new FrameLayout.LayoutParams((getResources().getDisplayMetrics().widthPixels * 438) / 1080, (getResources().getDisplayMetrics().heightPixels * 140) / 1920));
        TabLayout.Tab tabAt = this.tabLayout.getTabAt(0);
        tabAt.setCustomView((View) null);
        tabAt.setCustomView(inflate);
    }

    public View getTabView(int i) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.custom_tab, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.tab);
        textView.setText(this.tabs[i]);
        textView.setTextColor(getResources().getColor(R.color.tab_txt_press));
        textView.setBackgroundResource(R.drawable.press_tab);
        textView.setLayoutParams(new FrameLayout.LayoutParams((getResources().getDisplayMetrics().widthPixels * 438) / 1080, (getResources().getDisplayMetrics().heightPixels * 140) / 1920));
        return inflate;
    }

    public View getTabViewUn(int i) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.custom_tab, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.tab);
        textView.setText(this.tabs[i]);
        textView.setTextColor(getResources().getColor(R.color.tab_txt_unpress));
        textView.setBackgroundResource(R.drawable.unpress_tab);
        textView.setLayoutParams(new FrameLayout.LayoutParams((getResources().getDisplayMetrics().widthPixels * 438) / 1080, (getResources().getDisplayMetrics().heightPixels * 140) / 1920));
        return inflate;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        AllStatusActivity.this.finish();
    }
}
