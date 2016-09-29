package com.example.maqingwei.rongyunchat.ui;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maqingwei.rongyunchat.R;
import com.example.maqingwei.rongyunchat.view.SearchEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddFriendsActivity extends BaseActivity {

    @BindView(R.id.left_btn)
    TextView mLeft;
    @BindView(R.id.mid_btn)
    TextView mCenter;
    @BindView(R.id.right_btn)
    TextView mRight;
    @BindView(R.id.ed_search)
    SearchEditText mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);

        ButterKnife.bind(this);
        initView();

    }

    private void initView() {

        mLeft.setText("返回");
        mCenter.setText("添加");
        mRight.setText("");

        mSearch.setHint("用户ID/昵称/手机号");
        mSearch.setSearchListener(new SearchEditText.onSearchListener() {
            @Override
            public void onSearch(String text) {
                Toast.makeText(AddFriendsActivity.this, "点击了搜索,并且搜索内容是："+text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchFriends(){

    }
}
