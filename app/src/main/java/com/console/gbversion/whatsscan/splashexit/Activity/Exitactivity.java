package com.console.gbversion.whatsscan.splashexit.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import com.console.gbversion.whatsscan.R;

/* loaded from: classes3.dex */
public class Exitactivity extends AppCompatActivity {
    ImageView exit;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_exit);
        getWindow().setFlags(1024, 1024);
        ImageView imageView = (ImageView) findViewById(R.id.exit);
        this.exit = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.console.globalcall.xvideocall.splashexit.Activity.royaldi_Exitactivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Exitactivity.this.finishAffinity();
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        finishAffinity();
    }
}
