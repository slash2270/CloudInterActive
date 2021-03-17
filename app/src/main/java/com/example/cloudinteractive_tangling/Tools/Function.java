package com.example.cloudinteractive_tangling.Tools;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cloudinteractive_tangling.Adapter.ContentAdapter;
import com.example.cloudinteractive_tangling.Data.ContentItem;

import java.util.ArrayList;

public class Function {

    public static final String TAG = "取得Log";

    public View getView(View view, Context context, int intResource){

        view = LayoutInflater.from(context).inflate(intResource, null);
        return view;

    }

    public RelativeLayout getRelativeLayout(View view, Context context, int intResource, RelativeLayout relativeLayout){

        relativeLayout = view.findViewById(intResource);
        return relativeLayout;

    }

    public TextView getTextView(View view, Context context, int intResource, TextView textView){

        textView = view.findViewById(intResource);
        return textView;

    }

    public ImageView getImageView(View view, Context context, int intResource, ImageView imageView){

        imageView = view.findViewById(intResource);
        return imageView;

    }

    public void setField(TextView tvId, TextView tvTitle, TextView tvUrl, ImageView ivColor,String strId, String strTitle, String strThumUrl, String strThumColor){ // 不想重複寫

        tvId.setText(strId);
        tvTitle.setText(strTitle);
        tvUrl.setText(strThumUrl);
        ivColor.setBackgroundColor(Color.parseColor(strThumColor));  // <---ImageView bug???

    }

     public String getBackgroudColor(String color,String url){

             color = "#" + url.replace("https://via.placeholder.com/150/", "").trim();

             if (color.length() == 6) { //顏色補字元

                 color = color + "0";

             } else if (color.length() == 5) {

                 color = color + "0" + "0";

             } else if (color.length() == 4) {

                 color = color + "0" + "0" + "0";

             }

             return color;

     }

     public String getUrl(String url){

         url = url.replace("https://via.placeholder.com/", "").trim();

         StringBuffer sbThumUrl = new StringBuffer(url);

         url = sbThumUrl.replace(3, 10, "").toString().trim();
         url = url + " x " + url;

         return url;

     }

    public Integer getGvItemPosition(SharedPreferences sP, int gvPosition, GridView gridView){

        if(sP.getInt("gvPosition", 0) > 0) {

            gvPosition = sP.getInt("gvPosition", 0);

            gridView.setSelection(gvPosition);

        //        Log.d(TAG,"取值 gvPosition +" + gvPosition);

        }

        return gvPosition;

    }

    public void setGvAdapter(ContentAdapter contentAdapter, Activity activity, Context context, ArrayList<ContentItem> arrayList, GridView gridView){

        contentAdapter = new ContentAdapter(activity, context, arrayList);

        gridView.setAdapter(contentAdapter);

    }

}
