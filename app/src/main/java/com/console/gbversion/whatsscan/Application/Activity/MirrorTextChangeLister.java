package com.console.gbversion.whatsscan.Application.Activity;

import android.text.Editable;
import android.text.TextWatcher;

/* loaded from: classes2.dex */
public final class MirrorTextChangeLister implements TextWatcher {
    final MirrorTextActivity mirrorTextActivity;

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MirrorTextChangeLister(MirrorTextActivity mirrorTextActivity) {
        this.mirrorTextActivity = mirrorTextActivity;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        if (editable == null) {
            return;
        }
        if (editable.toString().length() > 0) {
            this.mirrorTextActivity.generateRequiredText();
        } else {
            this.mirrorTextActivity.deleteText();
        }
    }
}
