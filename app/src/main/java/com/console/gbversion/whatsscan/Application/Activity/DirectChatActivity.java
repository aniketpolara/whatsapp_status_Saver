package com.console.gbversion.whatsscan.Application.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import com.console.gbversion.whatsscan.Application.Utils.SharedPrefs;
import com.console.gbversion.whatsscan.Application.Utils.Utils;
import com.console.gbversion.whatsscan.R;

/* loaded from: classes2.dex */
public class DirectChatActivity extends AppCompatActivity {
    ImageView back;
    CountryCodePicker countryCodePicker;
    EditText edtPhoneNumber;
    EditText msg_edt;
    LinearLayout wapp;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_directchat);
        getWindow().setFlags(1024, 1024);
        Utils.setLanguage(this, SharedPrefs.getLang(this));
        ImageView imageView = (ImageView) findViewById(R.id.back);
        this.back = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.DirectChatActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DirectChatActivity.this.onBackPressed();
            }
        });
        this.countryCodePicker = (CountryCodePicker) findViewById(R.id.ccp);
        EditText editText = (EditText) findViewById(R.id.phone_number_edt);
        this.edtPhoneNumber = editText;
        this.countryCodePicker.registerPhoneNumberTextView(editText);
        this.msg_edt = (EditText) findViewById(R.id.msg_edt);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.wapp);
        this.wapp = linearLayout;
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.DirectChatActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DirectChatActivity.this.redirect();
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    public void redirect() {
        if (TextUtils.isEmpty(this.edtPhoneNumber.getText().toString())) {
            Toast.makeText(this, (int) R.string.select_country, 0).show();
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + this.countryCodePicker.getSelectedCountryCode() + this.edtPhoneNumber.getText().toString() + "&text=" + this.msg_edt.getText().toString()));
            startActivity(intent);
        } catch (Exception unused) {
            Toast.makeText(this, "Install WhatsApp First...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
                DirectChatActivity.this.finish();
    }
}
