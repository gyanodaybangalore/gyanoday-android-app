package com.gdjt.gyanoday.screens;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import com.gdjt.gyanoday.AppData;
import com.gdjt.gyanoday.Constant;
import com.gdjt.gyanoday.R;

public class SplashActivity extends AppCompatActivity {
    private boolean mTimerCompleted;
    private boolean mPermissionCompleted;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        setContentView(R.layout.activity_splash);
        if(getSupportActionBar()!= null){
            getSupportActionBar().hide();
        }
        mContext = this;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTimerCompleted = true;
                onComplete();
            }
        }, Constant.SPLASH_TIMER);

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {
                        AppData.getInstance().loadDatabase(mContext);
                        mPermissionCompleted = true;
                        onComplete();
                    }
                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {
                        mPermissionCompleted = true;
                        onComplete();
                    }
                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
                }).check();
    }


    private void onComplete(){
        if(mPermissionCompleted && mTimerCompleted){
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }
    }

}
