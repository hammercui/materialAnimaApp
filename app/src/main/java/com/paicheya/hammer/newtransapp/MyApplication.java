package com.paicheya.hammer.newtransapp;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.paicheya.hammer.newtransapp.dagger2.ApplicationComponent;
import com.paicheya.hammer.newtransapp.dagger2.ApplicationModule;
import com.paicheya.hammer.newtransapp.dagger2.DaggerApplicationComponent;

/**
 * 全局唯一application
 * Created by cly on 17/1/31.
 */

public class MyApplication extends Application {

    private static MyApplication instance;
    public static MyApplication getIns(){
        return  instance;
    }
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        initInject();
    }

    /**
     * dagger2的依赖注入
     */
    private void initInject(){
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

    /**
     * 获得app 包信息
     * @return
     */
    public PackageInfo getAppInfo(){
        PackageManager packageManager = getPackageManager();
        PackageInfo packInfo = null;
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        try
        {
            packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return packInfo;
    }
}
