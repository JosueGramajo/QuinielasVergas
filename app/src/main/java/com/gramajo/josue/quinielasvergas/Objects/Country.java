package com.gramajo.josue.quinielasvergas.Objects;

import android.media.Image;

import com.gramajo.josue.quinielasvergas.R;

/**
 * Created by josuegramajo on 6/14/18.
 */

public class Country {
    private String name;
    private String flag;

    public Country(String name, String flag) {
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
