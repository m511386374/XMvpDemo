package com.huitouwuyou.huitou.xmvpdemo.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 韩莫熙 on 2017/2/16.
 */

public class LoginModel implements Serializable {


    private static final long serialVersionUID = 4989068052468264964L;
    /**
     * _id : 58a077b0421aa901f7902c69
     * createdAt : 2017-02-12T22:56:48.763Z
     * desc : 我可以认识你吗
     * publishedAt : 2017-02-16T10:07:37.13Z
     * source : chrome
     * type : 休息视频
     * url : http://v.youku.com/v_show/id_XMjUwMDQ5MDM2NA==.html
     * used : true
     * who : lxxself
     * images : ["http://img.gank.io/86d3f3e6-26c7-459d-95f7-1816cc2ca517"]
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private List<String> images;

    @Override
    public String toString() {
        return "LoginModel{" +
                "_id='" + _id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", desc='" + desc + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", used=" + used +
                ", who='" + who + '\'' +
                ", images=" + images +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

}
