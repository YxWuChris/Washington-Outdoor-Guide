package com.example.googlemap;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "TYPE";
    public static final String COL_4 = "NOTE";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY, NAME TEXT unique, TYPE TEXT, NOTE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,type);
        contentValues.put(COL_4,"");

        long result = db.insertWithOnConflict(TABLE_NAME,null ,contentValues , SQLiteDatabase.CONFLICT_REPLACE);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public void updateNote(String name, String input){
        ContentValues newValues = new ContentValues();
        newValues.put(COL_4, input);
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = new String[]{name};
        db.update(TABLE_NAME,newValues,"NAME=?",args);

    }


    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "NAME = ?",new String[] {id});
    }

    public void DeleteAll(){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM student_table");
        db.close();

    }



}