package com.chalknpaper.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.chalknpaper.popularmovies.data.MdbVideoTrailerResult;
import com.chalknpaper.popularmovies.data.Result;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intent = getIntent();

        if(intent.hasExtra("singleMovieDetailsObj")){

            Result singleMovieDetails = intent.getParcelableExtra("singleMovieDetailsObj");

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
                    BuildConfig.MOVIEDB_API_KEY,"en-US").enqueue(new Callback<MdbVideoTrailerResult>() {
                @Override
                public void onResponse(Call<MdbVideoTrailerResult> call, Response<MdbVideoTrailerResult> response) {


                    if (response != null) {
                        Log.d(MovieDetailActivity.this.getClass().getSimpleName(),"response.raw().request().url();"+response.raw().request().url());
                        mMovieTrailerAdapter = new MovieTrailerAdapter(MovieDetailActivity.this);
                        mRecyclerView.setAdapter(mMovieTrailerAdapter);

                        mMovieTrailerAdapter.setMovieData(response.body());
                        mRecyclerView.setAdapter(mMovieTrailerAdapter);
                    } else {
                        Log.d(this.getClass().getSimpleName(),"could not retrieve Trailer data");
                    }
                }

                @Override
                public void onFailure(Call<MdbVideoTrailerResult> call, Throwable t) {

                    Log.d(this.getClass().getSimpleName(),"Access failed");
                }
            });

        }

    }
}
