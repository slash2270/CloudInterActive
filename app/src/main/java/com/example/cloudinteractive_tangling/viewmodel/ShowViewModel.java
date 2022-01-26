package com.example.cloudinteractive_tangling.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.lifecycle.ViewModel;

import com.example.cloudinteractive_tangling.databinding.ShowActivityBinding;

public class ShowViewModel extends ViewModel{

    public String strId, strTitle, strUrl, strColor;

    public void getData(ShowActivityBinding binding, Activity activity){

        Intent intent = activity.getIntent();

        Bundle bundle = intent.getExtras();

        if(bundle != null){

            strId = bundle.getString("idKey");
            strTitle = bundle.getString("titleKey");
            strUrl = bundle.getString("urlKey");
            strColor = bundle.getString("colorKey");

            binding.setId(strId);
            binding.setTitle(strTitle);
            binding.setUrl(strUrl);
            binding.rltColor.setBackgroundColor(Color.parseColor(strColor));

        }

    }

    public void setBtn(ShowActivityBinding binding, Activity activity){

        binding.rltColor.setOnClickListener(v -> activity.finish());

    }

}
