package com.anadolstudio.hotelstest;

import com.anadolstudio.hotelstest.model.Hotel;

import java.util.List;

import io.reactivex.SingleObserver;

public interface MainContract {

    interface View<T> {
        void show(T t);

        boolean onPreShow();

        void onPostShow();

        interface HotelListView extends View<List<Hotel>> {
            @Override
            void show(List<Hotel> hotels);
        }

        interface HotelView extends View<Hotel> {
            @Override
            void show(Hotel hotels);
        }
    }

    interface Presenter {

        void updateHotelList(View.HotelListView view);

        void updateHotel(View.HotelView view, long id);

        void onDestroy();
    }

    interface Repository {

        void loadHotelList(SingleObserver<List<Hotel>> observer);

        void loadHotel(SingleObserver<Hotel> observer, long id);
    }
}
