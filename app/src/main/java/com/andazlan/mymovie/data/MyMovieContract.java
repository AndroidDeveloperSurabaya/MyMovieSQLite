package com.andazlan.mymovie.data;

import android.provider.BaseColumns;

/**
 * Created by andazlan on 2/8/18.
 */

public final class MyMovieContract {
    public MyMovieContract() {
    }

    public static class MovieEntry implements BaseColumns{
        public static final String TABLE = "movie";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_RATING = "rating";

        public static final String SQL_CREATE_MOVIE_ENTRIES =
                "CREATE TABLE " + MovieEntry.TABLE + " (" +
                    MovieEntry._ID + " INTEGER PRIMARY KEY, " +
                    MovieEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                    MovieEntry.COLUMN_RATING + " INTEGER DEFAULT 0)";
    }
}
