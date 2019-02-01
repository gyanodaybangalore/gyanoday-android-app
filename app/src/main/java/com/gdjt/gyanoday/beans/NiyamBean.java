package com.gdjt.gyanoday.beans;


public class NiyamBean {

    private int mId;

    private String mName;

    private int mTypeId;


    public NiyamBean(int aId, String aName, int aTypeId) {
        this.mId = aId;
        this.mName = aName;
        this.mTypeId = aTypeId;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public int getTypeId() {
        return mTypeId;
    }
}
