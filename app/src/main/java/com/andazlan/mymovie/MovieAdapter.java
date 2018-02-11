package com.andazlan.mymovie;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.andazlan.mymovie.data.MyMovieContract;

/**
 * Created by andazlan on 2/8/18.
 */

public class MovieAdapter extends CursorAdapter {
    public MovieAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.movie_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView movTitle = (TextView) view.findViewById(R.id.txt_title);
        TextView movRating = (TextView) view.findViewById(R.id.txt_rating);

        movTitle.setText(cursor.getString(cursor.getColumnIndex(MyMovieContract.MovieEntry.COLUMN_TITLE)));
        movRating.setText("" + cursor.getInt(cursor.getColumnIndex(MyMovieContract.MovieEntry.COLUMN_RATING)));
    }
}
