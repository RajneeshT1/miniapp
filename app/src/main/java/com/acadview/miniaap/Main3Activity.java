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

    ListView listview;
    DBClass dbClass;
    ArrayList<String> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        listview = findViewById(R.id.listview);
        dbClass = new DBClass(this);

        getAllUser();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String data = arrayList.get(position);
                String[] arr = data.split("\n");
                String name = arr[0];
                //delete
                dbClass.onDelete(name);
                getAllUser();

            }
        });
    }

    private void getAllUser() {

        Cursor cursor = dbClass.getData();
        final ArrayList<String> arrayList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("Name"));

                String password = cursor.getString(cursor.getColumnIndex("Password"));
                String number = cursor.getString(cursor.getColumnIndex("Number"));
                String type = cursor.getString(cursor.getColumnIndex("Type"));

                String totData = name + "\n" + password + "\n" + number + "\n" + type;
                arrayList.add(totData);

            } while (cursor.moveToNext());

            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
            listview.setAdapter(arrayAdapter);


        }


    }

}

