package com.example.maqingwei.rongyunchat.ui;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maqingwei.rongyunchat.Constants;
import com.example.maqingwei.rongyunchat.R;
import com.example.maqingwei.rongyunchat.entity.ChatRoomInfo;
import com.example.maqingwei.rongyunchat.entity.Token;
import com.google.gson.Gson;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.kymjs.rxvolley.http.VolleyError;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

public class MainActivity extends BaseActivity implements RongIM.UserInfoProvider {

    private static final String TAG = "MainActivity";

    private String uername;
    private String userid;


    private  Gson gson = new Gson();

    @BindView(R.id.et_userid)
    EditText mUserId;
    @BindView(R.id.et_username)
    EditText mUserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        RongIM.setUserInfoProvider(this, true);

    }

    /**
     * 连接融云
     *
     * @param t
     */
    private void connectRong(String t) {
        RongIM.connect(t, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                dismissLoadDialog();
                Toast.makeText(MainActivity.this, "Token Incorrect", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(String s) {
                dismissLoadDialog();
                Toast.makeText(MainActivity.this, "连接成功:" + s, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                dismissLoadDialog();
                Toast.makeText(MainActivity.this, "连接失败：" + errorCode, Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 根据用户名获取token
     *
     * @param userid
     * @param usernaem
     */
    private void getToken(String userid, String usernaem) {

        HttpParams parmas = new HttpParams();
        parmas.put("userId", userid);
        parmas.put("name", usernaem);
        parmas.put("portraitUri", Constants.USER_PHOTO);

        RxVolley.post(Constants.TOKEN_URL, parmas, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                Log.i(TAG, "onSuccess: " + t);

                Token token = gson.fromJson(t, Token.class);
                connectRong(token.getToken());

            }

            @Override
            public void onFailure(VolleyError error) {
                super.onFailure(error);
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });
    }

    //获取聊天室信息
    private void getChatRoomInfo(String chatroomname) {
   showLoadingDialog();
        String url = Constants.CHATROOM_INFO + chatroomname;

        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onFailure(VolleyError error) {
                super.onFailure(error);
                dismissLoadDialog();
                Toast.makeText(MainActivity.this, "OnFailure", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                dismissLoadDialog();
                String trim = t.toString().replace("[", "").replace("]", "").trim();
                ChatRoomInfo chatRoomInfo = gson.fromJson(trim, ChatRoomInfo.class);
                connectChatRoom(chatRoomInfo.getId());
            }
        });
    }

    //连接聊天室
    private void connectChatRoom(String chatroomId) {
        if (RongIM.getInstance() != null) {
            RongIM.getInstance().startConversation(this, Conversation.ConversationType.CHATROOM, chatroomId, "");
        }
    }

    @OnClick({R.id.btn_message, R.id.btn_myfriend, R.id.btn_group,
            R.id.btn_mylivechat, R.id.btn_connectrong,R.id.btn_singlep})
    public void click(View v) {

        switch (v.getId()) {
            case R.id.btn_connectrong:
                //连接融云
                showLoadingDialog();
                userid = mUserId.getText().toString().trim();
                uername = mUserName.getText().toString().trim();
                if (!userid.equals("") && !uername.equals("")) {
                    getToken(userid, uername);
                } else {
                    Toast.makeText(MainActivity.this, "用户名或者id不能为空", Toast.LENGTH_SHORT).show();
                }



                break;
            case  R.id.btn_singlep:


                break;
            case R.id.btn_message:
                //消息

                openActivity(ConversationListActivity.class);

                break;
            case R.id.btn_myfriend:
                //我的好友
                openActivity(FriendsActivity.class);

                break;
            case R.id.btn_group:
                //群组


                break;
            case R.id.btn_mylivechat:
                //直播聊天室
                getChatRoomInfo("cctv1");

                break;
        }
    }



    @Override
    public UserInfo getUserInfo(String s) {
        return getUserInfoById(s);
    }

    //根据Userid 去系统里查询用户信息 并返回给融云
    private UserInfo getUserInfoById(String userid) {
        UserInfo user = new UserInfo(userid, uername, Uri.parse(Constants.USER_IMG));

        return user;
    }
}