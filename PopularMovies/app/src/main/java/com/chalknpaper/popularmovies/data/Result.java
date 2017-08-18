
package com.chalknpaper.popularmovies.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "vote_count",
    "id",
    "video",
    "vote_average",
    "title",
    "popularity",
    "poster_path",
    "original_language",
    "original_title",
    "genre_ids",
    "backdrop_path",
    "adult",
    "overview",
    "release_date"
})
public class Result implements Parcelable
{

    @JsonProperty("vote_count")
    private int vote_count;
    @JsonProperty("id")
    private int id;
    @JsonProperty("video")
    private boolean video;
    @JsonProperty("vote_average")
    private double vote_average;
    @JsonProperty("title")
    private String title;
    @JsonProperty("popularity")
    private double popularity;
    @JsonProperty("poster_path")
    private String poster_path;
    @JsonProperty("original_language")
    private String original_language;
    @JsonProperty("original_title")
    private String original_title;
    @JsonProperty("genre_ids")
    private List<Integer> genre_ids = null;
    @JsonProperty("backdrop_path")
    private String backdrop_path;
    @JsonProperty("adult")
    private boolean adult;
    @JsonProperty("overview")
    private String overview;
    @JsonProperty("release_date")
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
            instance.vote_average = ((double) in.readValue((double.class.getClassLoader())));
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

    @JsonProperty("vote_count")
    public int getvote_count() {
        return vote_count;
    }

    @JsonProperty("vote_count")
    public void setvote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("video")
    public boolean isVideo() {
        return video;
    }

    @JsonProperty("video")
    public void setVideo(boolean video) {
        this.video = video;
    }

    @JsonProperty("vote_average")
    public double getvote_average() {
        return vote_average;
    }

    @JsonProperty("vote_average")
    public void setvote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("popularity")
    public double getPopularity() {
        return popularity;
    }

    @JsonProperty("popularity")
    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    @JsonProperty("poster_path")
    public String getposter_path() {
        return poster_path;
    }

    @JsonProperty("poster_path")
    public void setposter_path(String poster_path) {
        this.poster_path = poster_path;
    }

    @JsonProperty("original_language")
    public String getoriginal_language() {
        return original_language;
    }

    @JsonProperty("original_language")
    public void setoriginal_language(String original_language) {
        this.original_language = original_language;
    }

    @JsonProperty("original_title")
    public String getoriginal_title() {
        return original_title;
    }

    @JsonProperty("original_title")
    public void setoriginal_title(String original_title) {
        this.original_title = original_title;
    }

    @JsonProperty("genre_ids")
    public List<Integer> getgenre_ids() {
        return genre_ids;
    }

    @JsonProperty("genre_ids")
    public void setgenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    @JsonProperty("backdrop_path")
    public String getbackdrop_path() {
        return backdrop_path;
    }

    @JsonProperty("backdrop_path")
    public void setbackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    @JsonProperty("adult")
    public boolean isAdult() {
        return adult;
    }

    @JsonProperty("adult")
    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    @JsonProperty("overview")
    public String getOverview() {
        return overview;
    }

    @JsonProperty("overview")
    public void setOverview(String overview) {
        this.overview = overview;
    }

    @JsonProperty("release_date")
    public String getrelease_date() {
        return release_date;
    }

    @JsonProperty("release_date")
    public void setrelease_date(String release_date) {
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
