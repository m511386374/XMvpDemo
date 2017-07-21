package com.huitouwuyou.huitou.xmvpdemo;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.apkfuns.logutils.LogLevel;
import com.apkfuns.logutils.LogUtils;
import com.blankj.utilcode.utils.Utils;
import com.huitouwuyou.huitou.xmvpdemo.net.okGo;
import com.huitouwuyou.huitou.xmvpdemo.update.UpdateConfig;

import cn.droidlover.xdroidmvp.imageloader.ILFactory;

/**
 * Created by Nick.Ming on 2017/2/16.
 */
public class MyApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        UpdateConfig.initGet(this);
        /*======================================================================================
        版本更新初始化
        * */
        okGo.initOkgo(this);
         /*======================================================================================
        版本更新初始化
        okgo网路请求初始化
        * */
        ILFactory.getLoader().init(context);
         /*======================================================================================
        工具类初始化
        * */
        Utils.init(context);
       /*======================================================================================
       ARouter路由初始化
        * */
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init(this); // 尽可
       /*======================================================================================
       LogUtils日志初始化
        * */
        LogUtils.getLogConfig()
                .configAllowLog(true)
                .configTagPrefix("MyAppName")
                .configShowBorders(true)
                .configFormatTag("%d{HH:mm:ss:SSS} %t %c{-5}")
                .configLevel(LogLevel.TYPE_VERBOSE);
    }

    public static Context getContext() {
        return context;
    }
}
