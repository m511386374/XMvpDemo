package com.huitouwuyou.huitou.xmvpdemo.ui.fragment;
import android.os.Bundle;

import com.huitouwuyou.huitou.xmvpdemo.R;

import cn.droidlover.xdroidmvp.mvp.XFragment;
import cn.droidlover.xdroidmvp.router.Router;


/**
 * Created by bruce on 2016/11/1.
 * BaseFragment
 */

public class BaseFragment1 extends XFragment {


    public static BaseFragment newInstance(String info) {
        Bundle args = new Bundle();
        BaseFragment fragment = new BaseFragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_base;
    }

    @Override
    public Object newP() {
        return null;
    }

    public void startActivity(Class activity){
        Router.newIntent(getActivity())
                .to(activity)
                .launch();
    }
}
