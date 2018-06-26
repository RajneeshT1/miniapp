package com.acadview.miniaap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBClass extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "acadview";
    private static final int DATABASE_version = 1;

    public DBClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE USER(Name text,Password text,Number text,Type text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USER");
        onCreate(db);

    }

    public boolean onAddData(String name, String password, String number, String type) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("Email", name);
        contentValues.put("Password", password);
        contentValues.put("Phone", number);
        contentValues.put("Type", type);

        long status = db.insert("USER", null, contentValues);
        contentValues.clear();
        return (status > -1);


    }

    public Cursor getData(){
        SQLiteDatabase db = getReadableDatabase();

        //select * from table
        Cursor cursor = db.query("USER", null,null,null,null,null,null);
        return cursor;
    }

    //delete data
    public void onDelete(String name){
        SQLiteDatabase db = getWritableDatabase();

        String[] arr = null;

        arr[0] = name;
        db.delete("USER","Email = ?",arr);

    }
}

