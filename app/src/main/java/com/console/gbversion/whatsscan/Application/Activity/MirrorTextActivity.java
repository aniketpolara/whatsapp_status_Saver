package com.console.gbversion.whatsscan.Application.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.console.gbversion.whatsscan.Application.Utils.CommonKt;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.Application.Utils.StoredData;
import com.console.gbversion.whatsscan.Application.Utils.Utility;
import java.util.HashMap;

import com.console.gbversion.whatsscan.R;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

public class MirrorTextActivity extends AppCompatActivity implements View.OnClickListener {
    private boolean bothSelect;
    private HashMap hashMap;
    private boolean mirrorSelect;
    private boolean reverseSelect;
    private Toolbar toolbar;
    private Utility utils;

    private final void setBothTextUI() {
    }

    private final void setMirrorTextUI() {
    }

    private final void setReverseTextUI() {
    }

    private final void setSimpleTextUI() {
    }

    public View _$_findCachedViewById(int i) {
        if (this.hashMap == null) {
            this.hashMap = new HashMap();
        }
        View view = (View) this.hashMap.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this.hashMap.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_mirror_text);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolBar);
        this.toolbar = toolbar2;
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        doInitializer();
        setOnClickListener();
        addTextChangeListener();
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void onPause() {
        super.onPause();
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @SuppressLint("ResourceType")
    private final void doInitializer() {
        this.utils = new Utility(this);
        ((EditText) _$_findCachedViewById(R.id.main_edit_text)).setText("");
        EditText editText = (EditText) _$_findCachedViewById(R.id.main_edit_text);
        editText.setBackgroundResource(17170445);
        Intrinsics.checkNotNullExpressionValue(editText, "main_edit_text");
        Utility utility = this.utils;
        if (utility == null) {
            Intrinsics.throwUninitializedPropertyAccessException("utils");
        }
        editText.setHint(utility.getStringFromResource(R.string.text_here));
        TextView textView = (TextView) _$_findCachedViewById(R.id.repeated_text_view);
        Intrinsics.checkNotNullExpressionValue(textView, "repeated_text_view");
        textView.setMovementMethod(new ScrollingMovementMethod());
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.repeated_text_view);
        Intrinsics.checkNotNullExpressionValue(textView2, "repeated_text_view");
        textView2.setTextSize(20.0f);
    }

    private final void setOnClickListener() {
        ((RelativeLayout) _$_findCachedViewById(R.id.iv_mirror)).setOnClickListener(this);
        ((RelativeLayout) _$_findCachedViewById(R.id.iv_reverse)).setOnClickListener(this);
        ((RelativeLayout) _$_findCachedViewById(R.id.iv_both)).setOnClickListener(this);
        ((RelativeLayout) _$_findCachedViewById(R.id.iv_copy)).setOnClickListener(this);
        ((RelativeLayout) _$_findCachedViewById(R.id.iv_share)).setOnClickListener(this);
        ((RelativeLayout) _$_findCachedViewById(R.id.iv_delete)).setOnClickListener(this);
    }

    private final void addTextChangeListener() {
        ((EditText) _$_findCachedViewById(R.id.main_edit_text)).addTextChangedListener(new MirrorTextChangeLister(this));
    }

    public final void generateRequiredText() {
        if (this.bothSelect) {
            generateBothText();
        } else if (this.mirrorSelect) {
            generateMirrorText();
        } else if (this.reverseSelect) {
            generateReverseText();
        } else {
            generateSimpleText();
        }
    }

    private final void generateMirrorText() {
        EditText editText = (EditText) _$_findCachedViewById(R.id.main_edit_text);
        Intrinsics.checkNotNullExpressionValue(editText, "main_edit_text");
        writeText(convertTextInMirror(editText.getText().toString()));
    }

    private final void generateReverseText() {
        EditText editText = (EditText) _$_findCachedViewById(R.id.main_edit_text);
        Intrinsics.checkNotNullExpressionValue(editText, "main_edit_text");
        writeText(convertTextInReverse(editText.getText().toString()));
    }

    private final void generateBothText() {
        EditText editText = (EditText) _$_findCachedViewById(R.id.main_edit_text);
        Intrinsics.checkNotNullExpressionValue(editText, "main_edit_text");
        writeText(convertTextInMirror(convertTextInReverse(editText.getText().toString())));
    }

    private final void generateSimpleText() {
        EditText editText = (EditText) _$_findCachedViewById(R.id.main_edit_text);
        Intrinsics.checkNotNullExpressionValue(editText, "main_edit_text");
        writeText(editText.getText().toString());
    }

    private final String convertTextInReverse(String str) {
        String str2 = "";
        for (int length = str.length() - 1; length >= 0; length--) {
            str2 = str2 + str.charAt(length);
        }
        return str2;
    }

    private final String convertTextInMirror(String str) {
        StringBuilder sb;
        int length = str.length();
        String str2 = "";
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            StoredData storedData = StoredData.INSTANCE;
            if (ArraysKt.contains(storedData.getSimple_small_capital_letters_and_digits(), charAt)) {
                sb = new StringBuilder();
                sb.append(str2);
                sb.append(storedData.getMirror_small_capital_letters_and_digits()[ArraysKt.indexOf(storedData.getSimple_small_capital_letters_and_digits(), charAt)]);
            } else {
                sb = new StringBuilder();
                sb.append(str2);
                sb.append(charAt);
            }
            str2 = sb.toString();
        }
        return str2;
    }

    private final void writeText(String str) {
        TextView textView = (TextView) _$_findCachedViewById(R.id.repeated_text_view);
        Intrinsics.checkNotNullExpressionValue(textView, "repeated_text_view");
        textView.setVisibility(0);
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.repeated_text_view);
        Intrinsics.checkNotNullExpressionValue(textView2, "repeated_text_view");
        textView2.setText(str);
    }

    public final void deleteText() {
        TextView textView = (TextView) _$_findCachedViewById(R.id.repeated_text_view);
        Intrinsics.checkNotNullExpressionValue(textView, "repeated_text_view");
        textView.setText("");
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.repeated_text_view);
        Intrinsics.checkNotNullExpressionValue(textView2, "repeated_text_view");
        textView2.setVisibility(0);
        textView2.getText();
        ((TextView) _$_findCachedViewById(R.id.repeated_text_view)).setVisibility(0);
    }

    public void onClick(View view) {
        if (view != null) {
            if (Intrinsics.areEqual(view, (RelativeLayout) _$_findCachedViewById(R.id.iv_mirror))) {
                mirrorFunction();
            } else if (Intrinsics.areEqual(view, (RelativeLayout) _$_findCachedViewById(R.id.iv_reverse))) {
                reverseFunction();
            } else if (Intrinsics.areEqual(view, (RelativeLayout) _$_findCachedViewById(R.id.iv_both))) {
                bothFunction();
            } else if (Intrinsics.areEqual(view, (RelativeLayout) _$_findCachedViewById(R.id.iv_share))) {
                shareText();
            } else if (Intrinsics.areEqual(view, (RelativeLayout) _$_findCachedViewById(R.id.iv_copy))) {
                copyText();
            } else if (Intrinsics.areEqual(view, (RelativeLayout) _$_findCachedViewById(R.id.iv_delete))) {
                deleteText();
            }
        }
    }

    private final void mirrorFunction() {
        this.mirrorSelect = !this.mirrorSelect;
        this.reverseSelect = false;
        this.bothSelect = false;
        setUI();
    }

    private final void reverseFunction() {
        this.reverseSelect = !this.reverseSelect;
        this.bothSelect = false;
        this.mirrorSelect = false;
        setUI();
    }

    private final void bothFunction() {
        this.bothSelect = true;
        this.mirrorSelect = true;
        this.reverseSelect = true;
        setUI();
    }

    private final void setUI() {
        if (this.bothSelect) {
            setBothTextUI();
        } else if (this.mirrorSelect) {
            setMirrorTextUI();
        } else if (this.reverseSelect) {
            setReverseTextUI();
        } else {
            setSimpleTextUI();
        }
        generateRequiredText();
    }

    private final void shareText() {
        TextView textView = (TextView) _$_findCachedViewById(R.id.repeated_text_view);
        Intrinsics.checkNotNullExpressionValue(textView, "repeated_text_view");
        if (!CommonKt.shareText(textView.getText().toString(), this)) {
            Utility utility = this.utils;
            if (utility == null) {
                Intrinsics.throwUninitializedPropertyAccessException("utils");
            }
//            utility.showToastFromResource(R.string.no_text_to_share);
            Toast.makeText(this, "No text found to share", Toast.LENGTH_SHORT).show();
        }
    }

    private final void copyText() {
        TextView textView = (TextView) _$_findCachedViewById(R.id.repeated_text_view);
        Intrinsics.checkNotNullExpressionValue(textView, "repeated_text_view");
        if (CommonKt.copyText(textView.getText().toString(), this)) {
            Utility utility = this.utils;
            if (utility == null) {
                Intrinsics.throwUninitializedPropertyAccessException("utils");
            }
//            utility.showToastFromResource(R.string.text_copied);
            Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_SHORT).show();

            return;
        }
        Utility utility2 = this.utils;
        if (utility2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("utils");
        }
//        utility2.showToastFromResource(R.string.no_text_to_copy);
        Toast.makeText(this, "No text found to copy", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {
                MirrorTextActivity.this.finish();
    }
}