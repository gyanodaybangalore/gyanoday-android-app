package com.gdjt.gyanoday;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by psethi2 on 9/11/18.
 */

public class GyanodayApplication extends Application{
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
