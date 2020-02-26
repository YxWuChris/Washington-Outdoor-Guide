package com.example.googlemap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class SkiingTips extends AppCompatActivity {
    private ImageView img,tip1,tip2,tip3,tip4,tip5,tip6,tip7;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skiing_tips);

        tip1= (ImageView) findViewById(R.id.skiing_tip1_image);
        tip1.setImageResource(R.drawable.skiing_tip1);

        tip2= (ImageView) findViewById(R.id.skiing_tip2_image);
        tip2.setImageResource(R.drawable.skiing_tip2);

        tip3= (ImageView) findViewById(R.id.skiing_tip3_image);
        tip3.setImageResource(R.drawable.skiing_tip3);

        tip4= (ImageView) findViewById(R.id.skiing_tip4_image);
        tip4.setImageResource(R.drawable.skiing_tip4);

        tip5= (ImageView) findViewById(R.id.skiing_tip5_image);
        tip5.setImageResource(R.drawable.skiing_tip5);

        tip6= (ImageView) findViewById(R.id.skiing_tip6_image);
        tip6.setImageResource(R.drawable.skiing_tip6);

        tip7= (ImageView) findViewById(R.id.skiing_tip7_image);
        tip7.setImageResource(R.drawable.skiing_tip7);


//
    }
}
