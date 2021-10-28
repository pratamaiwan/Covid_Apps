package com.example.covidapps.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.example.covidapps.dao.UserDao;
import com.example.covidapps.model.Data;
import com.example.covidapps.room.AppDatabase;

public class UserRepo {
    private UserDao userDao;
    private LiveData<List<Data>> allUsers;

    public UserRepo(Application application){
        AppDatabase database = AppDatabase.getDatabase(application);
        userDao = database.userDao();
        allUsers = userDao.getAllData();
    }

    public LiveData<List<Data>> getAllUsers(){
        return allUsers;
    }
}


