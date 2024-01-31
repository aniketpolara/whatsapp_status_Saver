package com.console.gbversion.whatsscan.Application.Activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.console.gbversion.whatsscan.Application.Adapter.CaptionCatAdapter;
import com.console.gbversion.whatsscan.Application.Utils.CaptionTitle;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.R;

/* loaded from: classes2.dex */
public class CaptionTypesActivity extends AppCompatActivity {
    private CaptionTitle captionTitle;
    private String[] caption_list;
    private RecyclerView category;
    private String[] eng_list;
    private String language;
    private Toolbar toolbar;
    private AppCompatTextView toolbar_text;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.caption_types_activity);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.category = (RecyclerView) findViewById(R.id.category);
        this.toolbar_text = (AppCompatTextView) findViewById(R.id.toolbar_text);
        this.captionTitle = new CaptionTitle();
        String stringExtra = getIntent().getStringExtra("LANGUAGE");
        this.language = stringExtra;
        if (stringExtra.equals("GUJARATI")) {
            this.toolbar_text.setText("Gujarati");
            CaptionTitle captionTitle = this.captionTitle;
            this.caption_list = captionTitle.gujarati;
            this.eng_list = captionTitle.english;
        } else if (this.language.equals("ENGLISH")) {
            this.toolbar_text.setText("English");
            String[] strArr = this.captionTitle.english;
            this.caption_list = strArr;
            this.eng_list = strArr;
        } else if (this.language.equals("HINDI")) {
            this.toolbar_text.setText("Hindi");
            CaptionTitle captionTitle2 = this.captionTitle;
            this.caption_list = captionTitle2.hindi;
            this.eng_list = captionTitle2.english;
        } else if (this.language.equals("PUNJABI")) {
            this.toolbar_text.setText("Punjabi");
            CaptionTitle captionTitle3 = this.captionTitle;
            this.caption_list = captionTitle3.panjabi;
            this.eng_list = captionTitle3.english;
        } else if (this.language.equals("MARATHI")) {
            this.toolbar_text.setText("Marathi");
            CaptionTitle captionTitle4 = this.captionTitle;
            this.caption_list = captionTitle4.marathi;
            this.eng_list = captionTitle4.english;
        } else if (this.language.equals("TAMIL")) {
            this.toolbar_text.setText("Tamil");
            CaptionTitle captionTitle5 = this.captionTitle;
            this.caption_list = captionTitle5.tamil;
            this.eng_list = captionTitle5.english;
        } else if (this.language.equals("TELUGU")) {
            this.toolbar_text.setText("Telugu");
            CaptionTitle captionTitle6 = this.captionTitle;
            this.caption_list = captionTitle6.telugu;
            this.eng_list = captionTitle6.english;
        } else if (this.language.equals("URDU")) {
            this.toolbar_text.setText("Urdu");
            CaptionTitle captionTitle7 = this.captionTitle;
            this.caption_list = captionTitle7.urdu;
            this.eng_list = captionTitle7.english;
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        gridLayoutManager.setOrientation(1);
        this.category.setLayoutManager(gridLayoutManager);
        this.category.setAdapter(new CaptionCatAdapter(this, this.caption_list, this.eng_list, this.language));
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
                CaptionTypesActivity.this.finish();
    }
}
