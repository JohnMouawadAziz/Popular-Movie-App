package com.gmail.aziz.mouawad.john.johnmovieapp;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jo on 11/20/2016.
 */
class MovieTrailer implements Parcelable {
    @SerializedName("key")
    private String key;


    public MovieTrailer() {
    }

    protected MovieTrailer(Parcel in) {
        key = in.readString();

    }

    public static final Creator<MovieTrailer> CREATOR = new Creator<MovieTrailer>() {
        @Override
        public MovieTrailer createFromParcel(Parcel in) {
            return new MovieTrailer(in);
        }

        @Override
        public MovieTrailer[] newArray(int size) {
            return new MovieTrailer[size];
        }
    };

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(key);

    }

    public static class MovieResult {
        private List<MovieTrailer> results;

        public List<MovieTrailer> getResults() {
            return results;
        }
    }

    public static class TopMovieResult {
        private List<MovieTrailer> results;

        public List<MovieTrailer> getResults() {
            return results;
        }
    }


}
