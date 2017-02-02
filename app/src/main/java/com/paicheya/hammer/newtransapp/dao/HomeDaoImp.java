package com.paicheya.hammer.newtransapp.dao;

import com.paicheya.hammer.newtransapp.core.BaseDao;
import com.paicheya.hammer.newtransapp.core.BaseResponse;
import com.paicheya.hammer.newtransapp.entity.BannerEntity;

import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by cly on 17/2/1.
 */

public class HomeDaoImp extends BaseDao implements HomeDao {

    @Override
    public Observable<BaseResponse<List<BannerEntity>>> getBanner() {
        return homeApi.getBannerData().subscribeOn(Schedulers.io());
    }
}
