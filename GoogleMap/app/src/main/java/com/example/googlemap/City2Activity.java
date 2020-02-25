package com.example.googlemap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class City2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city2_activity);


    }

    public void toCityStory(View v){
        Intent intent = new Intent(this, CityStory.class);
        startActivity(intent);
    }

    public void toCityMap(View v){
        Intent intent = new Intent(this, MapsActivity_Camping.class);
        startActivity(intent);
    }


}
