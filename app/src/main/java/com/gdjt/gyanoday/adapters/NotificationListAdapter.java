package com.gdjt.gyanoday.adapters;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import com.gdjt.gyanoday.R;
import com.gdjt.gyanoday.beans.NotificationBean;
import com.gdjt.gyanoday.callbacks.IRowClickCallback;
import com.squareup.picasso.Picasso;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.MyViewHolder>  {

    private List<NotificationBean> mDataBeanList;

    private Context mContext;
    private IRowClickCallback mRowClickCallBack;

    public NotificationListAdapter(Context aContext, ArrayList<NotificationBean> aDataList, IRowClickCallback aCallBack) {
        this.mContext = aContext;
        this.mDataBeanList = aDataList;
        this.mRowClickCallBack = aCallBack;
    }

    @Override
    public NotificationListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row_notification, parent, false);

        return new NotificationListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotificationListAdapter.MyViewHolder holder, final int position) {
        final NotificationBean curBean = mDataBeanList.get(position);
        holder.mTitleTv.setText(curBean.getTitle());
        holder.mDateTv.setText(curBean.getDateStr());
        if(curBean.getImageURL() != null && curBean.getImageURL().length() > 0){
            Picasso.get().load(curBean.getImageURL()).into(holder.mLogoIv);
        }
        
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            if(!curBean.getNotificationMessage().isEmpty())
            holder.mSubTitleTv.setText(Html.fromHtml(curBean.getNotificationMessage(), Html.FROM_HTML_MODE_COMPACT));

        }else{
            if(!curBean.getNotificationMessage().isEmpty())
            holder.mSubTitleTv.setText(Html.fromHtml(curBean.getNotificationMessage()));
        }

        holder.notificationListItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRowClickCallBack.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataBeanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout notificationListItemLayout;
        TextView mTitleTv;
        TextView mSubTitleTv;
        ImageView mLogoIv;
        TextView mDateTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            notificationListItemLayout = itemView.findViewById(R.id.ll_row_notification_main);
            mTitleTv = itemView.findViewById(R.id.tv_row_data_notification_title);
            mSubTitleTv = itemView.findViewById(R.id.tv_row_data_notification_subtitle);
            mLogoIv = itemView.findViewById(R.id.iv_item_row_notification_icon);
            mDateTv = itemView.findViewById(R.id.tv_row_data_notification_date);
        }
    }


}
