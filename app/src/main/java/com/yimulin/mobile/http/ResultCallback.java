package com.yimulin.mobile.http;

import java.io.IOException;

import okhttp3.Request;

/**
 * <pre>
 *      author : Hurley
 *      e-mail : 1401682479@qq.com
 *      time   : 2018/10/30
 * </pre>
 */
public abstract class ResultCallback {
    /**
     * 请求失败
     *
     * @param request
     * @param e
     */
    public abstract void onError(Request request, Exception e);

    /**
     * 请求成功
     *
     * @param str
     * @throws IOException
     */
    public abstract void onResponse(String str) throws IOException;
}
