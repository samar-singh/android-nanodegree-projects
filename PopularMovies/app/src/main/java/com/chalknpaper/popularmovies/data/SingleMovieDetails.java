package com.chalknpaper.popularmovies.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by samarsingh on 13/06/17.
 */

@SuppressWarnings("unused")
public class SingleMovieDetails implements Parcelable{
    private String mVoteCount;
    private String mVideoPresent;
    private String mVoteAverage;
    private String mTitle;
    private String mPopularity;
    private String mPosterPath;
    private String mOriginalLanguage;
    private String mOriginalTitle;
    private String mBackdropPath;
    private String mAdult;
    private String mOverview;
    private String mReleaseDate;

    public SingleMovieDetails(){

    }

    private SingleMovieDetails(Parcel in) {
        mVoteCount = in.readString();
        mVideoPresent = in.readString();
        mVoteAverage = in.readString();
        mTitle = in.readString();
        mPopularity = in.readString();
        mPosterPath = in.readString();
        mOriginalLanguage = in.readString();
        mOriginalTitle = in.readString();
        mBackdropPath = in.readString();
        mAdult = in.readString();
        mOverview = in.readString();
        mReleaseDate = in.readString();
    }

    public static final Creator<SingleMovieDetails> CREATOR = new Creator<SingleMovieDetails>() {
        @Override
        public SingleMovieDetails createFromParcel(Parcel in) {
            return new SingleMovieDetails(in);
        }

        @Override
        public SingleMovieDetails[] newArray(int size) {
            return new SingleMovieDetails[size];
        }
    };

    public String getmVoteCount() {
        return mVoteCount;
    }

    public void setmVoteCount(String mVoteCount) {
        this.mVoteCount = mVoteCount;
    }

    public String getmVideoPresent() {
        return mVideoPresent;
    }

    public void setmVideoPresent(String mVideoPresent) {
        this.mVideoPresent = mVideoPresent;
    }

    public String getmVoteAverage() {
        return mVoteAverage;
    }

    public void setmVoteAverage(String mVoteAverage) {
        this.mVoteAverage = mVoteAverage;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmPopularity() {
        return mPopularity;
    }

    public void setmPopularity(String mPopularity) {
        this.mPopularity = mPopularity;
    }

    public String getmPosterPath() {
        return mPosterPath;
    }

    public String getmOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setmOriginalLanguage(String mOriginalLanguage) {
        this.mOriginalLanguage = mOriginalLanguage;
    }

    public void setmPosterPath(String mPosterPath) {
        this.mPosterPath = mPosterPath;
    }

    public String getmOriginalTitle() {
        return mOriginalTitle;
    }

    public void setmOriginalTitle(String mOriginalTitle) {
        this.mOriginalTitle = mOriginalTitle;
    }

    public String getmBackdropPath() {
        return mBackdropPath;
    }

    public void setmBackdropPath(String mBackdropPath) {
        this.mBackdropPath = mBackdropPath;
    }

    public String getmAdult() {
        return mAdult;
    }

    public void setmAdult(String mAdult) {
        this.mAdult = mAdult;
    }

    public String getmOverview() {
        return mOverview;
    }

    public void setmOverview(String mOverview) {
        this.mOverview = mOverview;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mVoteCount);
        dest.writeString(mVideoPresent);
        dest.writeString(mVoteAverage);
        dest.writeString(mTitle);
        dest.writeString(mPopularity);
        dest.writeString(mPosterPath);
        dest.writeString(mOriginalLanguage);
        dest.writeString(mOriginalTitle);
        dest.writeString(mBackdropPath);
        dest.writeString(mAdult);
        dest.writeString(mOverview);
        dest.writeString(mReleaseDate);
    }
}
