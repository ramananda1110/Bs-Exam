package com.orko.androidtestproject.network;

import com.orko.androidtestproject.model.ImageData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiService {

    @Headers("Accept: application/json")
    @GET("v2/list?")
    Call<List<ImageData>> getImageList();

}
