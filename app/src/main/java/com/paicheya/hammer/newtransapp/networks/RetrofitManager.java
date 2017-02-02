package com.paicheya.hammer.newtransapp.networks;


import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**Retrofit管理类
 * Created by hammerCui on 2016/1/5.
 */
public class RetrofitManager {
    private static final String BaseUrl = "https://github.com/hammercui/trandAnimation/raw/master/";
    public  static final MediaType jsonReq = MediaType.parse("application/json; charset=utf-8");
    private static  RetrofitManager ins = null;

    /**
     * 需要身份校验token的retrofit
     */
    public Retrofit retrofitToken = null;
    /**
     * 不需要身份校验的retrofit
     */
    public Retrofit retrofit = null;



    public RetrofitManager(){
        this.retrofitToken = this.initRetrofitToken();
        this.retrofit = this.initRetrofit();
    }

    //singleton模式
    public static RetrofitManager getIns()
    {
        if (ins == null)
            ins = new RetrofitManager();
        return  ins;
    }


    //初始化带token的http请求
    private Retrofit initRetrofitToken()
    {
        //新建OkHttpClient
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LogTokenInterceptor())
                .retryOnConnectionFailure(true)//出现错误重新连接
                .connectTimeout(15,TimeUnit.SECONDS)//设置超时时间
                .writeTimeout(15,TimeUnit.SECONDS)
                .build();
        //新建retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //添加对rxjava回调的支持
                .addConverterFactory(GsonConverterFactory.create())  //添加对json的解析
                .build();

        return  retrofit;

    }

    private Retrofit initRetrofit()
    {
        //设置okhttp的加密
        //OkHttpClientManager.getInstance().setCertificates(new InputStream[]{new Buffer().writeUtf8(CER_12306).inputStream()});
        //设置okhttp的超时 ms
        // OkHttpClientManager.getInstance().getOkHttpClient().setConnectTimeout(100000, TimeUnit.MILLISECONDS);
        //设置okhttp的cookie持久化
        //OkHttpClientManager.getInstance().setCookie(store, policy);

        //新建OkHttpClient
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LogInterceptor()) //添加日志输出
                .retryOnConnectionFailure(true)//出现错误重新连接
                .connectTimeout(15,TimeUnit.SECONDS)//设置超时时间
                .writeTimeout(15,TimeUnit.SECONDS)
                .build();

        //新建retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //添加对rxjava回调的支持
                .addConverterFactory(GsonConverterFactory.create())  //添加对json的解析
                .build();
        return  retrofit;
    }
}
