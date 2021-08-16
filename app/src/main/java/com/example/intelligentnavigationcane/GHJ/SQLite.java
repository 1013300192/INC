package com.example.intelligentnavigationcane.GHJ;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLite extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    public SQLite(Context context) {
        super(context, "db_test", null, 1);
        db = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS user(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }
    public void add(String name,String password ){
        db.execSQL("INSERT INTO user(name,password)VALUES(?,?)",new Object[]{name,password});
    }
    public ArrayList<User> getAllDATA(){
        ArrayList<User> list = new ArrayList<User>();
        Cursor cursor = db.query("user",null,null,null,null,null,"name DESC");
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            list.add(new User(name,password));
        }
        return list;


    }
}