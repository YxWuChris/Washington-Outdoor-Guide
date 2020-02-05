package com.example.googlemap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

public class City1Activity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city1_activity);


    }

    public void toCityStory(View v){
        Intent intent = new Intent(this, CityStory.class);
        startActivity(intent);
    }

    public void toCityMap(View v){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }



}
