package com.example.android.chklist;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import static android.R.attr.resource;

/**
 * Created by dangx on 8/7/2017.
 */

public class MyAdapter extends ArrayAdapter<item> {

    private Context context;
    private item[] items;
    private int mColorResourceId;

    public MyAdapter(@NonNull Context context, item[] items, int colorResourceId) {
        super(context, R.layout.list_item, items);
        this.context = context;
        this.items = items;
        this.mColorResourceId = colorResourceId;
    }

    class ViewHolder{
        ImageView pic;
        TextView tv;
        CheckBox cb;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
           ViewHolder holder = null;
        if(convertView == null){
                holder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
                holder.pic = (ImageView)convertView.findViewById(R.id.pic);
                holder.tv = (TextView)convertView.findViewById(R.id.name);
                holder.cb = (CheckBox) convertView.findViewById(R.id.cb);
                convertView.setTag(holder);
                }else {
                holder = (ViewHolder) convertView.getTag();
                }
                holder.pic.setImageResource(items[position].getPic());
                holder.tv.setText(items[position].getName());
                holder.cb.setChecked(items[position].isChecked());

        View textContainer = convertView.findViewById(R.id.shopping_item);
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        textContainer.setBackgroundColor(color);

        final ViewHolder finalHolder = holder;
        holder.cb.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        items[position].setChecked(finalHolder.cb.isChecked());
      //  Log.d("TAG", finalHolder.cb.isChecked() + "");
        }
        });
        return convertView;
        }
        }
