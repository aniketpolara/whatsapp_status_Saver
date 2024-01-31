package com.console.gbversion.whatsscan.Application.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.console.gbversion.whatsscan.Application.Adapter.HistoryAdapter;
import com.console.gbversion.whatsscan.Application.Utils.AppUtils;
import com.console.gbversion.whatsscan.Application.Utils.Preferences;
import com.console.gbversion.whatsscan.R;
import java.util.ArrayList;
import java.util.Collections;

/* loaded from: classes2.dex */
public class HistoryFragment extends Fragment {
    private HistoryAdapter adapter;
    private ArrayList<String> arrayList;
    private Context mContext;
    private RecyclerView mRecyclerView;
    private TextView noResultView;

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initVar();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_history, viewGroup, false);
        initView(inflate);
        initFunctionality();
        initListener();
        return inflate;
    }

    private void initVar() {
        this.mContext = getActivity().getApplicationContext();
    }

    private void initView(View view) {
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        this.noResultView = (TextView) view.findViewById(R.id.noResultView);
    }

    private void initFunctionality() {
        ArrayList<String> arrayList = new ArrayList<>();
        this.arrayList = arrayList;
        this.adapter = new HistoryAdapter(this.mContext, arrayList);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mRecyclerView.setAdapter(this.adapter);
        this.arrayList.addAll(Preferences.getInstance(this.mContext).getStringArray("result_list"));
        if (this.arrayList.isEmpty()) {
            this.noResultView.setVisibility(0);
            this.mRecyclerView.setVisibility(8);
        } else {
            this.noResultView.setVisibility(8);
            this.mRecyclerView.setVisibility(0);
        }
        Collections.reverse(this.arrayList);
        this.adapter.notifyDataSetChanged();
    }

    private void initListener() {
        this.adapter.setClickListener(new HistoryAdapter.ClickListener() { // from class: com.console.gbversion.whatsscan.Application.Fragment.HistoryFragment.1
            @Override // com.console.gbversion.whatsscan.Application.Adapter.HistoryAdapter.ClickListener
            public void onItemClicked(int i) {
                AppUtils.copyToClipboard(HistoryFragment.this.mContext, (String) HistoryFragment.this.arrayList.get(i));
            }
        });
    }
}
