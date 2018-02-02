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

public class Fruity extends AppCompatActivity {

    private SQLiteDatabase db;

    item[] items = {
            new item("Apple", R.drawable.apple_icon),
            new item("Banana", R.drawable.banana_icon),
            new item("Cherries", R.drawable.cherries_icon),
            new item("Grapes", R.drawable.grapes_icon),
            new item("Kiwi", R.drawable.kiwi_icon),
            new item("Mango", R.drawable.mango_icon),
            new item("Orange", R.drawable.orange_icon),
            new item("Pear", R.drawable.pear_icon),
            new item("Pineapple", R.drawable.pineapple_icon),
            new item("Watermelon", R.drawable.watermelon_icon),
            new item("Strawberry", R.drawable.strawberry_icon),
            new item("Raspberry", R.drawable.raspberry_icon),
            new item("Blueberry", R.drawable.blueberry_icon)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBhelper dbHelper = new DBhelper(this);
        db = dbHelper.getWritableDatabase();

        final ListView lv = (ListView)findViewById(R.id.lv);
        MyAdapter adapter = new MyAdapter(this, items, R.color.fruit_Background);
        lv.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (item i : items) {
                        //Log.d("TAG", i.isChecked() + "");
                        if (i.isChecked()) {
                            insertData(i.getName());
                            Toast.makeText(Fruity.this, "Items saved in Final List", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Fruity.this, MainActivity.class);
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
