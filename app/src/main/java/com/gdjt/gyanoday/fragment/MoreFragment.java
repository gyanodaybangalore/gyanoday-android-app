package com.gdjt.gyanoday.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.gdjt.gyanoday.AppData;
import com.gdjt.gyanoday.Constant;
import com.gdjt.gyanoday.R;
import com.gdjt.gyanoday.adapters.CustomListAdapter;
import com.gdjt.gyanoday.beans.DataBean;
import com.gdjt.gyanoday.callbacks.IRowClickCallback;
import com.gdjt.gyanoday.screens.ContactUsActivity;
import com.gdjt.gyanoday.screens.DonationActivity;
import com.gdjt.gyanoday.screens.FeedbackActivity;
import com.gdjt.gyanoday.screens.GalleryActivity;
import com.gdjt.gyanoday.screens.RuleActivity;
import com.gdjt.gyanoday.screens.TirthActivity;
import com.gdjt.gyanoday.screens.TrustActivity;

public class MoreFragment extends Fragment implements IRowClickCallback {

    private ArrayList<DataBean> mMoreDataList;
    private View mMainView;
    private Context mContext;
    private RecyclerView mMoreRv;
    private CustomListAdapter mAdapter;


    public MoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mContext = getActivity();
        mMainView = inflater.inflate(R.layout.fragment_more, container, false);
        return mMainView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initMore();
    }

    private void initMore() {
        mMoreDataList = AppData.getInstance().getMoreOptionList(mContext);
        if(mMoreDataList != null){
            mMoreRv = (RecyclerView) mMainView.findViewById(R.id.rv_more);
            RecyclerView.ItemAnimator animator = mMoreRv.getItemAnimator();
            if (animator instanceof DefaultItemAnimator) {
                ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
            }

            mAdapter = new CustomListAdapter(mContext, mMoreDataList, this);
            mMoreRv.setAdapter(mAdapter);
            LinearLayoutManager lLayout = new LinearLayoutManager(mContext);
            mMoreRv.setLayoutManager(lLayout);

        }

    }

    @Override
    public void onClick(int aPosition) {
        System.out.println("aPosition = " + aPosition);
    }

    @Override
    public void onClick(DataBean aBean) {
        if(aBean != null){
            switch (aBean.getActionIndex()){
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
    }



}
