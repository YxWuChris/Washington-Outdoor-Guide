package com.example.googlemap;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class DataBaseAcess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DataBaseAcess instance;
    Cursor c = null;

    private DataBaseAcess(Context context){
        this.openHelper = new ActivityDBHelper(context);
    }
    public static DataBaseAcess getInstance(Context context){
        if(instance == null){
            instance = new DataBaseAcess(context);
        }
        return  instance;
    }

    public void open(){
        this.db = openHelper.getWritableDatabase();
    }
    public void close(){
        if(db != null){
            this.db.close();
        }
    }
    public List<MarkerInfo> setMarkers(String table){
       List<MarkerInfo> markerList = new ArrayList();
        c = db.rawQuery("select * from "+table,null);
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()) {
            MarkerInfo markerInfo;
//            String id = c.getString(0);
            String name = c.getString(1);
            String type = c.getString(2);
            String website = c.getString(4);
            String category = c.getString(5);
            String location = c.getString(3);
            byte[] photo=c.getBlob(6);
            String[] loc = location.split(",");
            float lat = Float.parseFloat(loc[0]);
            float longti = Float.parseFloat(loc[1]);
            markerInfo = new MarkerInfo(name,type,lat,longti,category,website,photo);
            markerList.add(markerInfo);
        }
        return markerList;
    }
    public byte[] searchImageFromDB(String table,String name){
        byte[] photo = null;
        String vg = name;
        c = db.rawQuery("select image from "+table+" where name = '"+vg+"'",null);
        while(c.moveToNext()) {
            photo= c.getBlob(0);
        }
        return photo;

    }
        public String searchWebsiteFromDB(String table,String name){
        String a ="could't find";
        String vg = name;
        c = db.rawQuery("select website from "+table+" where name = '"+vg+"'",null);
            while(c.moveToNext()) {
                a= c.getString(0);
            }

        return a;

    }

}
