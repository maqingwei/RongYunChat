package com.example.maqingwei.rongyunchat.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maqingwei.rongyunchat.Constants;
import com.example.maqingwei.rongyunchat.R;
import com.example.maqingwei.rongyunchat.adapter.FriendsListAdapter;
import com.example.maqingwei.rongyunchat.entity.FriendsList;
import com.example.maqingwei.rongyunchat.view.SearchEditText;
import com.example.maqingwei.rongyunchat.view.XListView;
import com.google.gson.Gson;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.http.VolleyError;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.RongIM;

public class AddFriendsActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private List<FriendsList> mSearchResult;
    private FriendsListAdapter mAdapter;

    @BindView(R.id.left_btn)
    TextView mLeft;
    @BindView(R.id.mid_btn)
    TextView mCenter;
    @BindView(R.id.right_btn)
    TextView mRight;
    @BindView(R.id.ed_search)
    SearchEditText mSearch;
    @BindView(R.id.xl_searchresult)
    XListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);

        ButterKnife.bind(this);
        RongIM.init(this);
        initView();

    }

    private void initView() {

        mLeft.setText("返回");
        mCenter.setText("添加");
        mRight.setText("");

        mListView.setPullLoadEnable(false);
        mListView.setPullRefreshEnable(true);

        mListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                FriendsList(mSearch.getText().toString().trim());
            }

            @Override
            public void onLoadMore() {

            }
        });

        mSearchResult = new ArrayList<FriendsList>();

        mSearch.setHint("用户ID/昵称/手机号");
        mSearch.setSearchListener(new SearchEditText.onSearchListener() {
            @Override
            public void onSearch(String text) {

                mAdapter = new FriendsListAdapter(AddFriendsActivity.this,mSearchResult,R.layout.item_search_friends_result);
                FriendsList(text);
                mListView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                mListView.setOnItemClickListener(AddFriendsActivity.this);
            }
        });


    }

    private void FriendsList(String id) {

        showLoadingDialog();
        RxVolley.get(Constants.ADD_FRIENDS + "id=" + id, new HttpCallback() {
            @Override
            public void onFailure(VolleyError error) {
                super.onFailure(error);
                dismissLoadDialog();
                Log.i("TTT", "onFailure: " + error);
            }

            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);

                mListView.stopRefresh();
                dismissLoadDialog();

                if (mSearchResult.size() > 0) {
                    mSearchResult.clear();
                }

                mCenter.setText("用户");
                Toast.makeText(AddFriendsActivity.this, "点击了搜索,并且搜索内容是：" + t.toString(), Toast.LENGTH_SHORT).show();

                Gson gson = new Gson();
                FriendsList friendsInfo = gson.fromJson(t, FriendsList.class);
                mSearchResult.add(friendsInfo);
                mAdapter.notifyDataSetChanged();

            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        FriendsList friendsInfo = mSearchResult.get(i - 1);

        SharedPreferences sp = getSharedPreferences("addfriends",0);
        sp.edit().putString("username",friendsInfo.getUserName()).commit();
        sp.edit().putString("userid",friendsInfo.getId()).commit();
        sp.edit().putString("img",friendsInfo.getPortrait()).commit();

        Toast.makeText(AddFriendsActivity.this, "已经添加为好友 " + friendsInfo.getId(), Toast.LENGTH_SHORT).show();

    }
}
