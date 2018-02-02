package com.example.android.chklist;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

public class Bready extends AppCompatActivity {

    private SQLiteDatabase db;
    private ListView lv;
    private int scrolledPos = 0;

    item[] items = {
            new item("Baguette", R.drawable.baguette_icon),
            new item("Brown Bread", R.drawable.browbread_icon),
            new item("White Bread", R.drawable.whitebread_icon),
            new item("Croissant", R.drawable.croissant),
            new item("Cookies", R.drawable.cookies_icon),
            new item("Pie", R.drawable.pie_icon),
            new item("Pretzel", R.drawable.pretzel_icon),
            new item("Waffle", R.drawable.waffle_icon),
            new item("Jelly Beans", R.drawable.jellybean_icon)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBhelper dbHelper = new DBhelper(this);
        db = dbHelper.getWritableDatabase();

        lv = (ListView)findViewById(R.id.lv);
        MyAdapter adapter = new MyAdapter(this, items, R.color.bread_Background);
        lv.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(item i : items){
                    if(i.isChecked()) {
                        insertData(i.getName());
                        Toast.makeText(Bready.this, "Items saved in Final List", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Bready.this, MainActivity.class);
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


