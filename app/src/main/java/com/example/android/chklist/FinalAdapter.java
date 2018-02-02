package com.example.android.chklist;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dangx on 8/20/2017.
 */

public class FinalAdapter extends ArrayAdapter<Selected> {

    private Context context;
    private ArrayList<Selected> selected;

    public FinalAdapter(@NonNull Context context, ArrayList<Selected> selected) {
        super(context, R.layout.selected_item, selected);
        this.context = context;
        this.selected = selected;
    }

    private class ViewHolder{
     //   ImageView pic;
        TextView tv;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        FinalAdapter.ViewHolder holder = null;
        if(convertView == null){
            holder = new FinalAdapter.ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.selected_item, parent, false);
        //    holder.pic = (ImageView)convertView.findViewById(R.id.pic);
            holder.tv = (TextView)convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        }else {
            holder = (FinalAdapter.ViewHolder) convertView.getTag();
        }
      //  holder.pic.setImageResource(selected.get(position).getPic());
        holder.tv.setText(selected.get(position).getName());

        return convertView;
    }
}
