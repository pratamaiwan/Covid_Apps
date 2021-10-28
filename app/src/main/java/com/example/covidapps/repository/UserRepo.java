package com.example.covidapps.repository;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.covidapps.dao.UserDao;
import com.example.covidapps.model.Data;
import com.example.covidapps.room.AppDatabase;

public class UserRepo {
    private UserDao userDao;
    private LiveData<List<Data>> allUsers;

    private final ExecutorService networkExecutor =
            Executors.newFixedThreadPool(4);
    private final Executor mainThread = new Executor() {
        private Handler handler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(Runnable command) {
            handler.post(command);
        }
    };


    public UserRepo(Application application){
        AppDatabase database = AppDatabase.getDatabase(application);
        userDao = database.userDao();
        allUsers = userDao.getAllData();
    }

    public LiveData<List<Data>> getAllUsers(){
        return allUsers;
    }
}


