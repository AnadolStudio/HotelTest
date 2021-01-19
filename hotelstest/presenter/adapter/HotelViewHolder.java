package com.anadolstudio.hotelstest.presenter.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.anadolstudio.hotelstest.R;
import com.anadolstudio.hotelstest.databinding.ItemHotelListBinding;
import com.anadolstudio.hotelstest.model.Hotel;
import com.anadolstudio.hotelstest.presenter.FormatDistanceKm;


public class HotelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ItemHotelListBinding mBinding;
    private Hotel mHotel;
    private Detail mInterface;

    public HotelViewHolder(@NonNull View itemView, Detail mInterface) {
        super(itemView);
        mBinding = ItemHotelListBinding.bind(itemView);
        this.mInterface = mInterface;
        itemView.setOnClickListener(this);
    }

    public void onBind(Hotel hotel) {
        mHotel = hotel;
        mBinding.textNameHotel.setText(hotel.getName());
        mBinding.textAddInfo.setText(createAddInfo(hotel.getDistance(), hotel.getCountSuitesAvailability()));
        createStars(hotel.getStars());
    }

    private void createStars(int stars) {
        mBinding.starsContainer.removeAllViews();

        Context context = itemView.getContext();
        ImageView imageView;
        Drawable drawable;
        Log.i("TAG", "createStars: " + stars);
        for (int i = 0; i < stars; i++) {

            drawable = ContextCompat.getDrawable(context, R.drawable.ic_star);

            imageView = new ImageView(context);
            imageView.setImageDrawable(drawable);
            mBinding.starsContainer.addView(imageView);
        }
    }

    private String createAddInfo(double distance, int countSuites) {
        Resources resources = itemView.getResources();

        String distanceStr = resources.getString(R.string.distance_short, FormatDistanceKm.format(distance));
        String countSuitesStr = resources.getQuantityString(R.plurals.count_suites_short, countSuites, countSuites);
        return resources.getString(R.string.additional_information, distanceStr, countSuitesStr);
    }

    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        if (position == RecyclerView.NO_POSITION) return;

        mInterface.onDetail(mHotel.getId());
    }
}