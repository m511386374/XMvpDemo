package com.huitouwuyou.huitou.xmvpdemo.adapter;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huitouwuyou.huitou.xmvpdemo.R;
import com.huitouwuyou.huitou.xmvpdemo.model.LoginModel;
import com.huitouwuyou.huitou.xmvpdemo.model.LzyResponse;
import java.util.List;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;

/**
 * Created by Nick.Ming on 2017/2/17.
 */
public class QuickAdapter extends BaseQuickAdapter<LoginModel, BaseViewHolder> {
    Bitmap bitmap;
    public QuickAdapter(LzyResponse<List<LoginModel>> model) {
        super(R.layout.adapter_home, model.results);
    }

   public void QuickAdapter(Bitmap a){
       bitmap =a;
 }
    @Override
    protected void convert(final BaseViewHolder helper, final LoginModel item) {
        final String type = item.getType();
        switch (type) {
            case "休息视频":
                helper.setVisible(R.id.rl_message,true);
                helper.setVisible(R.id.iv_part,false);
                helper.setVisible(R.id.iv_vedio,true);
                helper.setText(R.id.tv_item,item.getDesc());
                break;
            case "福利":
                helper.setVisible(R.id.rl_message,false);
                helper.setVisible(R.id.iv_part,true).addOnClickListener(R.id.iv_part);
                helper.setVisible(R.id.iv_vedio,false);
                ILFactory.getLoader().loadNet((ImageView)helper.getView(R.id.iv_part), item.getUrl(), null);
                helper.getView(R.id.iv_part).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                break;
            default:
                helper.setVisible(R.id.rl_message,true);
                helper.setVisible(R.id.iv_part,false);
                helper.setVisible(R.id.iv_vedio,false);
                helper.setText(R.id.tv_item,item.getDesc());


                break;
        }
        Uri uri = null;
        switch (item.getType()) {
            case "Android":
                helper.setImageResource(R.id.iv_type,R.mipmap.android_icon);
                break;
            case "iOS":
                helper.setImageResource(R.id.iv_type,R.mipmap.ios_icon);
                break;
            case "前端":
                helper.setImageResource(R.id.iv_type,R.mipmap.js_icon);
                break;
            case "拓展资源":
                helper.setImageResource(R.id.iv_type,R.mipmap.other_icon);
                break;
        }

        String author = item.getWho();
        if (author != null) {
            helper.setText(R.id.tv_author,author);
            helper.setTextColor(R.id.tv_author,Color.parseColor("#87000000"));
        } else {
            helper.setText(R.id.tv_author,"");
        }
        helper.setText(R.id.tv_time,item.getCreatedAt());
        helper.setText(R.id.tv_type,type);

    }

}