package com.console.gbversion.whatsscan.Application.Activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.console.gbversion.whatsscan.Application.Adapter.MainPagerAdapter;
import com.console.gbversion.whatsscan.Application.Utils.AppUtils;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.R;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public class QRCodeScanActivity extends AppCompatActivity {
    private ArrayList<String> Fragments;
    private BottomNavigationView bottomNavigationView;
    private NetworkChangeReceiver brd;
    private boolean check;
    private Activity mActivity;
    private Context mContext;
    private ViewPager mViewPager;
    private Toolbar toolbar;

    @Override
    // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        getWindow().setFlags(1024, 1024);
        initVars();
        initViews();
        initFunctionality();
        initListeners();
        if (!Constant.isOnline(this)) {
            brodCast(this);
        }
    }

    private void initVars() {
        this.mActivity = this;
        this.mContext = getApplicationContext();
        this.Fragments = new ArrayList<>();
    }

    private void initViews() {
        setContentView(R.layout.activity_qr_code_scan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.mViewPager = (ViewPager) findViewById(R.id.viewpager);
        this.bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
    }

    private void initFunctionality() {
        if (ContextCompat.checkSelfPermission(this.mActivity, "android.permission.CAMERA") == 0 && ContextCompat.checkSelfPermission(this.mActivity, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            setUpViewPager();
        } else {
            ActivityCompat.requestPermissions(this.mActivity, new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"}, 445);
        }
    }

    private void initListeners() {
        this.bottomNavigationView.setOnNavigationItemSelectedListener(new C08281());
        this.mViewPager.addOnPageChangeListener(new C08292());
    }

    private void setUpViewPager() {
        this.Fragments.add(getString(R.string.menu_scan));
        this.Fragments.add(getString(R.string.menu_generate));
        this.Fragments.add(getString(R.string.menu_history));
        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), this.Fragments);
        this.mViewPager.setAdapter(mainPagerAdapter);
        mainPagerAdapter.notifyDataSetChanged();
    }

    @Override
    // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 445) {
            if (iArr.length <= 0 || iArr[0] != 0) {
                AppUtils.showToast(this.mContext, getString(R.string.permission_not_granted));
            } else {
                setUpViewPager();
            }
        }
    }

    public void brodCast(Context context) {
        try {
            this.brd = new NetworkChangeReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            context.registerReceiver(this.brd, intentFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    /* loaded from: classes2.dex */
    public class C08281 implements BottomNavigationView.OnNavigationItemSelectedListener {
        C08281() {
        }

        @Override // com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.nav_generate:
                    QRCodeScanActivity.this.mViewPager.setCurrentItem(1, true);
                    break;
                case R.id.nav_history:
                    QRCodeScanActivity.this.mViewPager.setCurrentItem(2, true);
                    break;
                case R.id.nav_scan:
                    QRCodeScanActivity.this.mViewPager.setCurrentItem(0, true);
                    break;
            }
            return true;
        }
    }

    /* loaded from: classes2.dex */
    public class C08292 implements ViewPager.OnPageChangeListener {
        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }

        C08292() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            if (i == 0) {
                QRCodeScanActivity.this.bottomNavigationView.setSelectedItemId(R.id.nav_scan);
            } else if (i == 1) {
                QRCodeScanActivity.this.bottomNavigationView.setSelectedItemId(R.id.nav_generate);
            } else if (i == 2) {
                QRCodeScanActivity.this.bottomNavigationView.setSelectedItemId(R.id.nav_history);
            }
        }
    }

    /* loaded from: classes2.dex */
    public class NetworkChangeReceiver extends BroadcastReceiver {
        boolean c = true;

        public NetworkChangeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (this.c) {
                this.c = false;
                QRCodeScanActivity.this.check = true;
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
            if ((networkInfo.isAvailable() || networkInfo2.isAvailable()) && Constant.isOnline(context)) {
                QRCodeScanActivity.this.check = false;
            }
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        NetworkChangeReceiver networkChangeReceiver = this.brd;
        if (networkChangeReceiver != null) {
            unregisterReceiver(networkChangeReceiver);
        }
        QRCodeScanActivity.this.finish();
    }
}
