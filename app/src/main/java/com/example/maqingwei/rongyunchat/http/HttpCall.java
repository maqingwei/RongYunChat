package com.example.maqingwei.rongyunchat.http;

import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.http.VolleyError;

/**
 * Created by maqingwei
 * Date on 16/9/29 下午2:29
 *
 * @Description:
 */
public class HttpCall extends HttpCallback{

    private CallBack mCall;

    public HttpCall(CallBack call){

        this.mCall = call;
    }

    @Override
    public void onFailure(VolleyError error) {
        super.onFailure(error);
        mCall.onFailure(error);
    }

    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
        mCall.onSuccess(t);
    }

    @Override
    public void onFinish() {
        super.onFinish();
        mCall.onFinish();
    }
}
