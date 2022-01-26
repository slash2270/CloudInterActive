package com.example.cloudinteractive_tangling.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.cloudinteractive_tangling.R;
import com.example.cloudinteractive_tangling.viewmodel.MainViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class MainActivity extends AppCompatActivity{

    private SharedPreferences .Editor spEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.cloudinteractive_tangling.databinding.MainActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.main_activity);

        initView();

        MainViewModel viewModel = new MainViewModel();

        viewModel.setTvBtn(binding);

        viewModel.mainClickcontent(binding, this);

        binding.setModel(viewModel);

    }

    private void initView() {

        SharedPreferences sP = this.getSharedPreferences("gvScroll", MODE_PRIVATE);

        spEditor = sP.edit();
        spEditor.clear().apply();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        spEditor.clear().apply();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        System.exit(0);

        return super.onKeyDown(keyCode, event);

    }

}