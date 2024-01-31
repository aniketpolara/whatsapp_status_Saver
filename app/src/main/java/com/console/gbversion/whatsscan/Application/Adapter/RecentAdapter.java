package com.console.gbversion.whatsscan.Application.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.console.gbversion.whatsscan.Application.Activity.PreviewActivity;
import com.console.gbversion.whatsscan.Application.Model.StatusModel;
import com.console.gbversion.whatsscan.Application.Utils.Utils;
import com.console.gbversion.whatsscan.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class RecentAdapter extends BaseAdapter {
    List<StatusModel> arrayList;
    Fragment context;
    LayoutInflater inflater;
    public OnCheckboxListener onCheckboxListener;
    int width;

    /* loaded from: classes2.dex */
    public interface OnCheckboxListener {
        void onCheckboxListener(View view, List<StatusModel> list);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return (long) i;
    }

    public RecentAdapter(Fragment fragment, List<StatusModel> list, OnCheckboxListener onCheckboxListener) {
        this.context = fragment;
        this.arrayList = list;
        this.inflater = (LayoutInflater) fragment.getActivity().getSystemService("layout_inflater");
        this.width = fragment.getResources().getDisplayMetrics().widthPixels;
        this.onCheckboxListener = onCheckboxListener;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.arrayList.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return Integer.valueOf(i);
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View inflate = this.inflater.inflate(R.layout.row_recent, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.play);
        if (!Utils.getBack(this.arrayList.get(i).getFilePath(), "((\\.mp4|\\.webm|\\.ogg|\\.mpK|\\.avi|\\.mkv|\\.flv|\\.mpg|\\.wmv|\\.vob|\\.ogv|\\.mov|\\.qt|\\.rm|\\.rmvb\\.|\\.asf|\\.m4p|\\.m4v|\\.mp2|\\.mpeg|\\.mpe|\\.mpv|\\.m2v|\\.3gp|\\.f4p|\\.f4a|\\.f4b|\\.f4v)$)").isEmpty()) {
            imageView.setVisibility(0);
        } else {
            imageView.setVisibility(8);
        }
        int i2 = (this.width * 320) / 1080;
        inflate.setLayoutParams(new AbsListView.LayoutParams(i2, i2));
        Glide.with(this.context.getActivity()).load(this.arrayList.get(i).getFilePath()).into((ImageView) inflate.findViewById(R.id.gridImage));
        CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.console.gbversion.whatsscan.Application.Adapter.RecentAdapter.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                RecentAdapter.this.arrayList.get(i).setSelected(z);
                RecentAdapter recentAdapter = RecentAdapter.this;
                OnCheckboxListener onCheckboxListener = recentAdapter.onCheckboxListener;
                if (onCheckboxListener != null) {
                    onCheckboxListener.onCheckboxListener(compoundButton, recentAdapter.arrayList);
                }
            }
        });
        if (this.arrayList.get(i).isSelected()) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
        inflate.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Adapter.RecentAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Log.e("click", "click");
                final Intent intent = new Intent(RecentAdapter.this.context.getActivity(), PreviewActivity.class);
                intent.putParcelableArrayListExtra("images", (ArrayList) RecentAdapter.this.arrayList);
                intent.putExtra("position", i);
                intent.putExtra("statusdownload", "");
                        RecentAdapter.this.context.startActivity(intent);
            }
        });
        return inflate;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        Log.d("MyAdapter", "onActivityResult");
    }
}
