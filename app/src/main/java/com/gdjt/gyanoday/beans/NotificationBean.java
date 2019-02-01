package com.gdjt.gyanoday.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class NotificationBean implements Parcelable{

    private String mTitle;
    private String mMessage;
    private String mDetailMessage;
    private String mImageURL;
    private String mDateStr;
    private String mNoteStr;

    public NotificationBean(String aTitle, String aMessage, String aDetailMessage, String aImageURL, String aNoteStr, String aDateStr) {
        this.mTitle = aTitle;
        this.mMessage = aMessage;
        this.mDetailMessage = aDetailMessage;
        this.mImageURL = aImageURL;
        this.mDateStr = aDateStr;
        this.mNoteStr = aNoteStr;
    }

    protected NotificationBean(Parcel in) {
        mTitle = in.readString();
        mMessage = in.readString();
        mDetailMessage = in.readString();
        mImageURL = in.readString();
        mDateStr = in.readString();
        mNoteStr = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mMessage);
        dest.writeString(mDetailMessage);
        dest.writeString(mImageURL);
        dest.writeString(mDateStr);
        dest.writeString(mNoteStr);
    }

    public static final Creator<NotificationBean> CREATOR = new Creator<NotificationBean>() {
        @Override
        public NotificationBean createFromParcel(Parcel in) {
            return new NotificationBean(in);
        }

        @Override
        public NotificationBean[] newArray(int size) {
            return new NotificationBean[size];
        }
    };

    public String getTitle() {
        return mTitle;
    }

    public String getNotificationMessage() {
        return mMessage;
    }

    public String getNotificationDetailMessage() {
        return mDetailMessage;
    }

    public String getImageURL() {
        return mImageURL;
    }

    public String getDateStr() {
        return mDateStr;
    }

    public String getNoteStr() {
        return mNoteStr;
    }

    @Override
    public int describeContents() {
        return 0;
    }


}
