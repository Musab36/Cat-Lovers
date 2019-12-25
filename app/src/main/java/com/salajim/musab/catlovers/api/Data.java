package com.salajim.musab.catlovers.api;

import com.salajim.musab.catlovers.models.CatsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Data {

    @GET("url")
    Call<CatsResponse> getImages(@Query("api_key")String api_key);
}
