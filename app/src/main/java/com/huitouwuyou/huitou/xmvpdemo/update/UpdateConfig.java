package com.huitouwuyou.huitou.xmvpdemo.update;

import android.content.Context;
import com.dou361.update.ParseData;
import com.dou361.update.UpdateHelper;
import com.dou361.update.bean.Update;
import com.dou361.update.type.RequestType;
import com.google.gson.Gson;
import com.huitouwuyou.huitou.xmvpdemo.update.bean.UpdateBean;

/**
 * Created by 韩莫熙 on 2017/2/28.
 */

public class UpdateConfig {

    //检测更新接口地址
    private static String baseUrl = "http://123.57.28.18:8080/seastation";
    private static String checkUrl = "/queryVersionMess.action";

    public static void initGet(Context context) {
        UpdateHelper.init(context);
        UpdateHelper.getInstance()
                .setClearCustomLayoutSetting()
                .setCheckJsonParser(new ParseData() {
                    @Override
                    public Update parse(String response) {
                        /**真实情况下使用的解析  response接口请求返回的数据*/
                        Gson gson = new Gson();
                        UpdateBean updateBean = gson.fromJson(response,UpdateBean.class);
                        Update update = new Update();
                        /**必填：此apk包的下载地址*/
//                        String updataUrl = updateBean.getData().getApp_url().replace("$",updateBean.getData().getId()+"");
                        update.setUpdateUrl("http://120.198.236.16/f5.market.xiaomi.com/download/AppStore/010f3241ca6ad498c1eec34a6d814ebac175fdfd1/com.yikelive.apk");
                        /**可填：此apk包的版本大小*/
//                        update.setApkSize(updateBean.getData().getApp_size());
//                        update.setApkSize(100);
                        /**可填：此apk包的更新内容*/
//                        update.setUpdateContent(updateBean.getData().getRenew());
                        update.setUpdateContent("您当前版本是V1.3.5，发现新版本V1.3.7，是否下载新版本？");
                        /**必填：此apk包的版本号*/
                        update.setVersionCode(3);
                        /**必填：此apk包的版本名称*/
                        update.setVersionName(updateBean.getData().getVersion_name());
                        return update;
                    }
                });
    }
}
