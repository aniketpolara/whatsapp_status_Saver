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
import com.console.gbversion.whatsscan.R;

/* loaded from: classes2.dex */
public class NameFragment extends Fragment {
    public static NameFragment content(String str, String str2) {
        NameFragment nameFragment = new NameFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", str);
        bundle.putString("style", str2);
        nameFragment.setArguments(bundle);
        return nameFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_name, viewGroup, false);
        Holder holder = new Holder(this, inflate);
        inflate.setTag(holder);
        String string = getArguments().getString("text");
        String string2 = getArguments().getString("style");
        try {
            final StringBuilder sb = new StringBuilder(string2);
            final String str = string2 + string + sb.reverse().toString();
            holder.txtNameStyle.setText(str);
            holder.ivCopy.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Fragment.NameFragment.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NameFragment.this.clickOnCopy(str.toString());
                }
            });
            holder.ivShare.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Fragment.NameFragment.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NameFragment.this.shareOnly(false, sb.toString());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inflate;
    }

    public void clickOnCopy(String str) {
        ((ClipboardManager) getActivity().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text", str));
        Toast.makeText(getActivity(), "Copy to Clipboard..", 0).show();
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
        TabView txtNameStyle;

        public Holder(NameFragment nameFragment, View view) {
            this.txtNameStyle = (TabView) view.findViewById(R.id.txtNameStyleId);
            this.ivCopy = (RelativeLayout) view.findViewById(R.id.ivCopy);
            this.ivShare = (RelativeLayout) view.findViewById(R.id.ivShare);
        }
    }
}
