package com.gdjt.gyanoday.beans;


import com.google.gson.annotations.SerializedName;

import com.gdjt.gyanoday.Constant;

public class TrustDataBean {

    @SerializedName(Constant.JSON_TRUST_ID_KEY)
    private int mId;
    @SerializedName(Constant.JSON_TRUST_NAME_KEY)
    private String mName;
    @SerializedName(Constant.JSON_TRUST_WORK_KEY)
    private String mWork;
    @SerializedName(Constant.JSON_TRUST_IMG_TYPE_KEY)
    private int mImageType;
    @SerializedName(Constant.JSON_TRUST_IMG_PATH_KEY)
    private String mImgPath;
    @SerializedName(Constant.JSON_TRUST_VIEW_TYPE_KEY)
    private int mViewType;

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getWork() {
        return mWork;
    }

    public int getImageType() {
        return mImageType;
    }

    public String getImgPath() {
        return mImgPath;
    }

    public int getViewType() {
        return mViewType;
    }
}
