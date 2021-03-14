package com.example.cloudinteractive_tangling.View;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cloudinteractive_tangling.R;

import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity{

    private Intent intent;
    private Bundle bundle;
    private RelativeLayout rltShow;
    private TextView tvId,tvTitle,tvThumUrl;
    private ImageView ivShow;
    private String id,title,thumUrl,thumColor;
    private static final String TAG = "取得Log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_activity);

        initView();

    }

    private void initView(){

        tvId = findViewById(R.id.tvId);
        tvTitle = findViewById(R.id.tvTitle);
        tvThumUrl = findViewById(R.id.tvthumUrl);
        ivShow = findViewById(R.id.ivShow);
        rltShow = findViewById(R.id.rltShow);

        intent = getIntent();

        bundle = intent.getExtras();

        Log.d(TAG,"取得Bundle ="+ bundle.toString().trim());

        if(bundle != null){

            id = bundle.getString("idKey");
            title = bundle.getString("titleKey");
            thumUrl = bundle.getString("thumUrlKey");
            thumColor = bundle.getString("thumColorKey");

            tvId.setText(id);
            tvTitle.setText(title);
            tvThumUrl.setText(thumUrl);
            ivShow.setBackgroundColor(Color.parseColor(thumColor));

            rltShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    intent = new Intent(ShowActivity.this, ContentActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();

                }
            });

        }

    }

}