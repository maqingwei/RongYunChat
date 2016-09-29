package com.example.maqingwei.rongyunchat.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.example.maqingwei.rongyunchat.R;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imlib.model.Conversation;

/**
 * Created by maqingwei
 * Date on 16/9/8 上午9:42
 *
 * @Description: 配置单聊窗口
 */
public class ConversationActivity extends FragmentActivity {

    private TextView mTitle;

    private Conversation.ConversationType mConversationType;//会话类型

    @BindView(R.id.layoutvideo)
    TextView mVideo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        ButterKnife.bind(this);

        //获取当前会话类型
        mConversationType = Conversation.ConversationType.valueOf(getIntent().getData()
                .getLastPathSegment().toUpperCase(Locale.getDefault()));

        if (mConversationType.equals(Conversation.ConversationType.CHATROOM)) {
            mVideo.setVisibility(View.VISIBLE);
        }

    }
}