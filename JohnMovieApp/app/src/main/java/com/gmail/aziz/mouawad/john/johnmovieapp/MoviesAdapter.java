package com.gmail.aziz.mouawad.john.johnmovieapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jo on 11/19/2016.
 */


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private List<Movie> mMovieList;
    private LayoutInflater mInflater;
    private Context mContext;
    boolean fav;

    public MoviesAdapter(Context context, List<Movie> mMovieList, boolean fav) {
        this.mContext = context;
        this.mMovieList = mMovieList;
        this.fav = fav;

    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_movie, parent, false);
        final MovieViewHolder viewHolder = new MovieViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Movie movie = mMovieList.get(position);
                ((MainActivity) mContext).viewDetails(movie);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = mMovieList.get(position);

        Log.i("movie", movie.getPoster());


        Picasso.with(mContext)
                .load("http://image.tmdb.org/t/p/w500" + movie.getPoster())
                .placeholder(R.color.colorAccent)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        Log.i("text", "" + (mMovieList == null ? 0 : mMovieList.size()));

        return (mMovieList == null) ? 0 : mMovieList.size();
    }

    public void setMovieList(List<Movie> movieList) {
        this.mMovieList = new ArrayList<>();
        this.mMovieList.addAll(movieList);
        notifyDataSetChanged();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}

