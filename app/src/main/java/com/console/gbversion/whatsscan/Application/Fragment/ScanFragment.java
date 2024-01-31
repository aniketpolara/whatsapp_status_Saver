package com.console.gbversion.whatsscan.Application.Fragment;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.Result;
import com.console.gbversion.whatsscan.Application.Activity.ResultActivity;
import com.console.gbversion.whatsscan.Application.Utils.ActivityUtils;
import com.console.gbversion.whatsscan.Application.Utils.AppUtils;
import com.console.gbversion.whatsscan.Application.Utils.Preferences;
import com.console.gbversion.whatsscan.R;
import java.util.ArrayList;
import java.util.Iterator;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/* loaded from: classes2.dex */
public class ScanFragment extends Fragment {
    private int camId;
    private FloatingActionButton camera;
    private ViewGroup contentFrame;
    private FloatingActionButton flash;
    private FloatingActionButton focus;
    private int frontCamId;
    private boolean isAutoFocus;
    private boolean isFlash;
    private Activity mActivity;
    private Context mContext;
    private ArrayList<Integer> mSelectedIndices;
    private int rearCamId;
    private ZXingScannerView zXingScannerView;

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initVar();
        this.zXingScannerView = new ZXingScannerView(this.mActivity);
        setupFormats();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_scan, viewGroup, false);
        initView(inflate);
        initListener();
        return inflate;
    }

    private void initVar() {
        FragmentActivity activity = getActivity();
        this.mActivity = activity;
        Context applicationContext = activity.getApplicationContext();
        this.mContext = applicationContext;
        this.isFlash = Preferences.getInstance(applicationContext).getBoolean("flash", false).booleanValue();
        this.isAutoFocus = Preferences.getInstance(this.mContext).getBoolean("focus", true).booleanValue();
        int integer = Preferences.getInstance(this.mContext).getInteger("cam_id");
        this.camId = integer;
        if (integer == -1) {
            this.camId = this.rearCamId;
        }
        loadCams();
    }

    private void initView(View view) {
        this.contentFrame = (ViewGroup) view.findViewById(R.id.content_frame);
        this.flash = (FloatingActionButton) view.findViewById(R.id.flash);
        this.focus = (FloatingActionButton) view.findViewById(R.id.focus);
        this.camera = (FloatingActionButton) view.findViewById(R.id.camera);
        initConfigs();
    }

    private void initListener() {
        this.flash.setOnClickListener(new C05011());
        this.focus.setOnClickListener(new C05022());
        this.camera.setOnClickListener(new C05033());
        this.zXingScannerView.setResultHandler(new C08344());
    }

    private void activateScanner() {
        ZXingScannerView zXingScannerView = this.zXingScannerView;
        if (zXingScannerView != null) {
            if (zXingScannerView.getParent() != null) {
                ((ViewGroup) this.zXingScannerView.getParent()).removeView(this.zXingScannerView);
            }
            this.contentFrame.addView(this.zXingScannerView);
            if (this.zXingScannerView.isActivated()) {
                this.zXingScannerView.stopCamera();
            }
            this.zXingScannerView.startCamera(this.camId);
            this.zXingScannerView.setFlash(this.isFlash);
            this.zXingScannerView.setAutoFocus(this.isAutoFocus);
        }
    }

    public void setupFormats() {
        ArrayList arrayList = new ArrayList();
        ArrayList<Integer> arrayList2 = this.mSelectedIndices;
        if (arrayList2 == null || arrayList2.isEmpty()) {
            this.mSelectedIndices = new ArrayList<>();
            for (int i = 0; i < ZXingScannerView.ALL_FORMATS.size(); i++) {
                this.mSelectedIndices.add(Integer.valueOf(i));
            }
        }
        Iterator<Integer> it = this.mSelectedIndices.iterator();
        while (it.hasNext()) {
            arrayList.add(ZXingScannerView.ALL_FORMATS.get(it.next().intValue()));
        }
        ZXingScannerView zXingScannerView = this.zXingScannerView;
        if (zXingScannerView != null) {
            zXingScannerView.setFormats(arrayList);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        activateScanner();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        ZXingScannerView zXingScannerView = this.zXingScannerView;
        if (zXingScannerView != null) {
            zXingScannerView.stopCamera();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void setMenuVisibility(boolean z) {
        super.setMenuVisibility(z);
        ZXingScannerView zXingScannerView = this.zXingScannerView;
        if (zXingScannerView != null) {
            if (z) {
                zXingScannerView.setFlash(this.isFlash);
            } else {
                zXingScannerView.setFlash(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleFlash() {
        if (this.isFlash) {
            this.isFlash = false;
            this.flash.setImageResource(R.drawable.ic_baseline_flash_on_24);
        } else {
            this.isFlash = true;
            this.flash.setImageResource(R.drawable.ic_baseline_flash_off_24);
        }
        Preferences.getInstance(this.mContext).setBoolean("flash", this.isFlash);
        this.zXingScannerView.setFlash(this.isFlash);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleFocus() {
        if (this.isAutoFocus) {
            this.isAutoFocus = false;
            this.focus.setImageResource(R.drawable.ic_baseline_filter_center_focus_24);
            AppUtils.showToast(this.mContext, getString(R.string.autofocus_off));
        } else {
            this.isAutoFocus = true;
            this.focus.setImageResource(R.drawable.ic_focus_off);
            AppUtils.showToast(this.mContext, getString(R.string.autofocus_on));
        }
        Preferences.getInstance(this.mContext).setBoolean("focus", this.isAutoFocus);
        this.zXingScannerView.setFocusable(this.isAutoFocus);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleCamera() {
        int i = this.camId;
        int i2 = this.rearCamId;
        if (i == i2) {
            this.camId = this.frontCamId;
        } else {
            this.camId = i2;
        }
        Preferences.getInstance(this.mContext).setInteger("cam_id", this.camId);
        this.zXingScannerView.stopCamera();
        this.zXingScannerView.startCamera(this.camId);
    }

    private void initConfigs() {
        if (this.isFlash) {
            this.flash.setImageResource(R.drawable.ic_baseline_flash_off_24);
        } else {
            this.flash.setImageResource(R.drawable.ic_baseline_flash_on_24);
        }
        if (this.isAutoFocus) {
            this.focus.setImageResource(R.drawable.ic_focus_off);
        } else {
            this.focus.setImageResource(R.drawable.ic_baseline_filter_center_focus_24);
        }
    }

    private void loadCams() {
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(i, cameraInfo);
            int i2 = cameraInfo.facing;
            if (i2 == 1) {
                this.frontCamId = i;
            } else if (i2 == 0) {
                this.rearCamId = i;
            }
        }
        Preferences.getInstance(this.mContext).setInteger("cam_id", this.rearCamId);
    }

    /* loaded from: classes2.dex */
    public class C05011 implements View.OnClickListener {
        C05011() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ScanFragment.this.toggleFlash();
        }
    }

    /* loaded from: classes2.dex */
    public class C05022 implements View.OnClickListener {
        C05022() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ScanFragment.this.toggleFocus();
        }
    }

    /* loaded from: classes2.dex */
    public class C05033 implements View.OnClickListener {
        C05033() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ScanFragment.this.toggleCamera();
        }
    }

    /* loaded from: classes2.dex */
    public class C08344 implements ZXingScannerView.ResultHandler {
        C08344() {
        }

        @Override // me.dm7.barcodescanner.zxing.ZXingScannerView.ResultHandler
        public void handleResult(Result result) {
            String text = result.getText();
            ArrayList<String> stringArray = Preferences.getInstance(ScanFragment.this.mContext).getStringArray("result_list");
            stringArray.add(text);
            Preferences.getInstance(ScanFragment.this.mContext).setStringArray("result_list", stringArray);
            ScanFragment.this.zXingScannerView.resumeCameraPreview(this);
            ActivityUtils.getInstance().invokeActivity(ScanFragment.this.mActivity, ResultActivity.class, false);
        }
    }
}
