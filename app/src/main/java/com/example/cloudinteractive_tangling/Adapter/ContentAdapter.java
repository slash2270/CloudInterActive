package com.example.cloudinteractive_tangling.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cloudinteractive_tangling.Data.ContentItem;
import com.example.cloudinteractive_tangling.Data.ItemSwitch;
import com.example.cloudinteractive_tangling.R;
import com.example.cloudinteractive_tangling.View.ShowActivity;

import java.util.ArrayList;

public class ContentAdapter extends BaseAdapter {

    private Activity activity;
    private Context context;
    private ArrayList<ContentItem> arrListContent;
    private String id, title, thumUrl,thumColor;
    private TextView tvId,tvTitle,tvthumbnailUrl;
    private ImageView ivItem;
    private RelativeLayout rltItem;
    private int selectorPosition;
    private static final String TAG = "取得Log";

    public ContentAdapter(Activity activity, Context context, ArrayList<ContentItem> arrListContent) {
        this.activity = activity;
        this.context = context;
        this.arrListContent = arrListContent;
    }

    @Override
    public int getCount() {
        return arrListContent == null ? 0 : arrListContent.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.item_content, null);

        tvId = convertView.findViewById(R.id.tvId);
        tvTitle = convertView.findViewById(R.id.tvTitle);
        tvthumbnailUrl = convertView.findViewById(R.id.tvthumUrl);
        ivItem = convertView.findViewById(R.id.ivItem);
        rltItem = convertView.findViewById(R.id.rltItem);

        ContentItem contentItem = arrListContent.get(position);

        if (contentItem != null) {

                id = arrListContent.get(position).getTvId().trim();

                title = arrListContent.get(position).getTvTitle().trim();

                thumUrl = arrListContent.get(position).getTvthumbnailUrl().trim();
                thumColor = "#" + thumUrl.replace("https://via.placeholder.com/150/", "").trim();

                if(thumColor.length() == 6){ //顏色補字元

                    thumColor = thumColor + "0";

                }else if(thumColor.length() == 5){

                    thumColor = thumColor + "0" + "0";

                }

                thumUrl = thumUrl.replace("https://via.placeholder.com/", "").trim();
                StringBuffer sbThumUrl = new StringBuffer(thumUrl);
                thumUrl = sbThumUrl.replace(3, 10, "").toString().trim();
                thumUrl = thumUrl + " x " +thumUrl;

                Log.d(TAG, id);
                Log.d(TAG, thumColor);

                tvId.setText(id);
                tvTitle.setText(title);
                tvthumbnailUrl.setText(thumUrl);
                ivItem.setBackgroundColor(Color.parseColor(thumColor));// <---bug???
                rltItem.setBackgroundColor(Color.parseColor(thumColor));// 暫時用Layout來補

                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(activity, ShowActivity.class);
                        Bundle bundle = new Bundle();

                        bundle.putString("idKey", id);
                        bundle.putString("titleKey", title);
                        bundle.putString("thumUrlKey", thumUrl);
                        bundle.putString("thumColorKey", thumColor);

                        intent.putExtras(bundle);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        activity.startActivity(intent);

                    }
                });

            }

        ItemSwitch.setGvPosition(position);

    //    getRefresh(position);

        return convertView;

    }

    public void getRefresh(int i) { //刷新

        selectorPosition = i;

        notifyDataSetChanged();

    }

}
