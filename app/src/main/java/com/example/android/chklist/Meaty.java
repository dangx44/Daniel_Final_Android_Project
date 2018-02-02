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

public class Meaty extends AppCompatActivity {

    private SQLiteDatabase db;

    item[] items = {
            new item("Kuubana", R.drawable.kuubana_icon),
            new item("Bacon", R.drawable.bacon_icon),
            new item("Beef Burger", R.drawable.burger_icon),
            new item("Chicken", R.drawable.chicken_icon),
            new item("Kebab", R.drawable.kebab_icon),
            new item("Salami", R.drawable.salami_icon),
            new item("Sausage", R.drawable.sausage_icon),
            new item("Steak", R.drawable.steak_icon),
            new item("Eggs", R.drawable.egg_icon),
            new item("Trout", R.drawable.trout_icon),
            new item("Salmon", R.drawable.salmon_icon)
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBhelper dbHelper = new DBhelper(this);
        db = dbHelper.getWritableDatabase();

        final ListView lv = (ListView)findViewById(R.id.lv);
        MyAdapter adapter = new MyAdapter(this, items, R.color.meat_Background);
        lv.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(item i : items){
                    //Log.d("TAG", i.isChecked() + "");
                    if(i.isChecked()) {
                        insertData(i.getName());
                        Toast.makeText(Meaty.this, "Items saved in Final List", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Meaty.this, MainActivity.class);
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
