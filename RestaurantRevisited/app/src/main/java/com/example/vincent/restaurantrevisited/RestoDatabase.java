package com.example.vincent.restaurantrevisited;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Objects;

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
                "name TEXT, price INTEGER, amount INTEGER);");


    }

    public void insert(String name, int price, int amount) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM orders",null);
        int new_amount = 0;
        boolean result = true;

        while(cursor.moveToNext()){
            if(Objects.equals(cursor.getString(cursor.getColumnIndex("name")), name)){
                int old_amount = cursor.getInt(cursor.getColumnIndex("amount"));
                new_amount = old_amount + amount;
                update(cursor.getInt(cursor.getColumnIndex("_id")), new_amount );
                result=false;
            }
        }

        if(result) {
            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("price", price);
            values.put("amount", 1);
            db.insert("orders", null, values);
        }

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + "orders");
        onCreate(db);
    }
    public Cursor selectAll(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =db.rawQuery("SELECT * FROM orders",null);

        return cursor;
    }
    public void update(long id, int amount){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("amount", amount);
        db.update("orders",values,"_id="+id, null);

    }

    public void delete(long id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("orders","_id="+id,null);

    }
    public void Clear(){
        SQLiteDatabase db = getWritableDatabase();
        onUpgrade(db,1,2);
    }
}
