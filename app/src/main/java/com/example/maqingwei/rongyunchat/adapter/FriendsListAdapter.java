package com.example.maqingwei.rongyunchat.adapter;

import android.content.Context;

import com.example.maqingwei.rongyunchat.R;
import com.example.maqingwei.rongyunchat.baselsitview.ListBaseAdapter;
import com.example.maqingwei.rongyunchat.baselsitview.ListViewHolder;
import com.example.maqingwei.rongyunchat.entity.FriendsList;

import java.util.List;

/**
 * Created by maqingwei
 * Date on 16/10/9 上午10:03
 *
 * @Description:
 */
public class FriendsListAdapter extends ListBaseAdapter<FriendsList> {

    public FriendsListAdapter(Context mContext, List<FriendsList> list, int id) {
        super(mContext, list, id);

    }

    @Override
    public void convert(ListViewHolder viewHolder, FriendsList date, int position) {

        viewHolder.setText(R.id.user_username, date.getUserName());
        viewHolder.setImage(R.id.user_img, date.getPortrait());

    }
}
