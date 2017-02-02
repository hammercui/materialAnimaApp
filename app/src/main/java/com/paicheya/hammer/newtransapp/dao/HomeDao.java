package com.paicheya.hammer.newtransapp.dao;

import com.paicheya.hammer.newtransapp.core.BaseResponse;
import com.paicheya.hammer.newtransapp.entity.BannerEntity;

import java.util.List;
import rx.Observable;

/**
 * Created by cly on 17/2/1.
 */

public interface HomeDao {
    public Observable<BaseResponse<List<BannerEntity>>> getBanner();
}
