package com.example.maqingwei.rongyunchat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maqingwei.rongyunchat.R;

/**
 * Created by maqingwei
 * Date on 16/9/28 上午10:09
 *
 * @Description:
 */
public class FriendsFragment  extends Fragment{

    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_friends,container,false);
        return view;
    }
}
