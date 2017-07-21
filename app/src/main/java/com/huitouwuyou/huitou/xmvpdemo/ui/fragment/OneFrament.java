package com.huitouwuyou.huitou.xmvpdemo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.huitouwuyou.huitou.xmvpdemo.R;
import com.huitouwuyou.huitou.xmvpdemo.adapter.ViewPagerAdapter;
import com.huitouwuyou.huitou.xmvpdemo.hepler.BottomNavigationViewHelper;
import com.youth.banner.WeakHandler;
import com.youth.banner.transformer.AccordionTransformer;
import com.youth.banner.transformer.BackgroundToForegroundTransformer;
import com.youth.banner.transformer.DefaultTransformer;
import com.youth.banner.transformer.DepthPageTransformer;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XLazyFragment;

/**
 * Created by bruce on 2016/11/1.
 * HomeActivity 主界面
 */

public class OneFrament extends XLazyFragment implements BaseFragment.FragmentCallBack {

    private MenuItem menuItem;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
    WeakHandler handlers = new WeakHandler();
    public static OneFrament newInstance() {
        return new OneFrament();
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(BaseFragment1.newInstance("新闻"));
        adapter.addFragment(BaseFragment1.newInstance("图书"));
        adapter.addFragment(BaseFragment1.newInstance("发现"));
        adapter.addFragment(BaseFragment1.newInstance("更多"));
        viewPager.setPageTransformer(true, new DefaultTransformer());

        viewPager.setAdapter(adapter);
//        handlers.postDelayed(task, 3000);
    }
   int currentItem=0;
    private final Runnable task = new Runnable() {
        @Override
        public void run() {
                currentItem++;
                currentItem = currentItem % 4;
               viewPager.setCurrentItem(currentItem);
                handlers.postDelayed(task, 3000);

        }
    };
    @Override
    public void initData(Bundle savedInstanceState) {
        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_news:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.item_lib:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.item_find:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.item_more:
                                viewPager.setCurrentItem(3);
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //禁止ViewPager滑动
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        setupViewPager(viewPager);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_one;
    }

    @Override
    public Object newP() {
        return null;
    }

    @Override
    public void showMsg(String msg) {
        LogUtils.d(msg);
    }
}