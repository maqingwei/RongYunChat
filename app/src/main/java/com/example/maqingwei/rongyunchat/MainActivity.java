package com.example.maqingwei.rongyunchat;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.kymjs.rxvolley.http.VolleyError;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

public class MainActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private Button mRoomBtn,mConnectBtn;
    private EditText mUserId,mUserName;

    private String uername;
    private String userId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView (){

        mRoomBtn = (Button) findViewById(R.id.btn_chatroom);
        mConnectBtn = (Button) findViewById(R.id.btn_connect);
        mUserId = (EditText) findViewById(R.id.et_userid);
        mUserName = (EditText) findViewById(R.id.et_username);

        mConnectBtn.setOnClickListener(this);


    }

    private  void connectRingCloud(final String t){

        RongIM.connect(t, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                //失效的处理,需要重新获取Token
                Toast.makeText(MainActivity.this, "Token Incrrect", Toast.LENGTH_SHORT).show();
                getTokenById(userId,uername);
            }

            @Override
            public void onSuccess(String token) {
                Toast.makeText(MainActivity.this, "Connect success", Toast.LENGTH_SHORT).show();

                mRoomBtn.setOnClickListener(MainActivity.this);

            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Toast.makeText(MainActivity.this, " Error  Code"+errorCode , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_chatroom:
                //启动聊天室

                if (RongIM.getInstance() != null) {
                    RongIM.getInstance().startConversation(this, Conversation.ConversationType.CHATROOM, "1001", "hhc");

                }
                break;
            case  R.id.btn_connect:
                //链接融云
                userId = mUserId.getText().toString().trim();
                uername =mUserName.getText().toString().trim();

                if(userId.equals("")||uername.equals("")){
                    Toast.makeText(MainActivity.this, "UserId或者用户名不能为空", Toast.LENGTH_SHORT).show();
                     return;
                }else{

                    //第一步根据UserId获取Token
                getTokenById(uername,userId);

                }
                break;
        }
    }

    private void getTokenById(String username,String id) {
        //http://192.168.1.101:8080/springmvc/restWebservice/common.do?userId=userid&name=usename&portraitUri=http://www.rongcloud.cn/images/logo.png&id=getToken
        String url = "http://192.168.1.101:8080/springmvc/restWebservice/common.do?";

        HttpParams parmas = new HttpParams();
        parmas.put("userId", id);
        parmas.put("name", username);
        parmas.put("portraitUri", "");
        parmas.put("id", "getToken");

        RxVolley.post(url, parmas, new HttpCallback() {
            @Override
            public void onFailure(VolleyError error) {
                super.onFailure(error);
                Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                Log.i("ttt",t);
                Gson gson = new Gson();
                Token token = gson.fromJson(t, Token.class);

                //第二步 根据Token链接融云

                connectRingCloud(token.getToken());
            }
        });
    }

    //加入聊天室
    private void getChatRoom(){


    }
}