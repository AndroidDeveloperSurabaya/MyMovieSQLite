package com.andazlan.mymovie;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.andazlan.mymovie.data.MyMovieContract;
import com.andazlan.mymovie.data.MyMovieHelper;

public class DetailMovieActivity extends AppCompatActivity {
    private MyMovieHelper mMovieHelper;
    private EditText title;
    private RatingBar rating;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        mMovieHelper = new MyMovieHelper(this);

        title = findViewById(R.id.edt_title);
        rating = findViewById(R.id.rtb_rating);

        submit = findViewById(R.id.btn_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (title.getText().toString().isEmpty()){
                    Toast.makeText(DetailMovieActivity.this, "Judul tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }

                SQLiteDatabase db = mMovieHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(MyMovieContract.MovieEntry.COLUMN_TITLE, title.getText().toString());
                values.put(MyMovieContract.MovieEntry.COLUMN_RATING, (int) rating.getRating());

                long newId = db.insert(MyMovieContract.MovieEntry.TABLE, null, values);

                if (newId > 0){
                    finish();
                }
            }
        });
    }
}
