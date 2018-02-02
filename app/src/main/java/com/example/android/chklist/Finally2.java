package com.example.android.chklist;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

import static android.R.attr.id;
import static android.R.attr.order;
import static android.R.id.list;
import static com.example.android.chklist.DBhelper.COL_ID;
import static com.example.android.chklist.DBhelper.COL_NAME;
import static com.example.android.chklist.DBhelper.TABLE_NAME;
import static com.example.android.chklist.R.drawable.send;
import static com.example.android.chklist.R.layout.selected_item;

public class Finally2 extends AppCompatActivity {

    private SQLiteDatabase db;
    ArrayList<String> selected;
    Button send_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_list);

        db = openOrCreateDatabase("database", MODE_PRIVATE , null);
        final ListView list = (ListView)findViewById(R.id.food_list);
        selected = new ArrayList<>();

            final String[] arr = query();
            for (String item : arr) {
                selected.add(item);
            }

        final ArrayAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, selected);
        list.setAdapter(listAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {

                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(Finally2.this);
                View mView = getLayoutInflater().inflate(R.layout.delete_item, null);
                TextView mtext = (TextView)mView.findViewById(R.id.delete_text);
                Button noBtn = (Button)mView.findViewById(R.id.delete_no);
                Button yesBtn = (Button)mView.findViewById(R.id.delete_yes);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                noBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Finally2.this, "Not Deleted", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                yesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selected.remove(position);
                        listAdapter.notifyDataSetChanged();
                        Toast.makeText(Finally2.this, "Deleted", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }
        });

        send_btn = (Button)findViewById(R.id.send);

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeEmail();
            }
        });
    }

    private String[] query() {
        Cursor cursor = db.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            String[] names = new String[cursor.getCount()];
            int counter = 0;
            do{
                names[counter++] = cursor.getString(cursor.getColumnIndex(COL_NAME));
                Log.d("TAG", String.valueOf(names));
            }
            while (cursor.moveToNext());
            return names;
        }
        return null;
    }

    public void composeEmail() {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Shopping List");
        StringBuilder sb = new StringBuilder();
        for (String s : selected) {
            sb.append(s);
            sb.append("\n");
        }
        intent.putExtra(Intent.EXTRA_TEXT, sb.toString());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
            finish();
        }
    }

    public void delete(int id){
        DBhelper dbHelper = new DBhelper(this);
        db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME, COL_ID + "=?", new String[] {String.valueOf(id)});
        db.close();
    }
}
