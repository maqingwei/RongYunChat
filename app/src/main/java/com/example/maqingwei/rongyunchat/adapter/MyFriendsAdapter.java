package com.example.maqingwei.rongyunchat.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by maqingwei
 * Date on 16/9/28 上午10:22
 *
 * @Description:
 */
public class MyFriendsAdapter extends FragmentPagerAdapter {

    private String titles[] = {"好友","关注","粉丝"};

    private List<Fragment> mLists;

    public MyFriendsAdapter(FragmentManager fm,List<Fragment> lists) {
        super(fm);
        this.mLists = lists;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mLists.get(position);
    }

    @Override
    public int getCount() {
        return mLists.size();
    }
}
