package com.example.covidapps.viewModel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.covidapps.entity.CountryItem;
import com.example.covidapps.model.CountryHeader;
import com.example.covidapps.repository.CovidCountryRepository;

import java.util.List;


public class CovidCountryViewModel extends AndroidViewModel {

    private CovidCountryRepository covidCountryRepository;
    private LiveData<List<CountryItem>> allCountry;


    public CovidCountryViewModel(@NonNull Application application) {
        super(application);
        covidCountryRepository = new CovidCountryRepository(application);
        allCountry = covidCountryRepository.getAllCountry();
    }

    public void insert(List<CountryItem> countries){
        covidCountryRepository.insert(countries);
    }


    public LiveData<List<CountryItem>> getAllCountry() {
        return allCountry;
    }



}
