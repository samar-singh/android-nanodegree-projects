package com.chalknpaper.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chalknpaper.popularmovies.data.MdbPageResult;
import com.chalknpaper.popularmovies.data.Result;
import com.chalknpaper.popularmovies.data.SingleMovieDetails;
import com.chalknpaper.popularmovies.utilities.MdbAPIService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements MovieAdapter.ListItemClickListener {

    // --Commented out by Inspection (19/06/17, 7:39 PM):private static final String TAG = MainActivity.class.getSimpleName();
    private ArrayList<SingleMovieDetails> mMovieData;

    private final Context context = this;
    /*
     * References to RecyclerView and Adapter to reset the list to its
     * "pretty" state when the reset menu item is clicked.
     */
    private MovieAdapter mMovieAdapter;
    private RecyclerView mRecyclerView;
    private ProgressBar mLoadingIndicator;
    private TextView mErrorMessageDisplay;
    private final String mDefaultPreference = "popular";
// --Commented out by Inspection START (19/06/17, 7:39 PM):
//    /*
//     * If we hold a reference to our Toast, we can cancel it (if it's showing)
//     * to display a new Toast. If we didn't do this, Toasts would be delayed
//     * in showing up if you clicked many list items in quick succession.
//     */
//    private Toast mToast;
// --Commented out by Inspection STOP (19/06/17, 7:39 PM)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * Using findViewById, we get a reference to our RecyclerView from xml. This allows us to
         * do things like set the adapter of the RecyclerView and toggle the visibility.
         */
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_numbers);
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);

        GridLayoutManager layoutManager =  new GridLayoutManager(this,2);

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        loadMovieData(mDefaultPreference);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        Intent intent = null;
        String moviePreference = null;


        switch (itemId) {
            /*
             * When you click the reset menu item, we want to start all over
             * and display the pretty gradient again. There are a few similar
             * ways of doing this, with this one being the simplest of those
             * ways. (in our humble opinion)
             */
            case R.id.action_sort_popularity:
                moviePreference = "popular";
                break;

            case  R.id.action_sort_rating:
                moviePreference = "top_rated";
                break;
            default:
                Toast.makeText(this,"invalid choice",Toast.LENGTH_LONG).show();

        }

        loadMovieData(moviePreference);
        return super.onOptionsItemSelected(item);
    }

    // COMPLETED (10) Override ListItemClickListener's onListItemClick method
    /**
     * This is where we receive our callback from
     * {@link com.chalknpaper.popularmovies.MovieAdapter.ListItemClickListener}
     *
     * This callback is invoked when you click on an item in the list.
     *
     * @param mSingleMovieDetailObj Single MovieDetails Class Object.
     */
    @Override
    public void onListItemClick(Result mSingleMovieDetailObj) {
        Intent intent = new Intent(this,MovieDetailActivity.class);
        intent.putExtra("singleMovieDetailsObj", mSingleMovieDetailObj);
        startActivity(intent);
    }

    /**
     * This method will get the user's preferred movie sorting order, and then tell some
     * background method to get the movie data in the background.
     * @param moviePreference
     */
    private void loadMovieData(String moviePreference) {
        showMovieDataView();
        mLoadingIndicator.setVisibility(View.VISIBLE);

        MdbAPIService mdbAPIService = MdbAPIService.retrofit.create(MdbAPIService.class);
        mdbAPIService.mdbFetchResults(moviePreference,
                BuildConfig.MOVIEDB_API_KEY).enqueue(new Callback<MdbPageResult>() {
            @Override
            public void onResponse(Call<MdbPageResult> call, Response<MdbPageResult> response) {

               //Todo: modify this code to pass the movies to the recyclerview
                if (response != null) {
                    Log.d(MainActivity.this.getClass().getSimpleName(),"response.raw().request().url();"+response.raw().request().url());
                    mLoadingIndicator.setVisibility(View.INVISIBLE);
                    showMovieDataView();
                    mMovieAdapter = new MovieAdapter((MovieAdapter.ListItemClickListener) context);
                    mRecyclerView.setAdapter(mMovieAdapter);

                    mMovieAdapter.setMovieData(response.body());
                } else {
                    showErrorMessage();
                }
            }

            @Override
            public void onFailure(Call<MdbPageResult> call, Throwable t) {

                Log.d(this.getClass().getSimpleName(),"Access failed");
            }
        });
/*
        Observable <MdbPageResult> mDbData = mdbAPIService.mdbFetchResults(moviePreference,
                com.chalknpaper.popularmovies.BuildConfig.OPEN_WEATHER_MAP_API_KEY);

        mDbData.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mdbPageResult -> {
                    Log.e("Movie Title", mdbPageResult.getResults().get(0).getTitle()
                            );
                });
*/

 //       new FetchMovieTask().execute(moviePreference);
    }

    private void showMovieDataView() {
        /* First, make sure the error is invisible */
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        /* Then, make sure the movie data is visible */
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        /* First, hide the currently visible data */
        mRecyclerView.setVisibility(View.INVISIBLE);
        /* Then, show the error */
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

}

