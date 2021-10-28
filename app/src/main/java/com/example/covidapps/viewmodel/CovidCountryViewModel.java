package com.example.covidapps.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.covidapps.model.covid.CountryHeader;
import com.example.covidapps.repository.CovidCountryRepository;

import java.util.List;


public class CovidCountryViewModel extends AndroidViewModel {

    private CovidCountryRepository covidCountryRepository;
    private MutableLiveData<List<CountryHeader>> allCovidCountry = new MutableLiveData<>();

    public CovidCountryViewModel(@NonNull Application application) {
        super(application);
        covidCountryRepository = new CovidCountryRepository(application);
        allCovidCountry = covidCountryRepository.getAllCovidCountry();
    }

    public MutableLiveData<List<CountryHeader>> getAllCovidCountry(){
        return allCovidCountry;
    }

}
