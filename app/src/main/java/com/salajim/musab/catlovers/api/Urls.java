package com.salajim.musab.catlovers.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Urls {
    public static final String BASE_URL = "https://api.thecatapi.com/v1/images/search?";
    public static Retrofit retrofit = null;

    public static Retrofit getUrl() {
        if (retrofit == null) {
            retrofit  = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
