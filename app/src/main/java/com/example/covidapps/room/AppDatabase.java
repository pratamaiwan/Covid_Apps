package com.example.covidapps.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.covidapps.dao.CountryDao;
import com.example.covidapps.entity.CountryItem;

@Database(entities = {CountryItem.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CountryDao countryDao();
    private final static String DB_NAME = "Country";

    private static volatile AppDatabase INSTANCE = null;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            AppDatabase.class, DB_NAME).
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
        private CountryDao countryDao;
        public DbAsync(AppDatabase appDatabase){
            countryDao=appDatabase.countryDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            countryDao.deleteAll();
            return null;
        }
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
