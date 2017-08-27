package com.chalknpaper.popularmovies.data;

/**
 * Created by samarsingh on 27/08/17.
 */

import android.net.Uri;
import android.provider.BaseColumns;


public class MovieContract {

    /* Add content provider constants to the Contract
     Clients need to know how to access the task data, and it's your job to provide
     these content URI's for the path to that data:
        1) Content authority,
        2) Base content URI,
        3) Path(s) to the tasks directory
        4) Content URI for data in the TaskEntry class
      */

    // The authority, which is how your code knows which Content Provider to access
    public static final String AUTHORITY = "com.chalknpaper.popularmovies";

    // The base content URI = "content://" + <authority>
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    // Define the possible paths for accessing data in this contract
    // This is the path for the "tasks" directory
    public static final String PATH_FAVOURITES = "favourites";

    /* TaskEntry is an inner class that defines the contents of the task table */
    public static final class MovieEntry implements BaseColumns {

        // TaskEntry content URI = base content URI + path
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVOURITES).build();


        // Task table and column names
        public static final String TABLE_NAME = "favourite";

        // Since TaskEntry implements the interface "BaseColumns", it has an automatically produced
        // "_ID" column in addition to the two below
        public static final String COLUMN_MOVIENAME = "name";
        public static final String COLUMN_POSTERIMAGEKEY = "posterimagekey";
        public static final String COLUMN_LAUNCHYEAR = "launchyear";
        public static final String COLUMN_RUNTIME = "runtime";
        public static final String COLUMN_RATING = "rating";
        public static final String COLUMN_RELEASE_DATE = "releasedate";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_TRAILERKEY = "trailerkey";

    }
}

