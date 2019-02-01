package com.gdjt.gyanoday.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import com.gdjt.gyanoday.Constant;

public class TempleBean implements Parcelable {


    @SerializedName(Constant.JSON_TEMPLE_ID_KEY)
    private String mId;
    @SerializedName(Constant.JSON_TEMPLE_ENAME_KEY)
    private String mName;
    @SerializedName(Constant.JSON_TEMPLE_HNAME_KEY)
    private String mHName;
    @SerializedName(Constant.JSON_TEMPLE_ADDRESS_KEY)
    private String mAddress;
    @SerializedName(Constant.JSON_TEMPLE_CONTACT_KEY)
    private String mContact;
    @SerializedName(Constant.JSON_TEMPLE_LANDMARK_KEY)
    private String mLandmarkStr;
    @SerializedName(Constant.JSON_TEMPLE_STAY_KEY)
    private boolean mHaveStay;
    @SerializedName(Constant.JSON_TEMPLE_FOOD_KEY)
    private boolean mHaveFood;
    @SerializedName(Constant.JSON_TEMPLE_LAT_KEY)
    private String mLatitude;
    @SerializedName(Constant.JSON_TEMPLE_LON_KEY)
    private String mLongitude;

    protected TempleBean(Parcel in) {
        mId = in.readString();
        mName = in.readString();
        mHName = in.readString();
        mAddress = in.readString();
        mContact = in.readString();
        mLandmarkStr = in.readString();
        mHaveStay = in.readByte() != 0;
        mHaveFood = in.readByte() != 0;
        mLatitude = in.readString();
        mLongitude = in.readString();
    }

    public static final Creator<TempleBean> CREATOR = new Creator<TempleBean>() {
        @Override
        public TempleBean createFromParcel(Parcel in) {
            return new TempleBean(in);
        }

        @Override
        public TempleBean[] newArray(int size) {
            return new TempleBean[size];
        }
    };

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getHName() {
        return mHName;
    }

    public String getAddress() {
        return mAddress;
    }

    public String getContact() {
        return mContact;
    }

    public String getLandmarkStr() {
        return mLandmarkStr;
    }

    public boolean isHaveStay() {
        return mHaveStay;
    }

    public boolean isHaveFood() {
        return mHaveFood;
    }

    public String getLatitude() {
        return mLatitude;
    }

    public String getLongitude() {
        return mLongitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mName);
        dest.writeString(mHName);
        dest.writeString(mAddress);
        dest.writeString(mContact);
        dest.writeString(mLandmarkStr);
        dest.writeByte((byte) (mHaveStay ? 1 : 0));
        dest.writeByte((byte) (mHaveFood ? 1 : 0));
        dest.writeString(mLatitude);
        dest.writeString(mLongitude);
    }
}
