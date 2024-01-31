package com.console.gbversion.whatsscan.Application.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.console.gbversion.whatsscan.Application.Fragment.GenerateFragment;
import com.console.gbversion.whatsscan.Application.Fragment.HistoryFragment;
import com.console.gbversion.whatsscan.Application.Fragment.ScanFragment;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class MainPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<String> mFragmentItems;

    public MainPagerAdapter(FragmentManager fragmentManager, ArrayList<String> arrayList) {
        super(fragmentManager, 1);
        this.mFragmentItems = arrayList;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        if (i == 0) {
            return new ScanFragment();
        }
        if (i == 1) {
            return new GenerateFragment();
        }
        if (i == 2) {
            return new HistoryFragment();
        }
        return null;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.mFragmentItems.size();
    }
}
