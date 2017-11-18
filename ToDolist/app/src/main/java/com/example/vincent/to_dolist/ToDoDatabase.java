package com.example.vincent.to_dolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ToDoDatabase extends SQLiteOpenHelper {
    private static ToDoDatabase instance;
    private static final String DBName = "toDos.db";
    private static final int version = 1;
    private ToDoDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public static ToDoDatabase getInstance(Context context){
        if(instance == null){
            instance = new ToDoDatabase(context.getApplicationContext(),DBName,null , version);
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table todos (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                               "title TEXT, completed INTEGER);");
        ContentValues values = new ContentValues();
        values.put("title","Do the dishes");
        values.put("completed",0);
        values.put("title","Do the laundry");
        values.put("completed",0);
        values.put("title","Get food for tonight");
        values.put("completed",0);
        SQLiteDatabase db =this.getWritableDatabase();
        db.insert("todos",null,values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "todos");
        onCreate(sqLiteDatabase);
    }
}
