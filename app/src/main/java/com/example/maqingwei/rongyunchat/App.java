package com.example.maqingwei.rongyunchat;

import android.app.Application;

import io.rong.imkit.RongIM;

/**
 * Created by maqingwei
 * Date on 16/9/8 上午9:24
 *
 * @Description:
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /*第一步初始化融云*/
        RongIM.init(this);
    }
}
