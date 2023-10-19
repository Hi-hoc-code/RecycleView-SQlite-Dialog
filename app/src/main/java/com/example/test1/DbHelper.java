package com.example.test1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper  {


    public DbHelper( Context context) {
        super(context, "ITEM", null, 6);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE  ITEM (id integer primary key autoincrement," +
                "name text," +
                "email text," +
                "sdt text)";
        db.execSQL(sql);
        sql = "INSERT INTO ITEM(name, email, sdt) VALUES ('Nguyen Van A','nguyena@gmail.com','098971236')";
        db.execSQL(sql);
        sql = "INSERT INTO ITEM(name, email, sdt) VALUES ('Nguyen Van B','nguyena@gmail.com','098971236')";
        db.execSQL(sql);
        sql = "INSERT INTO ITEM(name, email, sdt) VALUES ('Nguyen Van C','nguyena@gmail.com','098971236')";
        db.execSQL(sql);
        sql = "INSERT INTO ITEM(name, email, sdt) VALUES ('Nguyen Van D','nguyena@gmail.com','098971236')";
        db.execSQL(sql);
        sql = "INSERT INTO ITEM(name, email, sdt) VALUES ('Nguyen Van E','nguyena@gmail.com','098971236')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ITEM");
        onCreate(db);
    }
}
