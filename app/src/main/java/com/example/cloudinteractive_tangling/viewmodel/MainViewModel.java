package com.example.cloudinteractive_tangling.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;

import com.example.cloudinteractive_tangling.data.DataModel;
import com.example.cloudinteractive_tangling.view.ContentActivity;
import com.example.cloudinteractive_tangling.databinding.MainActivityBinding;

import androidx.databinding.ObservableBoolean;

public class MainViewModel implements DataModel.addText{

    public ObservableBoolean ovfBtn = new ObservableBoolean(false);
    public String strTv, strBtn;

    public void setTvBtn(MainActivityBinding binding){

        DataModel dataModel = new DataModel();
        dataModel.addText(this);

        binding.btnMain.setGravity(Gravity.CENTER);
        binding.btnMain.setTextSize(20);
        binding.btnMain.setTextColor(Color.parseColor("#000000"));
        binding.btnMain.getPaint().setFakeBoldText(true);

        binding.tvMain.setGravity(Gravity.CENTER);
        binding.tvMain.setTextSize(16);
        binding.tvMain.setTextColor(Color.parseColor("#000000"));
        binding.tvMain.getPaint().setFakeBoldText(true);

    }

    public void mainClickcontent(MainActivityBinding binding, Activity activity){

        ovfBtn.set(true);

        binding.btnMain.setOnClickListener(v -> {

            Intent intent = new Intent(activity, ContentActivity.class);
            activity.startActivity(intent);

        });

        ovfBtn.set(false);

    }

    @Override
    public void setTvt(String name) {

        strTv = name;

    }

    @Override
    public void setBtn(String name) {

        strBtn = name;

    }

}