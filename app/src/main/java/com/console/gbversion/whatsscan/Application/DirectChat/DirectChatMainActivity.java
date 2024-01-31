package com.console.gbversion.whatsscan.Application.DirectChat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import java.io.File;

import com.console.gbversion.whatsscan.Application.Utils.Utils;
import com.console.gbversion.whatsscan.R;

public class DirectChatMainActivity extends AppCompatActivity {
    private final int STORAGE_PERMISSION_REQUESTED_CODE = 101;
    RadioButton btnWA;
    RadioButton btnWAB;
    CardView cardViewIdentity;
    CountryCodePicker ccp;
    CheckBox checkBoxLaterUse;
    EditText edtIdentity;
    EditText edtMessage;
    EditText edtPhoneNumber;
    String filePath = null;
    boolean isBackPress = false;
    ImageView ivPreview;
    String message;
    String name;
    String number;
    String selectedWAPackageName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_chat_main);
        getWindow().setFlags(1024, 1024);

        ivPreview = findViewById(R.id.ivPreview);
        btnWA = findViewById(R.id.btnWA);
        btnWAB = findViewById(R.id.btnWAB);
        cardViewIdentity = findViewById(R.id.cardViewIdentity);
        ccp = findViewById(R.id.ccp);
        checkBoxLaterUse = findViewById(R.id.checkBoxLaterUse);
        edtIdentity = findViewById(R.id.edtIdentity);
        edtMessage = findViewById(R.id.edtMessage);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        findViewById(R.id.linearSendMessage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkDetail()) {
                    return;
                }
                if (btnWA.isChecked()) {
                    if (Utils.isWhatsAppInstall(DirectChatMainActivity.this)) {
                        selectedWAPackageName = "com.whatsapp";
                        checkForAds();
                        return;
                    }
                    Toast.makeText(DirectChatMainActivity.this, getString(R.string.alert_install_whats_app), 0).show();
                } else if (!btnWAB.isChecked()) {
                    Toast.makeText(DirectChatMainActivity.this, getString(R.string.select_wa_type), 0).show();
                } else if (Utils.isBusinessWhatsAppInstall(DirectChatMainActivity.this)) {
                    selectedWAPackageName = "com.whatsapp.w4b";
                    checkForAds();
                } else {
                    Toast.makeText(DirectChatMainActivity.this, getString(R.string.alert_install_whats_app_buisness), 0).show();
                }
            }
        });

        findViewById(R.id.relativeSendImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFile();
            }
        });

        checkBoxLaterUse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                DirectChatMainActivity.this.cardViewIdentity.setVisibility(z ? 0 : 8);
            }
        });

        this.btnWA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    DirectChatMainActivity.this.btnWAB.setChecked(false);
                }
            }
        });
        this.btnWAB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    DirectChatMainActivity.this.btnWA.setChecked(false);
                }
            }
        });
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.linearSendMessage) {
            this.isBackPress = false;
            if (!checkDetail()) {
                return;
            }
            if (this.btnWA.isChecked()) {
                if (Utils.isWhatsAppInstall(this)) {
                    this.selectedWAPackageName = "com.whatsapp";
                    checkForAds();
                    return;
                }
                Toast.makeText(this, getString(R.string.alert_install_whats_app), 0).show();
            } else if (!this.btnWAB.isChecked()) {
                Toast.makeText(this, getString(R.string.select_wa_type), 0).show();
            } else if (Utils.isBusinessWhatsAppInstall(this)) {
                this.selectedWAPackageName = "com.whatsapp.w4b";
                checkForAds();
            } else {
                Toast.makeText(this, getString(R.string.alert_install_whats_app_buisness), 0).show();
            }
        }
//        else if (id == R.id.relativeSendImage && Permission.checkPermission(this, 101)) {
        else if (id == R.id.relativeSendImage) {
            selectFile();
        }
    }

    private void checkForAds() {
        openWa(this.selectedWAPackageName);
    }

    private boolean checkDetail() {
        this.message = this.edtMessage.getText().toString();
        this.number = this.edtPhoneNumber.getText().toString();
        this.name = this.edtIdentity.getText().toString();
        if (this.number.length() <= 0) {
            this.edtPhoneNumber.setError(getResources().getString(R.string.alert_enter_whats_app_number));
            return false;
        } else if (!this.checkBoxLaterUse.isChecked() || this.name.length() > 0) {
            return true;
        } else {
            this.edtIdentity.setError(getResources().getString(R.string.hint_enter_name_for_identity));
            return false;
        }
    }

    private void openWa(String str) {
        try {
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
            if (this.filePath == null || this.filePath.length() <= 0) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(Utils.WA_API_BASE_URL + this.ccp.getSelectedCountryCode() + this.edtPhoneNumber.getText().toString() + "&text=" + this.message));
                intent.setPackage(str);
                startActivity(intent);
            } else {
                Intent intent2 = new Intent();
                intent2.setAction("android.intent.action.SEND");
                intent2.setType("text/plain");
                intent2.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(this.filePath)));
                intent2.putExtra("android.intent.extra.TEXT", this.message);
                intent2.setPackage(str);
                StringBuilder sb = new StringBuilder();
                sb.append(this.ccp.getSelectedCountryCode() + this.number);
                sb.append("@s.whatsapp.net");
                intent2.putExtra("jid", sb.toString());
                intent2.addFlags(268435456);
                startActivity(intent2);
            }
            this.edtMessage.setText("");
            this.edtPhoneNumber.setText("");
            this.edtIdentity.setText("");
            this.filePath = "";
            Glide.with((FragmentActivity) this).load(new File(this.filePath)).into(this.ivPreview);
            this.ivPreview.setVisibility(8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 101) {
            selectFile();
        }
    }

    private void selectFile() {
        String[] strArr = {"image/*"};
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        if (Build.VERSION.SDK_INT >= 19) {
            intent.setType(strArr[0]);
            intent.putExtra("android.intent.extra.MIME_TYPES", strArr);
        } else {
            String str = "";
            for (int i = 0; i < 1; i++) {
                str = str + strArr[i] + "|";
            }
            intent.setTypeAndNormalize(str.substring(0, str.length() - 1));
        }
        try {
            startActivityForResult(intent, 100);
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(this, getString(R.string.please_install_file_manager), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100 && intent != null) {
            String path = FileUtils.getPath(this, intent.getData());
            this.filePath = path;
            if (path == null || path.length() <= 0) {
                this.ivPreview.setVisibility(4);
                return;
            }
            this.ivPreview.setVisibility(0);
            Glide.with((FragmentActivity) this).load(new File(this.filePath)).into(this.ivPreview);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
