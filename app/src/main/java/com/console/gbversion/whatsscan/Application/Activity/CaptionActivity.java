package com.console.gbversion.whatsscan.Application.Activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.console.gbversion.whatsscan.Application.Adapter.CaptionAdapter;
import com.console.gbversion.whatsscan.Application.Utils.CaptionTitle;
import com.console.gbversion.whatsscan.Application.Utils.Captions;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.R;

/* loaded from: classes2.dex */
public class CaptionActivity extends AppCompatActivity {
    String[] CaptionList;
    String cap_language;
    String cap_type;
    CaptionTitle captionTitle;
    Captions captions;
    RecyclerView recyclerView;
    private Toolbar toolbar;
    private AppCompatTextView toolbar_text;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_captions);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.toolbar_text = (AppCompatTextView) findViewById(R.id.toolbar_text);
        this.captionTitle = new CaptionTitle();
        this.captions = new Captions();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        gridLayoutManager.setOrientation(1);
        this.recyclerView.setLayoutManager(gridLayoutManager);
        this.cap_type = getIntent().getStringExtra("TYPE");
        this.cap_language = getIntent().getStringExtra("LANGUAGE");
        AppCompatTextView appCompatTextView = this.toolbar_text;
        appCompatTextView.setText(this.cap_type.substring(0, 1).toUpperCase() + this.cap_type.substring(1));
        String[] checkShayriData = checkShayriData(this.cap_language, this.cap_type);
        this.CaptionList = checkShayriData;
        setCaptionAdapter(checkShayriData);
    }

    public void setCaptionAdapter(String[] strArr) {
        this.recyclerView.setAdapter(new CaptionAdapter(this, strArr));
    }

    public String[] checkShayriData(String str, String str2) {
        if (str.equals("GUJARATI")) {
            if (str2.equals("Love Status")) {
                this.CaptionList = this.captionTitle.guj_love;
            } else if (str2.equals("Sad Status")) {
                this.CaptionList = this.captionTitle.guj_sad;
            } else if (str2.equals("Attitude Status")) {
                this.CaptionList = this.captionTitle.guj_attitude;
            } else if (str2.equals("Romantic Status")) {
                this.CaptionList = this.captionTitle.guj_romantic;
            } else if (str2.equals("Alone Status")) {
                this.CaptionList = this.captionTitle.guj_alone;
            } else if (str2.equals("Angry Status")) {
                this.CaptionList = this.captionTitle.guj_angry;
            } else if (str2.equals("Funny Status")) {
                this.CaptionList = this.captionTitle.guj_funny;
            }
        } else if (str.equals("ENGLISH")) {
            if (str2.equals("Love Status")) {
                this.CaptionList = this.captionTitle.eng_love;
            } else if (str2.equals("Sad Status")) {
                this.CaptionList = this.captionTitle.eng_sad;
            } else if (str2.equals("Attitude Status")) {
                this.CaptionList = this.captionTitle.eng_attitude;
            } else if (str2.equals("Romantic Status")) {
                this.CaptionList = this.captionTitle.eng_romantic;
            } else if (str2.equals("Alone Status")) {
                this.CaptionList = this.captionTitle.eng_alone;
            } else if (str2.equals("Angry Status")) {
                this.CaptionList = this.captionTitle.eng_angry;
            } else if (str2.equals("Funny Status")) {
                this.CaptionList = this.captionTitle.eng_funny;
            }
        } else if (str.equals("HINDI")) {
            if (str2.equals("Love Status")) {
                this.CaptionList = this.captionTitle.hin_love;
            } else if (str2.equals("Sad Status")) {
                this.CaptionList = this.captionTitle.hin_sad;
            } else if (str2.equals("Attitude Status")) {
                this.CaptionList = this.captionTitle.hin_attitude;
            } else if (str2.equals("Romantic Status")) {
                this.CaptionList = this.captionTitle.hin_romantic;
            } else if (str2.equals("Alone Status")) {
                this.CaptionList = this.captionTitle.hin_alone;
            } else if (str2.equals("Angry Status")) {
                this.CaptionList = this.captionTitle.hin_angry;
            } else if (str2.equals("Funny Status")) {
                this.CaptionList = this.captionTitle.hin_funny;
            }
        } else if (str.equals("PUNJABI")) {
            if (str2.equals("Love Status")) {
                this.CaptionList = this.captionTitle.pun_love;
            } else if (str2.equals("Sad Status")) {
                this.CaptionList = this.captionTitle.pun_sad;
            } else if (str2.equals("Attitude Status")) {
                this.CaptionList = this.captionTitle.pun_attitude;
            } else if (str2.equals("Romantic Status")) {
                this.CaptionList = this.captionTitle.pun_romantic;
            } else if (str2.equals("Alone Status")) {
                this.CaptionList = this.captionTitle.pun_alone;
            } else if (str2.equals("Angry Status")) {
                this.CaptionList = this.captionTitle.pun_angry;
            } else if (str2.equals("Funny Status")) {
                this.CaptionList = this.captionTitle.pun_funny;
            }
        } else if (str.equals("MALAYALAM")) {
            if (str2.equals("Love Status")) {
                this.CaptionList = this.captionTitle.malayalam_love;
            } else if (str2.equals("Sad Status")) {
                this.CaptionList = this.captionTitle.malayalam_sad;
            } else if (str2.equals("Attitude Status")) {
                this.CaptionList = this.captionTitle.malayalam_attitude;
            } else if (str2.equals("Romantic Status")) {
                this.CaptionList = this.captionTitle.malayalam_romantic;
            } else if (str2.equals("Alone Status")) {
                this.CaptionList = this.captionTitle.malayalam_alone;
            } else if (str2.equals("Angry Status")) {
                this.CaptionList = this.captionTitle.malayalam_angry;
            } else if (str2.equals("Funny Status")) {
                this.CaptionList = this.captionTitle.malayalam_funny;
            }
        } else if (str.equals("TAMIL")) {
            if (str2.equals("Love Status")) {
                this.CaptionList = this.captions.tamil_love;
            } else if (str2.equals("Sad Status")) {
                this.CaptionList = this.captions.tamil_sad;
            } else if (str2.equals("Attitude Status")) {
                this.CaptionList = this.captions.tamil_attitude;
            } else if (str2.equals("Romantic Status")) {
                this.CaptionList = this.captions.tamil_romantic;
            } else if (str2.equals("Alone Status")) {
                this.CaptionList = this.captions.tamil_alone;
            } else if (str2.equals("Angry Status")) {
                this.CaptionList = this.captions.tamil_angry;
            } else if (str2.equals("Funny Status")) {
                this.CaptionList = this.captions.tamil_funny;
            }
        } else if (str.equals("TELUGU")) {
            if (str2.equals("Love Status")) {
                this.CaptionList = this.captions.telugu_love;
            } else if (str2.equals("Sad Status")) {
                this.CaptionList = this.captions.telugu_sad;
            } else if (str2.equals("Attitude Status")) {
                this.CaptionList = this.captions.telugu_attitude;
            } else if (str2.equals("Romantic Status")) {
                this.CaptionList = this.captions.telugu_romantic;
            } else if (str2.equals("Alone Status")) {
                this.CaptionList = this.captions.telugu_alone;
            } else if (str2.equals("Angry Status")) {
                this.CaptionList = this.captions.telugu_angry;
            } else if (str2.equals("Funny Status")) {
                this.CaptionList = this.captions.telugu_funny;
            }
        } else if (str.equals("URDU")) {
            if (str2.equals("Love Status")) {
                this.CaptionList = this.captions.urdu_love;
            } else if (str2.equals("Sad Status")) {
                this.CaptionList = this.captions.urdu_sad;
            } else if (str2.equals("Attitude Status")) {
                this.CaptionList = this.captions.urdu_attitude;
            } else if (str2.equals("Romantic Status")) {
                this.CaptionList = this.captions.urdu_romantic;
            } else if (str2.equals("Alone Status")) {
                this.CaptionList = this.captions.urdu_alone;
            } else if (str2.equals("Angry Status")) {
                this.CaptionList = this.captions.urdu_angry;
            } else if (str2.equals("Funny Status")) {
                this.CaptionList = this.captions.urdu_funny;
            }
        } else if (str.equals("MARATHI")) {
            if (str2.equals("Love Status")) {
                this.CaptionList = this.captions.marathi_love;
            } else if (str2.equals("Sad Status")) {
                this.CaptionList = this.captions.marathi_sad;
            } else if (str2.equals("Attitude Status")) {
                this.CaptionList = this.captions.marathi_attitude;
            } else if (str2.equals("Romantic Status")) {
                this.CaptionList = this.captions.marathi_romantic;
            } else if (str2.equals("Alone Status")) {
                this.CaptionList = this.captions.marathi_alone;
            } else if (str2.equals("Angry Status")) {
                this.CaptionList = this.captions.marathi_angry;
            } else if (str2.equals("Funny Status")) {
                this.CaptionList = this.captions.marathi_funny;
            }
        }
        return this.CaptionList;
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
                CaptionActivity.this.finish();
    }
}
