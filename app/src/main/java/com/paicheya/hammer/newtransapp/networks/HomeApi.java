package com.paicheya.hammer.newtransapp.networks;

import com.paicheya.hammer.newtransapp.core.BaseResponse;
import com.paicheya.hammer.newtransapp.entity.BannerEntity;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

/**
 * 首页api
 * Created by cly on 17/2/1.
 */

public interface HomeApi {

    @GET("home.json")
    public Observable<BaseResponse<List<BannerEntity>>> getBannerData();
}
