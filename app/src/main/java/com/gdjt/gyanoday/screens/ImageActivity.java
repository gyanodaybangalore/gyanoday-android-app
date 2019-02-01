package com.gdjt.gyanoday.screens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.gdjt.gyanoday.Constant;
import com.gdjt.gyanoday.R;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        SubsamplingScaleImageView imageView = (SubsamplingScaleImageView) findViewById(R.id.siv_image);

        if (getIntent() != null) {
            String title = getIntent().getStringExtra(Constant.INTENT_TITLE);
            if (title != null) {
                getSupportActionBar().setTitle(title);
            }

            String subTitle = getIntent().getStringExtra(Constant.INTENT_SUBTITLE);
            if (subTitle != null) {
                getSupportActionBar().setSubtitle(subTitle);
            }

            int imageIndex = getIntent().getIntExtra(Constant.INTENT_IMAGE_ACTIVITY_INDEX, -1);
            if(imageIndex != -1){
                switch (imageIndex){
                    case Constant.IMAGE_ACTIVITY_KARNATKA_INDEX:
                        imageView.setImage(ImageSource.resource(R.drawable.karnatka));
                        break;

                    case Constant.IMAGE_ACTIVITY_TAMILNADU_INDEX:
                        imageView.setImage(ImageSource.resource(R.drawable.tamilnadu));
                        break;
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
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
