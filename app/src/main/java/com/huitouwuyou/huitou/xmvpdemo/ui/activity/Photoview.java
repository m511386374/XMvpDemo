package com.huitouwuyou.huitou.xmvpdemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.huitouwuyou.huitou.xmvpdemo.R;

import qiu.niorgai.StatusBarCompat;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * Created by chenshuai on 2016/11/14.
 */

@Route(path = "/mvp/Photoview")
public class Photoview extends Activity {
    @Autowired
    String cs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        沉浸式状态栏
        StatusBarCompat.translucentStatusBar(Photoview.this);
        setContentView(R.layout.photoview);
//        ARouter注解初始化
        ARouter.getInstance().inject(this);
        initView();

    }

    private void initView() {
            PhotoView photoView = (PhotoView)findViewById(R.id.pv_photo);
            final ProgressBar mProgressBar = (ProgressBar)findViewById(R.id.progress);
            Glide.with(Photoview.this)
                    .load(cs)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            mProgressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                           // 这里可以设置进度条是否可见
                            mProgressBar.setVisibility(View.GONE);
                           // Toast.makeText(Photoview.this, "我加载成功了", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    })
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade()
                    .into(photoView);
            photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    finish();
                }

                @Override
                public void onOutsidePhotoTap() {

                }
            });
    }


}
