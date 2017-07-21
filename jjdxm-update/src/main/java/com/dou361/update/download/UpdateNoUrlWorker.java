package com.dou361.update.download;

import com.dou361.update.ParseData;
import com.dou361.update.UpdateHelper;
import com.dou361.update.bean.Update;
import com.dou361.update.listener.UpdateListener;
import com.dou361.update.type.UpdateType;
import com.dou361.update.util.HandlerUtil;
import com.dou361.update.util.InstallUtil;
import com.dou361.update.util.NetworkUtil;
import com.dou361.update.util.UpdateSP;

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
 * 创建日期：2016/10/13 9:56
 * <p/>
 * 描 述：无网络工作类
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class UpdateNoUrlWorker implements Runnable {

    protected UpdateListener checkCB;
    protected ParseData parser;
    protected String requestResultData;


    public void setRequestResultData(String requestResultData) {
        this.requestResultData = requestResultData;
    }

    public void setUpdateListener(UpdateListener checkCB) {
        this.checkCB = checkCB;
    }

    public void setParser(ParseData parser) {
        this.parser = parser;
    }

    @Override
    public void run() {
        try {
            Update parse = parser.parse(requestResultData);
            if (parse == null) {
                throw new IllegalArgumentException("parse response to update failed by " + parser.getClass().getCanonicalName());
            }
            int curVersion = InstallUtil.getApkVersion(UpdateHelper.getInstance().getContext());
            if ((parse.getVersionCode() > curVersion) &&
                    ((UpdateSP.isIgnore(parse.getVersionCode() + "")
                            && (UpdateHelper.getInstance().getUpdateType() == UpdateType.checkupdate))
                            || (!UpdateSP.isIgnore(parse.getVersionCode() + "")
                            && (UpdateHelper.getInstance().getUpdateType() != UpdateType.autowifiupdate))
                            || (!UpdateSP.isIgnore(parse.getVersionCode() + "")
                            && (UpdateHelper.getInstance().getUpdateType() == UpdateType.autowifiupdate)
                            && NetworkUtil.isConnectedByWifi()))) {
                /**有新版本*/
                UpdateSP.setForced(parse.isForce());
                sendHasUpdate(parse);
            } else {
                /**无新版本*/
                sendNoUpdate();
            }
        } catch (HttpException he) {
            sendOnErrorMsg(he.getCode(), he.getErrorMsg());
        } catch (Exception e) {
            sendOnErrorMsg(-1, e.getMessage());
        }
    }

    private void sendHasUpdate(final Update update) {
        if (checkCB == null) return;
        HandlerUtil.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                checkCB.hasUpdate(update);
            }
        });
    }

    private void sendNoUpdate() {
        if (checkCB == null) return;
        HandlerUtil.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                checkCB.noUpdate();
            }
        });
    }

    private void sendOnErrorMsg(final int code, final String errorMsg) {
        if (checkCB == null) return;
        HandlerUtil.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                checkCB.onCheckError(code, errorMsg);
            }
        });
    }
}
