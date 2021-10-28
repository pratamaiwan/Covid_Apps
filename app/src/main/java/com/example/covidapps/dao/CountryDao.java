package com.example.covidapps.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.covidapps.entity.Country;

import java.util.List;

import retrofit2.http.DELETE;

@Dao
public interface CountryDao {
    //@Query("SELECT * FROM Country")
    //List<Country> getAllCountry();

   // @Insert
    //void insertBookmark(Country...countries);

//    @Delete
 //   void deleteBookmark(Country country);
}
