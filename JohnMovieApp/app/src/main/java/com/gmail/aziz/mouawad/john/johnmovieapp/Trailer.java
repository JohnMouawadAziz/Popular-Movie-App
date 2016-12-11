package com.gmail.aziz.mouawad.john.johnmovieapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Trailer implements Parcelable
{
    @SerializedName("id")
    private String id;

    @SerializedName("iso_639_1")
    private String iso_639_1;

    @SerializedName("iso_3166_1")
    private String iso_3166_1;

    @SerializedName("key")
    private String key;

    @SerializedName("name")
    private String name;

    public Trailer() {
    }

    protected Trailer(Parcel in) {
        id = in.readString();
        iso_639_1 = in.readString();
        iso_3166_1 = in.readString();
        key = in.readString();
        name = in.readString();
        site = in.readString();
        size = in.readString();
        type = in.readString();
    }

    public static final Creator<Trailer> CREATOR = new Creator<Trailer>() {
        @Override
        public Trailer createFromParcel(Parcel in) {
            return new Trailer(in);
        }

        @Override
        public Trailer[] newArray(int size) {
            return new Trailer[size];
        }
    };

    public String getId() { return this.id; }
    @SerializedName("site")
    private String site;

    @SerializedName("size")
    private String size;

    @SerializedName("type")
    private String type;




    public void setId(String id) { this.id = id; }

    public String getIso6391() { return this.iso_639_1; }

    public void setIso6391(String iso_639_1) { this.iso_639_1 = iso_639_1; }


    public String getIso31661() { return this.iso_3166_1; }

    public void setIso31661(String iso_3166_1) { this.iso_3166_1 = iso_3166_1; }


    public String getKey() { return this.key; }

    public void setKey(String key) { this.key = key; }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }


    public String getSite() { return this.site; }

    public void setSite(String site) { this.site = site; }


    public String getSize() { return this.size; }

    public void setSize(String size) { this.size = size; }


    public String getType() { return this.type; }

    public void setType(String type) { this.type = type; }

    @Override
    public int describeContents() {return 0;}

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(iso_639_1);
        dest.writeString(iso_3166_1);
        dest.writeString(key);
        dest.writeString(name);
        dest.writeString(site);
        dest.writeString(size);
        dest.writeString(type);
    }

    public static class TrailerResult {
        private List<Trailer> results;

        public List<Trailer> getTResults() {
            return results;
        }
    }

    public static class TrailerMovieResult {
        private List<Trailer> results;

        public List<Trailer> getResults() {
            return results;
        }
    }

    public Trailer(String id, String Iso_639_1, String Iso_3166_1, String Key, String Name, String Site, String Size , String Type) {
        this.id = id;
        this.iso_639_1 = Iso_639_1;
        this.iso_3166_1 = Iso_3166_1;
        this.key = Key;
        this.name = Name;
        this.site = Site;
        this.size = Size;
        this.type = Type;

    }
}
