package com.console.gbversion.whatsscan.Application.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.console.gbversion.whatsscan.Application.Fragment.EmojiFragment;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.Application.Utils.TransformerOutSlide;
import com.console.gbversion.whatsscan.R;

public class EmojiDetailsActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ImageView img_next;
    private ImageView img_previous;
    private String[] list;
    private Toolbar toolbar;
    private AppCompatTextView toolbar_text;
    private ViewPager viewPager;

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_emoji_details);
        getWindow().setFlags(1024, 1024);
        Constant.setStatusBarGradiant(this, R.drawable.status_bar);
        this.toolbar = (Toolbar) findViewById(R.id.toolBar);
        this.toolbar_text = (AppCompatTextView) findViewById(R.id.toolbar_text);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Intent intent = getIntent();
        this.toolbar_text.setText(intent.getStringExtra("title"));
        new LoadAsyncTask().execute(Integer.valueOf(intent.getIntExtra("position", 0)));
        this.img_next = (ImageView) findViewById(R.id.img_next);
        this.img_previous = (ImageView) findViewById(R.id.img_previous);
        this.img_next.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.EmojiDetailsActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EmojiDetailsActivity.this.viewPager.setCurrentItem(EmojiDetailsActivity.this.getItem(1), true);
            }
        });
        this.img_previous.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.EmojiDetailsActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EmojiDetailsActivity.this.viewPager.setCurrentItem(EmojiDetailsActivity.this.getItem(-1), true);
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

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getItem(int i) {
        return this.viewPager.getCurrentItem() + i;
    }

    /* loaded from: classes2.dex */
    public class EmojiPagerAdapter extends FragmentPagerAdapter {
        public EmojiPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override // androidx.fragment.app.FragmentPagerAdapter
        public Fragment getItem(int i) {
            String str = EmojiDetailsActivity.this.list[i];
            return EmojiFragment.content(str, "" + (i + 1) + " / " + EmojiDetailsActivity.this.list.length);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return EmojiDetailsActivity.this.list.length;
        }
    }

    /* loaded from: classes2.dex */
    private class LoadAsyncTask extends AsyncTask<Integer, Void, Void> {
        private LoadAsyncTask() {
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            super.onPreExecute();
        }

        public Void doInBackground(Integer... numArr) {
            switch (numArr[0].intValue()) {
                case 0:
                    EmojiDetailsActivity emojiDetailsActivity = EmojiDetailsActivity.this;
                    emojiDetailsActivity.list = emojiDetailsActivity.getResources().getStringArray(R.array.cat_love);
                    return null;
                case 1:
                    EmojiDetailsActivity emojiDetailsActivity2 = EmojiDetailsActivity.this;
                    emojiDetailsActivity2.list = emojiDetailsActivity2.getResources().getStringArray(R.array.cat_sad);
                    return null;
                case 2:
                    EmojiDetailsActivity emojiDetailsActivity3 = EmojiDetailsActivity.this;
                    emojiDetailsActivity3.list = emojiDetailsActivity3.getResources().getStringArray(R.array.cat_sorry);
                    return null;
                case 3:
                    EmojiDetailsActivity emojiDetailsActivity4 = EmojiDetailsActivity.this;
                    emojiDetailsActivity4.list = emojiDetailsActivity4.getResources().getStringArray(R.array.cat_happy);
                    return null;
                case 4:
                    EmojiDetailsActivity emojiDetailsActivity5 = EmojiDetailsActivity.this;
                    emojiDetailsActivity5.list = emojiDetailsActivity5.getResources().getStringArray(R.array.cat_surprised);
                    return null;
                case 5:
                    EmojiDetailsActivity emojiDetailsActivity6 = EmojiDetailsActivity.this;
                    emojiDetailsActivity6.list = emojiDetailsActivity6.getResources().getStringArray(R.array.cat_scared);
                    return null;
                case 6:
                    EmojiDetailsActivity emojiDetailsActivity7 = EmojiDetailsActivity.this;
                    emojiDetailsActivity7.list = emojiDetailsActivity7.getResources().getStringArray(R.array.cat_sing_music);
                    return null;
                case 7:
                    EmojiDetailsActivity emojiDetailsActivity8 = EmojiDetailsActivity.this;
                    emojiDetailsActivity8.list = emojiDetailsActivity8.getResources().getStringArray(R.array.cat_dance);
                    return null;
                case 8:
                    EmojiDetailsActivity emojiDetailsActivity9 = EmojiDetailsActivity.this;
                    emojiDetailsActivity9.list = emojiDetailsActivity9.getResources().getStringArray(R.array.cat_smug);
                    return null;
                case 9:
                    EmojiDetailsActivity emojiDetailsActivity10 = EmojiDetailsActivity.this;
                    emojiDetailsActivity10.list = emojiDetailsActivity10.getResources().getStringArray(R.array.cat_failure);
                    return null;
                case 10:
                    EmojiDetailsActivity emojiDetailsActivity11 = EmojiDetailsActivity.this;
                    emojiDetailsActivity11.list = emojiDetailsActivity11.getResources().getStringArray(R.array.cat_newyear);
                    return null;
                case 11:
                    EmojiDetailsActivity emojiDetailsActivity12 = EmojiDetailsActivity.this;
                    emojiDetailsActivity12.list = emojiDetailsActivity12.getResources().getStringArray(R.array.cat_animals);
                    return null;
                case 12:
                    EmojiDetailsActivity emojiDetailsActivity13 = EmojiDetailsActivity.this;
                    emojiDetailsActivity13.list = emojiDetailsActivity13.getResources().getStringArray(R.array.cat_weapon);
                    return null;
                case 13:
                    EmojiDetailsActivity emojiDetailsActivity14 = EmojiDetailsActivity.this;
                    emojiDetailsActivity14.list = emojiDetailsActivity14.getResources().getStringArray(R.array.cat_flag);
                    return null;
                case 14:
                    EmojiDetailsActivity emojiDetailsActivity15 = EmojiDetailsActivity.this;
                    emojiDetailsActivity15.list = emojiDetailsActivity15.getResources().getStringArray(R.array.cat_evil);
                    return null;
                case 15:
                    EmojiDetailsActivity emojiDetailsActivity16 = EmojiDetailsActivity.this;
                    emojiDetailsActivity16.list = emojiDetailsActivity16.getResources().getStringArray(R.array.cat_number);
                    return null;
                case 16:
                    EmojiDetailsActivity emojiDetailsActivity17 = EmojiDetailsActivity.this;
                    emojiDetailsActivity17.list = emojiDetailsActivity17.getResources().getStringArray(R.array.cat_angry);
                    return null;
                case 17:
                    EmojiDetailsActivity emojiDetailsActivity18 = EmojiDetailsActivity.this;
                    emojiDetailsActivity18.list = emojiDetailsActivity18.getResources().getStringArray(R.array.cat_bye);
                    return null;
                case 18:
                    EmojiDetailsActivity emojiDetailsActivity19 = EmojiDetailsActivity.this;
                    emojiDetailsActivity19.list = emojiDetailsActivity19.getResources().getStringArray(R.array.cat_confused);
                    return null;
                case 19:
                    EmojiDetailsActivity emojiDetailsActivity20 = EmojiDetailsActivity.this;
                    emojiDetailsActivity20.list = emojiDetailsActivity20.getResources().getStringArray(R.array.cat_kiss);
                    return null;
                case 20:
                    EmojiDetailsActivity emojiDetailsActivity21 = EmojiDetailsActivity.this;
                    emojiDetailsActivity21.list = emojiDetailsActivity21.getResources().getStringArray(R.array.cat_shy);
                    return null;
                case 21:
                    EmojiDetailsActivity emojiDetailsActivity22 = EmojiDetailsActivity.this;
                    emojiDetailsActivity22.list = emojiDetailsActivity22.getResources().getStringArray(R.array.cat_tired);
                    return null;
                case 22:
                    EmojiDetailsActivity emojiDetailsActivity23 = EmojiDetailsActivity.this;
                    emojiDetailsActivity23.list = emojiDetailsActivity23.getResources().getStringArray(R.array.cat_wink);
                    return null;
                case 23:
                    EmojiDetailsActivity emojiDetailsActivity24 = EmojiDetailsActivity.this;
                    emojiDetailsActivity24.list = emojiDetailsActivity24.getResources().getStringArray(R.array.cat_other);
                    return null;
                default:
                    return null;
            }
        }

        public void onPostExecute(Void r3) {
            super.onPostExecute(r3);
            EmojiDetailsActivity emojiDetailsActivity = EmojiDetailsActivity.this;
            emojiDetailsActivity.viewPager = (ViewPager) emojiDetailsActivity.findViewById(R.id.vp_emoji);
            EmojiDetailsActivity emojiDetailsActivity2 = EmojiDetailsActivity.this;
            EmojiPagerAdapter emojiPagerAdapter = new EmojiPagerAdapter(emojiDetailsActivity2.getSupportFragmentManager());
            EmojiDetailsActivity.this.viewPager.addOnPageChangeListener(EmojiDetailsActivity.this);
            EmojiDetailsActivity.this.viewPager.setAdapter(emojiPagerAdapter);
            EmojiDetailsActivity.this.viewPager.setPageMargin(40);
            EmojiDetailsActivity.this.viewPager.setPageTransformer(true, new TransformerOutSlide());
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
                EmojiDetailsActivity.this.finish();
    }
}
