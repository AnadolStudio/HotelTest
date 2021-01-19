package com.anadolstudio.hotelstest.presenter.adapter;

import androidx.recyclerview.widget.DiffUtil;

import com.anadolstudio.hotelstest.model.Hotel;

import java.util.List;

public class HotelDiffUtilCallback extends DiffUtil.Callback {
    private final List<Hotel> oldList;
    private final List<Hotel> newList;

    public HotelDiffUtilCallback(List<Hotel> oldList, List<Hotel> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }


    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Hotel oldHotel = oldList.get(oldItemPosition);
        Hotel newHotel = newList.get(newItemPosition);

        return oldHotel.getId() == newHotel.getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Hotel oldHotel = oldList.get(oldItemPosition);
        Hotel newHotel = newList.get(newItemPosition);
        return oldHotel.equals(newHotel);
    }
}
