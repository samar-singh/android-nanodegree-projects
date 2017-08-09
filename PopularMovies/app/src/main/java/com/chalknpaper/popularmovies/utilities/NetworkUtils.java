package com.chalknpaper.popularmovies.utilities;

/**
 * Created by samarsingh on 12/06/17.
 */
/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the network.
 */
public class NetworkUtils {



    private final static String PARAM_THEMOVIEDB_BASE_URL =
            "http://api.themoviedb.org/3";
    private final static String PARAM_POSTER_BASE_URL =
            "http://image.tmdb.org";

    private final static String PARAM_QUERY_ITEM = "movie";
    private final static String PARAM_SORT_BY_POPULAR= "popular";
    private final static String PARAM_SORT_BY_RATING = "top_rated";


    /*
     * The sort field. One of stars, forks, or updated.
     * Default: results are sorted by best match if no field is specified.
     */
    private final static String PARAM_API_KEY = "api_key";

    private final static String PARAM_POSTER_SIZE = "w185";
    private final static String PARAM_POSTER_PARAM_1 = "t";
    private final static String PARAM_POSTER_PARAM_2 = "p";


    /**
     * Builds the URL used to query GitHub.
     *
     * @param movieDbSortOrder The keyword that will be sorted for.
     * @return The URL to use to query the MovieDb.
     */
    public static URL buildUrlMdb(String movieDbSortOrder) {
        String sortUrlString = null;
        if(movieDbSortOrder.matches("popular")){
            sortUrlString =  PARAM_SORT_BY_POPULAR;
        }else {
            sortUrlString =  PARAM_SORT_BY_RATING;
        }
        Uri builtUri = Uri.parse(PARAM_THEMOVIEDB_BASE_URL).buildUpon()
                .appendPath(PARAM_QUERY_ITEM)
                .appendPath(sortUrlString)
                .appendQueryParameter(PARAM_API_KEY, com.chalknpaper.popularmovies.BuildConfig.OPEN_WEATHER_MAP_API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }


    /**
     * Builds the URL used to query GitHub.
     *
     * @param posterImageName The keyword that will be sorted for.
     * @return The URL to use to query the MovieDb.
     */
    public static URL buildUrlPoster(String posterImageName) {
        String mParsedPosterImage = posterImageName.replace("/","");
        Uri builtUri = Uri.parse(PARAM_POSTER_BASE_URL).buildUpon()
                .appendPath(PARAM_POSTER_PARAM_1)
                .appendPath(PARAM_POSTER_PARAM_2)
                .appendPath(PARAM_POSTER_SIZE)
                .appendPath(mParsedPosterImage)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}