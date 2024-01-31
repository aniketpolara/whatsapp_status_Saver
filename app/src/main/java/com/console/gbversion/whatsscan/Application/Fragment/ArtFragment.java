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
public class ArtFragment extends Fragment {
    public static ArtFragment content(String str) {
        ArtFragment artFragment = new ArtFragment();
        Bundle bundle = new Bundle();
        bundle.putString("style", str);
        artFragment.setArguments(bundle);
        return artFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_art, viewGroup, false);
        Holder holder = new Holder(this, inflate);
        inflate.setTag(holder);
        final String string = getArguments().getString("style");
        holder.txtArtStyle.setText(string);
        holder.ivCopy.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Fragment.ArtFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ArtFragment.this.clickOnCopy(string);
            }
        });
        holder.ivShare.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Fragment.ArtFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ArtFragment.this.shareOnly(false, string);
            }
        });
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
                Toast.makeText(getActivity(), "Whatsapp Not Installed.", 0).show();
            }
        } else {
            getActivity().startActivity(Intent.createChooser(intent, "share via"));
        }
    }

    /* loaded from: classes2.dex */
    private class Holder {
        RelativeLayout ivCopy;
        RelativeLayout ivShare;
        TabView txtArtStyle;

        public Holder(ArtFragment artFragment, View view) {
            this.txtArtStyle = (TabView) view.findViewById(R.id.txtArtStyleId);
            this.ivCopy = (RelativeLayout) view.findViewById(R.id.ivCopy);
            this.ivShare = (RelativeLayout) view.findViewById(R.id.ivShare);
        }
    }
}
