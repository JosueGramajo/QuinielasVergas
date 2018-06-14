package com.gramajo.josue.quinielasvergas.Objects;

import android.media.Image;

import com.gramajo.josue.quinielasvergas.R;

/**
 * Created by josuegramajo on 6/14/18.
 */

public class Country {
    private String name;
    private int flag;

    public Country(String name, int flag) {
        this.name = name;
        this.flag = flag;
    }

    public Country(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
