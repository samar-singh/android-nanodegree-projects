package com.chalknpaper.popularmovies.data;

        import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
        import com.google.gson.annotations.SerializedName;

        import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "page",
        "total_results",
        "total_pages",
        "mdbMovieResults"
})
public class MdbPageResult {

    @JsonProperty("page")
    @SerializedName("page")
    private Integer page;
    @JsonProperty("total_results")
    @SerializedName("total_results")
    private Integer totalResults;
    @JsonProperty("total_pages")
    @SerializedName("total_pages")
    private Integer totalPages;
    @JsonProperty("mdbMovieResults")
    private List<MdbMovieResult> mdbMovieResults = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    @JsonProperty("mdbMovieResults")
    public List<MdbMovieResult> getMdbMovieResults() {
        return mdbMovieResults;
    }

    @JsonProperty("mdbMovieResults")
    public void setMdbMovieResults(List<MdbMovieResult> mdbMovieResults) {
        this.mdbMovieResults = mdbMovieResults;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}


