package com.gmail.aziz.mouawad.john.johnmovieapp;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jo on 11/16/2016.
 */
class MovieReviewe {


    @SerializedName("id")
    private String id;
    @SerializedName("author")
    private String author;
    @SerializedName("content")
    private String content;
    @SerializedName("url")
    private String url;

    public MovieReviewe() {}

    protected MovieReviewe(Parcel in) {
        this.id = in.readString();
        this.author = in.readString();
        this.content = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<MovieReviewe> CREATOR = new Parcelable.Creator<MovieReviewe>() {
        public MovieReviewe createFromParcel(Parcel source) {
            return new MovieReviewe(source);
        }

        public MovieReviewe[] newArray(int size) {
            return new MovieReviewe[size];
        }
    };


    public String getId() {
        return id;
    }

    public MovieReviewe setId(String id) {
        this.id = id;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public MovieReviewe setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MovieReviewe setContent(String content) {
        this.content = content;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public MovieReviewe setUrl(String url) {
        this.url = url;
        return this;
    }

    public int describeContents() {return 0;}



    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.author);
        parcel.writeString(this.content);
        parcel.writeString(this.url);
    }


    public static final class Response {


        @SerializedName("results")
        public List<MovieReviewe> reviews;

        @Expose
        @SerializedName("total_pages")
        public int totalPages;

        @Expose
        @SerializedName("total_results")
        public int totalResults;

        public List<MovieReviewe> getReviews() {
            return reviews;
        }

        public void setReviews(List<MovieReviewe> reviews) {
            this.reviews = reviews;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(int totalResults) {
            this.totalResults = totalResults;
        }
    }


}


