package com.example.googlemap;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class ActivityDBHelper extends SQLiteAssetHelper {
    public static final String DATABASE_NAME = "activity_data.db";
    public static final String TABLE_NAME = "WATER";
    public static final String COL_1 = "name";
    public static final String COL_2 = "type";
    public static final String COL_3 = "location";
    public static final String COL_4 = "category";
    public static final String COL_5 = "website";
    public static final String COL_6 = "image";


    public ActivityDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY, NAME TEXT unique, TYPE TEXT)");
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
//        onCreate(db);
//    }
//
//    public boolean insertData(String name, String type) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_2,name);
//        contentValues.put(COL_3,type);
//        long result = db.insertWithOnConflict(TABLE_NAME,null ,contentValues , SQLiteDatabase.CONFLICT_REPLACE);
//        if(result == -1)
//            return false;
//        else
//            return true;
//    }
//
//    public Cursor getAllData() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
//        return res;
//    }
//
//
//    public Integer deleteData (String id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
//    }
//
//    public void DeleteAll(){
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("DELETE FROM student_table");
//        db.close();
//
//    }


}