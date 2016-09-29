package com.example.maqingwei.rongyunchat.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.example.maqingwei.rongyunchat.R;
import com.example.maqingwei.rongyunchat.adapter.MyFriendsAdapter;
import com.example.maqingwei.rongyunchat.fragment.FansFragment;
import com.example.maqingwei.rongyunchat.fragment.FocusFragment;
import com.example.maqingwei.rongyunchat.fragment.FriendsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FriendActivity extends BaseActivity {

    private List<Fragment> mFragmetList;
    private MyFriendsAdapter mAdapter;

    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.myfriend_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.left_btn)
    TextView mLeft;
    @BindView(R.id.mid_btn)
    TextView mCenter;
    @BindView(R.id.right_btn)
    TextView mRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        ButterKnife.bind(this);
        initFragment();
        initView();
    }

    private void initView(){

        mLeft.setText("返回");
        mCenter.setText("我的好友");
        mRight.setText("添加好友");

        mAdapter = new MyFriendsAdapter(getSupportFragmentManager(),mFragmetList);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    private void initFragment(){

        mFragmetList = new ArrayList<Fragment>();

        FansFragment fans = new FansFragment();
        FocusFragment focus = new FocusFragment();
        FriendsFragment friend = new FriendsFragment();

        mFragmetList.add(friend);
        mFragmetList.add(focus);
        mFragmetList.add(fans);

    }

    @OnClick(R.id.right_btn)
    void addFriend(){
        openActivity(AddFriendsActivity.class);
    }
}

