> 演示Android5.0新增转场动画，以及新增Bottom navigation

## 本项目的学习目标
- [x] svg矢量图的使用
- [x] Bottom navigation的使用
>[官方指导](https://material.io/guidelines/components/bottom-navigation.html#bottom-navigation-specs)

- [x] 复习dagger2，使用dagger2管理application生命周期的greenDao数据库
>这里greenDao已经使用了3.0版本，修改的原因：3.0之前需要通过新建GreenDaoGenerator工程生成Java数据对象（实体）和DAO对象，非常的繁琐而且也加大了使用成本。GreenDao  3.0最大的变化就是采用注解的方式通过编译方式生成Java数据对象和DAO对象
- [x] 复习使用retrofit2+rxjava+rxandroid搭建mvp架构,并复习Android函数式响应编程处理网络任务
> 1. 统一的状态错误处理,般状态码：
>> *  2xx 正确
>> *  4xx 客户端错误，网络异常
>> *  5xx 服务端错误，弹出提示，特殊情况提供操作码
>
> 2. retrofit对api请求的封装，使用rxjava的函数式处理，并封装包含缓存的处理
> 3. retrofit的日志拦截
> 4. retrofit统一的错误处理

- [ ] 在activity嵌套Fragment，使用BotttomBar+ViewPagger进行导航切换
- [ ] 自定义toolbar,包含自定义的searchbar
- [ ] 在toolbar中使用Android5.0新增转场动画
- [ ] 试验bottomBar切换Fragment，Fragment包含AppBarLayout
- [ ] 试验Fragment，Fragment包含滚屏操作，背景是图片，

### retrofit2统一的错误处理

1. 新建抽象类`ResultSubscribe<T>`类，继承自Subscribe<T>类，在里面处理
`onComplete(),onErroe(),onNext()`操作
2. `Observable`的`subscribe`处理对象使用自定义的`ResultSubscribe`取代`Subscribe`









