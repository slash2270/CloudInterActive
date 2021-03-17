package com.example.cloudinteractive_tangling.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cloudinteractive_tangling.R;

import java.util.Set;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Intent intent;
    private Button btnStart;
    private SharedPreferences sP;
    private SharedPreferences .Editor spEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        initView();

        mainClickcontent();

    }

    private void initView() {

        sP = this.getSharedPreferences("gvScroll", MODE_PRIVATE);

        spEditor = sP.edit();
        spEditor.clear().apply();

        btnStart = findViewById(R.id.btnStart);

    }

    private void mainClickcontent(){

        btnStart.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        intent = new Intent(this,ContentActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        spEditor.clear().apply();

    }
}