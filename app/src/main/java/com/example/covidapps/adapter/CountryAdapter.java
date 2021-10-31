package com.example.covidapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidapps.DetailActivity;
import com.example.covidapps.R;
import com.example.covidapps.entity.CountryItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> implements Filterable {

    private Context context;
    private List<CountryItem> list;
    private List<CountryItem> listFilter;

    public CountryAdapter(Context context, List<CountryItem> list) {
        this.context = context;
        this.list = list;
        this.listFilter = new ArrayList<>(list);
    }

    public void setList(List<CountryItem> list) {
        this.list = list;
        this.listFilter = new ArrayList<>(list);
        notifyDataSetChanged();
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
        CountryItem countryItem = list.get(position);
        Log.d("Tbug", countryItem.getCountry() + " " + countryItem.getContinent());
        holder.country.setText(countryItem.getCountry());
        holder.continent.setText(countryItem.getContinent());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("country", countryItem.getCountry());
                i.putExtra("continent", countryItem.getContinent());
                i.putExtra("cases", countryItem.getCases());
                i.putExtra("todayCases", countryItem.getTodayCases());
                i.putExtra("deaths", countryItem.getDeaths());
                i.putExtra("todayDeaths", countryItem.getTodayDeaths());
                i.putExtra("recovered", countryItem.getRecovered());
                i.putExtra("todayRecovered", countryItem.getTodayRecovered());

                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(i);
                //Toast.makeText(context.getApplicationContext(), countryItemList.get(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getAllCountries(List<CountryItem> countryItems) {
        this.list = countryItems;
    }

    public static class CountryViewHolder extends RecyclerView.ViewHolder {
        public TextView country, continent;
        public CardView cardView;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            country = itemView.findViewById(R.id.room_country);
            continent = itemView.findViewById(R.id.room_continent);
            cardView = itemView.findViewById(R.id.countryOne);
        }

    }

//    @Override
//    public Filter getFilter() {
//        return getFilter;
//    }


    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CountryItem> fildteredList = new ArrayList<>();

            String filterPattern = constraint.toString().toLowerCase().trim();

            for (CountryItem item : listFilter){
                if( item.getCountry().toLowerCase().contains(filterPattern)){
                    fildteredList.add(item);
                }
            }

            FilterResults results = new FilterResults();
            results.values = fildteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

//    public Filter getFilter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            List<CountryItem> filtered = new ArrayList<>();
//            if (constraint == null || constraint.length() == 0) {
//                Collections.sort(listFilter, new Comparator<CountryItem>() {
//                    @Override
//                    public int compare(CountryItem o1, CountryItem o2) {
//                        return o1.getCountry().toLowerCase().compareTo(o2.getCountry().toLowerCase());
//                    }
//                });
//                filtered.addAll(listFilter);
//            } else {
//                String searchKeyword = constraint.toString().toLowerCase();
//                for (CountryItem country : listFilter) {
//                    if (country.getCountry().toLowerCase().contains(searchKeyword)) {
//                        filtered.add(country);
//                    }
//                }
//            }
//            FilterResults filterResults = new FilterResults();
//            filterResults.values = filtered;
//            return filterResults;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            list.clear();
//            list.addAll((List) results.values);
//            notifyDataSetChanged();
//        }
//    };
}


