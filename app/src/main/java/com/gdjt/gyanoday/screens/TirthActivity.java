package com.gdjt.gyanoday.screens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import com.gdjt.gyanoday.AppData;
import com.gdjt.gyanoday.Constant;
import com.gdjt.gyanoday.R;
import com.gdjt.gyanoday.adapters.CustomListAdapter;
import com.gdjt.gyanoday.beans.DataBean;
import com.gdjt.gyanoday.beans.TempleBean;
import com.gdjt.gyanoday.callbacks.IRowClickCallback;

public class TirthActivity extends AppCompatActivity implements IRowClickCallback {


    private ArrayList<DataBean> mTirthList;
    private Context mContext;
    private RecyclerView mTirthRv;
    private CustomListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tirth);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(R.string.menu_tirth_hindi);
            getSupportActionBar().setSubtitle(R.string.title_activity_tirth);
        }
        mContext = this;

        mTirthList = AppData.getInstance().getTirthList(mContext);
        if(mTirthList != null){
            mTirthRv = (RecyclerView) findViewById(R.id.rv_tirth);
            RecyclerView.ItemAnimator animator = mTirthRv.getItemAnimator();
            if (animator instanceof DefaultItemAnimator) {
                ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
            }

            mAdapter = new CustomListAdapter(mContext, mTirthList, this);
            mTirthRv.setAdapter(mAdapter);
            LinearLayoutManager lLayout = new LinearLayoutManager(mContext);
            mTirthRv.setLayoutManager(lLayout);
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
    public void onClick(int aPosition) {

    }

    @Override
    public void onClick(DataBean aBean) {
        switch (aBean.getActionIndex()){
            case Constant.TIRTH_INDEX_TEMPLE:
                ArrayList<TempleBean> list = AppData.getInstance().getBangaloreTemple(mContext);
                if(list.size() > 0){
                    Intent intent = new Intent(TirthActivity.this, TampleListActivity.class);
                    intent.putExtra(Constant.INTENT_TITLE, aBean.getHinName());
                    intent.putExtra(Constant.INTENT_SUBTITLE, aBean.getEngName());
                    intent.putParcelableArrayListExtra(Constant.INTENT_TEMPLE_LIST, list);

                    startActivity(intent);
                }
                break;

            case Constant.TIRTH_INDEX_KARNATKA:
                Intent intent = new Intent(TirthActivity.this, ImageActivity.class);
                intent.putExtra(Constant.INTENT_TITLE, aBean.getHinName());
                intent.putExtra(Constant.INTENT_SUBTITLE, aBean.getEngName());
                intent.putExtra(Constant.INTENT_IMAGE_ACTIVITY_INDEX, Constant.IMAGE_ACTIVITY_KARNATKA_INDEX);
                startActivity(intent);
                break;

            case Constant.TIRTH_INDEX_TAMILNADU:{
                Intent inte = new Intent(TirthActivity.this, ImageActivity.class);
                inte.putExtra(Constant.INTENT_TITLE, aBean.getHinName());
                inte.putExtra(Constant.INTENT_SUBTITLE, aBean.getEngName());
                inte.putExtra(Constant.INTENT_IMAGE_ACTIVITY_INDEX, Constant.IMAGE_ACTIVITY_TAMILNADU_INDEX);
                startActivity(inte);

            }
                break;
        }
    }
}
