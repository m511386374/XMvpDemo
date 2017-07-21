package com.huitouwuyou.huitou.xmvpdemo.update.bean;

/**
 * ========================================
 * <p/>
 * 版 权：dou361.com 版权所有 （C） 2015
 * <p/>
 * 作 者：陈冠明
 * <p/>
 * 个人网站：http://www.dou361.com
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2016/10/12 10:45
 * <p/>
 * 描 述：这里模拟你真实接口返回的实际json数据的解析对象
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class UpdateBean {

    /**
     * ret : 1
     * data : {"app_url":"/apk/seastation$.apk","version_name":"1.001","version_time":1488329690673,"app_size":"10000000","renew":"1.巴拉巴拉|2.滴沥滴沥|3.扑哧扑哧","system":"seastation","id":1}
     * msg : success
     */

    private int ret;
    private DataBean data;
    private String msg;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * app_url : /apk/seastation$.apk
         * version_name : 1.001
         * version_time : 1488329690673
         * app_size : 10000000
         * renew : 1.巴拉巴拉|2.滴沥滴沥|3.扑哧扑哧
         * system : seastation
         * id : 1
         */

        private String app_url;
        private String version_name;
        private long version_time;
        private Object app_size;
        private String renew;
        private String system;
        private int id;

        public String getApp_url() {
            return app_url;
        }

        public void setApp_url(String app_url) {
            this.app_url = app_url;
        }

        public String getVersion_name() {
            return version_name;
        }

        public void setVersion_name(String version_name) {
            this.version_name = version_name;
        }

        public long getVersion_time() {
            return version_time;
        }

        public void setVersion_time(long version_time) {
            this.version_time = version_time;
        }

        public Object getApp_size() {
            return app_size;
        }

        public void setApp_size(int app_size) {
            this.app_size = app_size;
        }

        public String getRenew() {
            return renew;
        }

        public void setRenew(String renew) {
            this.renew = renew;
        }

        public String getSystem() {
            return system;
        }

        public void setSystem(String system) {
            this.system = system;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
