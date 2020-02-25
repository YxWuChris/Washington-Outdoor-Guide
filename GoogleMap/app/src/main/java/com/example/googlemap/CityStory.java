package com.example.googlemap;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CityStory extends AppCompatActivity {

    private ImageView img,tip1,tip2,tip3,tip4,tip5,tip6,tip7,tip8,tip9,tip10;





    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_story);
        // load the image files
        tip1= (ImageView) findViewById(R.id.camping_tip1_image);
        tip1.setImageResource(R.drawable.camping_tip1);

        tip2= (ImageView) findViewById(R.id.camping_tip2_image);
        tip2.setImageResource(R.drawable.camping_tip2);

        tip3= (ImageView) findViewById(R.id.camping_tip3_image);
        tip3.setImageResource(R.drawable.camping_tip3);
//
        tip4= (ImageView) findViewById(R.id.camping_tip4_image);
        tip4.setImageResource(R.drawable.camping_tip4);

        tip5= (ImageView) findViewById(R.id.camping_tip5_image);
        tip5.setImageResource(R.drawable.camping_tip5);

//        tip6= (ImageView) findViewById(R.id.camping_tip6_image);
//        tip6.setImageResource(R.drawable.camping_tip6);

        tip7= (ImageView) findViewById(R.id.camping_tip7_image);
        tip7.setImageResource(R.drawable.camping_tip7);

        tip8= (ImageView) findViewById(R.id.camping_tip8_image);
        tip8.setImageResource(R.drawable.camping_tip8);

        tip9= (ImageView) findViewById(R.id.camping_tip9_image);
        tip9.setImageResource(R.drawable.camping_tip9);

        tip10= (ImageView) findViewById(R.id.camping_tip10_image);
        tip10.setImageResource(R.drawable.camping_tip10);
//
//        img= (ImageView) findViewById(R.id.safety);
//        img.setImageResource(R.drawable.crystal_mountain);



    }
}
