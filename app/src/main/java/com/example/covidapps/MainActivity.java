package com.example.covidapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.covidapps.session.SessionManager;

public class MainActivity extends AppCompatActivity {

    private SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm = new SessionManager(this);
        sm.setId("ID");
        sm.getId();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_main, CovidCountryFragment.newInstance())
                    .commitNow();
        }
    }

}
