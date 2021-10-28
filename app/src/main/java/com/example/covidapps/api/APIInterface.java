package com.example.covidapps.api;

import com.example.covidapps.model.ResponseUsers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {

    @FormUrlEncoded
    @POST("user/login")
    Call<ResponseUsers> login(@Field("username") String username, @Field("password") String password);
}
