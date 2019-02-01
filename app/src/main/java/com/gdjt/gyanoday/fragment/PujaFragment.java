package com.gdjt.gyanoday.fragment;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;

import com.gdjt.gyanoday.AppData;
import com.gdjt.gyanoday.Constant;
import com.gdjt.gyanoday.R;
import com.gdjt.gyanoday.adapters.CustomListAdapter;
import com.gdjt.gyanoday.beans.DataBean;
import com.gdjt.gyanoday.callbacks.IRowClickCallback;
import com.gdjt.gyanoday.screens.GeneralListActivity;

public class PujaFragment extends Fragment implements IRowClickCallback{

    private ArrayList<DataBean> mJinvaniDataList;
    private View mMainView;
    private Context mContext;
    private RecyclerView mPujaRv;
    private CustomListAdapter mAdapter;
    private DataBean mSelectedDataBean;

    public PujaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext = getActivity();
        mMainView = inflater.inflate(R.layout.fragment_puja, container, false);
        return mMainView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPuja();
    }

    private void initPuja() {

        SearchView my_search_view = (SearchView) mMainView.findViewById(R.id.sv_puja);

        SearchView.SearchAutoComplete searchAutoComplete =
                (SearchView.SearchAutoComplete) my_search_view.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setHintTextColor(Color.GRAY);
        searchAutoComplete.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));

        my_search_view.setMaxWidth(Integer.MAX_VALUE);
        // listening to search query text change
        my_search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });

        mJinvaniDataList = AppData.getInstance().getDataList(mContext);
        if(mJinvaniDataList != null){
            mPujaRv = (RecyclerView) mMainView.findViewById(R.id.rv_puja);
            mAdapter = new CustomListAdapter(mContext, mJinvaniDataList, this);
            mPujaRv.setAdapter(mAdapter);
            LinearLayoutManager lLayout = new LinearLayoutManager(mContext);
            mPujaRv.setLayoutManager(lLayout);

        }

    }

    @Override
    public void onClick(int aPosition) {
        System.out.println("aPosition = " + aPosition);
    }

    @Override
    public void onClick(DataBean aBean) {
        mSelectedDataBean = aBean;
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            Dexter.withActivity(getActivity())
                    .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .withListener(new PermissionListener() {
                        @Override public void onPermissionGranted(PermissionGrantedResponse response) {
                            startGeneralActivity();
                        }
                        @Override public void onPermissionDenied(PermissionDeniedResponse response) {
                            System.out.println("response = " + response);
                            if(response.isPermanentlyDenied()){
                                showDialogForPermissionSetting();
                            }
                        }
                        @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                            System.out.println("permission = " + permission);
                            token.continuePermissionRequest();
                        }
                    }).check();
        }else{
            startGeneralActivity();
        }
    }


    private void startGeneralActivity(){
        if(mSelectedDataBean != null){
            Intent intent = new Intent(getActivity(), GeneralListActivity.class);
            intent.putExtra(Constant.INTENT_GENERAL_LIST_TITLE, mSelectedDataBean.getHinName());
            intent.putExtra(Constant.INTENT_GENERAL_LIST_INDEX, mSelectedDataBean.getActionIndex());
            startActivity(intent);
        }
    }

    private void showDialogForPermissionSetting() {
        new AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.PermissionGoSettingTitle))
                .setMessage(getString(R.string.LocationGoSettingMsg))
                .setCancelable(true)
                .setPositiveButton(getString(R.string.PermissionGoSettingTitle), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        final Intent i = new Intent();
                        i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        i.addCategory(Intent.CATEGORY_DEFAULT);
                        i.setData(Uri.parse("package:" + getActivity().getPackageName()));
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                        startActivity(i);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


}
