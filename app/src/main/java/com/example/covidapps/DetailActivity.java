package com.example.covidapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covidapps.adapter.BookmarkAdapter;
import com.example.covidapps.adapter.CovidCountryAdapter;
import com.example.covidapps.dao.CountryInfoDao;
import com.example.covidapps.databinding.ActivityDetailBinding;
import com.example.covidapps.entity.Bookmark;
import com.example.covidapps.entity.CountryInfo;
import com.example.covidapps.repository.BookmarkRepository;
import com.example.covidapps.repository.CovidCountryInfoRepository;
import com.example.covidapps.session.SessionManager;
import com.example.covidapps.viewModel.BookmarkViewModel;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    private SharedPreferences sp;
    private CovidCountryAdapter cca;
    int position;

    private SessionManager sm;
    private BookmarkRepository repository;
    private BookmarkAdapter bookmarkAdapter;
    private RecyclerView recyclerView;
    private BookmarkViewModel model;
    private List<Bookmark> getBookmark;

    private CovidCountryInfoRepository repositoryInfo;
    private CountryInfo countryInfo;
    private List<CountryInfo> getInfo;

    private String countryData;
    private String continentData;
    private int casesData;
    private int todayCasesData;
    private int deathsData;
    private int todayDeathsData;
    private int recoveredData;
    private int todayRecoveredData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageView flag = findViewById(R.id.flag);
        ImageView back = findViewById(R.id.backIcon);
        ImageView save = findViewById(R.id.saveToBookmark);

        DecimalFormat decim = new DecimalFormat("#,###");


        repository = new BookmarkRepository(getApplication());
        Intent data = getIntent();
        countryData = data.getStringExtra("country");
        continentData = data.getStringExtra("continent");
        casesData = data.getIntExtra("cases", 0);
        todayCasesData = data.getIntExtra("todayCases", 0);
        deathsData = data.getIntExtra("deaths", 0);
        todayDeathsData = data.getIntExtra("todayDeaths", 0);
        recoveredData = data.getIntExtra("recovered", 0);
        todayRecoveredData = data.getIntExtra("todayRecovered", 0);

        repositoryInfo = new CovidCountryInfoRepository(getApplication());
        repositoryInfo.getCountryInfo(countryData);
        getInfo = new ArrayList<>();
        getInfo.add(countryInfo);
        Log.d("infoData", "onCreate: " + repositoryInfo.getCountryInfo(countryData).getValue() + " " + countryInfo.getCountry());

        TextView country, continent, cases, todayCases, deaths, todayDeaths, recovered, todayRecovered;
        TextView id, longitude, latitude, countryCode;
        String flagUrl = countryInfo.getFlag();

        id = findViewById(R.id.tv_id);
        longitude = findViewById(R.id.tv_longitude);
        latitude = findViewById(R.id.tv_latitude);
        countryCode = findViewById(R.id.tv_country_code);

        country = findViewById(R.id.tv_country);
        continent = findViewById(R.id.tv_continent);
        cases = findViewById(R.id.tv_cases);
        todayCases = findViewById(R.id.tv_cases_today);
        deaths = findViewById(R.id.tv_deaths);
        todayDeaths = findViewById(R.id.tv_deaths_today);
        recovered = findViewById(R.id.tv_recovered);
        todayRecovered = findViewById(R.id.tv_recovered_today);




        id.setText(countryInfo.getId());
        longitude.setText(countryInfo.getJsonMemberLong().toString());
        latitude.setText(countryInfo.getLat().toString());
        countryCode.setText(countryInfo.getIso3());
        Picasso.get().load(flagUrl).into(flag);

        country.setText(countryData);
        continent.setText(continentData);
        cases.setText(decim.format(casesData));
        todayCases.setText(decim.format(todayCasesData));
        deaths.setText(decim.format(deathsData));
        todayDeaths.setText(decim.format(todayDeathsData));
        recovered.setText(decim.format(recoveredData));
        todayRecovered.setText(decim.format(todayRecoveredData));

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

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(repository.checkExist(countryData) == 1){
                    Toast.makeText(getApplicationContext(), "Country already bookmarked", Toast.LENGTH_SHORT).show();
                }else {
                    saveToBookmark();
                }
                finish();
            }
        });

    }

    private void saveToBookmark() {
        List<Bookmark> lists = new ArrayList<>();
        Bookmark currentData = new Bookmark();

        currentData.setCountry(countryData);
        currentData.setContinent(continentData);
        currentData.setCases(casesData);
        currentData.setTodayCases(todayCasesData);
        currentData.setDeaths(deathsData);
        currentData.setTodayDeaths(todayDeathsData);
        currentData.setRecovered(recoveredData);
        currentData.setTodayRecovered(todayRecoveredData);

        lists.add(currentData);

        repository.insert(lists);

    }

}