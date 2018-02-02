package com.example.android.chklist;

/**
 * Created by dangx on 8/7/2017.
 */

public class item {

    private int pic;
    private  String name;
    private boolean checked;

    public item(String name, int pic) {
        this.name = name;
        this.pic = pic;
    }

    public String getName(){
        return name;
    }

    public int getPic(){
        return pic;
    }

    public void setChecked(boolean checked){
        this.checked = checked;
    }

    public boolean isChecked(){
        return checked;
    }
}
