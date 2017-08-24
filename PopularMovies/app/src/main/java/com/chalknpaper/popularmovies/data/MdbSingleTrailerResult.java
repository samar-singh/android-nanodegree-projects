
package com.chalknpaper.popularmovies.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "iso_639_1",
        "iso_3166_1",
        "key",
        "name",
        "site",
        "size",
        "type"
})
public class MdbSingleTrailerResult implements Parcelable {

    @JsonProperty("id")
    private String id;
    @JsonProperty("iso_639_1")
    private String iso_639_1;
    @JsonProperty("iso_3166_1")
    private String iso_3166_1;
    @JsonProperty("key")
    private String key;
    @JsonProperty("name")
    private String name;
    @JsonProperty("site")
    private String site;
    @JsonProperty("size")
    private int size;
    @JsonProperty("type")
    private String type;
    public final static Parcelable.Creator<MdbSingleTrailerResult> CREATOR = new Creator<MdbSingleTrailerResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MdbSingleTrailerResult createFromParcel(Parcel in) {
            MdbSingleTrailerResult instance = new MdbSingleTrailerResult();
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            instance.iso_639_1 = ((String) in.readValue((String.class.getClassLoader())));
            instance.iso_3166_1 = ((String) in.readValue((String.class.getClassLoader())));
            instance.key = ((String) in.readValue((String.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.site = ((String) in.readValue((String.class.getClassLoader())));
            instance.size = ((int) in.readValue((int.class.getClassLoader())));
            instance.type = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public MdbSingleTrailerResult[] newArray(int size) {
            return (new MdbSingleTrailerResult[size]);
        }

    };

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("iso_639_1")
    public String getIso_639_1() {
        return iso_639_1;
    }

    @JsonProperty("iso_639_1")
    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    @JsonProperty("iso_3166_1")
    public String getIso_3166_1() {
        return iso_3166_1;
    }

    @JsonProperty("iso_3166_1")
    public void setIso_3166_1(String iso_3166_1) {
        this.iso_3166_1 = iso_3166_1;
    }

    @JsonProperty("key")
    public String getKey() {
        return key;
    }

    @JsonProperty("key")
    public void setKey(String key) {
        this.key = key;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("site")
    public String getSite() {
        return site;
    }

    @JsonProperty("site")
    public void setSite(String site) {
        this.site = site;
    }

    @JsonProperty("size")
    public int getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(int size) {
        this.size = size;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(iso_639_1);
        dest.writeValue(iso_3166_1);
        dest.writeValue(key);
        dest.writeValue(name);
        dest.writeValue(site);
        dest.writeValue(size);
        dest.writeValue(type);
    }

    public int describeContents() {
        return 0;
    }

}
