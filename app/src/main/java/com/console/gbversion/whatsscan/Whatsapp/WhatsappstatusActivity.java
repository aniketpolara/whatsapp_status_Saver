package com.console.gbversion.whatsscan.Whatsapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.console.gbversion.whatsscan.Application.Activity.AllStatusActivity;
import com.console.gbversion.whatsscan.Application.Activity.HomeActivity;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.R;

public class WhatsappstatusActivity extends AppCompatActivity {


    ImageView wpstatus, wpdownstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsappstatus);


        wpstatus = findViewById(R.id.wpstatus);
        wpdownstatus = findViewById(R.id.wpdownstatus);

        wpstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constant.CallIntent(WhatsappstatusActivity.this, AllStatusActivity.class);
            }
        });
        wpdownstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(WhatsappstatusActivity.this, DownloadedStatus_new.class));

//                GalleryFragmentHelper br_whatsappSavedStatusFragment = new GalleryFragmentHelper();
//                FragmentTransaction beginTransaction = WhatsappstatusActivity.this.getSupportFragmentManager().beginTransaction();
//                beginTransaction.replace(R.id.fragment_container, br_whatsappSavedStatusFragment);
//                beginTransaction.commit();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(WhatsappstatusActivity.this, HomeActivity.class));
    }
}