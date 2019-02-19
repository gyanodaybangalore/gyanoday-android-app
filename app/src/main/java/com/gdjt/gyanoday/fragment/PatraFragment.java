package com.gdjt.gyanoday.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gdjt.gyanoday.R;
import com.gdjt.gyanoday.beans.PatraBean;
import com.gdjt.gyanoday.screens.PatraActivity;

import java.util.ArrayList;


public class PatraFragment extends Fragment {


    private int mPage;
    private View mMainView;

    private PatraBean mCurBean;
    private TextView mTitleTv;
    private TextView mNameTv;
    private ImageView mDisplayIv;

    private RecyclerView mNameListRv;
    public PatraFragment() {
        // Required empty public constructor
    }


    public static PatraFragment newInstance(int page) {
        PatraFragment fragmentFirst = new PatraFragment();
        Bundle args = new Bundle();
        args.putInt("page_index", page);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt("page_index", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mMainView = inflater.inflate(R.layout.fragment_patra, container, false);
        return mMainView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDisplayIv = mMainView.findViewById(R.id.ivPatraIcon);
        mTitleTv = mMainView.findViewById(R.id.tvPatraTitle);
        mNameTv = mMainView.findViewById(R.id.tvPatraName);
        mNameListRv = mMainView.findViewById(R.id.rvPatra);
        mCurBean = PatraActivity.getAllPatraList().get(mPage);
        mTitleTv.setText(mCurBean.getTitle());
        mDisplayIv.setImageResource(mCurBean.getResId());
        mNameTv.setText(mCurBean.getName());


        if (mCurBean.getDisplayType() == PatraBean.DISPLAY_TYPE_SINGLE){
            mNameTv.setVisibility(View.VISIBLE);
            mDisplayIv.setVisibility(View.VISIBLE);
            mNameListRv.setVisibility(View.GONE);
        }
        if (mCurBean.getDisplayType() == PatraBean.DISPLAY_TYPE_LIST){
            mNameTv.setVisibility(View.GONE);
            mDisplayIv.setVisibility(View.GONE);
            mNameListRv.setVisibility(View.VISIBLE);
            mNameListRv.setNestedScrollingEnabled(false);
            mNameListRv.setLayoutManager(new LinearLayoutManager(getActivity()));
            mNameListRv.setAdapter(new NameAdapter(getActivity(), mCurBean.getNameList()));
        }

    }
}


class NameAdapter extends RecyclerView.Adapter<NameAdapter.NameHolder>{


    private Context mContext;
    private ArrayList<String> mNameList;
    public NameAdapter(Context aContext, ArrayList<String> aList){
        mContext = aContext;
        mNameList = aList;
    }

    @NonNull
    @Override
    public NameHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.patra_name_layout, viewGroup, false);
        return new NameHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NameHolder nameHolder, int i) {
        nameHolder.mNameTv.setText(mNameList.get(i));
    }

    @Override
    public int getItemCount() {
        return mNameList.size();
    }

    class NameHolder extends RecyclerView.ViewHolder{

        public TextView mNameTv;
        public NameHolder(@NonNull View itemView) {
            super(itemView);
            mNameTv = itemView.findViewById(R.id.tvPatraRowName);
        }
    }
}
