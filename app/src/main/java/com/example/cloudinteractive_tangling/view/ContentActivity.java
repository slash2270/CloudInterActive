package com.example.cloudinteractive_tangling.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;

import com.example.cloudinteractive_tangling.R;
import com.example.cloudinteractive_tangling.viewmodel.ContentViewModel;
import com.example.cloudinteractive_tangling.databinding.ContentActivityBinding;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class ContentActivity extends AppCompatActivity {

    private ContentActivityBinding binding;
    private ContentViewModel viewModel;
    private SharedPreferences.Editor spEditor;
    private SharedPreferences sP;
    private Handler handler;
    private Runnable runData, runTouch, runScroll, runItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.content_activity);

        handler = new Handler();

        sP = getSharedPreferences("gvScroll", MODE_PRIVATE);

        spEditor = sP.edit();

        viewModel = new ContentViewModel();

        viewModel.initView(getApplicationContext());

        new Thread(() -> { // work

            Looper.prepare();

            viewModel.getData(getApplicationContext(), binding, handler, runData);

            viewModel.setTouchClick(binding, handler, runTouch);

            viewModel.gvScrollChange(binding, handler, runScroll);

            viewModel.onItemClick(binding, this, handler, runItem);

            Looper.loop();

        }).start();

        binding.setModel(viewModel);

    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(runData);
        handler.removeCallbacks(runTouch);
        handler.removeCallbacks(runScroll);
        handler.removeCallbacks(runItem);
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {

            if(event.getRepeatCount() == 0){

                if(binding.gvContent.getFirstVisiblePosition() != 0){

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

                    return false;

                }

            }

        }

        spEditor.clear().apply();

        return super.onKeyDown(keyCode, event);

    }

}