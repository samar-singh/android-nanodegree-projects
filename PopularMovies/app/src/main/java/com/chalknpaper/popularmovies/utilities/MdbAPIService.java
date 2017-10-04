package com.chalknpaper.popularmovies.utilities;

import com.chalknpaper.popularmovies.data.MdbPageResult;
import com.chalknpaper.popularmovies.data.MdbVideoTrailersResult;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by samarsingh on 10/08/17.
 */

public interface MdbAPIService {
    /*
        io.reactivex.Observable<MdbPageResult> mdbFetchResults(
                @Path("sortorder") String sortorder,
                @Query(value = "api_key", encoded = true) String apikey);

    */

    @GET("movie/{sortorder}")
    Call<MdbPageResult> mdbFetchResults(
            @Path("sortorder") String sortorder,
            @Query(value = "api_key", encoded = true) String apikey);


    @GET("movie/{movieid}/videos")
    Call<MdbVideoTrailersResult> mdbFetchTrailerResults(
            @Path("movieid") String movieid,
            @Query(value = "api_key", encoded = true) String apikey,
            @Query(value = "language") String language);


    @GET("movie/{movieid}/reviews")
    Call<MdbVideoTrailersResult> mdbFetchReviewsResults(
            @Path("movieid") String movieid,
            @Query(value = "api_key", encoded = true) String apikey,
            @Query(value = "language") String language);


    OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .addNetworkInterceptor(new StethoInterceptor())
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.themoviedb.org/3/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

/*
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.themoviedb.org/3/")
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
*/

}
