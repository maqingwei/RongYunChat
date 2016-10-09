package com.example.maqingwei.rongyunchat.baselsitview;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by maqingwei
 * Date on 16/9/6 上午11:13
 *
 * @Description:   ListView,GrideView通用的ViewHolder
 */
public class ListViewHolder {

    private final SparseArray<View> mViews;//用于存放viewholder
    private final View mConvertView;
    private Context mContext;

    public ListViewHolder(ViewGroup parent,int position,int layoutId){

        mContext = parent.getContext();
        this.mViews = new SparseArray<View>();
        this.mConvertView = LayoutInflater.from(mContext).inflate(layoutId,parent,false);
        mConvertView.setTag(this);
    }

    public static ListViewHolder get(View convertView,int position,int layoutId,ViewGroup parent){
        if (convertView == null){
            return  new ListViewHolder(parent,position,layoutId);
        }
        return (ListViewHolder) convertView.getTag();
    }

    public <T extends View> T getView (int id){

        View view = mViews.get(id);
        if (view == null ){
            view = mConvertView.findViewById(id);
            mViews.put(id,view);
        }
        return (T) view;
    }

    //设置文本
    public  void  setText(int id,String s){
       TextView textView =  getView(id);
        textView.setText(s);
    }

    //设置图片 这里使用Glide框架加载
    public  void  setImage (int id , String  url){
        ImageView img = getView(id);
        Glide.with(mContext).load(url).asBitmap().fitCenter().into(img);
    }
    //获取convertview对象
    public View getConvertView(){
        return mConvertView;
    }

}
