package com.anadolstudio.hotelstest;

import android.util.Log;

import com.anadolstudio.hotelstest.model.Hotel;
import com.anadolstudio.hotelstest.model.Server;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainRepository implements MainContract.Repository{
    public static final String TAG = MainRepository.class.getSimpleName();

    @Override
    public void loadHotelList(SingleObserver<List<Hotel>> observer) {
        Log.i(TAG, "uploadHotelList");
        Server.get().getApi().getHotelList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void loadHotel(SingleObserver<Hotel> observer, long id) {
        Log.i(TAG, "uploadHotel " + id);
        Server.get().getApi().getHotel(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
