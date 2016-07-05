package com.example.es.attendance;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void signup(View view) {
        EditText emailtxt = (EditText)findViewById(R.id.editText5);
        String email = emailtxt.getText().toString();
        EditText nametxt = (EditText)findViewById(R.id.editText);
        String name = nametxt.getText().toString();
        EditText passwordtxt = (EditText)findViewById(R.id.editText2);
        String password = passwordtxt.getText().toString();

        SQLiteDatabase sqliteDatabase = openOrCreateDatabase("UsersDB",MODE_PRIVATE,null);
        String select = "Select * from USERS";
        Cursor cursor = sqliteDatabase.rawQuery(select,null);
        if (cursor.getCount()>0) {
            cursor.moveToFirst();
            do {
                if (email.equals(cursor.getString(2)))
                    Toast.makeText(Main2Activity.this, "Account already exist!", Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        }

        if (name.isEmpty() || email.isEmpty() || password.isEmpty())
            Toast.makeText(Main2Activity.this, "Required fields missing!", Toast.LENGTH_SHORT).show();

        else
        {
            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("UsersDB",MODE_PRIVATE,null);
            String create = "CREATE TABLE IF NOT EXISTS USERS (ID INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR, Email VARCHAR, Password Varchar);";
            sqLiteDatabase.execSQL(create);

            String insert = "INSERT INTO USERS(Name,Email,Password) values('"+name+"','"+email+"','"+password+"');";
            sqLiteDatabase.execSQL(insert);

            finish();
        }
            
    }
}
