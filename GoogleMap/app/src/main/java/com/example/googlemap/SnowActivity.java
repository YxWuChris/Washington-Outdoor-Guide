package com.example.googlemap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.net.Uri;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

public class SnowActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snow);


    }

    public void toCityStory(View v){
        Intent intent = new Intent(this, SnowRecom.class);
        startActivity(intent);
    }


    public void toCityMap(View v){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }



}
