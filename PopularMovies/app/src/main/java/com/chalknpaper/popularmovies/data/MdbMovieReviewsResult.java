
package com.chalknpaper.popularmovies.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "page",
    "results",
    "total_pages",
    "total_results"
})
public class MdbMovieReviewsResult implements Parcelable
{

    @JsonProperty("id")
    private int id;
    @JsonProperty("page")
    private int page;
    @JsonProperty("results")
    private List<MdbSingleMovieReviewResult> results = null;
    @JsonProperty("total_pages")
    private int total_pages;
    @JsonProperty("total_results")
    private int total_results;
    public final static Parcelable.Creator<MdbMovieReviewsResult> CREATOR = new Creator<MdbMovieReviewsResult>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MdbMovieReviewsResult createFromParcel(Parcel in) {
            return new MdbMovieReviewsResult(in);
        }

        public MdbMovieReviewsResult[] newArray(int size) {
            return (new MdbMovieReviewsResult[size]);
        }

    }
    ;

    protected MdbMovieReviewsResult(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.page = ((int) in.readValue((int.class.getClassLoader())));
        in.readList(this.results, (MdbSingleMovieReviewResult.class.getClassLoader()));
        this.total_pages = ((int) in.readValue((int.class.getClassLoader())));
        this.total_results = ((int) in.readValue((int.class.getClassLoader())));
    }

    public MdbMovieReviewsResult() {
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("page")
    public int getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(int page) {
        this.page = page;
    }

    @JsonProperty("results")
    public List<MdbSingleMovieReviewResult> getResults() {
        return results;
    }

    @JsonProperty("results")
    public void setResults(List<MdbSingleMovieReviewResult> results) {
        this.results = results;
    }

    @JsonProperty("total_pages")
    public int getTotal_pages() {
        return total_pages;
    }

    @JsonProperty("total_pages")
    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    @JsonProperty("total_results")
    public int getTotal_results() {
        return total_results;
    }

    @JsonProperty("total_results")
    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(page);
        dest.writeList(results);
        dest.writeValue(total_pages);
        dest.writeValue(total_results);
    }

    public int describeContents() {
        return  0;
    }

}
