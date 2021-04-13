package com.example.cloudinteractive_tangling.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.lifecycle.ViewModel;

import com.example.cloudinteractive_tangling.databinding.ShowActivityBinding;

public class ShowViewModel extends ViewModel{

    private Runnable runnable;
    private Intent intent;
    private Bundle bundle;
    public String strId, strTitle, strUrl, strColor;

    public void getData(ShowActivityBinding binding, Activity activity){

        intent = activity.getIntent();

        bundle = intent.getExtras();

        if(bundle != null){

            runnable = new Runnable() {
                @Override
                public void run() {

                    strId = bundle.getString("idKey");
                    strTitle = bundle.getString("titleKey");
                    strUrl = bundle.getString("urlKey");
                    strColor = bundle.getString("colorKey");

                    binding.setId(strId);
                    binding.setTitle(strTitle);
                    binding.setUrl(strUrl);
                    binding.rltColor.setBackgroundColor(Color.parseColor(strColor));

                }
            };

        }

        activity.runOnUiThread(runnable);

    }

    public void setBtn(ShowActivityBinding binding, Activity activity){

        Runnable runnable = () -> binding.rltColor.setOnClickListener(v -> activity.finish());

        new Thread(runnable).start();

    }

}
