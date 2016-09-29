package com.example.maqingwei.rongyunchat.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.example.maqingwei.rongyunchat.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

/**
 * Created by maqingwei
 * Date on 16/9/8 上午9:42
 *
 * @Description: 配置会话列表
 */
public class ConversationListActivity extends FragmentActivity {


    @BindView(R.id.left_btn)
    TextView mLeft;
    @BindView(R.id.mid_btn)
    TextView mMid;
    @BindView(R.id.right_btn)
    TextView mRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        initConversationList();
        initView();
    }

    private void initView(){
        mLeft.setText("返回");
        mMid.setText("消息");
        mRight.setText("忽略未读");


    }


    private void initConversationList() {

            ConversationListFragment listFragment = ConversationListFragment.getInstance();
            Uri uri;

            uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                    .appendPath("conversationlist")
                    .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "true") //设置私聊会话是否聚合显示
                    .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//群组
                    .appendQueryParameter(Conversation.ConversationType.CHATROOM.getName(), "true")//群组
                    .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "true")//公共服务号
                    .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "true")//订阅号
                    .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                    .build();
            listFragment.setUri(uri);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //xxx 为你要加载的 id
        transaction.add(R.id.frameconversationlist, listFragment);
        transaction.commit();

    }

}
