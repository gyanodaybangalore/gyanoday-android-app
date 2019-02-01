package com.gdjt.gyanoday.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import com.gdjt.gyanoday.R;
import com.gdjt.gyanoday.beans.TempleBean;
import com.gdjt.gyanoday.callbacks.ITempleRowCallback;

public class TempleListAdapter extends RecyclerView.Adapter<TempleListAdapter.MyViewHolder>  {

    private List<TempleBean> mDataBeanList;

    private Context mContext;
    private ITempleRowCallback mRowClickCallBack;

    public TempleListAdapter(Context aContext, ArrayList<TempleBean> aDataList, ITempleRowCallback aCallBack) {
        this.mContext = aContext;
        this.mDataBeanList = aDataList;
        this.mRowClickCallBack = aCallBack;

    }

    @Override
    public TempleListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row_temple_layout, parent, false);

        return new TempleListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TempleListAdapter.MyViewHolder holder, final int position) {
        final TempleBean curBean = mDataBeanList.get(position);
        holder.mTitleTv.setText(curBean.getHName());
        holder.mSubTitleTv.setText(curBean.getName());
        holder.mAddress.setText(curBean.getAddress());
        String str = "Landmark: " + curBean.getLandmarkStr();
        holder.mLandMark.setText(str);
        holder.mContactTv.setText(curBean.getContact());
        holder.mContactTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRowClickCallBack.onContactClick(curBean.getContact());
            }
        });
        if(curBean.isHaveStay()){
            holder.mStayTv.setTextColor(Color.GREEN);
            holder.mStayTv.setText(mContext.getString(R.string.YesHindiLabel));
        }else{
            holder.mStayTv.setTextColor(Color.RED);
            holder.mStayTv.setText(mContext.getString(R.string.NoHindiLabel));
        }

        if(curBean.isHaveFood()){
            holder.mFoodTv.setTextColor(Color.GREEN);
            holder.mFoodTv.setText(mContext.getString(R.string.YesHindiLabel));
        }else{
            holder.mFoodTv.setTextColor(Color.RED);
            holder.mFoodTv.setText(mContext.getString(R.string.NoHindiLabel));
        }

        if(!curBean.getLatitude().isEmpty() && !curBean.getLongitude().isEmpty()){
            holder.mDirectionBtn.setVisibility(View.VISIBLE);
            holder.mDirectionBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRowClickCallBack.onDirectionClick(curBean);
                }
            });

        }else{
            holder.mDirectionBtn.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mDataBeanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTitleTv;
        TextView mSubTitleTv;
        TextView mAddress;
        TextView mStayTv;
        TextView mFoodTv;
        TextView mContactTv;
        TextView mLandMark;
        Button mDirectionBtn;


        public MyViewHolder(View itemView) {
            super(itemView);
            mTitleTv = itemView.findViewById(R.id.tv_row_temple_title);
            mSubTitleTv = itemView.findViewById(R.id.tv_row_temple_subtitle);
            mAddress = itemView.findViewById(R.id.tv_row_data_temple_add);
            mStayTv = itemView.findViewById(R.id.tv_row_temple_stay_val);
            mFoodTv = itemView.findViewById(R.id.tv_row_temple_food_val);
            mContactTv = itemView.findViewById(R.id.tv_row_temple_contact_val);
            mLandMark = itemView.findViewById(R.id.tv_row_temple_landmark_val);
            mDirectionBtn = itemView.findViewById(R.id.btn_row_temple_map);
        }
    }


}
