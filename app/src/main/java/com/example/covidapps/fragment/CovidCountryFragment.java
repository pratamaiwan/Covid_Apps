package com.example.covidapps.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidapps.R;
import com.example.covidapps.adapter.CovidCountryAdapter;
import com.example.covidapps.adapter.CovidCountryClickableCallback;
import com.example.covidapps.adapter.CovidCountryNewAdapter;
import com.example.covidapps.adapter.CovidCountryNewClickableCallback;
import com.example.covidapps.entity.CountryItem;
import com.example.covidapps.model.CountryHeader;
import com.example.covidapps.room.AppDatabase;
import com.example.covidapps.viewModel.CovidCountryViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CovidCountryFragment extends Fragment {

    private CovidCountryViewModel covidCountryViewModel;

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private CovidCountryNewAdapter covidCountryNewAdapter;
    private ProgressBar pb;

    private final CovidCountryNewClickableCallback callback = new CovidCountryNewClickableCallback() {
        @Override
        public void onClick(View view, CountryItem countryItem) {
            Gson gson = new Gson();
            String userString = gson.toJson(countryItem);
            Toast.makeText(requireActivity(), userString, Toast.LENGTH_SHORT).show();
        }
    };

    public static CovidCountryFragment newInstance() {
        return new CovidCountryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        covidCountryViewModel = new ViewModelProvider(requireActivity()).get(CovidCountryViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.covid_room_recyclerview, container, false);
        pb = view.findViewById(R.id.rv_pb);
        recyclerView = view.findViewById(R.id.roomRecyclerView);
        covidCountryNewAdapter = new CovidCountryNewAdapter(callback);
        recyclerView.setAdapter(covidCountryNewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        AppDatabase ad = AppDatabase.getDatabase(requireContext());
//
//        covidCountryViewModel.getAllCovidCountry().observe(getViewLifecycleOwner(), coutries -> {
//            pb.setVisibility(View.INVISIBLE);
// //           covidCountryAdapter.submitList(coutries);
//            List<CountryItem> list = new ArrayList<>();
//
//            for (int i = 0; i < coutries.size(); i++) {
//                //ad.countryDao().insert(coutries.get(i));
//                CountryHeader currentData = coutries.get(i);
//                CountryItem ci = new CountryItem();
//
//                ci.setContinent(currentData.getContinent());
//
//
//                ad.countryDao().insert(ci);
//                list.add(ci);
//            }
//
//            covidCountryNewAdapter.submitList(list);
//
//        });
    }

}
