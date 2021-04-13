package com.example.cloudinteractive_tangling.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;

import com.example.cloudinteractive_tangling.data.DataModel;
import com.example.cloudinteractive_tangling.view.ContentActivity;
import com.example.cloudinteractive_tangling.databinding.MainActivityBinding;
import com.example.cloudinteractive_tangling.view.MainActivity;

import androidx.databinding.ObservableBoolean;

public class MainViewModel implements DataModel.addText{

    public ObservableBoolean ovfBtn = new ObservableBoolean(false);
    private DataModel dataModel = new DataModel();
    public String strTv, strBtn;

    public void setTvBtn(MainActivityBinding binding, Activity activity){

        dataModel.addText(this, activity);

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

        binding.btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity, ContentActivity.class);
                activity.startActivity(intent);

            }
        });

        ovfBtn.set(false);

    }

    @Override
    public String setTvt(String name) {

        strTv = name;

        return strTv;

    }

    @Override
    public String setBtn(String name) {

        strBtn = name;

        return strBtn;
    }

}