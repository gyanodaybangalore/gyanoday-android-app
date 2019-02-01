package com.gdjt.gyanoday.callbacks;

import com.gdjt.gyanoday.beans.TempleBean;

public interface ITempleRowCallback {
    public void onContactClick(String aContactStr);
    public void onDirectionClick(TempleBean aBean);
}
