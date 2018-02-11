package com.andazlan.mymovie;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.andazlan.mymovie.data.MyMovieContract;
import com.andazlan.mymovie.data.MyMovieHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listMovie;
    private MyMovieHelper mMovieHelper;
    private MovieAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieHelper = new MyMovieHelper(this);

        listMovie = findViewById(R.id.list_movie);
        mAdapter = new MovieAdapter(this, getAllDatas());
        listMovie.setAdapter(mAdapter);
        //insertDummyData();
    }

    private void insertDummyData() {
        SQLiteDatabase db = mMovieHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyMovieContract.MovieEntry.COLUMN_TITLE, "Wonder woman");
        values.put(MyMovieContract.MovieEntry.COLUMN_RATING, 5);
        long newId = db.insert(MyMovieContract.MovieEntry.TABLE, null, values);
        Log.d("NewEntry", "New id : " + newId);
    }

    private Cursor getAllDatas(){
        String[] projection = {
            MyMovieContract.MovieEntry._ID,
            MyMovieContract.MovieEntry.COLUMN_TITLE,
            MyMovieContract.MovieEntry.COLUMN_RATING
        };

        //String selection = MyMovieContract.MovieEntry.COLUMN_RATING + " > ?";
        //String[] selectionArgs = {"2"};

        SQLiteDatabase db = mMovieHelper.getReadableDatabase();
        Cursor cursor = db.query(
                MyMovieContract.MovieEntry.TABLE,
                projection,
                null,
                null,
                null,
                null,
                null);

        return cursor;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.swapCursor(getAllDatas());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                Intent intent = new Intent(this, DetailMovieActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        getAllDatas().close();
        super.onDestroy();
    }
}
