package com.anadolstudio.hotelstest.view.activity;


import androidx.fragment.app.Fragment;

import com.anadolstudio.hotelstest.view.fragment.HotelListFragment;

public class HotelListActivity extends SimpleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return HotelListFragment.newInstance();
    }
}