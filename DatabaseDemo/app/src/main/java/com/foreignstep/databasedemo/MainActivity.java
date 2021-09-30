package com.foreignstep.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS newUsers (name VARCHAR, age INT(4), id INTEGER PRIMARY KEY)");
//            database.execSQL("INSERT INTO newUsers (name, age) VALUES ('Pully', 39)");
//            database.execSQL("INSERT INTO newUsers (name, age) VALUES ('Hully', 40)");
//            database.execSQL("INSERT INTO newUsers (name, age) VALUES ('Mullly', 29)");

            database.execSQL("DELETE FROM newUsers WHERE id = 1");

            Cursor c = database.rawQuery("SELECT * FROM newUsers", null);
            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");
            int idIndex = c.getColumnIndex("id");
            c.moveToFirst();
            while (c != null) {
                Log.i("Name", c.getString(nameIndex));
                Log.i("Age", Integer.toString(c.getInt(ageIndex)));
                Log.i("ID", Integer.toString(c.getInt(idIndex)));
                c.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}