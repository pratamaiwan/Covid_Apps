package com.example.covidapps.adapter;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidapps.BookmarkActivity;
import com.example.covidapps.DetailActivity;
import com.example.covidapps.R;
import com.example.covidapps.entity.Bookmark;
import com.example.covidapps.entity.CountryItem;
import com.example.covidapps.repository.BookmarkRepository;

import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.bookmarkViewHolder> {

    private Context context;
    private List<Bookmark> bookmarkList;

    public BookmarkAdapter(Context context, List<Bookmark> countryItemList){
        this.context = context;
        this.bookmarkList = countryItemList;
    }

    @NonNull
    @Override
    public BookmarkAdapter.bookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.country_item, parent, false);
        bookmarkViewHolder bookmarkViewHolder = new bookmarkViewHolder(view);
        return bookmarkViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull bookmarkViewHolder holder, int position) {
        Bookmark bookmark = bookmarkList.get(position);
        //Log.d("Tbug", countryItem.getCountry() + " " +countryItem.getContinent());
        holder.country.setText(bookmark.getCountry());
        holder.continent.setText(bookmark.getContinent());
        holder.deleteBookmark.setVisibility(View.VISIBLE);

        holder.deleteBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context.getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
                
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setCancelable(true);
                builder.setTitle("Delete Country From Bookmark");
                builder.setMessage("Do you want to delete this country from the bookmark?");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(context, BookmarkActivity.class);
                        i.putExtra("countryToDelete", bookmark.getCountry());

                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(i);
                    }
                });
                builder.show();
            }
        });


        if(bookmarkList.size() > 0) {
            holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("country", bookmark.getCountry());
                    i.putExtra("continent", bookmark.getContinent());
                    i.putExtra("cases", bookmark.getCases());
                    i.putExtra("todayCases", bookmark.getTodayCases());
                    i.putExtra("deaths", bookmark.getDeaths());
                    i.putExtra("todayDeaths", bookmark.getTodayDeaths());
                    i.putExtra("recovered", bookmark.getRecovered());
                    i.putExtra("todayRecovered", bookmark.getTodayRecovered());

                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return bookmarkList.size();
    }

    public void getAllCountries(List<Bookmark> bookmarkItems){
        this.bookmarkList = bookmarkItems;
    }

    public static class bookmarkViewHolder extends RecyclerView.ViewHolder{
        public TextView country, continent;
        public CardView cardView;
        public ConstraintLayout constraintLayout;
        public ImageView deleteBookmark;

        public bookmarkViewHolder(@NonNull View itemView){
            super(itemView);
            country=itemView.findViewById(R.id.room_country);
            continent=itemView.findViewById(R.id.room_continent);
            cardView=itemView.findViewById(R.id.countryOne);
            constraintLayout=itemView.findViewById(R.id.cl_card);
            deleteBookmark=itemView.findViewById(R.id.deleteBookmark);
        }

    }
}
