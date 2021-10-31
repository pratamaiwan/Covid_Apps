package com.example.covidapps.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.covidapps.entity.Bookmark;
import com.example.covidapps.entity.CountryItem;

import java.util.List;


@Dao
public interface BookmarkDao {
    @Query("SELECT * FROM Bookmark")
    LiveData<List<Bookmark>> getAllCountryBookmark();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Bookmark> item);

    @Query("DELETE FROM Bookmark")
    void deleteAll();

    @Query("SELECT COUNT(1) FROM Bookmark")
    int size();

    @Query("SELECT COUNT(1) FROM Bookmark where country = :country")
    int checkExist(String country);

    @Query("DELETE FROM Bookmark where country = :country")
    void deleteBookmark(String country);
}
