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

        db.execSQL("CREATE TABLE USER(Email text,Password text,Phone text,Type text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USER");
        onCreate(db);

    }

    public boolean onAddData(String email, String password, String phone, String type) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("Email",email );
        contentValues.put("Password", password);
        contentValues.put("Phone", phone);
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
    public Cursor Login(String email,String password){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select Email,Password,Type,Phone FROM USER WHERE Name=? AND Password=?",new String[]{
                email,password
        });
        return cursor;
    }
   // update data
      boolean Update(String email,String oldpassword,String newpassword){
        int update;
        SQLiteDatabase db = getWritableDatabase();
          ContentValues contentValues = new ContentValues();
          contentValues.put("Email",email );
          contentValues.put("Password", newpassword);
          String arr[] = {"",""};
          arr[0] = email;
          arr[1] =  oldpassword;
          update = db.update("USER",contentValues,"Email=? and password=?",arr);
          return (update>-1);
      }



    }


