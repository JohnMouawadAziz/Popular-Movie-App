package com.gmail.aziz.mouawad.john.johnmovieapp;



import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jo on 10/6/15.
 */
public class Movie implements Parcelable {
    @SerializedName("id")
    private String id;
    @SerializedName("vote_average")
    private String vote;
    @SerializedName("release_date")
    private String releaseDate;

    private String title;
    @SerializedName("poster_path")
    private String poster;
    @SerializedName("overview")
    private String description;
    @SerializedName("backdrop_path")
    private String backdrop;


    public Movie(String s, String valueOf, String col_RELEASE_DATE, String col_TITLE, String col_IMAGE, String col_DESCRIBTION, String col_REVIEWE) {
    }

    protected Movie(Parcel in) {
        title = in.readString();
        poster = in.readString();
        description = in.readString();
        backdrop = in.readString();
        id = in.readString();
        vote = in.readString();
        releaseDate = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return  poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBackdrop() {
        return "http://image.tmdb.org/t/p/w500" + backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getVote() { return vote; }

    public void setVote(String vote) { this.vote = vote; }

    public String getRelaseDate() { return releaseDate; }

    public void setRelaseDate(String relaseDate) { this.releaseDate = relaseDate; }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(title);
        parcel.writeString(poster);
        parcel.writeString(description);
        parcel.writeString(backdrop);
        parcel.writeString(id);
        parcel.writeString(vote);
        parcel.writeString(releaseDate);
    }

    public static class MovieResult {
        private List<Movie> results;

        public List<Movie> getResults() {
            return results;
        }
    }

    public static class TopMovieResult {
        private List<Movie> results;

        public List<Movie> getResults() {
            return results;
        }
    }

    public Movie(String id, String vote, String releaseDate, String title, String poster, String description) {
        this.id = id;
        this.vote = vote;
        this.releaseDate = releaseDate;
        this.title = title;
        this.poster = poster;
        this.description = description;
    }
}






