package com.chalknpaper.popularmovies.utilities;

import com.chalknpaper.popularmovies.data.MdbPageResult;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by samarsingh on 10/08/17.
 */

public interface MdbAPIService {
    @GET("movie/{sortorder}")
    io.reactivex.Observable<MdbPageResult> mdbFetchResults(
            @Path("sortorder") String sortorder,
            @Query(value = "api_key", encoded = true) String apikey);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.themoviedb.org/3/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
