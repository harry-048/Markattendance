package com.example.es.attendance;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class Main3Activity extends AppCompatActivity {

    String d;

    String name;

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);




        Button button = (Button) findViewById(R.id.button);
        final TextView textView = (TextView) findViewById(R.id.textView9);
        CalendarView calendar =(CalendarView)findViewById(R.id.calendarView);


        Long date = calendar.getDate();
       if (calendar.getDate() != date)
            date= calendar.getDate();

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int myear, int mmonth, int mday) {
                String year=Integer.toString(myear);
                String month=Integer.toString((mmonth+1));
                String day=Integer.toString(mday);
                 d=(""+day+"-"+month+"-"+year+"").toString();
                Toast.makeText(Main3Activity.this, "You select "+d, Toast.LENGTH_SHORT).show();

            }
        });


        Bundle bundle = getIntent().getExtras();
         name = bundle.getString("name");


    }


    public void present(View view) {



        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("UsersDB",MODE_PRIVATE,null);
        String create = "CREATE TABLE IF NOT EXISTS DTE (ID INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR, Date VARCGAR);";
        sqLiteDatabase.execSQL(create);

        String insert = "INSERT INTO DTE(Name,DATE) values('"+name+"','"+d+"');";
        sqLiteDatabase.execSQL(insert);

    }

    public void viewattendance(View view) {
        Intent i=new Intent(this,Viewattendance.class);
        i.putExtra("n",name);
        startActivity(i);
    }




}
