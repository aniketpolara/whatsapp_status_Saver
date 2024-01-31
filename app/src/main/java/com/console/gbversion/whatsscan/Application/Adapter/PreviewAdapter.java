package com.console.gbversion.whatsscan.Application.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.viewpager.widget.PagerAdapter;
import com.bumptech.glide.Glide;
import com.console.gbversion.whatsscan.Application.Activity.VideoPlayerActivity;
import com.console.gbversion.whatsscan.Application.Model.StatusModel;
import com.console.gbversion.whatsscan.Application.Utils.Utils;
import com.console.gbversion.whatsscan.R;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class PreviewAdapter extends PagerAdapter {
    Activity activity;
    ArrayList<StatusModel> imageList;

    public PreviewAdapter(Activity activity, ArrayList<StatusModel> arrayList) {
        this.activity = activity;
        this.imageList = arrayList;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(this.activity).inflate(R.layout.preview_list_item, viewGroup, false);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.imageView);
        ImageView imageView2 = (ImageView) inflate.findViewById(R.id.iconplayer);
        if (!Utils.getBack(this.imageList.get(i).getFilePath(), "((\\.mp4|\\.webm|\\.ogg|\\.mpK|\\.avi|\\.mkv|\\.flv|\\.mpg|\\.wmv|\\.vob|\\.ogv|\\.mov|\\.qt|\\.rm|\\.rmvb\\.|\\.asf|\\.m4p|\\.m4v|\\.mp2|\\.mpeg|\\.mpe|\\.mpv|\\.m2v|\\.3gp|\\.f4p|\\.f4a|\\.f4b|\\.f4v)$)").isEmpty()) {
            imageView2.setVisibility(0);
        } else {
            imageView2.setVisibility(8);
        }
        Glide.with(this.activity).load(this.imageList.get(i).getFilePath()).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.console.gbversion.whatsscan.Application.Adapter.PreviewAdapter$$ExternalSyntheticLambda0

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                lambda$instantiateItem$0(i, view);
            }
        });
        viewGroup.addView(inflate);
        return inflate;
    }

    public void lambda$instantiateItem$0(int i, View view) {
        if (!Utils.getBack(this.imageList.get(i).getFilePath(), "((\\.mp4|\\.webm|\\.ogg|\\.mpK|\\.avi|\\.mkv|\\.flv|\\.mpg|\\.wmv|\\.vob|\\.ogv|\\.mov|\\.qt|\\.rm|\\.rmvb\\.|\\.asf|\\.m4p|\\.m4v|\\.mp2|\\.mpeg|\\.mpe|\\.mpv|\\.m2v|\\.3gp|\\.f4p|\\.f4a|\\.f4b|\\.f4v)$)").isEmpty()) {
            Utils.mPath = this.imageList.get(i).getFilePath();
                    PreviewAdapter.this.activity.startActivity(new Intent(PreviewAdapter.this.activity, VideoPlayerActivity.class));
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.imageList.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((RelativeLayout) obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == ((RelativeLayout) obj);
    }
}
