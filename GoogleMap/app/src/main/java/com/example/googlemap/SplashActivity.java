package com.example.googlemap;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.eftimoff.androipathview.PathView;
import android.content.SharedPreferences;



public class SplashActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences.Editor editor = getSharedPreferences("UserData", MODE_PRIVATE).edit();
        editor.putString("yxwu@uw.edu", "password");
        editor.commit();

        final PathView pathView = findViewById(R.id.pathView);
        pathView.getPathAnimator()
                .delay(1200)
                .duration(1200)
                .interpolator(new AccelerateDecelerateInterpolator())
                .start();

        pathView.useNaturalColors();
        pathView.setFillAfter(true);
        //mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Intent i;
        //if (mSharedPreferences.getString(USER_TOKEN, null) != null) {
         //i = MenuActivity.getStartIntent(SplashActivity.this);//password
        i = LoginActivity.getStartIntent(SplashActivity.this);//password
        //} else {
          //  i = LoginActivity.getStartIntent(SplashActivity.this);
        //}

        new Handler().postDelayed(() -> {
            startActivity(i);
            finish();
        }, 2500);//prototype 减少到 100



    }
}
