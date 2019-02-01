package com.gdjt.gyanoday.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import com.gdjt.gyanoday.R;
import com.gdjt.gyanoday.beans.DataBean;
import com.gdjt.gyanoday.callbacks.IRowClickCallback;

public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.MyViewHolder> implements Filterable {

    private List<DataBean> mDataBeanList;
    private List<DataBean> mFilteredDataBeanList;

    private Context mContext;
    private IRowClickCallback mRowClickCallBack;

    public CustomListAdapter(Context aContext, ArrayList<DataBean> aDataList, IRowClickCallback aCallBack) {
        this.mContext = aContext;
        this.mDataBeanList = aDataList;
        this.mRowClickCallBack = aCallBack;
        this.mFilteredDataBeanList = aDataList;
    }

    @Override
    public CustomListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row_list_layout, parent, false);

        return new CustomListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomListAdapter.MyViewHolder holder, final int position) {
        final DataBean curBean = mFilteredDataBeanList.get(position);
        holder.mTitleTv.setText(curBean.getHinName());
        holder.mSubTitleTv.setText(curBean.getEngName());
        holder.mMainLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRowClickCallBack.onClick(curBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFilteredDataBeanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mMainLl;
        TextView mTitleTv;
        TextView mSubTitleTv;
        ImageView mLogoIv;

        public MyViewHolder(View itemView) {
            super(itemView);
            mMainLl = itemView.findViewById(R.id.ll_row_data_list_main);
            mTitleTv = itemView.findViewById(R.id.tv_row_data_trust_title);
            mSubTitleTv = itemView.findViewById(R.id.tv_row_data_trust_subtitle);
            mLogoIv = itemView.findViewById(R.id.iv_item_row_trust_icon);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mFilteredDataBeanList = mDataBeanList;
                } else {
                    List<DataBean> filteredList = new ArrayList<>();
                    for (DataBean row : mDataBeanList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getEngName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    mFilteredDataBeanList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredDataBeanList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredDataBeanList = (ArrayList<DataBean>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
