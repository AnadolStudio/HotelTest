package com.anadolstudio.hotelstest.view.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.anadolstudio.hotelstest.view.fragment.HotelDetailFragment;

public class HotelDetailActivity extends SimpleFragmentActivity {
    public static final String HOTEL_ID = "hotel_id";

    public static Intent newIntent(Context context, long id) {
        Intent intent = new Intent(context, HotelDetailActivity.class);
        intent.putExtra(HOTEL_ID, id);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return HotelDetailFragment.newInstance(getIntent().getLongExtra(HOTEL_ID, -1));
    }
}