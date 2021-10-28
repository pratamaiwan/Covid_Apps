package com.example.covidapps.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.covidapps.entity.CountryItem;

import java.util.List;


@Dao
public interface CountryDao {
    @Query("SELECT * FROM CountryItem")
    List<CountryItem> getAllCountry();

    @Insert
    void insert(CountryItem item);
}
