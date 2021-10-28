package com.example.covidapps.api;

import com.example.covidapps.model.covid.CountryHeader;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyApiEndPointInterface {

    // Trailing slash is needed
    String BASE_COVID_URL = "https://corona.lmao.ninja/v2/";

    @GET("countries?sort=country/")
    Call<List<CountryHeader>> getCountryData();


}
