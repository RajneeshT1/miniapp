package com.acadview.miniaap;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    ListView Listview;
    DBClass dbClass;


    ArrayList<String> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Listview = findViewById(R.id.Listview);
        dbClass = new DBClass(this);

        getAllUser();

        Listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String data = arrayList.get(position);
                String[] arr = data.split("\n");
                String email = arr[0];
                //delete
                dbClass.onDelete(email);
                getAllUser();

            }
        });
    }

    private void getAllUser() {

        Cursor cursor = dbClass.getData();
        final ArrayList<String> arrayList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String email = cursor.getString(cursor.getColumnIndex("Email"));

                String password = cursor.getString(cursor.getColumnIndex("Password"));
                String phone = cursor.getString(cursor.getColumnIndex("Phone"));
                String type = cursor.getString(cursor.getColumnIndex("Type"));

                String totData = email + "\n" + password + "\n" + phone  + "\n" + type;
                arrayList.add(totData);

            } while (cursor.moveToNext());

            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
            Listview.setAdapter(arrayAdapter);


        }


    }

}

