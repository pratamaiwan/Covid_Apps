package com.example.covidapps.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServerLogin {
    private static final String base_url = "https://talentpool.oneindonesia.id/api/";

    private static Retrofit setInit(){
        OkHttpClient client = new TokenInterceptor().getClient();
        return new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public static APIInterface getInstance(){
        return setInit().create(APIInterface.class);
    }
}