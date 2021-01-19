package com.anadolstudio.hotelstest.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anadolstudio.hotelstest.presenter.InternetConnection;
import com.anadolstudio.hotelstest.MainContract;
import com.anadolstudio.hotelstest.MainPresenter;
import com.anadolstudio.hotelstest.R;
import com.anadolstudio.hotelstest.databinding.FragmentHotelListBinding;
import com.anadolstudio.hotelstest.model.Hotel;
import com.anadolstudio.hotelstest.presenter.HotelComparators;
import com.anadolstudio.hotelstest.presenter.adapter.Detail;
import com.anadolstudio.hotelstest.presenter.adapter.HotelListAdapter;
import com.anadolstudio.hotelstest.view.activity.HotelDetailActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HotelListFragment extends SimpleFragment implements Detail, MainContract.View.HotelListView {
    public static final String TAG = HotelListFragment.class.getName();
    private FragmentHotelListBinding mBinding;
    private HotelListAdapter mAdapter;
    private MainContract.Presenter mPresenter;

    public static HotelListFragment newInstance() {

        Bundle args = new Bundle();

        HotelListFragment fragment = new HotelListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentHotelListBinding.inflate(inflater);
        View view = mBinding.getRoot();

        setup();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_hotel_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_sort_number_suites:
                sortHotels(mAdapter.getData(), new HotelComparators.ComparatorHotelSuites());
                return true;

            case R.id.menu_sort_distance:
                sortHotels(mAdapter.getData(), new HotelComparators.ComparatorHotelDistance());
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sortHotels(List<Hotel> data, Comparator<Hotel> comparator) {
        ArrayList<Hotel> list = new ArrayList<>(data);
        Collections.sort(list, comparator);
        mAdapter.setData(list);
    }

    private void setup() {
        RecyclerView recyclerView = mBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new HotelListAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(mAdapter);
        mPresenter = new MainPresenter();
        mPresenter.updateHotelList(this);

        mBinding.swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorSecondary));
        mBinding.swipeRefresh.setOnRefreshListener(() -> {
            mBinding.swipeRefresh.setRefreshing(false);
            mPresenter.updateHotelList(this);
        });
    }

    @Override
    public void onDetail(long id) {
        Intent intent = HotelDetailActivity.newIntent(getContext(), id);
        startActivity(intent);
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
    public void show(List<Hotel> hotels) {
        mAdapter.setData(hotels);
    }

    @Override
    public void onPostShow() {
        hideLoadingDialog();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}