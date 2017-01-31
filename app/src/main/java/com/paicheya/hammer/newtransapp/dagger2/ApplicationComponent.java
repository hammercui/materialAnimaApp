package com.paicheya.hammer.newtransapp.dagger2;

import com.paicheya.hammer.newtransapp.MyApplication;

import javax.inject.Singleton;

import dagger.Component;


/**
 * application的Component
 * 提供全局对象，方便其他的模块依赖使用
 * 其生命周期与Application同步
 * Created by hammer on 2016/7/28.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    MyApplication getMyApplication();
    //注入依赖
    MyApplication inject(MyApplication myApplication);

}
