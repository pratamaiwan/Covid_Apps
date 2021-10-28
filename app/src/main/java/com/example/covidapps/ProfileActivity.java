package com.example.covidapps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.covidapps.databinding.FragmentProfileBinding;
import com.example.covidapps.model.Data;
import com.example.covidapps.session.SessionManager;

import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    private FragmentProfileBinding binding;
    private SharedPreferences sp;
    SessionManager sm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ImageView back = findViewById(R.id.backIcon);

        sm= new SessionManager(this);
        sm.getId();

        binding.tvName.setText(sm.getFULLNAME());
        binding.tvEmail.setText(sm.getEMAIL());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });

    }

}
