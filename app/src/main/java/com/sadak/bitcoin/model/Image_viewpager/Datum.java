
package com.sadak.bitcoin.model.Image_viewpager;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable
{

    @SerializedName("themePics")
    @Expose
    private String themePics;
    private final static long serialVersionUID = 6741972994466085690L;

    public String getThemePics() {
        return themePics;
    }

    public void setThemePics(String themePics) {
        this.themePics = themePics;
    }

}
