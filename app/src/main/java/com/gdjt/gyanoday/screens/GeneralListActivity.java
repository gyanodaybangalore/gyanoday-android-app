package com.gdjt.gyanoday.screens;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import com.gdjt.gyanoday.AppData;
import com.gdjt.gyanoday.Constant;
import com.gdjt.gyanoday.R;
import com.gdjt.gyanoday.adapters.CustomListAdapter;
import com.gdjt.gyanoday.beans.DataBean;
import com.gdjt.gyanoday.callbacks.IRowClickCallback;

public class GeneralListActivity extends AppCompatActivity implements IRowClickCallback {


    private RelativeLayout mMainRl;
    private Context mContext;
    private int mIndex = -1;

    private ArrayList<DataBean> mJinvaniDataList;
    private RecyclerView mPujaRv;
    private CustomListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        mContext = this;
        init();

        if (getIntent() != null) {
            String title = getIntent().getStringExtra(Constant.INTENT_GENERAL_LIST_TITLE);
            if (title != null && getSupportActionBar() != null) {
                getSupportActionBar().setTitle(title);
            }

            mIndex = getIntent().getIntExtra(Constant.INTENT_GENERAL_LIST_INDEX, -1);
            if (mIndex != -1) {
                loadData();
            }
        }

    }

    private void init() {
        mMainRl = findViewById(R.id.rl_general_list);
        mMainRl.setVisibility(View.GONE);

        SearchView my_search_view = (SearchView) findViewById(R.id.sv_general_list);

        SearchView.SearchAutoComplete searchAutoComplete =
                (SearchView.SearchAutoComplete) my_search_view.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setHintTextColor(Color.GRAY);
        searchAutoComplete.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));

        my_search_view.setMaxWidth(Integer.MAX_VALUE);
        // listening to search query text change
        my_search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });

        mJinvaniDataList = new ArrayList<>();
        if (mJinvaniDataList != null) {
            mPujaRv = (RecyclerView) findViewById(R.id.rv_general_list);
            mAdapter = new CustomListAdapter(mContext, mJinvaniDataList, this);
            mPujaRv.setAdapter(mAdapter);
            LinearLayoutManager lLayout = new LinearLayoutManager(mContext);
            mPujaRv.setLayoutManager(lLayout);

        }
    }

    private void loadData() {
        ArrayList<DataBean> dbDataList = null;
        switch (mIndex) {
            case Constant.PUJA_DENIK_INDEX:
                dbDataList = AppData.getInstance().getDenikPujaSangrah(mContext);
                break;

            case Constant.PUJA_OTHER_INDEX:
                dbDataList = AppData.getInstance().getOtherPujaSangrah(mContext);
                break;

            case Constant.PUJA_AARTI_INDEX:
                dbDataList = AppData.getInstance().getAartiData(mContext);
                break;

            case Constant.PUJA_BHAJAN_INDEX:
                dbDataList = AppData.getInstance().getBhajanData(mContext);
                break;

            case Constant.PUJA_BHAKTI_INDEX:
                dbDataList = AppData.getInstance().getBhaktiData(mContext);
                break;

            case Constant.PUJA_STUTI_INDEX:
                dbDataList = AppData.getInstance().getStutiData(mContext);
                break;

            case Constant.PUJA_STROTA_INDEX:
                dbDataList = AppData.getInstance().getStrotaData(mContext);
                break;

            case Constant.PUJA_CHALISA_INDEX:
                dbDataList = AppData.getInstance().getChalisaData(mContext);
                break;
        }

        if (dbDataList != null && dbDataList.size() > 0) {
            mJinvaniDataList.addAll(dbDataList);
            mAdapter.notifyDataSetChanged();
            mMainRl.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(int aPosition) {

    }

    @Override
    public void onClick(DataBean aBean) {
        Intent intent = new Intent(mContext, GeneralDetailsActivity.class);
        intent.putExtra(Constant.INTENT_GENERAL_DETAILS_TITLE, aBean.getHinName());
        intent.putExtra(Constant.INTENT_GENERAL_DETAILS_DESC, aBean.getTextValue());
        startActivity(intent);

    }
}
