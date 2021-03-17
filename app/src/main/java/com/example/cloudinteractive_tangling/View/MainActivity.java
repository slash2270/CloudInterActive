package com.example.cloudinteractive_tangling.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cloudinteractive_tangling.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Intent intent;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        initView();

        mainClickcontent();

    }

    private void initView() {


        btnStart = findViewById(R.id.btnStart);

    }

    private void mainClickcontent(){

        btnStart.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        intent = new Intent(this,ContentActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

}