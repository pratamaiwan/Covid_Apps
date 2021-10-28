package com.example.covidapps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidapps.R;
import com.example.covidapps.model.CountryHeader;

public class CovidCountryAdapter extends ListAdapter<CountryHeader, CovidCountryAdapter.ViewHolder> {

    private final CovidCountryClickableCallback covidCountryClickableCallback;

    public CovidCountryAdapter(@NonNull CovidCountryClickableCallback covidCountryClickableCallback) {
        super(new AsyncDifferConfig.Builder<>(new DiffUtil.ItemCallback<CountryHeader>() {
            @Override
            public boolean areItemsTheSame(@NonNull CountryHeader oldItem, @NonNull CountryHeader newItem) {
                return oldItem.getCountry().equals(newItem.getCountry());
            }
            @Override
            public boolean areContentsTheSame(@NonNull CountryHeader oldItem, @NonNull CountryHeader newItem) {
                return oldItem.getCountry().equals(newItem.getCountry());
            }
        }).build());
        this.covidCountryClickableCallback = covidCountryClickableCallback;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.covid_room_recyclerview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvFirstName.setText(getItem(position).getCountry());
        holder.tvLastName.setText(getItem(position).getContinent());
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvFirstName;
        TextView tvLastName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFirstName = itemView.findViewById(R.id.room_tv_firstname);
            tvLastName = itemView.findViewById(R.id.room_tv_lastname);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                CountryHeader countryHeader = getItem(position);
                covidCountryClickableCallback.onClick(v, countryHeader);
            }
        }
    }

}
