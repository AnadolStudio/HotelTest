package com.anadolstudio.hotelstest.model;

import com.anadolstudio.hotelstest.model.Hotel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServerApi {

    @GET("0777.json")
    Single<List<Hotel>> getHotelList();

    @GET("{id}.json")
    Single<Hotel> getHotel(@Path("id") long id);
}
