package com.example.covidapps.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.covidapps.entity.Bookmark;
import com.example.covidapps.entity.CountryItem;
import com.example.covidapps.repository.BookmarkRepository;
import com.example.covidapps.repository.CovidCountryRepository;

import java.util.List;


public class BookmarkViewModel extends AndroidViewModel {

    private BookmarkRepository bookmarkRepository;
    private LiveData<List<Bookmark>> allCountryBookmark;


    public BookmarkViewModel(@NonNull Application application) {
        super(application);
        bookmarkRepository = new BookmarkRepository(application);
        allCountryBookmark = bookmarkRepository.getAllBookmarkCountry();
    }

    public void insert(List<Bookmark> countries){
        bookmarkRepository.insert(countries);
    }

    public LiveData<List<Bookmark>> getAllCountry() {
        return allCountryBookmark;
    }


}
