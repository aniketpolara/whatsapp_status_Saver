package com.console.gbversion.whatsscan.splashexit.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.console.gbversion.whatsscan.Application.Activity.HomeActivity;
import com.console.gbversion.whatsscan.R;


public class Backactivity extends AppCompatActivity {
    LinearLayout no;
    LinearLayout rate;
    LinearLayout yes;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_back);
        getWindow().setFlags(1024, 1024);

        ((ImageView) findViewById(R.id.s_yes)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Backactivity.this, Exitactivity.class));
            }
        });

        ((ImageView) findViewById(R.id.s_no)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Backactivity.this, HomeActivity.class));

            }
        });

    }

    @Override
    public void onBackPressed() {
    }


}
