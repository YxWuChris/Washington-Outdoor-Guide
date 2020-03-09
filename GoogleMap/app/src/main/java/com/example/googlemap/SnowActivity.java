package com.example.googlemap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SnowActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snow);


    }
    public void toSnowActivity(View v){
        Intent intent = new Intent(this, MapActivitySnow.class);
        startActivity(intent);
    }
    public void toSnowSafetyPage(View v){
        Intent intent = new Intent(this, SnowRecom.class);
        startActivity(intent);
    }

}