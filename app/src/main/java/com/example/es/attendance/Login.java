package com.example.es.attendance;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends AppCompatActivity {

        int f=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }


    public void login(View view) {

        EditText mailtxt = (EditText) findViewById(R.id.editText6);
        String mail = mailtxt.getText().toString();
        EditText nmetxt = (EditText) findViewById(R.id.editText3);
        String nme = nmetxt.getText().toString();
        EditText paswrdtxt = (EditText) findViewById(R.id.editText4);
        String paswrd = paswrdtxt.getText().toString();

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("UsersDB",MODE_PRIVATE,null);
        String select = "Select * from USERS";
        Cursor cursor = sqLiteDatabase.rawQuery(select,null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                if (mail.equals(cursor.getString(2)) && paswrd.equals(cursor.getString(3)))
                        f=1;
            }while (cursor.moveToNext());
            if (f==1){
                Toast.makeText(Login.this, "Welcome "+nme, Toast.LENGTH_SHORT).show();
                f=0;
                Intent intent = new Intent(this,Main3Activity.class);
               intent.putExtra("name",nme);
                startActivity(intent);
            }
            else if (nme.isEmpty() || mail.isEmpty() || paswrd.isEmpty())
                Toast.makeText(Login.this, "Required fields missing!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(Login.this, "Email Id or Password incorrect", Toast.LENGTH_SHORT).show();
        }
    }
}
