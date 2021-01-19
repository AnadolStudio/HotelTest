package com.anadolstudio.hotelstest.presenter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.anadolstudio.hotelstest.model.Hotel;
import com.anadolstudio.hotelstest.R;

import java.util.ArrayList;
import java.util.List;

public class HotelListAdapter extends RecyclerView.Adapter<HotelViewHolder> {
    public static final String TAG = HotelListAdapter.class.getName();

    private List<Hotel> mList;
    private Detail mDetailActivity;

    public HotelListAdapter(ArrayList<Hotel> list, Detail myInterface) {
        mList = list;
        mDetailActivity = myInterface;
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotel_list, parent, false);

        return new HotelViewHolder(view, mDetailActivity);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        holder.onBind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public List<Hotel> getData() {
        return mList;
    }

    public void setData(List<Hotel> list) {
        HotelDiffUtilCallback hotelDiffUtilCallback =
                new HotelDiffUtilCallback(mList, list);
        DiffUtil.DiffResult hotelDiffResult = DiffUtil.calculateDiff(hotelDiffUtilCallback, false);

        mList = list;
        hotelDiffResult.dispatchUpdatesTo(this);
    }
}
