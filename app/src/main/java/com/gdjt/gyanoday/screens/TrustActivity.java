package com.gdjt.gyanoday.screens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import java.util.ArrayList;
import com.gdjt.gyanoday.AppData;
import com.gdjt.gyanoday.R;
import com.gdjt.gyanoday.adapters.TrustAdapter;
import com.gdjt.gyanoday.beans.TrustDataBean;

public class TrustActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trust);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(R.string.menu_trust_hindi);
            getSupportActionBar().setSubtitle(R.string.title_activity_trust);
        }

    ArrayList<TrustDataBean> trustDataList = AppData.getInstance().getTrustDataList(this);
        if(trustDataList != null){
            RecyclerView trustRv = (RecyclerView) findViewById(R.id.rv_trust);
            TrustAdapter adapter = new TrustAdapter(this, trustDataList);
            trustRv.setAdapter(adapter);
            LinearLayoutManager lLayout = new LinearLayoutManager(this);
            trustRv.setLayoutManager(lLayout);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
