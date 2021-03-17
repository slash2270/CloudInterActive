package com.example.cloudinteractive_tangling.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cloudinteractive_tangling.R;
import com.example.cloudinteractive_tangling.Tools.Function;

import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity implements View.OnClickListener{

    private Function function;
    private Intent intent;
    private Bundle bundle;
    private RelativeLayout rltColor;
    private TextView tvId,tvTitle,tvUrl;
    private ImageView ivColor;
    private String strId,strTitle,strThumUrl,strThumColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_activity);

        initView();

        getData();

        showClickcontent();

    }

    private void initView(){

        function = new Function();

        rltColor = findViewById(R.id.rltColor);
        tvId = findViewById(R.id.tvId);
        tvTitle = findViewById(R.id.tvTitle);
        tvUrl = findViewById(R.id.tvUrl);
        ivColor = findViewById(R.id.ivColor);

    }

    private void getData(){

        intent = getIntent();

        bundle = intent.getExtras();

    //    Log.d(function.TAG,"取得Bundle ="+ bundle.toString().trim());

        if(bundle != null){

            strId = bundle.getString("idKey");
            strTitle = bundle.getString("titleKey");
            strThumUrl = bundle.getString("urlKey");
            strThumColor = bundle.getString("colorKey");

            function.setField(tvId, tvTitle, tvUrl, ivColor, strId, strTitle, strThumUrl, strThumColor);

        }

    }

    private void showClickcontent(){

        rltColor.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        intent = new Intent(this, ContentActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            finish();

        }

        return super.onKeyDown(keyCode, event);
    }

}