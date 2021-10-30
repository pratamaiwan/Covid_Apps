package com.example.covidapps.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidapps.R;
import com.example.covidapps.dao.CountryDao;
import com.example.covidapps.entity.CountryItem;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private Context context;
    private List<CountryItem> countryItemList;

    public CountryAdapter(Context context, List<CountryItem> countryItemList){
        this.context = context;
        this.countryItemList = countryItemList;
    }

    @NonNull
    @Override
    public CountryAdapter.CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.country_item, parent, false);
        CountryViewHolder countryViewHolder = new CountryViewHolder(view);
        return countryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        CountryItem countryItem = countryItemList.get(position);
        Log.d("Tbug", countryItem.getCountry() + " " +countryItem.getContinent());
        holder.country.setText(countryItem.getCountry());
        holder.continent.setText(countryItem.getContinent());
    }

    @Override
    public int getItemCount() {
        return countryItemList.size();
    }

    public void getAllCountries(List<CountryItem> countryItems){
        this.countryItemList = countryItems;
    }

    public static class CountryViewHolder extends RecyclerView.ViewHolder{
        public TextView country, continent;

        public CountryViewHolder(@NonNull View itemView){
            super(itemView);
            country=itemView.findViewById(R.id.room_country);
            continent=itemView.findViewById(R.id.room_continent);
        }

    }
}
