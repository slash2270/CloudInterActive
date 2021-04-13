package com.example.cloudinteractive_tangling.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.cloudinteractive_tangling.R;
import com.example.cloudinteractive_tangling.tools.Function;
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
    private Function function;
    private int gvPosition;
    private String oldStrPosition;
    private ArrayList<String> arrPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.content_activity);

        function = new Function();

        sP = getSharedPreferences("gvScroll", MODE_PRIVATE);

        spEditor = sP.edit();

        viewModel = new ContentViewModel();

        viewModel.initView(this);

        viewModel.getData(this, binding);

        viewModel.setTouchClick(binding);

        viewModel.gvScrollChange(binding);

        viewModel.onItemClick(binding, this);

        binding.setModel(viewModel);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {

            if(event.getRepeatCount() == 0){

                if(binding.gvContent.getFirstVisiblePosition() == 0){

                }else{

                    oldStrPosition = sP.getString("gvPosition", "");

                    arrPosition = new ArrayList<String>(Arrays.asList(oldStrPosition.split(",")));

                    if(arrPosition.size() == 1){

                        oldStrPosition = arrPosition.get(arrPosition.size() - 1);

                    }else {

                        oldStrPosition = arrPosition.get(arrPosition.size() - 2);

                    }

                    oldStrPosition = oldStrPosition.replace("[", "").replace("]", "").replace(",", "").trim();

                    gvPosition = Integer.parseInt(oldStrPosition);

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