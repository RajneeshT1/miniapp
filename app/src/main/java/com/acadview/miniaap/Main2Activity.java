package com.acadview.miniaap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

EditText email,password,phone,state,type;
Button button;
 private DBClass dbclass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        email = findViewById(R.id.email);
        password = findViewById(R.id.pas);
        phone = findViewById(R.id.ph);
        type = findViewById(R.id.type);
        button = findViewById(R.id.button);


        dbclass = new DBClass(this);

        dbclass.onAddData("email","password","phone","type");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                String phone1 = phone.getText().toString();
                String type1 = type.getText().toString();

                boolean isSuccessfull = dbclass.onAddData(email1,password1,phone1,type1);

                if(isSuccessfull){
                    Toast.makeText(Main2Activity.this,"Successful stored",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Main2Activity.this,Main3Activity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(Main2Activity.this,"omsuccessful",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
