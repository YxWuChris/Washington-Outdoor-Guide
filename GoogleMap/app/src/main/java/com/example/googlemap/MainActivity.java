package com.example.googlemap;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private SearchView search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        search = (SearchView) findViewById(R.id.search);
//        search.setBackgroundResource(R.drawable.searchview_rounded);
    }

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    public void toSnowActivity(View v) {
      Intent intent = new Intent(this, SnowActivity.class);
        startActivity(intent);
    }


    public void toHikingCampingActivity(View v){
        Intent intent = new Intent(this, HikingCampingActivity.class);
        startActivity(intent);
    }

    public void toWaterActivity(View v){
        Intent intent = new Intent(this, WaterActivity.class);
        startActivity(intent);
    }

    public void toFlightActivity(View v){
        Intent intent = new Intent(this, FlightActivity.class);
        startActivity(intent);
    }
}
