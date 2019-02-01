package com.gdjt.gyanoday;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import com.gdjt.gyanoday.beans.DataBean;
import com.gdjt.gyanoday.beans.NiyamBean;

public class DbHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "LOCAL.db";
    private String DB_PATH = null;
    private SQLiteDatabase mSqliteDatabase;
    private Context mContext;

    public static final String TABLE_DENIK = "Denik";
    public static final String TABLE_OTHER = "Other";
    public static final String TABLE_STROTA = "Strota";
    public static final String TABLE_AARTI = "Aarti";
    public static final String TABLE_BHAJAN = "Bhajan";
    public static final String TABLE_BHAKTI = "Bhakti";
    public static final String TABLE_CHALISA = "Chalisa";
    public static final String TABLE_STUTI = "Stuti";
    public static final String TABLE_NIYAM = "Niyam";


    public static final String GENERAL_ID_KEY = "id";
    public static final String GENERAL_FIRST_TITLE_KEY = "first_title";
    public static final String GENERAL_SECOND_TITLE_KEY = "second_title";
    public static final String GENERAL_DETAILS_KEY = "val";
    public static final String GENERAL_INDEX_KEY = "index";

    public static final String NIYAM_ID_KEY =  "id";
    public static final String NIYAM_DATA_KEY = "text";
    public static final String NIYAM_TYPE_KEY = "type";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        mContext = context;
        DB_PATH = context.getApplicationInfo().dataDir+"/databases/";
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {
        try {
            InputStream myInput = mContext.getAssets().open(DATABASE_NAME);
            String outFileName = DB_PATH + DATABASE_NAME;
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }catch (Exception e){
            System.out.println("e = " + e);
        }

    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DATABASE_NAME;
        mSqliteDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {
        if (mSqliteDatabase != null)
            mSqliteDatabase.close();
        super.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();

            }
    }


    public ArrayList<DataBean> getDenikPujaList(String aTableName){
        ArrayList<DataBean> list = new ArrayList<>();
        try {
            openDataBase();
            String selectQuery = "SELECT  * FROM " + aTableName;
            Cursor cursor = mSqliteDatabase.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(GENERAL_ID_KEY));
                    String first_title = cursor.getString(cursor.getColumnIndex(GENERAL_FIRST_TITLE_KEY));
                    String second_title = cursor.getString(cursor.getColumnIndex(GENERAL_SECOND_TITLE_KEY));
                    int index = cursor.getInt(cursor.getColumnIndex(GENERAL_INDEX_KEY));
                    String desc = cursor.getString(cursor.getColumnIndex(GENERAL_DETAILS_KEY));
                    list.add(new DataBean(id, first_title, second_title, index, desc));
                } while (cursor.moveToNext());
            }

        }catch (Exception e){
            System.out.println("e = " + e);
        }finally {
            close();
        }
        return list;
    }


    public ArrayList<NiyamBean> getChildNiyamList(){
        ArrayList<NiyamBean> list = new ArrayList<>();
        try {
            openDataBase();
            String selectQuery = "SELECT  * FROM " + TABLE_NIYAM + " WHERE " + NIYAM_TYPE_KEY + " = " + Constant.NIYAM_CHILD_TYPE ;
            Cursor cursor = mSqliteDatabase.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(NIYAM_ID_KEY));
                    String first_title = cursor.getString(cursor.getColumnIndex(NIYAM_DATA_KEY));
                    int second_title = cursor.getInt(cursor.getColumnIndex(NIYAM_TYPE_KEY));
                    list.add(new NiyamBean(id, first_title, second_title));
                } while (cursor.moveToNext());
            }

        }catch (Exception e){
            System.out.println("e = " + e);
        }finally {
            close();
        }
        return list;
    }


    public ArrayList<NiyamBean> getAdultNiyamList(){
        ArrayList<NiyamBean> list = new ArrayList<>();
        try {
            openDataBase();
            String selectQuery = "SELECT  * FROM " + TABLE_NIYAM + " WHERE " + NIYAM_TYPE_KEY + " = " + Constant.NIYAM_ADULT_TYPE ;
            Cursor cursor = mSqliteDatabase.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(NIYAM_ID_KEY));
                    String first_title = cursor.getString(cursor.getColumnIndex(NIYAM_DATA_KEY));
                    int second_title = cursor.getInt(cursor.getColumnIndex(NIYAM_TYPE_KEY));
                    list.add(new NiyamBean(id, first_title, second_title));
                } while (cursor.moveToNext());
            }

        }catch (Exception e){
            System.out.println("e = " + e);
        }finally {
            close();
        }
        return list;
    }


}
