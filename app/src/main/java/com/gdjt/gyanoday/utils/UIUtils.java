package com.gdjt.gyanoday.utils;

import android.content.Context;
import android.widget.Toast;

public class UIUtils {

    public static void showToast(Context aContext, String aString){
        Toast.makeText(aContext, aString, Toast.LENGTH_SHORT).show();

    }
}
