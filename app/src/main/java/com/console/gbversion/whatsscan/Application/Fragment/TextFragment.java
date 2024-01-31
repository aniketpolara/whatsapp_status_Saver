package com.console.gbversion.whatsscan.Application.Fragment;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.console.gbversion.whatsscan.Application.Utils.TabView;
import com.console.gbversion.whatsscan.Application.Utils.Utils;
import com.console.gbversion.whatsscan.R;

/* loaded from: classes2.dex */
public class TextFragment extends Fragment {
    public static TextFragment content(String str, String str2) {
        TextFragment textFragment = new TextFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", str);
        bundle.putString("style", str2);
        textFragment.setArguments(bundle);
        return textFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_text, viewGroup, false);
        Holder holder = new Holder(this, inflate);
        inflate.setTag(holder);
        String string = getArguments().getString("text");
        String string2 = getArguments().getString("style");
        try {
            final StringBuilder sb = new StringBuilder();
            String[] split = string2.split("~~~");
            for (int i = 0; i < string.length(); i++) {
                sb.append(split[Utils.getRerutnCharacter(String.valueOf(string.charAt(i)))]);
            }
            holder.txtTextStyle.setText(sb.toString());
            holder.ivCopy.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Fragment.TextFragment.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    TextFragment.this.clickOnCopy(sb.toString());
                }
            });
            holder.ivShare.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Fragment.TextFragment.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    TextFragment.this.shareOnly(false, sb.toString());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inflate;
    }

    public void clickOnCopy(String str) {
        ((ClipboardManager) getActivity().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text", str));
        Toast.makeText(getActivity(), "Copy to clipboard..", 0).show();
    }

    public void shareOnly(boolean z, String str) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", str);
        if (z) {
            try {
                intent.setPackage("com.whatsapp");
                getActivity().startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                Toast.makeText(getActivity(), "Whatsapp Not Installed/Disable.", 0).show();
            }
        } else {
            getActivity().startActivity(Intent.createChooser(intent, "share via"));
        }
    }

    /* loaded from: classes2.dex */
    private class Holder {
        RelativeLayout ivCopy;
        RelativeLayout ivShare;
        TabView txtTextStyle;

        public Holder(TextFragment textFragment, View view) {
            this.txtTextStyle = (TabView) view.findViewById(R.id.txtTextStyleId);
            this.ivCopy = (RelativeLayout) view.findViewById(R.id.ivCopy);
            this.ivShare = (RelativeLayout) view.findViewById(R.id.ivShare);
        }
    }
}
