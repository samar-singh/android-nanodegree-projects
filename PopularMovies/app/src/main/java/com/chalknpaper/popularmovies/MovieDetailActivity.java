package com.chalknpaper.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.chalknpaper.popularmovies.data.SingleMovieDetails;
import com.chalknpaper.popularmovies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.net.URL;

public class MovieDetailActivity extends AppCompatActivity {

    private TextView movieNameTextView;
    private ImageView moviePosterImageView;
    private TextView movieYearLaunchTextView;
    private TextView movieRunTimeTextView;
    private TextView movieRatingTextView;
    private TextView movieReleaseDateTextView;
    private TextView movieDescriptionTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intent = getIntent();

        if(intent.hasExtra("singleMovieDetailsObj")){

            SingleMovieDetails singleMovieDetails = intent.getParcelableExtra("singleMovieDetailsObj");

            movieNameTextView = (TextView) findViewById(R.id.movieNameTextView);
            moviePosterImageView = (ImageView) findViewById(R.id.moviePosterImageView);
            movieYearLaunchTextView = (TextView) findViewById(R.id.movieYearLaunchTextView);
            movieRunTimeTextView = (TextView) findViewById(R.id.movieRunTimeTextView);
            movieRatingTextView = (TextView) findViewById(R.id.movieRatingTextView);
            movieReleaseDateTextView = (TextView) findViewById(R.id.movieReleaseDateTextView);
            movieDescriptionTextView = (TextView) findViewById(R.id.movieDescriptionTextView);
            movieDescriptionTextView.setMovementMethod(new ScrollingMovementMethod());

            movieNameTextView.setText(singleMovieDetails.getmOriginalTitle());
            String mMoviePosterPath = singleMovieDetails.getmPosterPath();
            URL mPosterUrl = NetworkUtils.buildUrlPoster(mMoviePosterPath);


            // Use Picasso here to load images onto Grid
            Picasso.with(this).load(mPosterUrl.toString()).into(moviePosterImageView);


            String mMovieReleaseDate = singleMovieDetails.getmReleaseDate();
            String []yyMmDdTokens = mMovieReleaseDate.split("-");
            String mMovieReleaseYear = yyMmDdTokens[0];
            movieYearLaunchTextView.setText(mMovieReleaseYear);// Need to change it after adding an extract text to releasedata string
            movieRunTimeTextView.setText("2h45m");// There is no data in the inputstream that tells the movie duration,
            // but the final UI shows the duration.Adding a default value till I know how to get these details

            movieRatingTextView.setText(singleMovieDetails.getmVoteAverage());
            movieReleaseDateTextView.setText(singleMovieDetails.getmReleaseDate());
            movieDescriptionTextView.setText(singleMovieDetails.getmOverview());

        }
    }
}
