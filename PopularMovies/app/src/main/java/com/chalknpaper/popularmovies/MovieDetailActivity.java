package com.chalknpaper.popularmovies;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chalknpaper.popularmovies.data.MdbSingleMovieResult;
import com.chalknpaper.popularmovies.data.MdbVideoTrailersResult;
import com.chalknpaper.popularmovies.data.MovieContract;
import com.chalknpaper.popularmovies.data.MovieDbHelper;
import com.chalknpaper.popularmovies.utilities.MdbAPIService;
import com.chalknpaper.popularmovies.utilities.NetworkUtils;
import com.chalknpaper.popularmovies.utilities.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {

    private TextView movieNameTextView;
    private ImageView moviePosterImageView;
    private TextView movieYearLaunchTextView;
    private TextView movieRunTimeTextView;
    private TextView movieRatingTextView;
    private TextView movieReleaseDateTextView;
    private TextView movieDescriptionTextView;
    private MovieTrailerAdapter mMovieTrailerAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList trailerImageList ;
    MdbSingleMovieResult singleMovieDetails;
    private SQLiteDatabase mDb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        MovieDbHelper dbHelper = new MovieDbHelper(this);
        mDb = dbHelper.getWritableDatabase();
        Intent intent = getIntent();

        if(intent.hasExtra("singleMovieDetailsObj")){

            singleMovieDetails = intent.getParcelableExtra("singleMovieDetailsObj");

            movieNameTextView = (TextView) findViewById(R.id.movieNameTextView);
            moviePosterImageView = (ImageView) findViewById(R.id.moviePosterImageView);
            movieYearLaunchTextView = (TextView) findViewById(R.id.movieYearLaunchTextView);
            movieRunTimeTextView = (TextView) findViewById(R.id.movieRunTimeTextView);
            movieRatingTextView = (TextView) findViewById(R.id.movieRatingTextView);
            movieReleaseDateTextView = (TextView) findViewById(R.id.movieReleaseDateTextView);
            movieDescriptionTextView = (TextView) findViewById(R.id.movieDescriptionTextView);
            movieDescriptionTextView.setMovementMethod(new ScrollingMovementMethod());

            movieNameTextView.setText(singleMovieDetails.getTitle());
            String mMoviePosterPath = singleMovieDetails.getposter_path();
            URL mPosterUrl = NetworkUtils.buildUrlPoster(mMoviePosterPath);


            // Use Picasso here to load images onto Grid
            Picasso.with(this).load(mPosterUrl.toString())
                    .transform(new RoundedTransformation(8,0))
                    .into(moviePosterImageView);


            String mMovieReleaseDate = singleMovieDetails.getrelease_date();
            String []yyMmDdTokens = mMovieReleaseDate.split("-");
            String mMovieReleaseYear = yyMmDdTokens[0];
            movieYearLaunchTextView.setText(mMovieReleaseYear);// Need to change it after adding an extract text to releasedata string
            movieRunTimeTextView.setText("2h45m");// There is no data in the inputstream that tells the movie duration,
            // but the final UI shows the duration.Adding a default value till I know how to get these details

            movieRatingTextView.setText(String.valueOf(singleMovieDetails.getvote_average()));
            movieReleaseDateTextView.setText(singleMovieDetails.getrelease_date());
            movieDescriptionTextView.setText(singleMovieDetails.getOverview());

            mRecyclerView = (RecyclerView) findViewById(R.id.rv_trailers);
            GridLayoutManager layoutManager =  new GridLayoutManager(this,1, LinearLayoutManager.HORIZONTAL,false);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setHasFixedSize(true);

            MdbAPIService mdbAPIService = MdbAPIService.retrofit.create(MdbAPIService.class);
            mdbAPIService.mdbFetchTrailerResults(String.valueOf(singleMovieDetails.getId()),
                    BuildConfig.MOVIEDB_API_KEY,"en-US").enqueue(new Callback<MdbVideoTrailersResult>() {
                @Override
                public void onResponse(Call<MdbVideoTrailersResult> call, Response<MdbVideoTrailersResult> response) {


                    if (response != null) {
                        Log.d(MovieDetailActivity.this.getClass().getSimpleName(),"response.raw().request().url();"+response.raw().request().url());
                        mMovieTrailerAdapter = new MovieTrailerAdapter(MovieDetailActivity.this);
                        mRecyclerView.setAdapter(mMovieTrailerAdapter);

                        mMovieTrailerAdapter.setMovieData(response.body());
                        mRecyclerView.setAdapter(mMovieTrailerAdapter);
                        //todo : need to get all the returned trailer thumbnails results and store
                        // for now just setting th Id to chekc DB functionality

                    } else {
                        Log.d(this.getClass().getSimpleName(),"could not retrieve Trailer data");
                    }
                }

                @Override
                public void onFailure(Call<MdbVideoTrailersResult> call, Throwable t) {

                    Log.d(this.getClass().getSimpleName(),"Access failed");
                }
            });

        }

    }

    public void favouriteSelect(View view) {

        final int defaultColourId = -17613;
        //// Completed: 29/08/17 write to local database via ContentProvider

        boolean isFavourite = isFavorite(singleMovieDetails.getId());

        if(isFavourite){
            view.getBackground().setColorFilter(ContextCompat.getColor(this, android.R.color.holo_orange_light), PorterDuff.Mode.MULTIPLY);
        }else {
            view.setBackgroundColor(Color.GREEN);
            //ColorDrawable color = (ColorDrawable) view.getBackground();
            //int colorId = color.getColor();
            ContentValues contentValues = new ContentValues();
            //// Completed: 31/08/17 add all the required fields to the database insert that are mentioned "NOT NULL" in the dbhelper
            contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIEID, singleMovieDetails.getId());
            contentValues.put(MovieContract.MovieEntry.COLUMN_MOVIENAME, singleMovieDetails.getTitle());
            contentValues.put(MovieContract.MovieEntry.COLUMN_DESCRIPTION, singleMovieDetails.getOverview());
            contentValues.put(MovieContract.MovieEntry.COLUMN_POSTERIMAGEKEY, singleMovieDetails.getposter_path());
            contentValues.put(MovieContract.MovieEntry.COLUMN_LAUNCHYEAR, "2017");
            contentValues.put(MovieContract.MovieEntry.COLUMN_RELEASE_DATE, singleMovieDetails.getrelease_date());
            contentValues.put(MovieContract.MovieEntry.COLUMN_RUNTIME, "2hrs");
            contentValues.put(MovieContract.MovieEntry.COLUMN_RATING, singleMovieDetails.getvote_average());

            Uri uri = getContentResolver().insert(MovieContract.MovieEntry.CONTENT_URI, contentValues);
        }
    }

    private boolean isFavorite(int movieId) {
        Cursor cursor = getContentResolver().query(ContentUris.withAppendedId(MovieContract.MovieEntry.CONTENT_URI, movieId),
                null,
                null,
                null,
                null);

        boolean ret = (cursor != null && cursor.getCount() > 0);
        if (cursor != null) {
            cursor.close();
        }
        return ret;
    }
}
