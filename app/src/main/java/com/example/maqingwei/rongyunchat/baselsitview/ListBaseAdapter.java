package com.example.maqingwei.rongyunchat.baselsitview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by maqingwei
 * Date on 16/9/6 下午12:16
 *
 * @Description:    Listview GrideView通用的adapter
 *
 * 但此Aadapter只适用于 单种item(即加载相同的item)
 */
public abstract class ListBaseAdapter<T> extends BaseAdapter{

    private List<T> mDatas;
    private Context mContexts;
    private int  mLayoutId;

    public  ListBaseAdapter(Context context,List<T> data ,int layoutId){

        mContexts = context;
        mDatas = data;
        mLayoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        ListViewHolder listViewHolder = ListViewHolder.get(view, i, mLayoutId, viewGroup);

        convert(listViewHolder,mDatas.get(i),i);

        return listViewHolder.getConvertView();
    }

    public abstract  void  convert(ListViewHolder viewHolder , T date,int position);


}
