package com.gdjt.gyanoday.screens;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import com.gdjt.gyanoday.AppData;
import com.gdjt.gyanoday.R;
import com.gdjt.gyanoday.beans.NiyamBean;
import com.gdjt.gyanoday.utils.UIUtils;

public class RuleActivity extends AppCompatActivity {


    private Context mContext;
    public static final int CAT_CHILD_INDEX = 1;
    public static final int CAT_ADULT_INDEX = 2;

    private Button mChildBtn;
    private Button mAdultBtn;
    private Button mNiyamChooseBtn;


    private ArrayList<NiyamBean> mAdultList;
    private ArrayList<NiyamBean> mChildList;

    private LinearLayout mDetailsLl;
    private TextView mNiyamTv;


    private int mSelectedCategoryIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(R.string.menu_niyam_hindi);
            getSupportActionBar().setSubtitle(R.string.title_activity_rule);
        }
        mContext = this;
        mDetailsLl = findViewById(R.id.ll_niyam_details);
        mDetailsLl.setVisibility(View.GONE);
        mNiyamTv = findViewById(R.id.tv_niyam_details_data);
        mChildBtn = findViewById(R.id.btn_niyam_child);
        mAdultBtn = findViewById(R.id.btn_niyam_adult);
        mNiyamChooseBtn = findViewById(R.id.btn_niyam_choose);
        mNiyamChooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSelectedCategoryIndex == -1){
                    UIUtils.showToast(mContext, getString(R.string.NiyamCategoryLabel));
                }else{
                    if(mSelectedCategoryIndex == CAT_ADULT_INDEX){
                        handleAdultSelection();
                    }
                    if(mSelectedCategoryIndex == CAT_CHILD_INDEX){
                        handleChildSelection();
                    }
                }
            }
        });

        mChildBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectedCategoryIndex = CAT_CHILD_INDEX;
                setChildState(true);
                setAdultState(false);
                mDetailsLl.setVisibility(View.GONE);

            }
        });

        mAdultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectedCategoryIndex = CAT_ADULT_INDEX;
                setChildState(false);
                setAdultState(true);
                mDetailsLl.setVisibility(View.GONE);
            }
        });
        mChildList = AppData.getInstance().getChildNiyamData(mContext);
        mAdultList = AppData.getInstance().getAdultNiyamData(mContext);

    }


    private void handleChildSelection(){
        int randomNumer = new Random().nextInt(Math.abs(((mChildList.size() -1) - 0) + 1) + 0);
        NiyamBean curBean = mChildList.get(randomNumer);
        String htmlStr = curBean.getName().replace("\n", "<br>");
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            mNiyamTv.setText(Html.fromHtml(htmlStr, Html.FROM_HTML_MODE_COMPACT));

        }else{
            mNiyamTv.setText(Html.fromHtml(htmlStr));
        }
        mDetailsLl.setVisibility(View.VISIBLE);

    }

    private void handleAdultSelection(){
        int randomNumer = new Random().nextInt(Math.abs(((mAdultList.size() -1) - 0) + 1)  + 0);
        NiyamBean curBean = mAdultList.get(randomNumer);
        String htmlStr = curBean.getName().replace("\n", "<br>");
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            mNiyamTv.setText(Html.fromHtml(htmlStr, Html.FROM_HTML_MODE_COMPACT));

        }else{
            mNiyamTv.setText(Html.fromHtml(htmlStr));
        }
        mDetailsLl.setVisibility(View.VISIBLE);

    }

    private void setChildState(boolean aSelected){
        if(aSelected){
            mChildBtn.setTextColor(ContextCompat.getColor(mContext, R.color.colorNiyamSelected));
            mChildBtn.setBackgroundResource(R.drawable.round_corner_shape_filled);
        }else{

            mChildBtn.setTextColor(ContextCompat.getColor(mContext, R.color.colorNiyamNormal));
            mChildBtn.setBackgroundResource(R.drawable.round_corner_shape); }
    }

    private void setAdultState(boolean aSelected){
        if(aSelected){
            mAdultBtn.setTextColor(ContextCompat.getColor(mContext, R.color.colorNiyamSelected));
            mAdultBtn.setBackgroundResource(R.drawable.round_corner_shape_filled);
        }else{

            mAdultBtn.setTextColor(ContextCompat.getColor(mContext, R.color.colorNiyamNormal));
            mAdultBtn.setBackgroundResource(R.drawable.round_corner_shape); }
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

}
