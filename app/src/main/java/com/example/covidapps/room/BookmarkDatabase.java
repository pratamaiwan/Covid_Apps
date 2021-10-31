package com.example.covidapps.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.covidapps.dao.BookmarkDao;
import com.example.covidapps.dao.CountryDao;
import com.example.covidapps.entity.Bookmark;
import com.example.covidapps.entity.CountryItem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Bookmark.class}, version = 3, exportSchema = false)
public abstract class BookmarkDatabase extends RoomDatabase {
    public abstract BookmarkDao bookmarkDao();
    private final static String DB_NAME = "Bookmark";

    private static volatile BookmarkDatabase INSTANCE = null;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static BookmarkDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BookmarkDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            BookmarkDatabase.class, DB_NAME).
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
        private BookmarkDao bookmarkDao;
        public DbAsync(BookmarkDatabase bookmarkDatabase){
            bookmarkDao=bookmarkDatabase.bookmarkDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            bookmarkDao.deleteAll();
            return null;
        }
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
