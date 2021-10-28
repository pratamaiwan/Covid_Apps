package com.example.covidapps.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import com.example.covidapps.model.Data;

@Dao
public interface UserDao {
    @Query("SELECT * FROM Data")
    LiveData<List<Data>> getAllData();

}
