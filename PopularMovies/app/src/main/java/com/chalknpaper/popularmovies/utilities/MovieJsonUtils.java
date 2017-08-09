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
package com.chalknpaper.popularmovies.utilities;

import com.chalknpaper.popularmovies.data.SingleMovieDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Utility functions to handle OpenMovieMap JSON data.
 */
public final class MovieJsonUtils {

    /**
     * This method parses JSON from a web response and returns an array of Strings
     * describing the Movie over various days from the forecast.
     * <p/>
     * Later on, we'll be parsing the JSON into structured data within the
     * getFullMovieDataFromJson function, leveraging the data we have stored in the JSON. For
     * now, we just convert the JSON into human-readable strings.
     *
     * @param forecastJsonStr JSON response from server
     *
     * @return Array of Strings describing Movie data
     *
     * @throws JSONException If JSON data cannot be properly parsed
     */
    public static ArrayList<SingleMovieDetails> getSimpleMovieStringsFromJson(String forecastJsonStr)
            throws JSONException {

        /* Movie information. Each day's forecast info is an element of the "list" array */
        final String MDB_LIST = "results";

        /* All temperatures are children of the "temp" object */
        final String MDB_VOTE_COUNT = "vote_count";

        /* Max temperature for the day */
        final String MDB_VIDEO = "video";
        final String MDB_VOTE_AVERAGE = "vote_average";

        final String MDB_TITLE = "title";
        final String MDB_POPULARITY = "popularity";
        final String MDB_POSTER_PATH = "poster_path";
        final String MDB_ORIGINAL_LANGUAGE = "original_language";
        final String MDB_ORIGINAL_TITLE = "original_title";
        final String MDB_BACKDROP_PATH = "backdrop_path";
        final String MDB_ADULT = "adult";
        final String MDB_OVERVIEW = "overview";
        final String MDB_RELEASE_DATE = "release_date";


        final String MDB_MESSAGE_CODE = "cod";


        JSONObject forecastJson = new JSONObject(forecastJsonStr);
        ArrayList<SingleMovieDetails> aMovieDetailsArrayList = new ArrayList<>();

        /* Is there an error? */
        if (forecastJson.has(MDB_MESSAGE_CODE)) {
            int errorCode = forecastJson.getInt(MDB_MESSAGE_CODE);

            switch (errorCode) {
                case HttpURLConnection.HTTP_OK:
                    break;
                case HttpURLConnection.HTTP_NOT_FOUND:
                    /* Location invalid */
                    return null;
                default:
                    /* Server probably down */
                    return null;
            }
        }

        JSONArray movieDetailArray = forecastJson.getJSONArray(MDB_LIST);

        long localDate = System.currentTimeMillis();

        for (int i = 0; i < movieDetailArray.length(); i++) {


            SingleMovieDetails movieDetailsObj = new SingleMovieDetails();
            /* Get the JSON object representing the day */
            JSONObject movieDetails = movieDetailArray.getJSONObject(i);
            movieDetailsObj.setmVoteCount(movieDetails.getString(MDB_VOTE_COUNT));
            movieDetailsObj.setmVideoPresent(movieDetails.getString(MDB_VIDEO));
            movieDetailsObj.setmVoteAverage(movieDetails.getString(MDB_VOTE_AVERAGE));
            movieDetailsObj.setmTitle(movieDetails.getString(MDB_TITLE));
            movieDetailsObj.setmPopularity(movieDetails.getString(MDB_POPULARITY));
            movieDetailsObj.setmPosterPath(movieDetails.getString(MDB_POSTER_PATH));
            movieDetailsObj.setmOriginalLanguage(movieDetails.getString(MDB_ORIGINAL_LANGUAGE));
            movieDetailsObj.setmOriginalTitle(movieDetails.getString(MDB_ORIGINAL_TITLE));
            movieDetailsObj.setmBackdropPath(movieDetails.getString(MDB_BACKDROP_PATH));
            movieDetailsObj.setmAdult(movieDetails.getString(MDB_ADULT));
            movieDetailsObj.setmOverview(movieDetails.getString(MDB_OVERVIEW));
            movieDetailsObj.setmReleaseDate(movieDetails.getString(MDB_RELEASE_DATE));

            aMovieDetailsArrayList.add(movieDetailsObj);

        }

        return aMovieDetailsArrayList;
    }

}