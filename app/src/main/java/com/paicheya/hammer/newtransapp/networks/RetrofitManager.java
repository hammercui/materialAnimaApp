package com.paicheya.hammer.newtransapp.networks;

import com.cailutong.app.zlot.constants.H5URL;

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
    private static final String DEFAULT_URL = "file:///E:/Json/user.json";
    //public String baseUrl = "http://192.168.4.47:8092/wapif/";
    //public String baseUrl = "http://192.168.2.240:8088/app/"; //240测试服务器

   // public String baseUrl = "http://192.168.2.240/app/";
     public String baseUrl = H5URL.Base + "app/";
    //public String baseUrl ="http://192.168.3.239:8080/app/"; //王文龙
    //public String baseUrl ="http://192.168.3.215/app/";//	刘国强
    //public String baseUrl ="http://192.168.3.2:8080/app/"; //王小军id
    //public String baseUrl ="http://192.168.2.240:8088/app/"; //我自己
//	public String baseUrl ="http://192.168.3.194:8083/app/"; //梁鹏
    //public String baseUrl = "https://api.github.com/";
//    public String baseUrl = "http://192.168.3.248/app/"; //凉棚

    private static  RetrofitManager ins = null;
    /**
     * 需要身份校验的aouth
     */
    public Retrofit retrofitAouth = null;
    /**
     * 不需要身份校验的retrofit
     */
    public Retrofit retrofit = null;

    public static RetrofitManager getIns()
    {
        if (ins == null)
            ins = new RetrofitManager();
        return  ins;
    }
    public static final MediaType jsonReq = MediaType.parse("application/json; charset=utf-8");

    public RetrofitManager(){
        this.retrofitAouth = this.initRetrofitAouth();
        this.retrofit = this.initRetrofit();
    }

    private Retrofit initRetrofitAouth()
    {
        //新建OkHttpClient
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LogAouthInterceptor())
                .retryOnConnectionFailure(true)//出现错误重新连接
                .connectTimeout(15,TimeUnit.SECONDS)//设置超时时间
                .writeTimeout(15,TimeUnit.SECONDS)
                .build();
        //新建retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
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
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //添加对rxjava回调的支持
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //添加对rxjava回调的支持
                .build();
        return  retrofit;
    }
}
