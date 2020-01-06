package com.salajim.musab.catlovers.api;

import com.salajim.musab.catlovers.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    public static final String api_key = BuildConfig.API_KEY;
    public static final String BASE_URL = "https://api.thecatapi.com/v1/images/search?";
    public static final String QUERY_API = "api_key";

}
