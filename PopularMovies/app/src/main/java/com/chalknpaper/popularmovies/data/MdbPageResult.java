
package com.chalknpaper.popularmovies.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MdbPageResult implements Parcelable
{

    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("total_results")
    @Expose
    private int total_results;
    @SerializedName("total_pages")
    @Expose
    private int total_pages;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    public final static Parcelable.Creator<MdbPageResult> CREATOR = new Creator<MdbPageResult>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MdbPageResult createFromParcel(Parcel in) {
            MdbPageResult instance = new MdbPageResult();
            instance.page = ((int) in.readValue((int.class.getClassLoader())));
            instance.total_results = ((int) in.readValue((int.class.getClassLoader())));
            instance.total_pages = ((int) in.readValue((int.class.getClassLoader())));
            in.readList(instance.results, (com.chalknpaper.popularmovies.data.Result.class.getClassLoader()));
            return instance;
        }

        public MdbPageResult[] newArray(int size) {
            return (new MdbPageResult[size]);
        }

    }
    ;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeValue(total_results);
        dest.writeValue(total_pages);
        dest.writeList(results);
    }

    public int describeContents() {
        return  0;
    }

}
