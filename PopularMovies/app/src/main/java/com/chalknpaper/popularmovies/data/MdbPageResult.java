
package com.chalknpaper.popularmovies.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "page",
    "total_results",
    "total_pages",
    "results"
})
public class MdbPageResult implements Parcelable
{

    @JsonProperty("page")
    private int page;
    @JsonProperty("total_results")
    private int totalResults;
    @JsonProperty("total_pages")
    private int totalPages;
    @JsonProperty("mdbSingleMovieResults")
    private List<MdbSingleMovieResult> results = null;
    public final static Parcelable.Creator<MdbPageResult> CREATOR = new Creator<MdbPageResult>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MdbPageResult createFromParcel(Parcel in) {
            MdbPageResult instance = new MdbPageResult();
            instance.page = ((int) in.readValue((int.class.getClassLoader())));
            instance.totalResults = ((int) in.readValue((int.class.getClassLoader())));
            instance.totalPages = ((int) in.readValue((int.class.getClassLoader())));
            in.readList(instance.results, (MdbSingleMovieResult.class.getClassLoader()));
            return instance;
        }

        public MdbPageResult[] newArray(int size) {
            return (new MdbPageResult[size]);
        }

    }
    ;

    @JsonProperty("page")
    public int getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(int page) {
        this.page = page;
    }

    @JsonProperty("total_results")
    public int getTotalResults() {
        return totalResults;
    }

    @JsonProperty("total_results")
    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    @JsonProperty("total_pages")
    public int getTotalPages() {
        return totalPages;
    }

    @JsonProperty("total_pages")
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    @JsonProperty("mdbSingleMovieResults")
    public List<MdbSingleMovieResult> getMdbSingleMovieResults() {
        return results;
    }

    @JsonProperty("mdbSingleMovieResults")
    public void setMdbSingleMovieResults(List<MdbSingleMovieResult> mdbSingleMovieResults) {
        this.results = mdbSingleMovieResults;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeValue(totalResults);
        dest.writeValue(totalPages);
        dest.writeList(results);
    }

    public int describeContents() {
        return  0;
    }

}
