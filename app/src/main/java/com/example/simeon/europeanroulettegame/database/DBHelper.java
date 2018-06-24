package com.example.simeon.europeanroulettegame.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class DBHelper  extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "roulette.db";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table numbers " +
                        "(id integer primary key autoincrement, number integer)"
        );
        db.execSQL(
                "create table users"+
                        "(id integer primary key autoincrement,name text,balance integer)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS numbers");
        onCreate(db);
    }


    public void addNumber(Integer number){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("number",number);
        db.insert("numbers",null,contentValues);
    }

   public void createUser(String name){
       SQLiteDatabase db = this.getWritableDatabase();
       ContentValues contentValues = new ContentValues();
       contentValues.put("name",name);
       contentValues.put("balance",1000);
       db.insert("users",null,contentValues);
   }





    public LinkedHashMap<String,String> getNumbers(){
        SQLiteDatabase db = this.getReadableDatabase();
        LinkedHashMap<String,String> numbers = new LinkedHashMap<>();
        Cursor cursor = db.rawQuery("Select * from numbers",null);
        if (cursor.moveToFirst()) {
            do {
                String id =  cursor.getString(cursor.getColumnIndex("id"));
                String number = cursor.getString(cursor.getColumnIndex("number"));
              numbers.put(id,number);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return numbers;
    }




    public String getUsername(String name){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("users",
                new String[]{"balance"},
                "name = ?",
                new String[]{name},
                null,
                null,
                null);

        String temp ="";
        if(cursor.moveToFirst()){
            temp = cursor.getString(cursor.getColumnIndex("balance"));
        }
        cursor.close();
        return temp;
    }

}
