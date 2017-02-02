package com.paicheya.hammer.newtransapp.networks;
import com.paicheya.hammer.newtransapp.MyApplication;
import com.paicheya.hammer.newtransapp.util.LogUtil;
import com.paicheya.hammer.newtransapp.util.NetUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * 不带身份校验的okhttp拦截器
 * Created by hammer on 2016/6/20.
 */

public  class LogInterceptor implements Interceptor {



    // private String cookie = null;
    @Override public Response intercept(Chain chain) throws IOException {
        //请求
        Request request = chain.request();
        LogUtil.Http(String.format("-->: %s",request.url()));
        LogUtil.Http("-->header内容"+request.headers().toString());
        //响应
        Response response = chain.proceed(request);
        //响应body log输出
        if (LogUtil.isDebug){
            ResponseBody responseBody = response.body();
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();
            LogUtil.Debug("<-- "+buffer.clone().readString(NetUtil.UTF8));
        }
        return response;
    }


}