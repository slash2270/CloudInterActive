package com.example.cloudinteractive_tangling.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import com.example.cloudinteractive_tangling.BR;
import com.example.cloudinteractive_tangling.data.ContentBean;
import com.example.cloudinteractive_tangling.R;

import java.util.ArrayList;

import androidx.databinding.DataBindingUtil;

public class ContentAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<ContentBean> arrListContent;

    public ContentAdapter(Context context, ArrayList<ContentBean> arrListContent) {

        this.context = context;
        this.arrListContent = arrListContent;

    }

    @Override
    public int getCount() {
        return arrListContent == null ? 0 : arrListContent.size();
    }

    @Override
    public Object getItem(int position) {
        return arrListContent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        com.example.cloudinteractive_tangling.databinding.ContentItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.content_item, parent, false);

        convertView = binding.getRoot();

        int itemHeight = convertView.getResources().getDisplayMetrics().heightPixels / 8;
        int itemWidth = convertView.getResources().getDisplayMetrics().widthPixels / 4;

        AbsListView.LayoutParams param = new AbsListView.LayoutParams(itemWidth, itemHeight);
        convertView.setLayoutParams(param);

        convertView.setTag(binding);

    /*    if (convertView == null) {

            binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.content_item, parent, false);

            convertView = binding.getRoot();

            int itemHeight = convertView.getResources().getDisplayMetrics().heightPixels / 8;
            int itemWidth = convertView.getResources().getDisplayMetrics().widthPixels / 4;

            AbsListView.LayoutParams param = new AbsListView.LayoutParams(itemWidth, itemHeight);
            convertView.setLayoutParams(param);

            convertView.setTag(binding);

        } else {

            binding = (ContentItemBinding) convertView.getTag();

        }*/

        binding.rltColor.setBackgroundColor(Color.parseColor(arrListContent.get(position).getStrColor()));// 暫時用Layout來補
        binding.setVariable(BR.item, arrListContent.get(position));
        binding.executePendingBindings();
        //    notifyDataSetChanged();  重複刷新會造成卡頓

        return convertView;

    }

}

