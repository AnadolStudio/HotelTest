package com.anadolstudio.hotelstest.presenter;

import com.anadolstudio.hotelstest.model.Hotel;

import java.util.Comparator;

public class HotelComparators {
    public static class ComparatorHotelSuites implements Comparator<Hotel> {

        @Override
        public int compare(Hotel o1, Hotel o2) {
            return Integer.compare(o2.getCountSuitesAvailability(), o1.getCountSuitesAvailability());
        }
    }

    public static class ComparatorHotelDistance implements Comparator<Hotel> {

        @Override
        public int compare(Hotel o1, Hotel o2) {
            return Double.compare(o1.getDistance(), o2.getDistance());
        }
    }
}
