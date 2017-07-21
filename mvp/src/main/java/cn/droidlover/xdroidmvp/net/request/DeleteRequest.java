package cn.droidlover.xdroidmvp.net.request;


import java.io.IOException;

import cn.droidlover.xdroidmvp.net.model.HttpHeaders;
import cn.droidlover.xdroidmvp.net.utils.HttpUtils;
import cn.droidlover.xdroidmvp.net.utils.OkLogger;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * ================================================
 * 作    者：廖子尧
 * 版    本：1.0
 * 创建日期：2016/1/16
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class DeleteRequest extends BaseBodyRequest<DeleteRequest> {

    public DeleteRequest(String url) {
        super(url);
        method = "DELETE";
    }

    @Override
    public Request generateRequest(RequestBody requestBody) {
        try {
            headers.put(HttpHeaders.HEAD_KEY_CONTENT_LENGTH, String.valueOf(requestBody.contentLength()));
        } catch (IOException e) {
            OkLogger.e(e);
        }
        Request.Builder requestBuilder = HttpUtils.appendHeaders(headers);
        return requestBuilder.delete(requestBody).url(url).tag(tag).build();
    }
}