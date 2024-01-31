package com.console.gbversion.whatsscan.Application.Fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.console.gbversion.whatsscan.Application.Utils.AppUtils;
import com.console.gbversion.whatsscan.Application.Utils.CodeGenerator;
import com.console.gbversion.whatsscan.Application.Utils.Constant;
import com.console.gbversion.whatsscan.Application.Utils.SaveImage;
import com.console.gbversion.whatsscan.R;

/* loaded from: classes2.dex */
public class GenerateFragment extends Fragment {
    private static int TYPE;
    private String inputStr;
    private EditText inputText;
    private Activity mActivity;
    private Context mContext;
    private Bitmap output;
    private ImageView outputBitmap;
    private FloatingActionButton save;
    private FloatingActionButton share;
    private ImageButton switcher;

    private void initFunctionality() {
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initVar();
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_generate, viewGroup, false);
        initView(inflate);
        initFunctionality();
        initListener();
        return inflate;
    }

    private void initVar() {
        FragmentActivity activity = getActivity();
        this.mActivity = activity;
        this.mContext = activity.getApplicationContext();
    }

    private void initView(View view) {
        this.inputText = (EditText) view.findViewById(R.id.inputText);
        this.outputBitmap = (ImageView) view.findViewById(R.id.outputBitmap);
        this.switcher = (ImageButton) view.findViewById(R.id.switcher);
        this.save = (FloatingActionButton) view.findViewById(R.id.save);
        this.share = (FloatingActionButton) view.findViewById(R.id.share);
        this.save.hide();
        this.share.hide();
    }

    private void initListener() {
        this.inputText.addTextChangedListener(new TextWatcher() { // from class: com.console.gbversion.whatsscan.Application.Fragment.GenerateFragment.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.length() != 0) {
                    GenerateFragment.this.generateCode(charSequence.toString());
                    return;
                }
                GenerateFragment.this.save.hide();
                GenerateFragment.this.share.hide();
                if (GenerateFragment.TYPE == 0) {
                    GenerateFragment.this.outputBitmap.setImageResource(R.drawable.ic_qr_placeholder);
                } else {
                    GenerateFragment.this.outputBitmap.setImageResource(R.drawable.ic_bar_placeholder);
                }
            }
        });
        this.switcher.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Fragment.GenerateFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GenerateFragment.this.inputText.setText("");
                GenerateFragment.this.save.hide();
                GenerateFragment.this.share.hide();
                if (GenerateFragment.TYPE == 0) {
                    int unused = GenerateFragment.TYPE = 1;
                    GenerateFragment.this.switcher.setImageResource(R.drawable.ic_qr_placeholder);
                    GenerateFragment.this.inputText.setHint(R.string.type_here_bar);
                    GenerateFragment.this.outputBitmap.setImageResource(R.drawable.ic_bar_placeholder);
                    return;
                }
                int unused2 = GenerateFragment.TYPE = 0;
                GenerateFragment.this.switcher.setImageResource(R.drawable.ic_bar_placeholder);
                GenerateFragment.this.inputText.setHint(R.string.type_here_qr);
                GenerateFragment.this.outputBitmap.setImageResource(R.drawable.ic_qr_placeholder);
            }
        });
        this.save.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Fragment.GenerateFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GenerateFragment generateFragment = GenerateFragment.this;
                generateFragment.save(true, generateFragment.inputStr, GenerateFragment.this.output);
            }
        });
        this.share.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Fragment.GenerateFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GenerateFragment generateFragment = GenerateFragment.this;
                generateFragment.share(true, generateFragment.inputStr, GenerateFragment.this.output);
//                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
//                Uri screenshotUri = Uri.parse(MediaStore.Images.Media.EXTERNAL_CONTENT_URI + "/" + outputBitmap);
//                sharingIntent.setType("image/jpeg");
//                sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
//                startActivity(Intent.createChooser(sharingIntent, "Share image using"));

            }
        });
    }

    public void generateCode(final String str) {
        CodeGenerator codeGenerator = new CodeGenerator();
        if (TYPE == 1) {
            codeGenerator.generateBarFor(str);
        } else {
            codeGenerator.generateQRFor(str);
        }
        codeGenerator.setResultListener(new CodeGenerator.ResultListener() { // from class: com.console.gbversion.whatsscan.Application.Fragment.GenerateFragment.5
            @Override
            public void onResult(Bitmap bitmap) {
                GenerateFragment.this.output = bitmap;
                GenerateFragment.this.inputStr = str;
                GenerateFragment.this.outputBitmap.setImageBitmap(bitmap);
                if (!GenerateFragment.this.save.isShown()) {
                    GenerateFragment.this.save.show();
                    GenerateFragment.this.share.show();
                }
            }
        });
        codeGenerator.execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void save(boolean z, String str, Bitmap bitmap) {
        if (z) {
            AppUtils.showToast(this.mContext, getString(R.string.preparing));
        } else {
            AppUtils.showToast(this.mContext, getString(R.string.saving));
        }
        SaveImage saveImage = new SaveImage(str, bitmap);
        saveImage.setSaveListener(new SaveImage.SaveListener() { // from class: com.console.gbversion.whatsscan.Application.Fragment.GenerateFragment.6
            @Override // com.console.gbversion.whatsscan.Application.Utils.SaveImage.SaveListener
            public void onSaved(String str2) {
                Context context = GenerateFragment.this.mContext;
                AppUtils.showToast(context, GenerateFragment.this.getString(R.string.saved_to) + "'" + Constant.SAVE_TO + "' " + GenerateFragment.this.getString(R.string.directory_in));
            }
        });
        saveImage.execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void share(final boolean z, String str, Bitmap bitmap) {
        if (z) {
            AppUtils.showToast(this.mContext, getString(R.string.preparing));
        } else {
            AppUtils.showToast(this.mContext, getString(R.string.saving));
        }
        SaveImage saveImage = new SaveImage(str, bitmap);
        saveImage.setSaveListener(new SaveImage.SaveListener() {
            @Override
            public void onSaved(String str2) {
                if (z) {
                    AppUtils.share(GenerateFragment.this.mActivity, str2);
                    return;
                }
                Context context = GenerateFragment.this.mContext;
                AppUtils.showToast(context, GenerateFragment.this.getString(R.string.saved_to) + "'" + Constant.SAVE_TO + "' " + GenerateFragment.this.getString(R.string.directory_in));
            }
        });
        saveImage.execute(new Void[0]);
    }
}
