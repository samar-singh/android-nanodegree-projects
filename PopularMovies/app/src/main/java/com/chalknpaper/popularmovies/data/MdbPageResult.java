
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
    private Integer page;
    @JsonProperty("total_results")
    private Integer totalResults;
    @JsonProperty("total_pages")
    private Integer totalPages;
    @JsonProperty("results")
    private List<Result> results = null;
    public final static Parcelable.Creator<MdbPageResult> CREATOR = new Creator<MdbPageResult>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MdbPageResult createFromParcel(Parcel in) {
            MdbPageResult instance = new MdbPageResult();
            instance.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.totalResults = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(instance.results, (com.chalknpaper.popularmovies.data.Result.class.getClassLoader()));
            return instance;
        }

        public MdbPageResult[] newArray(int size) {
            return (new MdbPageResult[size]);
        }

    }
    ;

    @JsonProperty("page")
    public Integer getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(Integer page) {
        this.page = page;
    }

    @JsonProperty("total_results")
    public Integer getTotalResults() {
        return totalResults;
    }

    @JsonProperty("total_results")
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    @JsonProperty("total_pages")
    public Integer getTotalPages() {
        return totalPages;
    }

    @JsonProperty("total_pages")
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    @JsonProperty("results")
    public List<Result> getResults() {
        return results;
    }

    @JsonProperty("results")
    public void setResults(List<Result> results) {
        this.results = results;
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
