
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
        "results"
})
public class MdbVideoTrailerResult implements Parcelable {

    @JsonProperty("id")
    private int id;
    @JsonProperty("results")
    private List<MdbSingleTrailerResult> results = null;
    public final static Parcelable.Creator<MdbVideoTrailerResult> CREATOR = new Creator<MdbVideoTrailerResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MdbVideoTrailerResult createFromParcel(Parcel in) {
            MdbVideoTrailerResult instance = new MdbVideoTrailerResult();
            instance.id = ((int) in.readValue((int.class.getClassLoader())));
            in.readList(instance.results, (MdbSingleMovieResult.class.getClassLoader()));
            return instance;
        }

        public MdbVideoTrailerResult[] newArray(int size) {
            return (new MdbVideoTrailerResult[size]);
        }

    };

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("results")
    public List<MdbSingleTrailerResult> getResults() {
        return results;
    }

    @JsonProperty("results")
    public void setResults(List<MdbSingleTrailerResult> results) {
        this.results = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeList(results);
    }

    public int describeContents() {
        return 0;
    }

}
