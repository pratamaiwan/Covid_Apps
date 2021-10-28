package com.example.covidapps.repository;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.MutableLiveData;

import com.example.covidapps.api.MyApiEndPointInterface;
import com.example.covidapps.api.RetrofitInstance;
import com.example.covidapps.model.covid.CountryHeader;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CovidCountryRepository {

    private MyApiEndPointInterface API;
    private MutableLiveData<List<CountryHeader>> allCovidCountry = new MutableLiveData<>();

    private final ExecutorService networkExecutor =
            Executors.newFixedThreadPool(4);
    private final Executor mainThread = new Executor() {
        private Handler handler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(Runnable command) {
            handler.post(command);
        }
    };

    public CovidCountryRepository(Application application){
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        API = retrofitInstance.getAPI();
    }

    public void getCovidCountryFromNetwork(){
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    List<CountryHeader> countryHeadersFromNetwork = API.getCountryData().execute().body();
                    mainThread.execute(new Runnable() {
                        @Override
                        public void run() {
                            allCovidCountry.setValue(countryHeadersFromNetwork);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MutableLiveData<List<CountryHeader>> getAllCovidCountry(){
        if (allCovidCountry.getValue() == null || allCovidCountry.getValue().isEmpty())
            getCovidCountryFromNetwork();
        return allCovidCountry;
    }

}
