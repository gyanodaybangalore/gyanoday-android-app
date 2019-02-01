package com.gdjt.gyanoday.utils;

import android.content.Context;
import android.net.ConnectivityManager;


public class NetworkUtils {

    public static boolean isNetworkConnected(Context aContext){
        ConnectivityManager conMgr = (ConnectivityManager) aContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean internet = false;
        if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected()) {
            internet = true;

        }
        return internet;
    }
}
