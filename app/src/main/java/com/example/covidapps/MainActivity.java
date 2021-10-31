package com.example.covidapps;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.covidapps.adapter.CountryAdapter;
import com.example.covidapps.api.MyApiEndPointInterface;
import com.example.covidapps.api.RetrofitInstance;
import com.example.covidapps.databinding.ActivityMainBinding;
import com.example.covidapps.entity.CountryInfo;
import com.example.covidapps.entity.CountryItem;
import com.example.covidapps.model.CountryHeader;
import com.example.covidapps.repository.CovidCountryInfoRepository;
import com.example.covidapps.repository.CovidCountryRepository;
import com.example.covidapps.session.SessionManager;
import com.example.covidapps.viewModel.CovidCountryViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private SessionManager sm;
    private CovidCountryRepository repository;
    private CovidCountryInfoRepository repositoryInfo;
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
        ImageView book = findViewById(R.id.enterBookmark);
        ImageView search = findViewById(R.id.search_main);
        TextInputLayout textInputLayout = findViewById(R.id.searchCountry);
        TextView title = findViewById(R.id.mainTitle);
        TextInputEditText searchKeyword = findViewById(R.id.editSearchMain);

        sm = new SessionManager(this);
        sm.getId();

        repository = new CovidCountryRepository(getApplication());
        getCountries = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_country);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        model = new ViewModelProvider(this).get(CovidCountryViewModel.class);
        countryAdapter = new CountryAdapter(this, getCountries);

        if(repository.countCountry() == 0){
            loadCountries();
        }else{

        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sm.logout();
                repository.delete();
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


        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, BookmarkActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(title.getVisibility() == View.VISIBLE){
                    title.setVisibility(View.INVISIBLE);
                    textInputLayout.setVisibility(View.VISIBLE);
                }else {
                    title.setVisibility(View.VISIBLE);
                    textInputLayout.setVisibility(View.INVISIBLE);
                }
            }
        });

        searchKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //countryAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                countryAdapter.getFilter().filter(s);
            }


        });

        model.getAllCountry().observe(this, new Observer<List<CountryItem>>() {
            @Override
            public void onChanged(List<CountryItem> countryItemList) {
                recyclerView.setAdapter(countryAdapter);
                countryAdapter.getAllCountries(countryItemList);
            }
        });

    }

    private void loadCountries(){
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        MyApiEndPointInterface api = retrofitInstance.getAPI();

        Call<List<CountryHeader>> call = api.getCountryData();
        call.enqueue(new Callback<List<CountryHeader>>() {
            @Override
            public void onResponse(Call<List<CountryHeader>> call, Response<List<CountryHeader>> response) {
                List<CountryItem> lists = new ArrayList<>();
                List<CountryInfo> listsInfo = new ArrayList<>();

                for (int i = 0; i < response.body().size(); i++) {
                    CountryHeader currentData = response.body().get(i);
                    CountryItem roomData = new CountryItem();
                    CountryInfo roomInfo = new CountryInfo();

                    roomData.setCountry(currentData.getCountry());
                    roomData.setContinent(currentData.getContinent());
                    roomData.setCases(currentData.getCases());
                    roomData.setTodayCases(currentData.getTodayCases());
                    roomData.setDeaths(currentData.getDeaths());
                    roomData.setTodayDeaths(currentData.getTodayDeaths());
                    roomData.setRecovered(currentData.getRecovered());
                    roomData.setTodayRecovered(currentData.getTodayRecovered());

                    //Log.d("Dbug", currentData.getCountry() + " " + currentData.getContinent());

                    lists.add(roomData);

                    roomInfo.setCountry(currentData.getCountry());
                    roomInfo.setFlag(currentData.getCountryInfo().getFlag());
                    roomInfo.setId(currentData.getCountryInfo().getId());
                    roomInfo.setIso2(currentData.getCountryInfo().getIso2());
                    roomInfo.setIso3(currentData.getCountryInfo().getIso3());
                    roomInfo.setJsonMemberLong(currentData.getCountryInfo().getJsonMemberLong());
                    roomInfo.setLat(currentData.getCountryInfo().getLat());

                    listsInfo.add(roomInfo);

                    Log.d("infoData", roomInfo.getFlag());
                    //Log.d("infoData", currentData.getCountry());

                }
                repository.insert(lists);
                repositoryInfo.insert(listsInfo);
                //Log.d("D_bug", lists.toString());

            }

            @Override
            public void onFailure(Call<List<CountryHeader>> call, Throwable t) {

            }

        });

    }

}
