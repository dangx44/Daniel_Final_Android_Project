package com.example.android.chklist;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private CircleMenu circleMenu;
    private static int LIST_TIMEOUT = 1200;
    private Button finalListBtn, newListBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle);

        DBhelper dbHelper = new DBhelper(this);
        db = dbHelper.getWritableDatabase();

        newListBtn = (Button) findViewById(R.id.new_list);
        finalListBtn = (Button) findViewById(R.id.final_list);
        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);

        circleMenu.setMainMenu(Color.parseColor("#263238"), R.drawable.shopping_cart, R.drawable.shopping_cart)
                .addSubMenu(Color.parseColor("#ab47bc"), R.drawable.veggie)
                .addSubMenu(Color.parseColor("#ff5722"), R.drawable.fruit)
                .addSubMenu(Color.parseColor("#00b0ff"), R.drawable.milk)
                .addSubMenu(Color.parseColor("#ff4081"), R.drawable.meat)
                .addSubMenu(Color.parseColor("#1b5e20"), R.drawable.whitebread_icon)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int i) {
                        switch (i){
                            case 0:
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(MainActivity.this, Veggie.class);
                                        startActivity(intent);
                                    }
                                }, LIST_TIMEOUT);
                                break;

                            case 1:
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(MainActivity.this, Fruity.class);
                                        startActivity(intent);
                                    }
                                }, LIST_TIMEOUT);
                                break;

                            case 2:
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(MainActivity.this, Milky.class);
                                        startActivity(intent);
                                    }
                                }, LIST_TIMEOUT);
                                break;

                            case 3:
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(MainActivity.this, Meaty.class);
                                        startActivity(intent);
                                    }
                                }, LIST_TIMEOUT);
                                break;

                            case 4:
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(MainActivity.this, Bready.class);
                                        startActivity(intent);
                                    }
                                }, LIST_TIMEOUT);
                                break;
                        }
                    }
                });

        finalListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isEmpty()) {
                    Toast.makeText(MainActivity.this, "Final List is Empty", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(MainActivity.this, Finally2.class);
                    startActivity(intent);
                }
            }
        });

        newListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
                Toast.makeText(MainActivity.this, "List Cleared", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void deleteData() {
        DBhelper dbHelper = new DBhelper(this);
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        /*db.delete( DBhelper.TABLE_NAME,
              DBhelper.COL_ID + "=?",
              new String[]{String.valueOf(id)});
        */
        db.execSQL("delete from "+ DBhelper.TABLE_NAME);
        db.delete(DBhelper.TABLE_NAME, null, null);
        db.close();
    }

    public boolean isEmpty(){
        DBhelper dbHelper = new DBhelper(this);
        db = dbHelper.getWritableDatabase();
        boolean empty = false;
        Cursor c = db.rawQuery("SELECT COUNT(*) FROM " + DBhelper.TABLE_NAME, null);
        if (c != null && c.moveToFirst()) {
            empty = (c.getInt (0) == 0);
        }
        c.close();
        return empty;
    }

    public void sum(View view){
        Log.d("TAG", "done");
    }
}
