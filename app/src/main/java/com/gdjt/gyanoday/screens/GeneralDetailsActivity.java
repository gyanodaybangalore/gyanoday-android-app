package com.gdjt.gyanoday.screens;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;
import com.gdjt.gyanoday.Constant;
import com.gdjt.gyanoday.R;

public class GeneralDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        if (getIntent() != null) {
            String title = getIntent().getStringExtra(Constant.INTENT_GENERAL_DETAILS_TITLE);
            if (title != null && getSupportActionBar() != null) {
                getSupportActionBar().setTitle(title);
            }

            String desc = getIntent().getStringExtra(Constant.INTENT_GENERAL_DETAILS_DESC);
            if(desc != null){
                TextView tv = findViewById(R.id.tv_general_details);
                String htmlStr = desc.replace("\n", "<br>");
                if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                    tv.setText(Html.fromHtml(htmlStr, Html.FROM_HTML_MODE_COMPACT));

                }else{
                    tv.setText(Html.fromHtml(htmlStr));
                }
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

}
