package com.gdjt.gyanoday.screens;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import com.gdjt.gyanoday.Constant;
import com.gdjt.gyanoday.R;
import com.gdjt.gyanoday.beans.NotificationBean;

public class NotificationDetailsActivity extends AppCompatActivity {


    private NotificationBean mDataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        if(getIntent() != null && getIntent().getExtras() != null){
            mDataBean = getIntent().getParcelableExtra(Constant.INTENT_NOTIFICATION_DATA);
            if(mDataBean != null){
                setData();
            }
        }
    }

    private void setData(){
        TextView titleTv = findViewById(R.id.tv_notification_details_title);
        titleTv.setText(mDataBean.getTitle());

        ImageView imageView = findViewById(R.id.iv_notification_details);
        if(mDataBean.getImageURL() != null && mDataBean.getImageURL().length() > 0){
            Picasso.get().load(mDataBean.getImageURL()).into(imageView);
        }else{
            imageView.setBackgroundResource(R.drawable.temple_bg);
        }

        TextView msgTv = findViewById(R.id.tv_notification_details_msg);
        if(mDataBean.getNotificationDetailMessage() != null){
            if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                msgTv.setText(Html.fromHtml(mDataBean.getNotificationDetailMessage(), Html.FROM_HTML_MODE_COMPACT));

            }else{
                msgTv.setText(Html.fromHtml(mDataBean.getNotificationDetailMessage()));
            }
        }else{
            msgTv.setVisibility(View.GONE);
        }

        TextView otherTv = findViewById(R.id.tv_notification_details_other_msg);
        if(mDataBean.getNoteStr() != null){
            if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                otherTv.setText(Html.fromHtml(mDataBean.getNoteStr(), Html.FROM_HTML_MODE_COMPACT));

            }else{
                otherTv.setText(Html.fromHtml(mDataBean.getNoteStr()));
            }

        }else{
            otherTv.setVisibility(View.GONE);
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
}
