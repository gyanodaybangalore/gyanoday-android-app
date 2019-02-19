package com.gdjt.gyanoday.screens;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.gdjt.gyanoday.R;
import com.gdjt.gyanoday.beans.PatraBean;
import com.gdjt.gyanoday.fragment.PatraFragment;

import java.util.ArrayList;

public class PatraActivity extends AppCompatActivity {


    private ViewPager mPatraVp;
    private ArrayList<PatraBean> mPatraList = new ArrayList<>();
    private MyPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patra);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mPatraVp = findViewById(R.id.vpPatra);

        mPatraList = PatraActivity.getAllPatraList();
        mAdapter = new MyPagerAdapter(getSupportFragmentManager(), mPatraList.size());
        mPatraVp.setAdapter(mAdapter);

        mPatraVp.setClipToPadding(false);
        mPatraVp.setPadding(60,0,60,0);
        mPatraVp.setPageMargin(10);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private int mSize = 0;


        MyPagerAdapter(FragmentManager supportFragmentManager, int aSize) {
            super(supportFragmentManager);
            mSize = aSize;
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return mSize;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            return PatraFragment.newInstance(position);
        }
    }

    public static ArrayList<PatraBean> getAllPatraList() {
        ArrayList<PatraBean> list = new ArrayList<>();
        list.add(new PatraBean("माता पिता", "श्री तारा चंद जी कविता सेठी", R.drawable.kalash, PatraBean.DISPLAY_TYPE_SINGLE, null));
        list.add(new PatraBean("सौधर्म इंद्र", "श्री रितेश शैली जैन", R.drawable.kalash, PatraBean.DISPLAY_TYPE_SINGLE, null));
        list.add(new PatraBean("धनपति कुवेर", "श्री मनोज विभा बाँझल", R.drawable.kalash, PatraBean.DISPLAY_TYPE_SINGLE, null));
        list.add(new PatraBean("महायज्ञ नायक", "श्री कमल जैन", R.drawable.kalash, PatraBean.DISPLAY_TYPE_SINGLE, null));
        list.add(new PatraBean("राजा भरत", "शश्री दिवाकर जैन", R.drawable.kalash, PatraBean.DISPLAY_TYPE_SINGLE, null));

        list.add(new PatraBean("राजा बाहुवली", "श्री विकाश स्नेहा जैन", R.drawable.kalash, PatraBean.DISPLAY_TYPE_SINGLE, null));
        list.add(new PatraBean("राजा श्रेयांश", "श्री संदीप स्वेता जैन", R.drawable.kalash, PatraBean.DISPLAY_TYPE_SINGLE, null));
        list.add(new PatraBean("राजा सोम", "श्री मयंक मल्लिका जैन", R.drawable.kalash, PatraBean.DISPLAY_TYPE_SINGLE, null));
        list.add(new PatraBean("ईशान इंद्र", "श्री पिंकेश प्रिंसी जैन", R.drawable.kalash, PatraBean.DISPLAY_TYPE_SINGLE, null));
        list.add(new PatraBean("सानत कुमार इंद्र", "श्री रचित स्नेहा जैन", R.drawable.kalash, PatraBean.DISPLAY_TYPE_SINGLE, null));
        list.add(new PatraBean("माहेन्द्र इंद्र", "श्री प्रशांत रिंकी जैन", R.drawable.kalash, PatraBean.DISPLAY_TYPE_SINGLE, null));


        ArrayList<String> indraList = new ArrayList<>();
        indraList.add("श्री मुकेश भाईजी ");
        indraList.add("श्री अलोक जैन");
        indraList.add("श्री निशांत पंचरत्न");
        indraList.add("श्री गौरव जैन ");
        indraList.add("श्री सृजन जैन");
        indraList.add("श्री अमित बज");
        indraList.add("श्री पियूष जैन");
        indraList.add("श्री सुमित जैन");
        indraList.add("श्री नरेंद्र जैन");
        indraList.add("श्री अमित जैन (A-601)");
        indraList.add("श्री रवि लुहारिया");
        indraList.add("श्री अमित पाटनी");

        list.add(new PatraBean("इंद्रों की सूची", "", R.drawable.kalash, PatraBean.DISPLAY_TYPE_LIST, indraList));


        ArrayList<String> mahamandleswerList = new ArrayList<>();
        mahamandleswerList.add("श्री कपिल अर्चना जैन");
        mahamandleswerList.add("श्री प्रफुल्ल जैन");
        mahamandleswerList.add("श्री विशेष जैन");
        mahamandleswerList.add("श्री अमित मधु जैन (ब्रेंटवुड A-603)");
        list.add(new PatraBean("महामंडलेश्वर राजा", "", R.drawable.kalash, PatraBean.DISPLAY_TYPE_LIST, mahamandleswerList));


        ArrayList<String> mandlesweList = new ArrayList<>();
        mandlesweList.add("श्री आशीष शचि जैन");
        list.add(new PatraBean("मंडलेश्वर राजा", "", R.drawable.kalash, PatraBean.DISPLAY_TYPE_LIST, mandlesweList));



        return list;
    }

}



