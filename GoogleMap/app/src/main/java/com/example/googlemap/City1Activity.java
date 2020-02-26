package com.example.googlemap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.net.Uri;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

public class City1Activity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city1_activity);


    }

    public void toCityStory(View v){
        Intent intent = new Intent(this, SkiingTips.class);
        startActivity(intent);
    }

    public void test(View v){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("http://www.baidu.com"));
        startActivity(intent);
    }

    public void toCityMap(View v){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }



}
