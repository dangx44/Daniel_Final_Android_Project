package com.example.android.chklist;

/**
 * Created by dangx on 8/20/2017.
 */

public class Selected {

    private int pic;
    private  String name;

    public Selected(String name, int pic) {
        this.name = name;
        this.pic = pic;
    }

    public String getName(){
        return name;
    }

    public int getPic(){
        return pic;
    }
}
