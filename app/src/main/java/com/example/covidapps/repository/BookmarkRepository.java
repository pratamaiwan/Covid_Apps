package com.example.covidapps.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;

import com.example.covidapps.api.MyApiEndPointInterface;
import com.example.covidapps.api.RetrofitInstance;
import com.example.covidapps.dao.BookmarkDao;
import com.example.covidapps.dao.CountryDao;
import com.example.covidapps.entity.Bookmark;
import com.example.covidapps.entity.CountryItem;
import com.example.covidapps.room.AppDatabase;
import com.example.covidapps.room.BookmarkDatabase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookmarkRepository {
    public BookmarkDao bookmarkDao;
    private LiveData<List<Bookmark>> allCountryBookmark;
    private BookmarkDatabase bookmarkDatabase;


    private final ExecutorService networkExecutor =
            Executors.newFixedThreadPool(4);
    private final Executor mainThread = new Executor() {
        private Handler handler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(Runnable command) {
            handler.post(command);
        }
    };

    public BookmarkRepository(Application application){
        bookmarkDatabase = BookmarkDatabase.getDatabase(application);
        bookmarkDao = bookmarkDatabase.bookmarkDao();
        allCountryBookmark = bookmarkDao.getAllCountryBookmark();
    }

    public void insert(List<Bookmark> countryBookmarkList){
        new InsertAsyncTask(bookmarkDao).execute(countryBookmarkList);
    };

    public int countBookmark(){
        return bookmarkDao.size();
    }


    public void delete(){
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mainThread.execute(new Runnable() {
                    @Override
                    public void run() {
                        bookmarkDao.deleteAll();
                    }
                });
            }
        });
    }

    public void deleteBookmark(String country){
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mainThread.execute(new Runnable() {
                    @Override
                    public void run() {
                        bookmarkDao.deleteBookmark(country);
                    }
                });
            }
        });
    }

    public int checkExist(String country){
        return bookmarkDao.checkExist(country);
    }


    public LiveData<List<Bookmark>> getAllBookmarkCountry(){
        return allCountryBookmark;
    }

    private static  class InsertAsyncTask extends AsyncTask<List<Bookmark>,Void, Void>{
        private BookmarkDao bookmarkDao;

        public InsertAsyncTask(BookmarkDao bookmarkDao){
            this.bookmarkDao = bookmarkDao;
        }

        @Override
        protected Void doInBackground(List<Bookmark>... lists) {
            bookmarkDao.insert(lists[0]);
            return null;
        }
    }

}
