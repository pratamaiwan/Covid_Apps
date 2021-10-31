package com.example.covidapps.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.covidapps.entity.CountryItem;

import java.util.List;


@Dao
public interface CountryDao {
    @Query("SELECT * FROM CountryItem")
    LiveData<List<CountryItem>> getAllCountry();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<CountryItem> item);

    @Query("DELETE FROM CountryItem")
    void deleteAll();

    @Query("SELECT COUNT(1) FROM CountryItem")
    int size();

    @Query("SELECT * FROM CountryItem where country Like :country")
    LiveData<List<CountryItem>> getSearchedCountry(String country);
}
