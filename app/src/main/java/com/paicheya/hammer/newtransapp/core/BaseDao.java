package com.paicheya.hammer.newtransapp.core;

import com.paicheya.hammer.newtransapp.networks.HomeApi;
import com.paicheya.hammer.newtransapp.networks.RetrofitManager;

/**
 * Dao操作的基础类
 * Created by cly on 17/2/1.
 */

public class BaseDao {
    protected HomeApi homeApi;
    public BaseDao(){
        this.homeApi = RetrofitManager.getIns().retrofit.create(HomeApi.class);
    }
}
