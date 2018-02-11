package com.andazlan.mymovie.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by andazlan on 2/8/18.
 */

public class MyMovieHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mymovie.db";
    private static final int DATABASE_VERSION = 1;

    public MyMovieHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(MyMovieContract.MovieEntry.SQL_CREATE_MOVIE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
