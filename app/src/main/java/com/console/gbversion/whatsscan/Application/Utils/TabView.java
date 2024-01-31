package com.console.gbversion.whatsscan.Application.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import androidx.appcompat.widget.AppCompatTextView;

/* loaded from: classes2.dex */
public class TabView extends AppCompatTextView {
    public float defaultSize;
    private ScaleGestureDetector mScaleDetector;
    public float mScaleFactor = 1.0f;
    public float zoomLimit = 2.0f;

    /* loaded from: classes2.dex */
    public class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        private ScaleListener() {
//            TabView.this = r1;
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            TabView.this.mScaleFactor *= scaleGestureDetector.getScaleFactor();
            TabView tabView = TabView.this;
            tabView.mScaleFactor = Math.max(1.0f, Math.min(tabView.mScaleFactor, tabView.zoomLimit));
            TabView tabView2 = TabView.this;
            tabView2.setTextSize(0, tabView2.defaultSize * tabView2.mScaleFactor);
            TabView.this.setGravity(80);
            return true;
        }
    }

    public TabView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize();
    }

    private void initialize() {
        this.defaultSize = getTextSize();
        this.mScaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        this.mScaleDetector.onTouchEvent(motionEvent);
        return true;
    }
}
