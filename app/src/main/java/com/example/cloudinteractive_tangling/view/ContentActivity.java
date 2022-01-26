package com.example.cloudinteractive_tangling.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.cloudinteractive_tangling.R;
import com.example.cloudinteractive_tangling.viewmodel.ContentViewModel;
import com.example.cloudinteractive_tangling.databinding.ContentActivityBinding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class ContentActivity extends AppCompatActivity {

    private SharedPreferences.Editor spEditor;
    private SharedPreferences sP;
    private ContentActivityBinding binding;
    private Set set;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.content_activity);

        sP();

        ContentViewModel viewModel = new ContentViewModel();

        set = new Set();
        set.thread();
        set.setViewModel(this, viewModel, getApplicationContext(), binding);

        binding.setModel(viewModel);

    }

    public void sP(){

        sP = getSharedPreferences("gvScroll", MODE_PRIVATE);
        spEditor = sP.edit();

    }

    @Override
    protected void onDestroy() {
        set.destroy();
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {

            if(event.getRepeatCount() == 0){

                if(binding.gvContent.getFirstVisiblePosition() != 0){

                    set.keyDown(binding, sP);

                    return false;

                }

            }

        }

        spEditor.clear().apply();

        return super.onKeyDown(keyCode, event);

    }

}