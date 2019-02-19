package com.gdjt.gyanoday.beans;

import java.util.ArrayList;

public class PatraBean {
    public static int DISPLAY_TYPE_SINGLE = 1;
    public static int DISPLAY_TYPE_LIST = 2;

    private String mTitle;
    private String mName;
    private int mResId;
    private int mDisplayType;
    private ArrayList<String> mNameList;

    public String getTitle() {
        return mTitle;
    }

    public String getName() {
        return mName;
    }

    public int getResId() {
        return mResId;
    }

    public int getDisplayType() {
        return mDisplayType;
    }

    public ArrayList<String> getNameList() {
        return mNameList;
    }

    public PatraBean(String mTitle, String mName, int mResId, int mDisplayType, ArrayList<String> aNameList) {
        this.mTitle = mTitle;
        this.mName = mName;
        this.mResId = mResId;
        this.mDisplayType = mDisplayType;
        this.mNameList = aNameList;
    }




}