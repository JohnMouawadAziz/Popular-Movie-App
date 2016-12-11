package com.gmail.aziz.mouawad.john.johnmovieapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Review implements Parcelable
{
    @SerializedName("id")
    private String id;

    @SerializedName("author")
    private String author;

    @SerializedName("content")
    private String content;

    @SerializedName("url")
    private String url;

    public Review() {
    }

    protected Review(Parcel in) {
        id = in.readString();
        author = in.readString();
        content = in.readString();
        url = in.readString();
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    public String getId() { return this.id; }

    public void setId(String id) { this.id = id; }


    public String getAuthor() { return this.author; }

    public void setAuthor(String author) { this.author = author; }


    public String getContent() { return this.content; }

    public void setContent(String content) { this.content = content; }


    public String getUrl() { return this.url; }

    public void setUrl(String url) { this.url = url; }

    @Override
    public int describeContents() {return 0;}

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(author);
        dest.writeString(content);
        dest.writeString(url);
    }

    public static class RevieweResult {
        private List<Review> results;

        public List<Review> getResults() {
            return results;
        }
    }

    public Review(String Id, String Author, String Content, String Url) {
        this.id = Id;
        this.author = Author;
        this.content = Content;
        this.url = Url;

    }
}
