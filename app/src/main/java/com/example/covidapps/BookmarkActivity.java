package com.example.covidapps;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidapps.adapter.BookmarkAdapter;
import com.example.covidapps.adapter.CountryAdapter;
import com.example.covidapps.api.MyApiEndPointInterface;
import com.example.covidapps.api.RetrofitInstance;
import com.example.covidapps.databinding.ActivityMainBinding;
import com.example.covidapps.entity.Bookmark;
import com.example.covidapps.entity.CountryItem;
import com.example.covidapps.model.CountryHeader;
import com.example.covidapps.repository.BookmarkRepository;
import com.example.covidapps.repository.CovidCountryRepository;
import com.example.covidapps.session.SessionManager;
import com.example.covidapps.viewModel.BookmarkViewModel;
import com.example.covidapps.viewModel.CovidCountryViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookmarkActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SessionManager sm;
    private BookmarkRepository repository;
    private BookmarkAdapter bookmarkAdapter;
    private RecyclerView recyclerView;
    private BookmarkViewModel model;
    private List<Bookmark> getBookmark;

    private String countryDataToDelete;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.bookmark_layout);

        ImageView back = findViewById(R.id.backIcon);

        sm = new SessionManager(this);
        sm.getId();

        Intent data = getIntent();
        countryDataToDelete = data.getStringExtra("countryToDelete");

        repository = new BookmarkRepository(getApplication());

        repository.deleteBookmark(countryDataToDelete);


        getBookmark = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_country_bookmark);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        model = new ViewModelProvider(this).get(BookmarkViewModel.class);
        bookmarkAdapter = new BookmarkAdapter(this, getBookmark);

        if (repository.countBookmark() == 0) {

        } else {
            model.getAllCountry().observe(this, new Observer<List<Bookmark>>() {
                @Override
                public void onChanged(List<Bookmark> bookmarkItemList) {
                    recyclerView.setAdapter(bookmarkAdapter);
                    bookmarkAdapter.getAllCountries(bookmarkItemList);
                }
            });
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BookmarkActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });

    }

}







