package com.example.covidapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.covidapps.adapter.CountryAdapter;
import com.example.covidapps.adapter.CovidCountryAdapter;
import com.example.covidapps.api.MyApiEndPointInterface;
import com.example.covidapps.api.RetrofitInstance;
import com.example.covidapps.databinding.ActivityMainBinding;
import com.example.covidapps.entity.CountryItem;
import com.example.covidapps.fragment.CovidCountryFragment;
import com.example.covidapps.model.CountryHeader;
import com.example.covidapps.repository.CovidCountryRepository;
import com.example.covidapps.room.AppDatabase;
import com.example.covidapps.session.SessionManager;
import com.example.covidapps.viewModel.CovidCountryViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private SessionManager sm;
    private CovidCountryRepository repository;
    private CountryAdapter countryAdapter;
    private RecyclerView recyclerView;
    private CovidCountryViewModel model;
    private List<CountryItem> getCountries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);
        Button logout = findViewById(R.id.btnLogout);
        ImageView prof = findViewById(R.id.enterProfile);

        sm = new SessionManager(this);
        sm.getId();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sm.logout();
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();
            }
        });

        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });

        repository = new CovidCountryRepository(getApplication());
        getCountries = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_country);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        model = new ViewModelProvider(this).get(CovidCountryViewModel.class);
        countryAdapter = new CountryAdapter(this, getCountries);

        loadCountries();
        model.getAllCountry().observe(this, new Observer<List<CountryItem>>() {
            @Override
            public void onChanged(List<CountryItem> countryItemList) {
                recyclerView.setAdapter(countryAdapter);
                countryAdapter.getAllCountries(countryItemList);
            }
        });


//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container_main, CovidCountryFragment.newInstance())
//                    .commitNow();
//        }
    }

    private void loadCountries(){
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        MyApiEndPointInterface api = retrofitInstance.getAPI();

        repository.delete();

        Call<List<CountryHeader>> call = api.getCountryData();
        call.enqueue(new Callback<List<CountryHeader>>() {
            @Override
            public void onResponse(Call<List<CountryHeader>> call, Response<List<CountryHeader>> response) {
                List<CountryItem> lists = new ArrayList<>();

                for (int i = 0; i < response.body().size(); i++) {
                    CountryHeader currentData = response.body().get(i);
                    CountryItem roomData = new CountryItem();

                    roomData.setCountry(currentData.getCountry());
                    roomData.setContinent(currentData.getContinent());
                    roomData.setCases(currentData.getCases());
                    roomData.setTodayCases(currentData.getTodayCases());
                    roomData.setDeaths(currentData.getDeaths());
                    roomData.setTodayDeaths(currentData.getTodayDeaths());
                    roomData.setRecovered(currentData.getRecovered());
                    roomData.setTodayRecovered(currentData.getTodayRecovered());

                    Log.d("Dbug", currentData.getCountry() + " " + currentData.getContinent());

                    lists.add(roomData);
                }
                repository.insert(lists);

                Log.d("D_bug", lists.toString());

            }

            @Override
            public void onFailure(Call<List<CountryHeader>> call, Throwable t) {

            }

        });

    }

}
