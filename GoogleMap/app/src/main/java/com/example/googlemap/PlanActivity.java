package com.example.googlemap;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
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
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        myDb = new DBHelper(this);
        Cursor res = myDb.getAllData();

        if(res.getCount() == 0) {

            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
           // buffer.append("Id :"+ res.getString(0)+"\n");
            String name = res.getString(1);
            String type = res.getString(2);
            buffer.append("Name :"+ res.getString(1)+"\n");
            Spot spot=new Spot(name,type);
            SpotList.add(spot);
        }

        if(res.getCount() == 0) {
            // show message
            System.out.println("Nothing found");
            return;
        }

    }

    public void toNote(View v){
        Intent intent = new Intent(this, NoteActivity.class);
        startActivity(intent);
    }

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP) {

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            Toast.makeText(PlanActivity.this, "on Move", Toast.LENGTH_SHORT).show();
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
            Toast.makeText(PlanActivity.this, "on Swiped ", Toast.LENGTH_SHORT).show();
            //Remove swiped item from list and notify the RecyclerView
            int position = viewHolder.getAdapterPosition();
            SpotList.remove(position);
            //spotAdapter.notifyDataSetChanged();

        }
    };




    public void deleteAll(View v){

        myDb.DeleteAll();


    }





}

