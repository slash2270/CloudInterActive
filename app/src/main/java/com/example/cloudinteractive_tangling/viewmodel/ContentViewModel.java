package com.example.cloudinteractive_tangling.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.example.cloudinteractive_tangling.data.ContentBean;
import com.example.cloudinteractive_tangling.data.DataModel;
import com.example.cloudinteractive_tangling.tools.Function;
import com.example.cloudinteractive_tangling.view.ShowActivity;
import com.example.cloudinteractive_tangling.databinding.ContentActivityBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.lifecycle.ViewModel;

import static android.content.Context.MODE_PRIVATE;

public class ContentViewModel extends ViewModel implements DataModel.SetArrayList {

    private SharedPreferences.Editor spEditor;
    private SharedPreferences sP;
    private DataModel dataModel;
    private Function function;
    private Context context;
    private Intent intent;
    private Bundle bundle;
    private FloatingActionButton fabContent;
    private ArrayList<ContentBean> arrContent;
    private ArrayList<String> arrPosition;
    private String strId, strTitle, strUrl, strColor, newStrPosition, oldStrPosition;
    private int i, gvPosition;
    private Handler handler;
    private Runnable runnable;

    public void initView(Activity activity) {

        context = activity.getApplicationContext();

        dataModel = new DataModel();

        bundle = new Bundle();

        function = new Function();

        arrContent = new ArrayList<>();

        handler = new Handler();

        sP = context.getSharedPreferences("gvScroll", MODE_PRIVATE);

        spEditor = sP.edit();

    }

    public void getData(Activity activity, ContentActivityBinding binding){

        dataModel.getData(this, binding, context, activity);

    }

    public void setTouchClick(ContentActivityBinding binding){

        runnable = new Runnable() {
            @Override
            public void run() {

                binding.fabContent.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        function.scrollGvPosition(0, binding.gvContent);

                        return false;
                    }
                });

                binding.fabContent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        function.scrollGvPosition(0, binding.gvContent);

                    }
                });

            }

        };

        handler.post(runnable);

    }

    public void gvScrollChange(ContentActivityBinding binding) {

        runnable = new Runnable() {
            @Override
            public void run() {

                binding.gvContent.setOnScrollListener(new AbsListView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(AbsListView view, int scrollState) {

                        switch (scrollState) {

                            case 0:      //靜止

                                binding.fabContent.show();
                                //    function.fabShow(fabContent);

                                break;

                            case 1:      //手滑

                                binding.fabContent.hide();
                                //    function.fabHide(fabContent);

                                gvPosition = view.getFirstVisiblePosition() + 4;

                                newStrPosition = String.valueOf(gvPosition);

                                if(!sP.getString("gvPosition", "").equals("")){

                                    oldStrPosition = sP.getString("gvPosition", "");

                                    arrPosition = new ArrayList<>(Arrays.asList(oldStrPosition));

                                }else {

                                    arrPosition = new ArrayList<>();

                                }

                                arrPosition.add(newStrPosition);

                                newStrPosition = arrPosition.toString();

                                spEditor.putString("gvPosition", newStrPosition).apply();

                                arrPosition.clear();

                                break;

                            case 2:      //自動滑

                                break;

                        }

                    }

                    @Override
                    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            /*    if ((firstVisibleItem % 32) == 0 && firstVisibleItem > 31){ // 仿ig效果

                    gvContent.smoothScrollToPosition(firstVisibleItem);

                }*/

                    }

                });

                function.scrollGvPosition(gvPosition, binding.gvContent);

            }
        };

        handler.post(runnable);

    }

    @Override
    public ArrayList<ContentBean> getArrayList(ArrayList<ContentBean> arrayList) {

        arrContent = arrayList ;

        return arrContent;

    }

    public void onItemClick(ContentActivityBinding binding, Activity activity){

        runnable = new Runnable() {
            @Override
            public void run() {

                binding.gvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        strId = arrContent.get(position).getStrId();
                        strTitle = arrContent.get(position).getStrTitle();
                        strUrl = arrContent.get(position).getStrUrl();
                        strColor = arrContent.get(position).getStrColor();

                        intent = new Intent(activity, ShowActivity.class);

                        bundle.putString("idKey", strId);
                        bundle.putString("titleKey", strTitle);
                        bundle.putString("urlKey", strUrl);
                        bundle.putString("colorKey", strColor);

                        intent.putExtras(bundle);
                        activity.startActivity(intent);

                    }
                });

            }
        };

        handler.post(runnable);

    }

}