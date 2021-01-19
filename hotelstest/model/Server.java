package com.anadolstudio.hotelstest.model;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Server {
    public static final String BASE_URL = "https://raw.githubusercontent.com/iMofas/ios-android-test/master/";
    public static final String BASE_IMAGE_URl = "https://github.com/iMofas/ios-android-test/raw/master/";
    private static Server server;
    private static ServerApi serverApi;

    private Server() {
    }

    public static Server get() {
        if (server == null) {
            server = new Server();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            serverApi = retrofit.create(ServerApi.class);
        }

        return server;
    }

    public ServerApi getApi() {
        return serverApi;
    }
}
