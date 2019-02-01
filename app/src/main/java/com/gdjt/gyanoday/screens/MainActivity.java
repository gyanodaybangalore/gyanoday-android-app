package com.gdjt.gyanoday.screens;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.lang.reflect.Field;

import com.gdjt.gyanoday.Constant;
import com.gdjt.gyanoday.R;
import com.gdjt.gyanoday.fragment.AboutFragment;
import com.gdjt.gyanoday.fragment.HomeFragment;
import com.gdjt.gyanoday.fragment.MoreFragment;
import com.gdjt.gyanoday.fragment.PujaFragment;

public class MainActivity extends AppCompatActivity {


    private int mCurrentScreenIndex;


//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    addFragment(Constant.HOME_SCREEN_INDEX);
//                    return true;
//
//                case R.id.navigation_jinvani:
//                    addFragment(Constant.PUJA_SCREEN_INDEX);
//                    return true;
//
//                case R.id.navigation_more:
//                    addFragment(Constant.MORE_SCREEN_INDEX);
//                    return true;
//
//                case R.id.navigation_about:
//                    addFragment(Constant.ABOUT_SCREEN_INDEX);
//                    return true;
//
//            }
//
//            return false;
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        BottomNavigationViewHelper.disableShiftMode(navigation);
//        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationView);
        getSupportActionBar().setSubtitle("Shri Gyanoday Digamber Jain Mandir");
        addFragment(Constant.HOME_SCREEN_INDEX);
    }

    private void addFragment(int aIndex) {
        if (mCurrentScreenIndex != aIndex) {
            mCurrentScreenIndex = aIndex;
            Fragment fragment = null;
            switch (aIndex) {
                case Constant.HOME_SCREEN_INDEX:
                    fragment = new HomeFragment();
                    break;

//                case Constant.PUJA_SCREEN_INDEX:
//                    fragment = new PujaFragment();
//                    break;
//
//                case Constant.ABOUT_SCREEN_INDEX:
//                    fragment = new AboutFragment();
//                    break;
//
//                case Constant.MORE_SCREEN_INDEX:
//                    fragment = new MoreFragment();
//                    break;
            }

            if (fragment != null) {
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.fl_main, fragment, "HELLO");
                fragmentTransaction.commit();
            }
        }


    }

//    public static class BottomNavigationViewHelper {
//        @SuppressLint("RestrictedApi")
//        public static void disableShiftMode(BottomNavigationView view) {
//            BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
//            try {
//                Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
//                shiftingMode.setAccessible(true);
//                shiftingMode.setBoolean(menuView, false);
//                shiftingMode.setAccessible(false);
//                for (int i = 0; i < menuView.getChildCount(); i++) {
//                    BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
//                    //noinspection RestrictedApi
//                    item.setShiftingMode(false);
//                    // set once again checked value, so view will be updated
//                    //noinspection RestrictedApi
//                    item.setChecked(item.getItemData().isChecked());
//                }
//            } catch (NoSuchFieldException e) {
//                Log.e("BNVHelper", "Unable to get shift mode field", e);
//            } catch (IllegalAccessException e) {
//                Log.e("BNVHelper", "Unable to change value of shift mode", e);
//            }
//        }
//    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_home, menu);
//        MenuItem item = menu.findItem(R.id.action_notification);
//        if(item != null){
//            RelativeLayout layout = (RelativeLayout) item.getActionView();
//            if(layout != null){
//                Button btn = layout.findViewById(R.id.btn_home_notification);
//                if(btn != null){
//                    btn.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
//                            startActivity(intent);
//                        }
//                    });
//                }
//            }
//        }
//        return true;
//    }



}
