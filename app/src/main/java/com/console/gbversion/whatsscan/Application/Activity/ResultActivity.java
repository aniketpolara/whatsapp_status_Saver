package com.console.gbversion.whatsscan.Application.Activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.console.gbversion.whatsscan.Application.Utils.AppUtils;
import com.console.gbversion.whatsscan.Application.Utils.Preferences;
import com.console.gbversion.whatsscan.R;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class ResultActivity extends AppCompatActivity {
    private FloatingActionButton copy;
    private Context mContext;
    private TextView result;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initVars();
        initViews();
        initFunctionality();
        initListeners();
    }

    private void initVars() {
        this.mContext = getApplicationContext();
    }

    private void initViews() {
        setContentView(R.layout.activity_result);
        getWindow().setFlags(1024, 1024);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        this.result = (TextView) findViewById(R.id.result);
        this.copy = (FloatingActionButton) findViewById(R.id.copy);
    }

    private void initFunctionality() {
        getSupportActionBar().setTitle(getString(R.string.result));
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        ArrayList<String> stringArray = Preferences.getInstance(this.mContext).getStringArray("result_list");
        String str = stringArray.get(stringArray.size() - 1);
        if (Build.VERSION.SDK_INT >= 24) {
            this.result.setText(Html.fromHtml(str, 0));
        } else {
            this.result.setText(Html.fromHtml(str));
        }
        this.result.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void initListeners() {
        this.copy.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Activity.ResultActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppUtils.copyToClipboard(ResultActivity.this.mContext, ResultActivity.this.result.getText().toString());
            }
        });
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
                ResultActivity.this.finish();
    }
}
