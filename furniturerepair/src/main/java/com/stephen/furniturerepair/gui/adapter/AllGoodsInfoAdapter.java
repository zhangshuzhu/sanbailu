package com.stephen.furniturerepair.gui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.stephen.furniturerepair.gui.beans.AllGoodsBean;

import java.util.List;


/**
 * Created by Stephen on 2016/4/4 0004.
 * Emial: 895745843@qq.com
 */
public class AllGoodsInfoAdapter extends BaseAdapter{

    private List<AllGoodsBean> list;

    public AllGoodsInfoAdapter(List<AllGoodsBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {

        return (list == null) ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return (list == null) ? null : list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public List<AllGoodsBean> getList() {
        return list;
    }

    public void setList(List<AllGoodsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
