package com.example.maqingwei.rongyunchat.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maqingwei.rongyunchat.R;
import com.example.maqingwei.rongyunchat.adapter.FriendsListAdapter;
import com.example.maqingwei.rongyunchat.entity.FriendsList;
import com.example.maqingwei.rongyunchat.view.XListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by maqingwei
 * Date on 16/9/28 上午10:09
 *
 * @Description:
 */
public class FriendsFragment extends Fragment {

    private View view;
    private List<FriendsList> mData;

    @BindView(R.id.xl_myfriends)
    XListView mFriendList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_friends, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView(){
        mData = new ArrayList<FriendsList>();
        mFriendList.setPullLoadEnable(false);
        mFriendList.setPullRefreshEnable(false);
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences sp = getActivity().getSharedPreferences("addfriends", 0);
        String username = sp.getString("username", "");
        String userid = sp.getString("userid", "");
        String img = sp.getString("img", "");
        if (!username.equals("") || !userid.equals("") || !img.equals("")) {
            if (mData.size()>0){
                mData.clear();
            }
            mData.add(new FriendsList("",username,img,"",userid));
            mFriendList.setAdapter(new FriendsListAdapter(getContext(),mData,R.layout.item_search_friends_result));
        }
    }
}
