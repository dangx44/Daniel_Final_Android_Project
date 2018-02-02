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

public class Veggie extends AppCompatActivity {

    private SQLiteDatabase db;

    item[] items = {
            new item("Bell Pepper", R.drawable.bell_pepper_icon),
            new item("Carrot", R.drawable.carrot_icon),
            new item("Cucumber", R.drawable.cucumber_icon),
            new item("Egg Plant", R.drawable.eggplant_icon),
            new item("Onion", R.drawable.onion_icon),
            new item("Potato", R.drawable.potato_icon),
            new item("Tomato", R.drawable.tomato_icon),
            new item("Broccoli", R.drawable.broccoli_icon),
            new item("Garlic", R.drawable.garlic_icon),
            new item("Artichoke", R.drawable.artichoke_icon),
            new item("Avocado", R.drawable.avocado_icon),
            new item("Cabage", R.drawable.cabage_icon),
            new item("Ginger", R.drawable.ginger_icon),
            new item("Peas", R.drawable.peas_icon)
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  DBhelper dbHelper = new DBhelper(this);
      //  db = dbHelper.getWritableDatabase();

        final ListView lv = (ListView)findViewById(R.id.lv);
        MyAdapter adapter = new MyAdapter(this, items, R.color.veggie_Background);
        lv.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(item i : items){
                    //Log.d("TAG", i.isChecked() + "");
                    if(i.isChecked()) {
                        insertData(i.getName());
                        Toast.makeText(Veggie.this, "Items saved in Final List", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Veggie.this, MainActivity.class);
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

