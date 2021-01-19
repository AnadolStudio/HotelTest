package com.anadolstudio.hotelstest;

import android.util.Log;

import com.anadolstudio.hotelstest.MainContract.View.HotelListView;
import com.anadolstudio.hotelstest.MainContract.View.HotelView;
import com.anadolstudio.hotelstest.model.Hotel;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;

public class MainPresenter implements MainContract.Presenter {
    public static final String TAG = MainPresenter.class.getSimpleName();
    private MainContract.Repository mRepository;
    private CompositeDisposable mCompositeDisposable;

    public MainPresenter() {
        mRepository = new MainRepository();
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void updateHotelList(HotelListView view) {
        if (!view.onPreShow()) {
            return;
        }


        DisposableSingleObserver<List<Hotel>> observer = new DisposableSingleObserver<List<Hotel>>() {
            @Override
            public void onSuccess(List<Hotel> hotels) {
                view.show(hotels);
                view.onPostShow();
            }

            @Override
            public void onError(Throwable e) {
                view.onPostShow();
                Log.i(TAG, "onError: " + e.getMessage());
                e.printStackTrace();
            }
        };
        mCompositeDisposable.add(observer);
        mRepository.loadHotelList(observer);
    }

    @Override
    public void updateHotel(HotelView view,long id) {
        if (!view.onPreShow()) {
            return;
        }

        DisposableSingleObserver<Hotel> observer = new DisposableSingleObserver<Hotel>() {
            @Override
            public void onSuccess(Hotel hotel) {
                view.show(hotel);
                view.onPostShow();
            }

            @Override
            public void onError(Throwable e) {
                view.onPostShow();
                Log.i(TAG, "onError: " + e.getMessage());
                e.printStackTrace();
            }
        };
        mCompositeDisposable.add(observer);
        mRepository.loadHotel(observer, id);
    }

    @Override
    public void onDestroy() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }


}
