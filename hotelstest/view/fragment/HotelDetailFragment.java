package com.anadolstudio.hotelstest.view.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anadolstudio.hotelstest.MainContract;
import com.anadolstudio.hotelstest.MainPresenter;
import com.anadolstudio.hotelstest.R;
import com.anadolstudio.hotelstest.databinding.FragmentHotelDetailBinding;
import com.anadolstudio.hotelstest.model.Hotel;
import com.anadolstudio.hotelstest.presenter.DownloadImage;
import com.anadolstudio.hotelstest.presenter.FormatDistanceKm;
import com.anadolstudio.hotelstest.presenter.InternetConnection;
import com.anadolstudio.hotelstest.view.GoogleMaps;

import static com.anadolstudio.hotelstest.view.activity.HotelDetailActivity.HOTEL_ID;

public class HotelDetailFragment extends SimpleFragment implements MainContract.View.HotelView {
    public static final String TAG = HotelDetailFragment.class.getName();
    private FragmentHotelDetailBinding mBinding;

    private long hotelId;
    private MainContract.Presenter mPresenter;

    public static HotelDetailFragment newInstance(long id) {
        HotelDetailFragment fragment = new HotelDetailFragment();
        Bundle args = new Bundle();
        args.putLong(HOTEL_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            hotelId = getArguments().getLong(HOTEL_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentHotelDetailBinding.inflate(inflater);
        mPresenter = new MainPresenter();
        mPresenter.updateHotel(this, hotelId);
        return mBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public boolean onPreShow() {
        boolean already;
        already = InternetConnection.isNetworkAvailable(getContext());

        if (already) {
            showLoadingDialog();
        }

        return already;
    }

    @Override
    public void show(Hotel hotel) {
        Resources resources = getResources();

        DownloadImage.download(hotel, mBinding.image);
        mBinding.textName.setText(hotel.getName());
        mBinding.textAddress.setText(getString(R.string.address, hotel.getAddress()));
        mBinding.textDistance.setText(getString(R.string.distance_long, FormatDistanceKm.format(hotel.getDistance())));

        int numberSuites = hotel.getCountSuitesAvailability();
        mBinding.textNumberSuites.setText(resources.getQuantityString(R.plurals.count_suites_long, numberSuites, numberSuites));
        mBinding.btnOpen.setOnClickListener(v -> {
            GoogleMaps.openMaps(getContext(), hotel.getLat(), hotel.getLon(), hotel.getAddress());
        });
    }

    @Override
    public void onPostShow() {
        hideLoadingDialog();
    }
}