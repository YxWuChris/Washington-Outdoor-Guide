package com.example.googlemap;

import android.content.SharedPreferences;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PlanActivity extends AppCompatActivity {


    DBHelper myDb;
    private List<Spot> SpotList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.Recycler_View);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        SpotAdapter spotAdapter=new SpotAdapter(SpotList);
        recyclerView.setAdapter(spotAdapter);

        myDb = new DBHelper(this);
        Cursor res = myDb.getAllData();

        if(res.getCount() == 0) {

            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
           // buffer.append("Id :"+ res.getString(0)+"\n");
            String name = res.getString(1);
            buffer.append("Name :"+ res.getString(1)+"\n");
            Spot spot=new Spot(name);
            SpotList.add(spot);
        }

        if(res.getCount() == 0) {
            // show message
            System.out.println("Nothing found");
            return;
        }

    }




    public void deleteAll(View v){

        myDb.DeleteAll();


    }





}

