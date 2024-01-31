package com.console.gbversion.whatsscan.Whatsapp;

import android.annotation.SuppressLint;
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
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.console.gbversion.whatsscan.Application.Activity.PreviewActivity;
import com.console.gbversion.whatsscan.Application.Model.StatusModel;
import com.console.gbversion.whatsscan.R;

public class MyStatusAdapter extends BaseAdapter {
    List<StatusModel> arrayList;
    Fragment context;
    LayoutInflater inflater;
    public OnCheckboxListener onCheckboxListener;
    int width;
    ImageView play;

    public interface OnCheckboxListener {
        void onCheckboxListener(View view, List<StatusModel> list);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    @SuppressLint("WrongConstant")
    public MyStatusAdapter(Fragment fragment, List<StatusModel> list, OnCheckboxListener onCheckboxListener2) {
        this.context = fragment;
        this.arrayList = list;
        this.inflater = (LayoutInflater) fragment.getActivity().getSystemService("layout_inflater");
        this.width = fragment.getResources().getDisplayMetrics().widthPixels;
        this.onCheckboxListener = onCheckboxListener2;
    }

    public int getCount() {
        return this.arrayList.size();
    }

    public Object getItem(int i) {
        return Integer.valueOf(i);
    }

    @SuppressLint("WrongConstant")
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View inflate = this.inflater.inflate(R.layout.row_my_status, (ViewGroup) null);
        play = (ImageView) inflate.findViewById(R.id.play);
        if (isVideoFile(this.arrayList.get(i).getFilePath())) {
            play.setVisibility(0);
        } else {
            play.setVisibility(8);
        }
        int i2 = this.width;
        inflate.setLayoutParams(new AbsListView.LayoutParams((i2 * 320) / 1080, (i2 * 320) / 1080));
        Glide.with(this.context.getActivity()).load(this.arrayList.get(i).getFilePath()).into((ImageView) inflate.findViewById(R.id.gridImageVideo));
        CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MyStatusAdapter.this.arrayList.get(i).setSelected(z);
                if (MyStatusAdapter.this.onCheckboxListener != null) {
                    MyStatusAdapter.this.onCheckboxListener.onCheckboxListener(compoundButton, MyStatusAdapter.this.arrayList);
                }
            }
        });

        if (this.arrayList.get(i).isSelected()) {
            checkBox.setChecked(true);
        }
        else {
            checkBox.setChecked(false);
        }

        play.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Toast.makeText(context.getActivity(), "click one", Toast.LENGTH_SHORT).show();
                Log.e("click", "click");
                Intent intent = new Intent(MyStatusAdapter.this.context.getActivity(), PreviewActivity.class);
                intent.putParcelableArrayListExtra("images", (ArrayList) MyStatusAdapter.this.arrayList);
                intent.putExtra("position", i);
                intent.putExtra("statusdownload", "download");
                context.startActivity(intent);
            }
        });
        return inflate;
    }

    public boolean isVideoFile(String str) {
        String guessContentTypeFromName = URLConnection.guessContentTypeFromName(str);
        return guessContentTypeFromName != null && guessContentTypeFromName.startsWith("video");
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        Log.d("MyAdapter", "onActivityResult");
    }
}
