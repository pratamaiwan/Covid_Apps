package com.example.covidapps;

import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.covidapps.databinding.FragmentLoginBinding;
import com.example.covidapps.databinding.FragmentProfileBinding;

public class ProfileActivity extends AppCompatActivity {
    private FragmentProfileBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
