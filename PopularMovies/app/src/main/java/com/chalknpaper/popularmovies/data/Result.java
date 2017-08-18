
package com.chalknpaper.popularmovies.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result implements Parcelable
{

    @SerializedName("vote_count")
    @Expose
    private int vote_count;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("video")
    @Expose
    private boolean video;
    @SerializedName("vote_average")
    @Expose
    private int vote_average;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("popularity")
    @Expose
    private double popularity;
    @SerializedName("poster_path")
    @Expose
    private String poster_path;
    @SerializedName("original_language")
    @Expose
    private String original_language;
    @SerializedName("original_title")
    @Expose
    private String original_title;
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genre_ids = null;
    @SerializedName("backdrop_path")
    @Expose
    private String backdrop_path;
    @SerializedName("adult")
    @Expose
    private boolean adult;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("release_date")
    @Expose
    private String release_date;
    public final static Parcelable.Creator<Result> CREATOR = new Creator<Result>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Result createFromParcel(Parcel in) {
            Result instance = new Result();
            instance.vote_count = ((int) in.readValue((int.class.getClassLoader())));
            instance.id = ((int) in.readValue((int.class.getClassLoader())));
            instance.video = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.vote_average = ((int) in.readValue((int.class.getClassLoader())));
            instance.title = ((String) in.readValue((String.class.getClassLoader())));
            instance.popularity = ((double) in.readValue((double.class.getClassLoader())));
            instance.poster_path = ((String) in.readValue((String.class.getClassLoader())));
            instance.original_language = ((String) in.readValue((String.class.getClassLoader())));
            instance.original_title = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.genre_ids, (java.lang.Integer.class.getClassLoader()));
            instance.backdrop_path = ((String) in.readValue((String.class.getClassLoader())));
            instance.adult = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.overview = ((String) in.readValue((String.class.getClassLoader())));
            instance.release_date = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Result[] newArray(int size) {
            return (new Result[size]);
        }

    }
    ;

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public int getVote_average() {
        return vote_average;
    }

    public void setVote_average(int vote_average) {
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(vote_count);
        dest.writeValue(id);
        dest.writeValue(video);
        dest.writeValue(vote_average);
        dest.writeValue(title);
        dest.writeValue(popularity);
        dest.writeValue(poster_path);
        dest.writeValue(original_language);
        dest.writeValue(original_title);
        dest.writeList(genre_ids);
        dest.writeValue(backdrop_path);
        dest.writeValue(adult);
        dest.writeValue(overview);
        dest.writeValue(release_date);
    }

    public int describeContents() {
        return  0;
    }

}
