package com.example.covidapps;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.covidapps.dao.UserDao;
import com.example.covidapps.databinding.FragmentLoginBinding;
import com.example.covidapps.databinding.FragmentProfileBinding;
import com.example.covidapps.model.Data;
import com.example.covidapps.repository.UserRepo;
import com.example.covidapps.room.AppDatabase;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private FragmentProfileBinding binding;
    private LiveData<List<Data>> userdata;
    private UserDao userDao;
    private AppDatabase ad;
    private Data data;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userdata = userDao.getAllData();

        binding.tvName.setText(data.getFullName());

    }
}
