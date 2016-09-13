package com.example.maqingwei.rongyunchat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

/**
 * Created by maqingwei
 * Date on 16/9/8 上午9:42
 *
 * @Description: 配置单聊窗口
 */
public class ConversationActivity extends FragmentActivity {

    private TextView mTitle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        mTitle = (TextView) findViewById(R.id.titile);
        String targetId = getIntent().getData().getQueryParameter("targetId");
        String title = getIntent().getData().getQueryParameter("title");
        mTitle.setText(title);

    }
}