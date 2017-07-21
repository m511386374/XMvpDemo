package com.huitouwuyou.huitou.xmvpdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

/**
 * Created by Nick.Ming on 2017/7/4.
 */

public class a extends RecyclerView {
    public a(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return true;
    }
}
