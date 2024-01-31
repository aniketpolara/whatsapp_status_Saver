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
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.console.gbversion.whatsscan.Application.Utils.TabView;
import com.console.gbversion.whatsscan.R;

/* loaded from: classes2.dex */
public class EmojiFragment extends Fragment {
    public static EmojiFragment content(String str, String str2) {
        EmojiFragment emojiFragment = new EmojiFragment();
        Bundle bundle = new Bundle();
        bundle.putString("style", str);
        bundle.putString("getCount", str2);
        emojiFragment.setArguments(bundle);
        return emojiFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_emoji, viewGroup, false);
        Holder holder = new Holder(inflate);
        inflate.setTag(holder);
        final String string = getArguments().getString("style");
        String string2 = getArguments().getString("getCount");
        holder.txtEmojiStyle.setText(string);
        holder.txtEmojiCount.setText(string2);
        holder.copyStyle.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Fragment.EmojiFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EmojiFragment.this.clickOnCopy(string);
            }
        });
        holder.ivShare.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Fragment.EmojiFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EmojiFragment.this.shareOnly(false, string);
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
                Toast.makeText(getActivity(), "Whatsapp Not Installed", 0).show();
            }
        } else {
            getActivity().startActivity(Intent.createChooser(intent, "share via"));
        }
    }

    /* loaded from: classes2.dex */
    private static class Holder {
        RelativeLayout copyStyle;
        RelativeLayout ivShare;
        TextView txtEmojiCount;
        TabView txtEmojiStyle;

        public Holder(View view) {
            this.txtEmojiStyle = (TabView) view.findViewById(R.id.txtEmojiStyleId);
            this.txtEmojiCount = (TextView) view.findViewById(R.id.txtEmojiCountId);
            this.copyStyle = (RelativeLayout) view.findViewById(R.id.ivCopy);
            this.ivShare = (RelativeLayout) view.findViewById(R.id.ivShare);
        }
    }
}
