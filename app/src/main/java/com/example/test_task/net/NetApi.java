package com.example.test_task.net;

import com.example.test_task.models.Cities;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NetApi {
    @GET("test")
    Call<Cities> loadCities();
}