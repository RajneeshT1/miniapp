package com.acadview.miniaap;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.EditText;

public class Main4Activity extends AppCompatActivity {
    String email,pass,type,phone,Type,updt;
    EditText editText;
    Button update,delete;
    DBClass dbClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        editText = findViewById(R.id.editText);

        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);

       update.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Cursor cursor = dbClass.getData();

               while(cursor.moveToNext()){
                   email = cursor.getString(cursor.getColumnIndex("Email"));
                   pass = cursor.getString(cursor.getColumnIndex("Password"));
                   phone = cursor.getString(cursor.getColumnIndex("Phone"));
                   Type = cursor.getString(cursor.getColumnIndex("Type"));

                   updt = email+"\n"+pass+"\n"+phone+"\n"Type;
                   arrayList.add(update);
               }
           }
       });
    }
}
