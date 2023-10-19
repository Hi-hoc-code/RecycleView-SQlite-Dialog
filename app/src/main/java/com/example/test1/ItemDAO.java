package com.example.test1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ItemDAO {
    DbHelper helper;
    SQLiteDatabase db;

    public ItemDAO(Context context) {
        helper = new DbHelper(context);
    }
    public ArrayList<Item> getAll(){
        ArrayList<Item> list= new ArrayList<>();
        String sql = "SELECT * FROM ITEM";
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Integer id = cursor.getInt(0);
            String name = cursor.getString(1);
            String email = cursor.getString(2);
            String sdt = cursor.getString(3);
            Item item = new Item(id, name, email, sdt);
            list.add(item);
            cursor.moveToNext();
        }
        db.close();
        return  list;
    }
    public boolean insert(Item item){
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",item.getName());
        values.put("email",item.getEmail());
        values.put("sdt",item.getSdt());
        long row = db.insert("ITEM",null,values);
        return  row !=-1;
    }
    public boolean delete(Integer index){
        db = helper.getWritableDatabase();
        int row = db.delete("ITEM","id=?",new String[]{String.valueOf(index)});
        return  row >0;
    }
    public boolean update(Item item){
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",item.getName());
        values.put("email",item.getEmail());
        values.put("sdt",item.getSdt());
        int row = db.update("ITEM",values,"id=?",new String[]{String.valueOf(item.getId())});
        return  row >0;
    }

}
