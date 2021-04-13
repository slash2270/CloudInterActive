package com.example.cloudinteractive_tangling.tools;

import android.app.Activity;
import android.content.Context;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.example.cloudinteractive_tangling.adapter.ContentAdapter;
import com.example.cloudinteractive_tangling.data.ContentBean;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Function {

     public String getBackgroudColor(String color, String url){

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

    public Integer scrollGvPosition(int gvPosition, GridView gridView){

        gridView.smoothScrollToPosition(gvPosition);

        return gvPosition;

    }

    public void setGvAdapter(ContentAdapter contentAdapter, Activity activity, Context context, ArrayList<ContentBean> arrayList, GridView gridView){

        contentAdapter = new ContentAdapter(activity, context, arrayList);

        gridView.setAdapter(contentAdapter);

        contentAdapter.notifyDataSetChanged();

    }

    public void fabHide(FloatingActionButton fab) {

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) fab.getLayoutParams();

        fab.animate().translationY(fab.getHeight()+layoutParams.bottomMargin).setInterpolator(new AccelerateInterpolator(3));

    }

    public void fabShow(FloatingActionButton fab) {

        fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));

    }

}
