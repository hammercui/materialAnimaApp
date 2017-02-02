package com.paicheya.hammer.newtransapp.core;

import android.widget.Toast;

import com.paicheya.hammer.newtransapp.util.LogUtil;
import com.paicheya.hammer.newtransapp.util.NetUtil;

import rx.Subscriber;

/**
 * 通过的结果处理Subscribe
 * Created by cly on 17/2/2.
 */

public abstract class ResultSubscribe<T> extends Subscriber<T> {
    private BaseActivity  context;

    public ResultSubscribe(BaseActivity context){
        this.context = context;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(!NetUtil.isNetworkAvailable(context)){
            Toast.makeText(context,"当前网络不可用，请检查网络",Toast.LENGTH_SHORT).show();

            onCompleted();
        }

    }


    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        LogUtil.Debug("Throwable:"+e.toString());
        try{
            //处理错误
            onError(ExceptionHandle.handleException(e));
        }
        catch (Exception exception){
            onError(new ExceptionHandle.ResponseThrowable(exception, ExceptionHandle.ERROR.UNKNOWN));
        }
    }

    @Override
    public void onNext(T t) {
        //在这里可以对错误进行统一处理
        //处理正确返回的各种错误
        if(t instanceof BaseResponse){
            BaseResponse baseResponse = (BaseResponse)t;
            if(baseResponse.isOk()){
                onSuccess(t);
            }
            else{
                this.handlerSuccessError(baseResponse);
            }

        }
        else{
            //模板包装错误
            onError(new ExceptionHandle.ResponseThrowable(ExceptionHandle.ERROR.T_ERROR,"模板包装错误，需要BaseResponse类型"));
        }
    }

    public abstract void onError(ExceptionHandle.ResponseThrowable responseThrowable);
    public abstract void onSuccess(T t);

    /**
     * 处理请求成功的错误
     */
    private void handlerSuccessError(BaseResponse baseResponse){


    }



}
