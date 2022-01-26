package com.example.cloudinteractive_tangling.view;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;

import com.example.cloudinteractive_tangling.databinding.ContentActivityBinding;
import com.example.cloudinteractive_tangling.viewmodel.ContentViewModel;

import java.util.ArrayList;
import java.util.Arrays;

public class Set {

    private Handler handler;
    private HandlerThread handlerThread;
    private Runnable runData, runTouch, runScroll, runItem;

    public void thread(){

        handlerThread = new HandlerThread("HandlerThread");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());

    }

    public void setViewModel(Activity activity, ContentViewModel viewModel, Context context, ContentActivityBinding binding){

        viewModel.initView(context);
        viewModel.getData(context, binding, handler, runData);
        viewModel.setTouchClick(binding, handler, runTouch);
        viewModel.gvScrollChange(binding, handler, runScroll);
        viewModel.onItemClick(binding, activity, handler, runItem);

    }

    public void destroy(){

        handlerThread.quit();
        handler.removeCallbacks(runData);
        handler.removeCallbacks(runTouch);
        handler.removeCallbacks(runScroll);
        handler.removeCallbacks(runItem);

    }

    public void keyDown(ContentActivityBinding binding, SharedPreferences sP){

        String oldStrPosition = sP.getString("gvPosition", "");

        ArrayList<String> arrPosition = new ArrayList<>(Arrays.asList(oldStrPosition.split(",")));

        if(arrPosition.size() <= 1){

            oldStrPosition = arrPosition.get(0);

        }else {

            oldStrPosition = arrPosition.get(arrPosition.size() - 2);

        }

        oldStrPosition = oldStrPosition.replace("[", "").replace("]", "").replace(",", "").trim();

        int gvPosition = Integer.parseInt(oldStrPosition);

        if(gvPosition == 4){

            binding.gvContent.smoothScrollToPosition(0, 0);

        }else {

            binding.gvContent.smoothScrollToPosition(gvPosition);

        }

    }

}