package com.paicheya.hammer.newtransapp.networks;

import com.paicheya.hammer.newtransapp.util.LogUtil;
import com.paicheya.hammer.newtransapp.util.NetUtil;

import java.io.IOException;


import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 带身份校验的okhttp拦截器
 * Created by hammer on 2016/6/20.
 */
public  class LogTokenInterceptor implements Interceptor {
    // private String cookie = null;
    @Override public Response intercept(Chain chain) throws IOException {
        //请求
        Request.Builder builder = chain.request().newBuilder();
        Request newrequest = setRequestHeader(builder).build();
        LogUtil.Http(String.format("-->: %s",newrequest.url()));
        LogUtil.Http("-->header内容"+newrequest.headers().toString());
        //响应
        Response response = chain.proceed(newrequest);
        getResponseHeader(response);
        //body log输出
        if (LogUtil.isDebug){
            ResponseBody responseBody = response.body();
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();
            //long contentLength = responseBody.contentLength();
            LogUtil.Debug("<-- "+buffer.clone().readString(NetUtil.UTF8));
        }
        return response;
    }

    /**
     * 添加request请求头部
     * @param builder
     * @return
     */
    private Request.Builder setRequestHeader(Request.Builder builder){
//
//        builder.addHeader(DBKEY.DEVICE_ID.toString(), SystemUtil.getDeviceId());
//        builder.addHeader(DBKEY.DEVICE_TYPE.toString(),"1");//1 android 2ios
        return builder;
    }

    /**
     * 获得response的header
     * @param response
     */
    private void getResponseHeader(Response response){
//        String token  = response.headers().get(DBKEY.TOKEN.toString());
//        if (token != null){
//            BaseApp.getIns().dBlocal.setValue(DBKEY.COOKIE,token);
//            ZlotLogger.Debug("获得TOKEN:"+token);
//        }
    }

    private void getCookie(Response response){
        //获得cookie
        if (response.headers().get("Set-Cookie") != null){
            final StringBuffer cookieBuffer = new StringBuffer();
            Observable.from(response.headers("Set-Cookie"))
                    .map(new Func1<String, String>() {
                        @Override
                        public String call(String s) {
                            String[] cookieArray = s.split(";");
                            return cookieArray[0];
                        }
                    })
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String cookie) {
                            cookieBuffer.append(cookie).append(";");
                        }
                    });
            //cookie = response.header("Set-Cookie");
            LogUtil.Debug("获得Cookie:"+cookieBuffer.toString());
        }
    }
}