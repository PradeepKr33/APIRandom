package com.example.apirandom;

import com.example.apirandom.response.MealResponse;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public interface API {
    @GET("random.php")
    Call<MealResponse> getMealsData();

}
