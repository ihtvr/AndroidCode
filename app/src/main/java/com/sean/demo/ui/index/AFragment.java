package com.sean.demo.ui.index;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.sean.demo.R;
import com.sean.demo.adapter.common.CommonListAdapter;
import com.sean.demo.adapter.common.CommonListModel;
import com.sean.demo.ui.BaseSupportFragment;
import com.sean.demo.ui.a.EventBusActivity;
import com.sean.demo.ui.a.HandlerTestActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AFragment extends BaseSupportFragment {


    @BindView(R.id.fragment_a_rv)
    RecyclerView fragmentARv;
    private List<CommonListModel> commonListModelList = new ArrayList<>();
    private CommonListAdapter commonListAdapter;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        view.setOnTouchListener(new DontSpillOnTouchListener());
        context = getActivity();
        ButterKnife.bind(this, view);
        initData();
        initRecycler();
        return view;
    }

    /**
     * 初始化主页显示数据
     */
    private void initData() {
        commonListModelList.add(new CommonListModel("Handler的简单使用", HandlerTestActivity.class));
        commonListModelList.add(new CommonListModel("EventBus3.0的简单使用", EventBusActivity.class));
    }

    private void initRecycler() {
        commonListAdapter = new CommonListAdapter(context, commonListModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        fragmentARv.setLayoutManager(linearLayoutManager);
        fragmentARv.setAdapter(commonListAdapter);
        fragmentARv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(context, commonListModelList.get(position).getActivity()));
            }
        });
    }
}
