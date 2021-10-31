package com.example.covidapps.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.covidapps.dao.CountryInfoDao;
import com.example.covidapps.entity.CountryInfo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {CountryInfo.class}, version = 3, exportSchema = false)
public abstract class CountryInfoDatabase extends RoomDatabase {
    public abstract CountryInfoDao countryInfoDao();
    private final static String DB_NAME = "CountryInfo";

    private static volatile CountryInfoDatabase INSTANCE = null;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static CountryInfoDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CountryInfoDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            CountryInfoDatabase.class, DB_NAME).
                            fallbackToDestructiveMigration().
                            addCallback(callback).
                            allowMainThreadQueries().
                            build();
                }
            }
        }
        return INSTANCE;
    }


    public static Callback callback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new DbAsync(INSTANCE);
        }
    };

    static class DbAsync extends AsyncTask<Void, Void, Void>{
        private CountryInfoDao countryInfoDao;
        public DbAsync(CountryInfoDatabase countryInfoDatabase){
            countryInfoDao=countryInfoDatabase.countryInfoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            countryInfoDao.deleteAll();
            return null;
        }
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
