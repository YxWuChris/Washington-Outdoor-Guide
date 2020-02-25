package com.example.googlemap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MenuActivity.class);
        return intent;
    }

    public void toCityPage(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void toPlanPage(View v) {
        Intent intent = new Intent(this, PlanActivity.class);
        startActivity(intent);
    }
}
