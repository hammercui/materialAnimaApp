package com.paicheya.hammer.newtransapp

import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.roughike.bottombar.BottomBar
import com.roughike.bottombar.OnTabSelectListener

/**
 * app首页
 * Created by cly on 17/1/25.
 */


class MainActivity:AppCompatActivity(){


    private var toolbar:Toolbar? = null
    private var fab:FloatingActionButton? = null
    private var bottomBar:BottomBar? = null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main);

        initUI(this)
    }
    //初始化ui
    private fun initUI(context: Context){
        //toolbar
        toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar!!);
        //floating按钮
        fab = findViewById(R.id.fab) as FloatingActionButton
        fab!!.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        //底部导航
        bottomBar = findViewById(R.id.bottomBar) as BottomBar
        bottomBar!!.setOnTabSelectListener( { tabId->
            when(tabId){
                R.id.bottomtab_home->{Toast.makeText(context,tabId,Toast.LENGTH_SHORT).show()}
                R.id.bottomtab_fliter->{Toast.makeText(context,tabId,Toast.LENGTH_SHORT).show()}
                R.id.bottomtab_account->{Toast.makeText(context,tabId,Toast.LENGTH_SHORT).show()}
                else->{Toast.makeText(context,"异常",Toast.LENGTH_SHORT).show()}
            }
        })

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
    }



}