package com.gdjt.gyanoday.beans;


public class HomeSliderBean {

    private String mName;
    private String mPath;

    public HomeSliderBean(String aName, String aPath) {
        this.mName = aName;
        this.mPath = aPath;
    }

    public String getPath() {
        return mPath;
    }

    public String getName() {
        return mName;
    }
}
