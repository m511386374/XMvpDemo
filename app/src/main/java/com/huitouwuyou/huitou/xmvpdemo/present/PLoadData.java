package com.huitouwuyou.huitou.xmvpdemo.present;
import com.apkfuns.logutils.LogUtils;
import com.huitouwuyou.huitou.xmvpdemo.JsonCallback.JsonCallback;
import com.huitouwuyou.huitou.xmvpdemo.model.LzyResponse;
import com.huitouwuyou.huitou.xmvpdemo.model.LoginModel;
import com.huitouwuyou.huitou.xmvpdemo.ui.fragment.BaseFragment;
import com.huitouwuyou.huitou.xmvpdemo.ui.fragment.HomeFragment;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.OkGo;
import okhttp3.Call;
import okhttp3.Response;
/**
 * Created by nick.ming on 2017/2/10.
 * HomeFragment数据获取
 */

    public class PLoadData extends XPresent<HomeFragment> {
      public  final String API_BASE_URL = "http://gank.io/api/data/";
        public void loadData(String code , int count, final int page, final DataLoadCallback callback) {
                 OkGo.get(API_BASE_URL+code+"/"+count+""+"/"+page+"")


                         // 请求方式和请求url
                    .execute(new JsonCallback<LzyResponse<List<LoginModel>>>() {
                        @Override
                        public void onSuccess(LzyResponse<List<LoginModel>> listLzyResponse, Call call, Response response) {
//                            getV().showData(listLzyResponse,page);
                            if (callback!=null) callback.onDataReady(listLzyResponse,page);
                        }   //这里传递Login


                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                         LogUtils.d("请求失败");
                        }

                        @Override
                        public void onCacheSuccess(LzyResponse<List<LoginModel>> listLzyResponse, Call call) {
                            super.onCacheSuccess(listLzyResponse, call);
                            LogUtils.d("缓存成功");
                            if (callback!=null) callback.onDataReady(listLzyResponse,page);
                        }

                        @Override
                        public void onCacheError(Call call, Exception e) {
                            super.onCacheError(call, e);
                            LogUtils.d("缓存失败");
                        }
                    });


        }


    public interface DataLoadCallback{
        void onDataReady(LzyResponse<List<LoginModel>> listLzyResponse,int page);
    }

}
