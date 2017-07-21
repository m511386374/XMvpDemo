package com.huitouwuyou.huitou.xmvpdemo.present;

import com.huitouwuyou.huitou.xmvpdemo.ui.activity.MainActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.OkGo;
import cn.droidlover.xdroidmvp.net.cache.CacheMode;
import cn.droidlover.xdroidmvp.net.callback.StringCallback;
import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by nick.ming on 2017/2/10.
 */

    public class PMainActivityData extends XPresent<MainActivity> {
    private static String baseUrl1 = "http://123.57.28.18:8080/seastation";
    private static String checkUrl1 = "/queryVersionMess.action";
    private static String baseUrls = "http://1b677709s3.iok.la";
    private static String checkUrls = "/getlist.req?";

        public void update (final UpDateCallback callback) {
            OkGo.get(baseUrl1 + checkUrl1)     // 请求方式和请求url
                    .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                    .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                    .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            // s 即为所需要的结果
                            if (callback != null) callback.onUpDateReady(s);
                        }
                    });
        }
     public void getdate (final GetDateCallback callback) {

        OkGo.post(baseUrls+checkUrls)
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        // s 即为所需要的结果
                        if (callback!=null) callback.onGetDateReady(s);
                    }
                });

    }


    public interface UpDateCallback{
        void onUpDateReady(String listLzyResponse);
    }
    public interface GetDateCallback{
        void onGetDateReady(String Response);
    }

}
