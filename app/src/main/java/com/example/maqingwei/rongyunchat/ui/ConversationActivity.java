package com.example.maqingwei.rongyunchat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maqingwei.rongyunchat.R;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Discussion;
import io.rong.imlib.model.PublicServiceProfile;

/**
 * Created by maqingwei
 * Date on 16/9/8 上午9:42
 *
 * @Description: 配置单聊窗口
 */
public class ConversationActivity extends FragmentActivity {


    private String mTitle;//title
    private String mTragetId;//会话ID;
    private Conversation.ConversationType mConversationType;//会话类型

    private Intent intent;

    @BindView(R.id.layoutvideo)
    TextView mVideo;
    @BindView(R.id.layout_chat_top)
    LinearLayout mLayout;
    @BindView(R.id.right_btn)
    TextView mRight;
    @BindView(R.id.mid_btn)
    TextView mCenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        ButterKnife.bind(this);
        intent = getIntent();

        mTragetId = intent.getData().getQueryParameter("targetId");
        mTitle = intent.getData().getQueryParameter("title");

        //获取当前会话类型
        mConversationType = Conversation.ConversationType.valueOf(getIntent().getData()
                .getLastPathSegment().toUpperCase(Locale.getDefault()));

        if (mConversationType.equals(Conversation.ConversationType.CHATROOM)) {
            mVideo.setVisibility(View.VISIBLE);
        }else if (mConversationType.equals(Conversation.ConversationType.PRIVATE)){

            mLayout.setVisibility(View.VISIBLE);
            mRight.setText("");
            setActionBarTitle(mConversationType,mTragetId);


        }

    }

    /**
     * 设置会话页面 Title
     *
     * @param conversationType 会话类型
     * @param targetId         目标 Id
     */
    private void setActionBarTitle(Conversation.ConversationType conversationType, String targetId) {

        if (conversationType == null)
            return;

        if (conversationType.equals(Conversation.ConversationType.PRIVATE)) {
            setPrivateActionBar(targetId);
        } else if (conversationType.equals(Conversation.ConversationType.GROUP)) {
            setGroupActionBar(targetId);
        } else if (conversationType.equals(Conversation.ConversationType.DISCUSSION)) {
            setDiscussionActionBar(targetId);
        } else if (conversationType.equals(Conversation.ConversationType.CHATROOM)) {
            mCenter.setText(mTitle);
        } else if (conversationType.equals(Conversation.ConversationType.SYSTEM)) {
            mCenter.setText(R.string.de_actionbar_system);
        } else if (conversationType.equals(Conversation.ConversationType.APP_PUBLIC_SERVICE)) {
            setAppPublicServiceActionBar(targetId);
        } else if (conversationType.equals(Conversation.ConversationType.PUBLIC_SERVICE)) {
            setPublicServiceActionBar(targetId);
        } else if (conversationType.equals(Conversation.ConversationType.CUSTOMER_SERVICE)) {
            mCenter.setText(R.string.main_customer);
        } else {
            mCenter.setText(R.string.de_actionbar_sub_defult);
        }

    }


    /**
     * 设置群聊界面 ActionBar
     *
     * @param targetId 会话 Id
     */
    private void setGroupActionBar(String targetId) {
        if (!TextUtils.isEmpty(mTitle)) {
            mCenter.setText(mTitle);
        } else {
            mCenter.setText(targetId);
        }
    }

    /**
     * 设置应用公众服务界面 ActionBar
     */
    private void setAppPublicServiceActionBar(String targetId) {
        if (targetId == null)
            return;

        RongIM.getInstance().getPublicServiceProfile(Conversation.PublicServiceType.APP_PUBLIC_SERVICE
                , targetId, new RongIMClient.ResultCallback<PublicServiceProfile>() {
                    @Override
                    public void onSuccess(PublicServiceProfile publicServiceProfile) {
                        mCenter.setText(publicServiceProfile.getName());
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {

                    }
                });
    }

    /**
     * 设置公共服务号 ActionBar
     */
    private void setPublicServiceActionBar(String targetId) {

        if (targetId == null)
            return;


        RongIM.getInstance().getPublicServiceProfile(Conversation.PublicServiceType.PUBLIC_SERVICE
                , targetId, new RongIMClient.ResultCallback<PublicServiceProfile>() {
                    @Override
                    public void onSuccess(PublicServiceProfile publicServiceProfile) {
                        mCenter.setText(publicServiceProfile.getName());
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {

                    }
                });
    }

    /**
     * 设置讨论组界面 ActionBar
     */
    private void setDiscussionActionBar(String targetId) {

        if (targetId != null) {

            RongIM.getInstance().getDiscussion(targetId
                    , new RongIMClient.ResultCallback<Discussion>() {
                        @Override
                        public void onSuccess(Discussion discussion) {
                            mCenter.setText(discussion.getName());
                        }

                        @Override
                        public void onError(RongIMClient.ErrorCode e) {
                            if (e.equals(RongIMClient.ErrorCode.NOT_IN_DISCUSSION)) {
                                mCenter.setText("不在讨论组中");
                                supportInvalidateOptionsMenu();
                            }
                        }
                    });
        } else {
            mCenter.setText("讨论组");
        }
    }


    /**
     * 设置私聊界面 ActionBar
     */
    private void setPrivateActionBar(String targetId) {
        if (!TextUtils.isEmpty(mTitle)) {
            mCenter.setText(mTitle);
        } else {
            mCenter.setText(targetId);
        }
    }

}