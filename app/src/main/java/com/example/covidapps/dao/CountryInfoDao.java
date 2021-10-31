package com.example.covidapps.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.covidapps.entity.Bookmark;
import com.example.covidapps.entity.CountryInfo;

import java.util.List;


@Dao
public interface CountryInfoDao {
    @Query("SELECT * FROM CountryInfo")
    LiveData<List<CountryInfo>> getAllCountriesInfo();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<CountryInfo> item);

    @Query("DELETE FROM CountryInfo")
    void deleteAll();

    @Query("SELECT COUNT(1) FROM CountryInfo")
    int size();

    @Query("SELECT * FROM CountryInfo where country = :country")
    LiveData<List<CountryInfo>> getCountryInfo(String country);

}
