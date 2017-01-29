package com.paicheya.hammer.newtransapp

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.paicheya.hammer.newtransapp.test;
/**
 * app首页
 * Created by cly on 17/1/25.
 */


class MainActivity:AppCompatActivity(){


    private var toolbar:Toolbar? = null
    private var fab:FloatingActionButton? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main);

        initUI()
    }
    //初始化ui
    private fun initUI(){
        //toolbar
        toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar!!);
        //floating按钮
        fab = findViewById(R.id.fab) as FloatingActionButton
        fab!!.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
    }



}