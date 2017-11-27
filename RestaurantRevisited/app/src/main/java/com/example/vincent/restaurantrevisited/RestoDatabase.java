package com.example.vincent.restaurantrevisited;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vincent on 27-11-2017.
 */

public class RestoDatabase extends SQLiteOpenHelper {
    private static RestoDatabase instance;
    private static final String DBName = "orders.db";
    private static final int version = 1;
    private RestoDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public static RestoDatabase getInstance(Context context){
        if(instance == null){
            instance = new RestoDatabase(context.getApplicationContext(),DBName,null , version);
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table orders (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, price INTEGER, amount INTEGER, image TEXT);");


    }

    public void insert(String name, int price, int amount, String image) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("price", price);
        values.put("amount", amount);
        values.put("image", image);
        db.insert("orders",null,values);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + "todos");
        onCreate(db);
    }
    public Cursor selectAll(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =db.rawQuery("SELECT * FROM orders",null);

        return cursor;
    }
    public void update(long id, int completed){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("completed", completed);
        db.update("todos",values,"_id="+id, null);

    }

    public void delete(long id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("todos","_id="+id,null);

    }
}
