package com.example.covidapps.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.covidapps.dao.UserDao;
import com.example.covidapps.model.Data;
import com.example.covidapps.repository.UserRepo;
import com.example.covidapps.room.AppDatabase;

import java.util.List;

public class UserViewModel extends AndroidViewModel{
    private LiveData<List<Data>> readAllData;
    private UserRepo userRepo;


    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepo = new UserRepo(application);
        readAllData = userRepo.getAllUsers();
    }
}
