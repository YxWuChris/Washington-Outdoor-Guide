package com.example.googlemap;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CityStory extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_story);
        final TextView tvCityHistory = (TextView) findViewById(R.id.cityHistory);
        final TextView tvCityFact = (TextView) findViewById(R.id.cityFact);
        SharedPreferences pref = getSharedPreferences("cityData", MODE_PRIVATE);
        String history = pref.getString("seattleHistory", "");
        String fact = pref.getString("seattleFacts", "");
        Log.i("MainActivity", "name is " + fact);
        tvCityHistory.setText(history);
        tvCityFact.setText(fact);

    }
}
