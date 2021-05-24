package com.parkho.recyclerview;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class PhRecyclerViewAdapter extends RecyclerView.Adapter<PhRecyclerViewHolder> {

    public interface OnItemClickEventListener {
        void onItemClick(int a_position);
    }

    private List<PhRecyclerItem> mItemList;

    private OnItemClickEventListener mItemClickListener = new OnItemClickEventListener() {
        @Override
        public void onItemClick(int a_position) {
            notifyItemChanged(mCheckedPosition, null);
            mCheckedPosition = a_position;
            notifyItemChanged(a_position, null);
        }
    };

    private int mCheckedPosition = -1;

    public PhRecyclerViewAdapter(List<PhRecyclerItem> a_list) {
        mItemList = a_list;
    }

    @Override
    public PhRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup a_viewGroup, int a_position) {
        View view = LayoutInflater.from(a_viewGroup.getContext()).inflate(R.layout.content_recycler_item, a_viewGroup, false);
        return new PhRecyclerViewHolder(view, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PhRecyclerViewHolder a_viewHolder, int a_position) {
        final PhRecyclerItem item = mItemList.get(a_position);

        final int color;
        if (a_viewHolder.getAdapterPosition() == mCheckedPosition) {
            color = ContextCompat.getColor(a_viewHolder.itemView.getContext(), R.color.green);
        } else {
            color = ContextCompat.getColor(a_viewHolder.itemView.getContext(), android.R.color.transparent);

        }
        a_viewHolder.itemView.setBackgroundColor(color);

        a_viewHolder.ivIcon.setImageResource(item.getImageResId());
        a_viewHolder.tvName.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public PhRecyclerItem getSelected() {
        if (mCheckedPosition > -1) {
            return mItemList.get(mCheckedPosition);
        }
        return null;
    }

    public int getCheckedPosition() {
        return mCheckedPosition;
    }

    public void clearSelected() {
        mCheckedPosition = -1;
    }
}