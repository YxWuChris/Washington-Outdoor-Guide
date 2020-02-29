package com.example.googlemap;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class HikingCampingActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camping);


    }

    public void toCityStory(View v){
        Intent intent = new Intent(this, HikingRecom.class);
        startActivity(intent);
    }



    public void toCityMap(View v){
        Intent intent = new Intent(this, MapsActivityCamping.class);
        startActivity(intent);
    }



}
