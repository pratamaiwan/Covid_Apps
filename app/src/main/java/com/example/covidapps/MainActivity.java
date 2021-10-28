package com.example.covidapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toolbar;

import com.example.covidapps.databinding.ActivityMainBinding;
import com.example.covidapps.databinding.FragmentLoginBinding;
import com.example.covidapps.session.SessionManager;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private SessionManager sm;
    private Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);


        sm = new SessionManager(this);
        sm.setId("ID");
        sm.getId();
    }
}
