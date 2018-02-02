package com.example.android.chklist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dangx on 8/9/2017.
 */

public class DBhelper extends SQLiteOpenHelper {

    public DBhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private static final String DB_NAME = "database";
    private static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "final_list";
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "food_name";
    public static final String COL_PIC = "food_pic";

    private static final String CREATE_TABLE_COMMAND = "CREATE TABLE " + TABLE_NAME + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NAME + " TEXT, " +
            COL_PIC + " INTEGER)";

    public DBhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data =  db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public boolean isEmpty(){
        SQLiteDatabase db = this.getReadableDatabase();
        boolean empty = false;
        Cursor c = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);
        if (c != null && c.moveToFirst()) {
            empty = (c.getInt (0) == 0);
        }
        c.close();
        return empty;
    }

    private int deleteItem(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "_id = ?", new String[]{String.valueOf(id)});
    }

}