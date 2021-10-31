package com.example.covidapps.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.example.covidapps.api.MyApiEndPointInterface;
import com.example.covidapps.api.RetrofitInstance;
import com.example.covidapps.dao.CountryDao;
import com.example.covidapps.dao.CountryInfoDao;
import com.example.covidapps.entity.CountryInfo;
import com.example.covidapps.entity.CountryItem;
import com.example.covidapps.room.AppDatabase;
import com.example.covidapps.room.CountryInfoDatabase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CovidCountryInfoRepository {
    public CountryInfoDao countryInfoDao;
    private MyApiEndPointInterface API;
    private LiveData<List<CountryInfo>> allCountryInfo;
    private CountryInfoDatabase countryInfoDatabase;


    private final ExecutorService networkExecutor =
            Executors.newFixedThreadPool(4);
    private final Executor mainThread = new Executor() {
        private Handler handler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(Runnable command) {
            handler.post(command);
        }
    };

    public CovidCountryInfoRepository(Application application){
        countryInfoDatabase = CountryInfoDatabase.getDatabase(application);
        countryInfoDao = countryInfoDatabase.countryInfoDao();
        allCountryInfo = countryInfoDao.getAllCountriesInfo();
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        API = retrofitInstance.getAPI();
    }

    public void insert(List<CountryInfo> countryItemList){
        new InsertAsyncTask(countryInfoDao).execute(countryItemList);
    };

    public void delete(){
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mainThread.execute(new Runnable() {
                    @Override
                    public void run() {
                        countryInfoDao.deleteAll();
                    }
                });
            }
        });
    }

    public int countCountry(){
        return countryInfoDao.size();
    }

    public LiveData<List<CountryInfo>> getAllCountryInfo(){
        return allCountryInfo;
    }

    public LiveData<List<CountryInfo>> getCountryInfo(String country) { return allCountryInfo;}

    private static  class InsertAsyncTask extends AsyncTask<List<CountryInfo>,Void, Void>{
        private CountryInfoDao countryInfoDao;

        public InsertAsyncTask(CountryInfoDao countryInfoDao){
            this.countryInfoDao = countryInfoDao;
        }

        @Override
        protected Void doInBackground(List<CountryInfo>... lists) {
            countryInfoDao.insert(lists[0]);
            return null;
        }
    }

}
