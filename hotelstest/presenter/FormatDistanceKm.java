package com.anadolstudio.hotelstest.presenter;

public class FormatDistanceKm {

    public static String format(double distance){
        return String.format("%.2f", distance / 1000);
    }
}
