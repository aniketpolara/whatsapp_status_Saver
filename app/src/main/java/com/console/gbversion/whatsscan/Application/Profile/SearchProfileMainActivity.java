package com.console.gbversion.whatsscan.Application.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import com.console.gbversion.whatsscan.Application.Utils.Utils;
import com.console.gbversion.whatsscan.R;

public class SearchProfileMainActivity extends AppCompatActivity {

    RadioButton btnWA;
    RadioButton btnWAB;
    CountryCodePicker ccp;
    EditText edtPhoneNumber;
    boolean isBackPress = false;
    String selectedWAPackageName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_profile_main); getWindow().setFlags(1024,1024);
        btnWA = findViewById(R.id.btnWA);
        btnWAB = findViewById(R.id.btnWAB);
        ccp = findViewById(R.id.ccp);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        btnWA = findViewById(R.id.btnWA);


        findViewById(R.id.tvSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtPhoneNumber.getText().length() <= 0) {
                    edtPhoneNumber.setError(getResources().getString(R.string.alert_enter_whats_app_number));
                    Toast.makeText(SearchProfileMainActivity.this, getString(R.string.alert_enter_whats_app_number), 0).show();
                } else if (btnWA.isChecked()) {
                    if (Utils.isWhatsAppInstall(SearchProfileMainActivity.this)) {
                        selectedWAPackageName = "com.whatsapp";
                        openWa(selectedWAPackageName);
                        return;
                    }
                    Toast.makeText(SearchProfileMainActivity.this, getString(R.string.alert_install_whats_app), 0).show();
                } else if (!btnWAB.isChecked()) {
                    Toast.makeText(SearchProfileMainActivity.this, getString(R.string.select_wa_type), 0).show();
                } else if (Utils.isBusinessWhatsAppInstall(SearchProfileMainActivity.this)) {
                    selectedWAPackageName = "com.whatsapp.w4b";
                    openWa(selectedWAPackageName);
                } else {
                    Toast.makeText(SearchProfileMainActivity.this, getString(R.string.alert_install_whats_app_buisness), 0).show();
                }
            }
        });

        this.btnWA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    SearchProfileMainActivity.this.btnWAB.setChecked(false);
                }
            }
        });
        this.btnWAB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    SearchProfileMainActivity.this.btnWA.setChecked(false);
                }
            }
        });
    }

    private void openWa(String str) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(Utils.WA_API_BASE_URL + this.ccp.getSelectedCountryCode() + this.edtPhoneNumber.getText().toString() + "&text="));
        intent.setPackage(str);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
                finish();
    }
}