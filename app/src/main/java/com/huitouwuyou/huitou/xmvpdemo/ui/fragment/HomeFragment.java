package com.huitouwuyou.huitou.xmvpdemo.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.huitouwuyou.huitou.xmvpdemo.R;
import com.huitouwuyou.huitou.xmvpdemo.adapter.QuickAdapter;
import com.huitouwuyou.huitou.xmvpdemo.base.*;
import com.huitouwuyou.huitou.xmvpdemo.model.LoginModel;
import com.huitouwuyou.huitou.xmvpdemo.model.LzyResponse;
import com.huitouwuyou.huitou.xmvpdemo.present.PLoadData;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.Kits;

import static android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;


public class HomeFragment extends com.huitouwuyou.huitou.xmvpdemo.base.BaseFragment<PLoadData> implements BaseQuickAdapter.RequestLoadMoreListener, OnRefreshListener {
    /*布局初始化*/
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;

    QuickAdapter quickadapter;
    /*常量定义*/
    public int page = 18;
    /*回调接口定义*/
    MainUiCallback callback;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    /*相当于oncreadview*/
    @Override
    public void initData(Bundle savedInstanceState) {
//        if (callback != null) {
//            callback.toast("aaaaaaaaaaaaaaaaa");
//        }
        getP().loadData("福利", 10, page, new PLoadData.DataLoadCallback() {
            @Override
            public void onDataReady(LzyResponse<List<LoginModel>> model, int page) {
                showData(model, page);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);
        initAdapter();
    }


    /*初始化布局*/
    @Override
    public int getLayoutId() {
        return R.layout.fragmentitem;
    }

    /*MVP数据绑定*/
    @Override
    public PLoadData newP() {
        return new PLoadData();
    }

    public void showData(final LzyResponse<List<LoginModel>> model, int page) {
        LogUtils.d("MODE1" + model.toString());
        if (page == 18) {
            quickadapter.addData(model.results);
        }
    }

    /*下拉加载数据*/
    @Override
    public void onLoadMoreRequested() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getP().loadData("福利", 10, ++page, new PLoadData.DataLoadCallback() {
                    @Override
                    public void onDataReady(LzyResponse<List<LoginModel>> model, int page) {
                        if (!Kits.Empty.check(model.results)) {
                            quickadapter.addData(model.results);
                            mSwipeRefreshLayout.setEnabled(true);
                            quickadapter.loadMoreComplete();
                        } else {
                            quickadapter.loadMoreEnd(false);
                        }
                    }
                });

            }
        }, 1000);


    }

    /*上拉刷新数据*/
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getP().loadData("福利", 10, page, new PLoadData.DataLoadCallback() {
                    @Override
                    public void onDataReady(LzyResponse<List<LoginModel>> model, int page) {
                        showData(model, page);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });


            }
        }, 1000);


    }

    /*初始化适配器*/
    private void initAdapter() {
        quickadapter = new QuickAdapter(new LzyResponse<List<LoginModel>>());
        quickadapter.setOnLoadMoreListener(this);
//      pullToRefreshAdapter.setAutoLoadMoreSize(3);
        mRecyclerView.setAdapter(quickadapter);
        View headerView = getHeaderView();
        quickadapter.addHeaderView(headerView);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ARouter.getInstance()
                        .build("/mvp/Photoview")
                        .withString("cs", quickadapter.getData().get(position).getUrl())
                        .navigation();

            }
        });

    }

    /*增加头布局，头布局可以独立出一个适配器。添加点击事件
    * */
    private View getHeaderView() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.head_view, (ViewGroup) mRecyclerView.getParent(), false);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv);
//        imageView.setImageResource(R.mipmap.android_icon);
        return view;
    }

    /* Activity于fragment的接口回调*/
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainUiCallback) {
            callback = (MainUiCallback) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    /* 定义回调接口*/
    public static interface MainUiCallback {
        void toast(String msg);
    }

}
