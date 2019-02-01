package com.gdjt.gyanoday.beans;

import com.google.gson.annotations.SerializedName;

import com.gdjt.gyanoday.Constant;

public class DataBean {

    @SerializedName(Constant.JSON_DATA_ID_KEY)
    private int mId;
    @SerializedName(Constant.JSON_DATA_HINDI_TEXT_KEY)
    private String mHinName;
    @SerializedName(Constant.JSON_DATA_ENGLISH_TEXT_KEY)
    private String mEngName;
    @SerializedName(Constant.JSON_DATA_IMAGE_PATH_KEY)
    private String mImgPath;
    @SerializedName(Constant.JSON_DATA_VAL_KEY)
    private String mTextValue;
    @SerializedName(Constant.JSON_DATA_IMAGE_TYPE_KEY)
    private int mType;

    @SerializedName(Constant.JSON_DATA_ACTION_KEY)
    private int mActionIndex;

    private int mPriorityIndex;

    public DataBean(int aId, String aEngName, String aHinName,int aPriorityIndex,  String aTextValue) {
        this.mId = aId;
        this.mHinName = aHinName;
        this.mEngName = aEngName;
        this.mTextValue = aTextValue;
        this.mPriorityIndex = aPriorityIndex;
    }

    public int getId() {
        return mId;
    }

    public String getHinName() {
        return mHinName;
    }

    public String getEngName() {
        return mEngName;
    }

    public String getImgPath() {
        return mImgPath;
    }

    public String getTextValue() {
        return mTextValue;
    }

    public int getType() {
        return mType;
    }

    public int getActionIndex() {
        return mActionIndex;
    }
}
