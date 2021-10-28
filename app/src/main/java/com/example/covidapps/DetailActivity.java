package com.example.covidapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.covidapps.adapter.CovidCountryAdapter;
import com.example.covidapps.databinding.ActivityDetailBinding;
import com.example.covidapps.session.SessionManager;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    private SharedPreferences sp;
    CovidCountryAdapter cca;
    SessionManager sm;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        ImageView back = findViewById(R.id.backIcon);

        sm = new SessionManager(this);
        sm.getId();



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });

    }
}