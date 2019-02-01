package com.gdjt.gyanoday.screens;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.Locale;

import com.gdjt.gyanoday.Constant;
import com.gdjt.gyanoday.R;
import com.gdjt.gyanoday.adapters.TempleListAdapter;
import com.gdjt.gyanoday.beans.TempleBean;
import com.gdjt.gyanoday.callbacks.ITempleRowCallback;

public class TampleListActivity extends AppCompatActivity implements ITempleRowCallback {

    private ArrayList<TempleBean> mTempleDataList;
    private Context mContext;
    private RecyclerView mTirthRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tample_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        mContext = this;
        if(getIntent() != null){
            String title = getIntent().getStringExtra(Constant.INTENT_TITLE);
            if(title != null){
                getSupportActionBar().setTitle(title);
            }

            String subTitle = getIntent().getStringExtra(Constant.INTENT_SUBTITLE);
            if(subTitle != null){
                getSupportActionBar().setSubtitle(subTitle);
            }

            mTempleDataList = getIntent().getParcelableArrayListExtra(Constant.INTENT_TEMPLE_LIST);
            if(mTempleDataList != null && mTempleDataList.size() > 0){
                mTirthRv = (RecyclerView) findViewById(R.id.rv_temple);
                RecyclerView.ItemAnimator animator = mTirthRv.getItemAnimator();
                if (animator instanceof DefaultItemAnimator) {
                    ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
                }
                TempleListAdapter adapter = new TempleListAdapter(mContext, mTempleDataList, this);
                mTirthRv.setAdapter(adapter);
                LinearLayoutManager lLayout = new LinearLayoutManager(mContext);
                mTirthRv.setLayoutManager(lLayout);
            }
        }
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


    @Override
    public void onContactClick(String aContactStr) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + aContactStr));
        startActivity(intent);
    }

    @Override
    public void onDirectionClick(TempleBean aBean) {
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", Double.parseDouble(aBean.getLatitude()), Double.parseDouble(aBean.getLongitude()));
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }
}
