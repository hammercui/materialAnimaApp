package com.paicheya.hammer.newtransapp.networks;


import com.cailutong.app.zlot.BaseApp;
import com.cailutong.app.zlot.constants.DBKEY;
import com.cailutong.app.zlot.util.NetUtil;
import com.cailutong.app.zlot.util.SystemUtil;
import com.cailutong.app.zlot.util.ZlotLogger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * 不带带身份校验的okhttp拦截器
 * Created by hammer on 2016/6/20.
 */

public  class LogInterceptor implements Interceptor {
    // private String cookie = null;
    @Override public Response intercept(Chain chain) throws IOException {
        //请求
        Request.Builder builder = chain.request().newBuilder();
        Request newrequest = setRequestHeader(builder).build();
        ZlotLogger.Http(String.format("-->: %s",newrequest.url()));
        ZlotLogger.Http("-->header内容"+newrequest.headers().toString());
        //响应
        Response response = chain.proceed(newrequest);
        getResponseHeader(response);
        //body log输出
        if (ZlotLogger.isDebug){
            ResponseBody responseBody = response.body();
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();
            //long contentLength = responseBody.contentLength();
            ZlotLogger.Debug("<-- "+buffer.clone().readString(NetUtil.UTF8));
        }
        return response;
    }

    /**
     * 添加request请求头部
     * @param builder
     * @return
     */
    private Request.Builder setRequestHeader(Request.Builder builder){
        if (BaseApp.getIns().deviceEntity.getToken() == null){
            if (BaseApp.getIns().dBlocal.getValue(DBKEY.TOKEN) != null){
                BaseApp.getIns().deviceEntity.setToken(BaseApp.getIns().dBlocal.getValue(DBKEY.TOKEN));
                builder.addHeader(DBKEY.TOKEN.toString(), BaseApp.getIns().deviceEntity.getToken());
            }
        }
        else{
            builder.addHeader(DBKEY.TOKEN.toString(), BaseApp.getIns().deviceEntity.getToken());
        }

        builder.addHeader(DBKEY.DEVICE_ID.toString(), SystemUtil.getDeviceId());
        builder.addHeader(DBKEY.DEVICE_TYPE.toString(),"1");//1 android 2ios
        return builder;
    }

    private void getResponseHeader(Response response){
        String token  = response.headers().get(DBKEY.TOKEN.toString());
        if (token != null){
            BaseApp.getIns().dBlocal.setValue(DBKEY.COOKIE,token);
            ZlotLogger.Debug("获得TOKEN:"+token);
        }
    }

}