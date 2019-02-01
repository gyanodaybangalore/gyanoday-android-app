package com.gdjt.gyanoday.callbacks;

import com.gdjt.gyanoday.beans.DataBean;

public interface IRowClickCallback {
    public void onClick(int aPosition);
    public void onClick(DataBean aBean);
}
