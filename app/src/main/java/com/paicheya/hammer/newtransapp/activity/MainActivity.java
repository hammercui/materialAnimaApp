package com.paicheya.hammer.newtransapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.paicheya.hammer.newtransapp.R;
import com.paicheya.hammer.newtransapp.core.BaseActivity;
import com.paicheya.hammer.newtransapp.core.BaseResponse;
import com.paicheya.hammer.newtransapp.core.ExceptionHandle;
import com.paicheya.hammer.newtransapp.core.ResultSubscribe;
import com.paicheya.hammer.newtransapp.dao.HomeDao;
import com.paicheya.hammer.newtransapp.dao.HomeDaoImp;
import com.paicheya.hammer.newtransapp.entity.BannerEntity;
import com.paicheya.hammer.newtransapp.util.LogUtil;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by cly on 17/1/31.
 */

public class MainActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    @BindView(R.id.bottomBar)
    public BottomBar bottomBar;
    @BindView(R.id.btn_test)
    public Button btnTest;

    private Context context;
    private HomeDao homeDao = new HomeDaoImp();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.context = this;
        initView();
    }

    private void initView(){
        this.setSupportActionBar(toolbar);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testHomeDao();
            }
        });

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId){
                    case R.id.bottomtab_home:
                        break;
                    case R.id.bottomtab_fliter:
                        break;
                    case R.id.bottomtab_account:
                        break;
                    default:
                        Toast.makeText(context,"异常", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void testHomeDao(){
        homeDao.getBanner()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResultSubscribe<BaseResponse<List<BannerEntity>>>(this) {
                    @Override
                    public void onError(ExceptionHandle.ResponseThrowable responseThrowable) {
                        LogUtil.Debug("错误代码"+responseThrowable.code+",错误信息："+responseThrowable.message);
                    }

                    @Override
                    public void onSuccess(BaseResponse<List<BannerEntity>> listBaseResponse) {
                       LogUtil.Debug("index 0 title:"+listBaseResponse.getData().get(0).getTitle());
                    }
                });
    }


}
