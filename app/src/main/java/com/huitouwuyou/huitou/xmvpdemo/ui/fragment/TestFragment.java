package com.huitouwuyou.huitou.xmvpdemo.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huitouwuyou.huitou.xmvpdemo.R;

/**
 * Created by bruce on 2016/11/1.
 * BaseFragment
 */

public class TestFragment extends Fragment {
    FragmentCallBack callBack;
    public static TestFragment newInstance(String info) {
        Bundle args = new Bundle();
        TestFragment fragment = new TestFragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, null);
        if(callBack!=null){
            callBack.showMsg("66666666666666666666666");
        }

        TextView tvInfo = (TextView) view.findViewById(R.id.textView);
        tvInfo.setText(getArguments().getString("info"));
        tvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Don't click me.please!.", Snackbar.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentCallBack){
            callBack= (FragmentCallBack) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callBack=null;
    }

    public static interface FragmentCallBack{
        void showMsg(String msg);
    }
}
