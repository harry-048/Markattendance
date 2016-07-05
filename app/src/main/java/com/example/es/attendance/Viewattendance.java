package com.example.es.attendance;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Viewattendance extends AppCompatActivity {

    TextView textView;
    TextView textView1;
    String nm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewattendance);

        textView = (TextView) findViewById(R.id.textView7);
        textView1 = (TextView) findViewById(R.id.textView10);
        textView.setMovementMethod(new ScrollingMovementMethod());
        Bundle b = getIntent().getExtras();
         nm = b.getString("n");
        textView1.setText(nm);
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("UsersDB", MODE_PRIVATE, null);
        String select = "Select * from DTE";
        Cursor cursor = sqLiteDatabase.rawQuery(select, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                    if (nm.equals(cursor.getString(1)))
                              textView.setText(textView.getText() + "\n" + cursor.getString(2));
            } while (cursor.moveToNext());
        }
    }


}
