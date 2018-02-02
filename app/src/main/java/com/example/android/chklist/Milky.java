package com.example.android.chklist;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Milky extends AppCompatActivity {

    private SQLiteDatabase db;

    item[] items = {
            new item("Blue Cheese", R.drawable.blue_cheese_icon),
            new item("Brie Cheese", R.drawable.briecheese_icon),
            new item("Chocolate", R.drawable.chocolate_icon),
            new item("Chocolate Cake", R.drawable.chocolatecake_icon),
            new item("Ice Cream", R.drawable.ice_cream_icon),
            new item("Milk", R.drawable.milk_icon),
            new item("Swiss", R.drawable.swisscheese_icon),
            new item("Butter", R.drawable.butter_icon)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBhelper dbHelper = new DBhelper(this);
        db = dbHelper.getWritableDatabase();

        final ListView lv = (ListView)findViewById(R.id.lv);
        MyAdapter adapter = new MyAdapter(this, items, R.color.dairy_Background);
        lv.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(item i : items){
                    //Log.d("TAG", i.isChecked() + "");
                    if(i.isChecked()) {
                        insertData(i.getName());
                        Toast.makeText(Milky.this, "Items saved in Final List", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Milky.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private void insertData(String name) {
        DBhelper dbHelper = new DBhelper(this);
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBhelper.COL_NAME, name);
        db.insert(DBhelper.TABLE_NAME, null, values);
        db.close();
    }
}
