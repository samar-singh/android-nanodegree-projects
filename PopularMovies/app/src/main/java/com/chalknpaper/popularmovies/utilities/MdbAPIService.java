package com.chalknpaper.popularmovies.utilities;

import com.chalknpaper.popularmovies.data.MdbPageResult;

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
    @GET("movie/{sortorder}")
/*
    io.reactivex.Observable<MdbPageResult> mdbFetchResults(
            @Path("sortorder") String sortorder,
            @Query(value = "api_key", encoded = true) String apikey);

*/
    Call<MdbPageResult> mdbFetchResults(
            @Path("sortorder") String sortorder,
            @Query(value = "api_key", encoded = true) String apikey);

/*
    OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build();
*/

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.themoviedb.org/3/")
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
