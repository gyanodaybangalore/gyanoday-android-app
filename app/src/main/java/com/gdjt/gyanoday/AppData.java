package com.gdjt.gyanoday;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.gdjt.gyanoday.beans.HomeSliderBean;
import com.gdjt.gyanoday.beans.DataBean;
import com.gdjt.gyanoday.beans.NiyamBean;
import com.gdjt.gyanoday.beans.NotificationBean;
import com.gdjt.gyanoday.beans.TempleBean;
import com.gdjt.gyanoday.beans.TrustDataBean;
import com.gdjt.gyanoday.utils.StorageUtils;


public class AppData {
    public static AppData smAppData;

    public static  AppData getInstance(){
        if(smAppData == null){
            smAppData = new AppData();
        }
        return smAppData;
    }

    private DbHelper mDBHelper;


    private ArrayList<DataBean> mJinvaniList;
    private ArrayList<DataBean> mTirthList;
    private ArrayList<TempleBean> mBangaloreTempleList;


    public boolean isAppFirstTime(Context aContext){
        return !(StorageUtils.getPrefForBool(aContext, Constant.SHARED_PREF_LAUNCH_FIRST_TIME));
    }

    public void firstTimeLaunchComplete(Context aContext){
        StorageUtils.putPref(aContext, Constant.SHARED_PREF_LAUNCH_FIRST_TIME, true);
    }

    public ArrayList<HomeSliderBean> getHomeSliderList(){
        ArrayList<HomeSliderBean> list = new ArrayList<>();
        HomeSliderBean b1 = new HomeSliderBean("", "http://www.vidyasagar.net/wp-content/gallery/e0a49ce0a588e0a4a8-e0a4a7e0a4b0e0a58de0a4ae/dharma2.jpg");
        HomeSliderBean b2 = new HomeSliderBean("", "http://www.vidyasagar.net/wp-content/gallery/e0a49ce0a588e0a4a8-e0a4a7e0a4b0e0a58de0a4ae/dharma10.jpg");
        HomeSliderBean b3 = new HomeSliderBean("", "http://www.vidyasagar.net/wp-content/gallery/e0a4ace0a4bee0a4b9e0a581e0a4ace0a4b2e0a580-e0a4a6e0a4b0e0a58de0a4b6e0a4a8/bahubali5.jpg");
        HomeSliderBean b4 = new HomeSliderBean("", "http://www.vidyasagar.net/wp-content/gallery/e0a495e0a581e0a4a3e0a58de0a4a1e0a4b2e0a4aae0a581e0a4b0/kundalpur1.jpg");
        HomeSliderBean b5 = new HomeSliderBean("", "https://i.imgur.com/9OEKm5T.png");

        list.add(b1);
        list.add(b2);
        list.add(b3);
        list.add(b4);
        list.add(b1);
        list.add(b5);

        return list;
    }


    private String getJsonFromAsset(Context aContext, String aFileName){
        String str = null;
        InputStream is = null;
        try {
            is = aContext.getAssets().open(aFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            str = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }


        return str;
    }

    private void loadTirthList(Context aContext) {
        String jsonStr = getJsonFromAsset(aContext, Constant.FILE_TIRTH_LIST_NAME);
        if (!jsonStr.equals("")) {
            Gson gson = new Gson();
            mTirthList = gson.fromJson(jsonStr, new TypeToken<List<DataBean>>() {
            }.getType());
        }
    }

    private void loadBangaloreTemple(Context aContext) {
        String jsonStr = getJsonFromAsset(aContext, Constant.FILE_BAN_TIRTH_NAME);
        if (!jsonStr.equals("")) {
            Gson gson = new Gson();
            mBangaloreTempleList = gson.fromJson(jsonStr, new TypeToken<List<TempleBean>>() {
            }.getType());
        }
    }

    private void loadJinvaniList(Context aContext){
        String jsonStr = getJsonFromAsset(aContext, Constant.FILE_JINVANI_NAME);
        if(!jsonStr.equals("")){
            Gson gson = new Gson();
            mJinvaniList = gson.fromJson(jsonStr, new TypeToken<List<DataBean>>(){}.getType());
        }
    }

    public ArrayList<DataBean> getDataList(Context aContext){
        ArrayList<DataBean> list = new ArrayList<>();
        loadJinvaniList(aContext);
        if(mJinvaniList != null){
            list = mJinvaniList;
        }
        return list;
    }

    public ArrayList<DataBean> getTirthList(Context aContext){
        ArrayList<DataBean> list = new ArrayList<>();
        loadTirthList(aContext);
        if(mTirthList != null){
            list = mTirthList;
        }
        return list;
    }

    public ArrayList<TempleBean> getBangaloreTemple(Context aContext){
        ArrayList<TempleBean> list = new ArrayList<>();
        loadBangaloreTemple(aContext);
        if(mBangaloreTempleList != null){
            list = mBangaloreTempleList;
        }
        return list;
    }

    public ArrayList<DataBean> getMoreOptionList(Context aContext){
        ArrayList<DataBean> list = new ArrayList<>();
        String jsonStr = getJsonFromAsset(aContext, Constant.FILE_MORE_NAME);
        if(!jsonStr.equals("")) {
            Gson gson = new Gson();
            list = gson.fromJson(jsonStr, new TypeToken<List<DataBean>>(){}.getType());
        }
        return list;
    }

    public ArrayList<TrustDataBean> getTrustDataList(Context aContext){
        ArrayList<TrustDataBean> list = null;
        String jsonStr = getJsonFromAsset(aContext, Constant.FILE_TRUST_NAME);
        if(!jsonStr.equals("")){
            Gson gson = new Gson();
            list = gson.fromJson(jsonStr, new TypeToken<List<TrustDataBean>>(){}.getType());
        }

        return list;
    }

    public void loadDatabase(Context aContext){
        try {
            if(mDBHelper == null) {
                mDBHelper = new DbHelper(aContext);
                mDBHelper.createDataBase();
            }
        }catch (Exception e){
            System.out.println("DB create Error");
        }
    }

    public ArrayList<DataBean> getDenikPujaSangrah(Context aContext){
        loadDatabase(aContext);
        return mDBHelper.getDenikPujaList(DbHelper.TABLE_DENIK);
    }


    public ArrayList<DataBean> getOtherPujaSangrah(Context aContext){
        loadDatabase(aContext);
        return mDBHelper.getDenikPujaList(DbHelper.TABLE_OTHER);
    }

    public ArrayList<DataBean> getAartiData(Context aContext){
        loadDatabase(aContext);
        return mDBHelper.getDenikPujaList(DbHelper.TABLE_AARTI);
    }

    public ArrayList<DataBean> getBhajanData(Context aContext){
        loadDatabase(aContext);
        return mDBHelper.getDenikPujaList(DbHelper.TABLE_BHAJAN);
    }

    public ArrayList<DataBean> getBhaktiData(Context aContext){
        loadDatabase(aContext);
        return mDBHelper.getDenikPujaList(DbHelper.TABLE_BHAKTI);
    }

    public ArrayList<DataBean> getStrotaData(Context aContext){
        loadDatabase(aContext);
        return mDBHelper.getDenikPujaList(DbHelper.TABLE_STROTA);
    }

    public ArrayList<DataBean> getStutiData(Context aContext){
        loadDatabase(aContext);
        return mDBHelper.getDenikPujaList(DbHelper.TABLE_STUTI);
    }
    public ArrayList<DataBean> getChalisaData(Context aContext){
        loadDatabase(aContext);
        return mDBHelper.getDenikPujaList(DbHelper.TABLE_CHALISA);
    }

    public ArrayList<NiyamBean> getChildNiyamData(Context aContext){
        loadDatabase(aContext);
        return mDBHelper.getChildNiyamList();
    }


    public ArrayList<NiyamBean> getAdultNiyamData(Context aContext){
        loadDatabase(aContext);
        return mDBHelper.getAdultNiyamList();
    }

    ////-------------DUMMY DATA PATTERN------------


    public ArrayList<NotificationBean> getNotificationData() {
        ArrayList<NotificationBean> list = new ArrayList<>();
        list.add(new NotificationBean("This is a normal News for all viewers",
                "what_the_html<b>What</b> <i>the</i> <u>Html</u>",
                "",
                "http://www.vidyasagar.net/wp-content/gallery/e0a49ce0a588e0a4a8-e0a4a7e0a4b0e0a58de0a4ae/dharma2.jpg",
                "10-jun-2018","Note"));



        return list;
    }
}
