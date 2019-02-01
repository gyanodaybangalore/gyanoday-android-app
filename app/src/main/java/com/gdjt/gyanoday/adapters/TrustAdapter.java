package com.gdjt.gyanoday.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import com.gdjt.gyanoday.Constant;
import com.gdjt.gyanoday.R;
import com.gdjt.gyanoday.beans.TrustDataBean;

public class TrustAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<TrustDataBean> mTrustDataList;
    private Context context;

    // Constructor
    public TrustAdapter(Context context, ArrayList<TrustDataBean> aList) {
        this.context = context;
        mTrustDataList = aList;
    }


    // We need to override this as we need to differentiate
    // which type viewHolder to be attached
    // This is being called from onBindViewHolder() method
    @Override
    public int getItemViewType(int position) {
        return mTrustDataList.get(position).getViewType();
    }

    // Invoked by layout manager to replace the contents of the views
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        TrustDataBean curBean = mTrustDataList.get(position);
        int viewType = holder.getItemViewType();
        switch (viewType) {
            case Constant.ROW_TYPE_HEADER_INDEX:
                HeaderHolder  headerHolder = (HeaderHolder) holder;
                headerHolder.mHeaderTv.setText(curBean.getName());
                break;
            case Constant.ROW_TYPE_CHILD_INDEX:
                ChildHolder  childHolder = (ChildHolder) holder;
                childHolder.mNameTv.setText(curBean.getName());
                childHolder.mWorkTv.setText(curBean.getWork());
                Picasso.get().load(curBean.getImgPath()).into(childHolder.mIconIv);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mTrustDataList.size();
    }

    // Invoked by layout manager to create new views
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        switch (viewType) {
            case Constant.ROW_TYPE_HEADER_INDEX:

                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_trust_header_layout, parent, false);

                viewHolder = new HeaderHolder(itemView);
                break;
            case Constant.ROW_TYPE_CHILD_INDEX:
                View itemView1 = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_trust_child_layout, parent, false);
                viewHolder = new ChildHolder(itemView1);
                break;
            default:
                viewHolder = null;
                break;
        }
        return viewHolder;
    }

    public class HeaderHolder extends RecyclerView.ViewHolder {
        private TextView mHeaderTv;

        public HeaderHolder(View itemView) {
            super(itemView);
            mHeaderTv = (TextView) itemView.findViewById(R.id.tv_row_trust_header);
        }
    }

    public class ChildHolder extends RecyclerView.ViewHolder {

        public TextView mNameTv;
        public TextView mWorkTv;
        public CircleImageView mIconIv;

        public ChildHolder(View itemView) {
            super(itemView);
            // Initiate view
            mNameTv = (TextView) itemView.findViewById(R.id.tv_row_data_trust_title);
            mWorkTv = (TextView) itemView.findViewById(R.id.tv_row_data_trust_subtitle);
            mIconIv = (CircleImageView) itemView.findViewById(R.id.iv_item_row_trust_icon);
        }
    }
}
