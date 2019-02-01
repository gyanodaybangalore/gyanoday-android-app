package com.gdjt.gyanoday.screens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;


import com.gdjt.gyanoday.R;

public class GalleryActivity extends AppCompatActivity {

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

    Gallery galleryView;
    ImageView imgView;

    //images from drawable
    private int[] imageResource = {
            R.drawable.ic_abouttemple, R.drawable.ic_feedback, R.drawable.ic_feedback, R.drawable.ic_donation, R.drawable.ic_book, R.drawable.ic_gallery, R.drawable.ic_call
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        imgView = (ImageView) findViewById(R.id.imageView);
        galleryView = (Gallery) findViewById(R.id.gallery);

        imgView.setImageResource(imageResource[0]);
        galleryView.setAdapter(new myImageAdapter(this));

        //gallery image onclick event
        galleryView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int i, long id) {
                imgView.setImageResource(imageResource[i]);
                int imagePosition = i + 1;
                Toast.makeText(getApplicationContext(), "You have selected image = " + imagePosition, Toast.LENGTH_LONG).show();
            }
        });

    }

    public class myImageAdapter extends BaseAdapter {
        private Context mcontext;

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView mgalleryView = new ImageView(mcontext);
            mgalleryView.setImageResource(imageResource[position]);
            mgalleryView.setLayoutParams(new Gallery.LayoutParams(150, 150));
            mgalleryView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            //imgView.setImageResource(R.drawable.image_border);
            mgalleryView.setPadding(20, 20, 20, 20);

            return mgalleryView;
        }

        public myImageAdapter(Context context) {
            mcontext = context;
        }

        public int getCount() {
            return imageResource.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }
    }

}
