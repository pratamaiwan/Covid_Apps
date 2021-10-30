package com.example.covidapps.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.covidapps.api.MyApiEndPointInterface;
import com.example.covidapps.api.RetrofitInstance;
import com.example.covidapps.dao.CountryDao;
import com.example.covidapps.entity.CountryItem;
import com.example.covidapps.model.CountryHeader;
import com.example.covidapps.room.AppDatabase;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CovidCountryRepository {
    public CountryDao countryDao;
    private MyApiEndPointInterface API;
    private MutableLiveData<List<CountryHeader>> allCovidCountry = new MutableLiveData<>();
    private LiveData<List<CountryItem>> allCountry;
    private AppDatabase appDatabase;


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
        appDatabase = AppDatabase.getDatabase(application);
        countryDao = appDatabase.countryDao();
        allCountry = countryDao.getAllCountry();
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        API = retrofitInstance.getAPI();
    }

    public void insert(List<CountryItem> countryItemList){
        new InsertAsyncTask(countryDao).execute(countryItemList);
    };

    public void delete(){
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mainThread.execute(new Runnable() {
                    @Override
                    public void run() {
                        countryDao.deleteAll();
                    }
                });
            }
        });
    }

    public LiveData<List<CountryItem>> getAllCountry(){
        return allCountry;
    }

    private static  class InsertAsyncTask extends AsyncTask<List<CountryItem>,Void, Void>{
        private CountryDao countryDao;

        public InsertAsyncTask(CountryDao countryDao){
            this.countryDao = countryDao;
        }

        @Override
        protected Void doInBackground(List<CountryItem>... lists) {
            countryDao.insert(lists[0]);
            return null;
        }
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
