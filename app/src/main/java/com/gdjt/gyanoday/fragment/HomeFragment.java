package com.gdjt.gyanoday.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

import java.util.ArrayList;

import com.gdjt.gyanoday.AppData;
import com.gdjt.gyanoday.Constant;
import com.gdjt.gyanoday.R;
import com.gdjt.gyanoday.beans.HomeSliderBean;
import com.gdjt.gyanoday.screens.ContactUsActivity;
import com.gdjt.gyanoday.screens.DonationActivity;
import com.gdjt.gyanoday.screens.FeedbackActivity;
import com.gdjt.gyanoday.screens.GalleryActivity;
import com.gdjt.gyanoday.screens.NotificationActivity;
import com.gdjt.gyanoday.screens.PoojaActivity;
import com.gdjt.gyanoday.screens.RuleActivity;
import com.gdjt.gyanoday.screens.TirthActivity;
import com.gdjt.gyanoday.screens.TrustActivity;
import com.lukedeighton.wheelview.WheelView;
import com.lukedeighton.wheelview.adapter.WheelAdapter;

public class HomeFragment extends Fragment {

    //private SliderLayout mImageSlider;
    private View mMainView;
    private Context mContext;
    private WheelView wheelView;
    private TextView selectedItemTitle;
    private TextView selectedItemHindiTitle;

    int[] menuDrawablesList = {
            R.drawable.ic_notification,
            R.drawable.ic_jinwani,
            R.drawable.ic_trust,
            R.drawable.ic_contactus,
            R.drawable.ic_tirth,
            R.drawable.ic_donation,
            R.drawable.ic_niyam,
            R.drawable.ic_feedback
//            R.drawable.ic_gallery
    };
    String[] menuItemNameList = {
            "Information",
            "Jinvani",
            "Trustee",
            "Contact Us",
            "Teerth",
            "Donation",
            "Niyam",
            "Feedback",
            "Gallery"
    };
    int[] menuHindiItemNameList = {
            R.string.menu_information_hindi,
            R.string.menu_jinvani_hindi,
            R.string.menu_trust_hindi,
            R.string.menu_contactus_hindi,
            R.string.menu_tirth_hindi,
            R.string.menu_donation_hindi,
            R.string.menu_niyam_hindi,
            R.string.menu_feedback_hindi,
            R.string.menu_gallery_hindi
    };


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext = getActivity();
        mMainView = inflater.inflate(R.layout.fragment_home, container, false);
        return mMainView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //mImageSlider =(SliderLayout)mMainView.findViewById(R.id.sl_home);
        setupWheelView();
//        initHomeView();
    }

    private void setupWheelView(){
        wheelView = (WheelView) mMainView.findViewById(R.id.wheelview);
        selectedItemTitle = (TextView) mMainView.findViewById(R.id.selectedItemTitle);
        selectedItemHindiTitle = (TextView) mMainView.findViewById(R.id.selectedItemHindiTitle);
        selectedItemTitle.setText(menuItemNameList[0]);
        selectedItemHindiTitle.setText(menuHindiItemNameList[0]);
        wheelView.setWheelItemCount(menuDrawablesList.length);
        wheelView.setAdapter(new WheelAdapter() {
            @Override
            public Drawable getDrawable(int position) {
                return getContext().getResources().getDrawable(menuDrawablesList[position]);
            }

            @Override
            public int getCount() {
                return menuDrawablesList.length;
            }
        });

        wheelView.setOnWheelItemClickListener(new WheelView.OnWheelItemClickListener() {
            @Override
            public void onWheelItemClick(WheelView parent, int position, boolean isSelected) {
                //the position in the adapter and whether it is closest to the selection angle
                switch (position){
                    case Constant.MORE_OPTION_NOTIFICATION_INDEX:
                        startActivity(new Intent(getActivity(), NotificationActivity.class));
                        break;

                    case Constant.MORE_OPTION_POOJA_INDEX:
                        startActivity(new Intent(getActivity(), PoojaActivity.class));
                        break;

                    case Constant.MORE_OPTION_TRUST_INDEX:
                        startActivity(new Intent(getActivity(), TrustActivity.class));
                        break;

                    case Constant.MORE_OPTION_CONTACT_INDEX:
                        startActivity(new Intent(getActivity(), ContactUsActivity.class));
                        break;

                    case Constant.MORE_OPTION_GALLERY_INDEX:
                        startActivity(new Intent(getActivity(), GalleryActivity.class));
                        break;

                    case Constant.MORE_OPTION_TIRTH_INDEX:
                        startActivity(new Intent(getActivity(), TirthActivity.class));
                        break;

                    case Constant.MORE_OPTION_FEEDBACK_INDEX:
                        startActivity(new Intent(getActivity(), FeedbackActivity.class));
                        break;

                    case Constant.MORE_OPTION_DONATION_INDEX:
                        startActivity(new Intent(getActivity(), DonationActivity.class));
                        break;

                    case Constant.MORE_OPTION_NIYAM_INDEX:
                        startActivity(new Intent(getActivity(), RuleActivity.class));
                        break;



            }
            }
        });

        wheelView.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectListener() {
            @Override
            public void onWheelItemSelected(WheelView parent,  Drawable itemDrawable, int position) {
                //the adapter position that is closest to the selection angle and it's drawable
                Log.d("WheelPoistion","Cureent selected position "+position);
                selectedItemTitle.setText(menuItemNameList[position]);
                selectedItemHindiTitle.setText(menuHindiItemNameList[position]);
            }
        });

    }
}
