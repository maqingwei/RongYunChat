package com.example.maqingwei.rongyunchat.http;

import com.kymjs.rxvolley.http.VolleyError;

/**
 * Created by maqingwei
 * Date on 16/9/29 下午12:14
 *
 * @Description: Http请求回调接口
 */
public interface CallBack {

    public void onSuccess(String response);

    public void onFinish();

    public void onFailure(VolleyError errormsg);

}
