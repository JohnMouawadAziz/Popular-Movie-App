package com.gmail.aziz.mouawad.john.johnmovieapp;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private MoviesAdapter mAdapter;

    String API_key = "f93d0797460573f3254b707975a09ed5";
    DatabaseHelper myDb;

    Button btnviewAll;

    MoviesApiService service;

    RestAdapter restAdapter;

    Movie Movie_;


    public MainFragment() {
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));



        myDb = new DatabaseHelper(getActivity());

        SharedPreferences sh = getContext().getSharedPreferences("sort",Context.MODE_PRIVATE);
        String sortby = sh.getString("sort", "pop");
        Toast.makeText(getActivity(), sortby, Toast.LENGTH_SHORT).show();
        if (sortby.equals("pop")) {
            getPopularMovies();
        } else if (sortby.equals("top")) {

            getTopularMovies();
        } else {
            getFavoriatMovie();
        }

        btnviewAll = (Button) v.findViewById(R.id.view);


//        if (savedInstanceState != null) {
//            savedInstanceState.getParcelable("on_rotation");
//        }

        return v;


    }


    private void getPopularMovies() {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.themoviedb.org/3")
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addEncodedQueryParam("api_key", "f93d0797460573f3254b707975a09ed5");
                    }
                })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        service = restAdapter.create(MoviesApiService.class);

        service.getPopularMovies("popularity.desc", new Callback<Movie.MovieResult>() {
            @Override
            public void success(Movie.MovieResult movieResult, Response response) {
                //mAdapter.setMovieList(movieResult.getResults());
                mAdapter = new MoviesAdapter(getActivity(), movieResult.getResults(), false);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(getContext()!= null) {
            SharedPreferences sh = getContext().getSharedPreferences("sort", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();

            try {
                switch (item.getItemId()) {
                    case R.id.top:
                        editor.putString("sort", "top");
                        editor.commit();
                        getTopularMovies();
                        return true;


                    case R.id.Favorit:
                        editor.putString("sort", "fav");
                        editor.commit();
                        getFavoriatMovie();
                        break;

                    case R.id.Popular:
                        editor.putString("sort", "pop");
                        editor.commit();
                        getPopularMovies();
                        break;
                    default:
                        return super.onOptionsItemSelected(item);
                }
            } catch (Exception e) {

            }

        }
        return super.onOptionsItemSelected(item);
    }


    private void getFavoriatMovie() {
        List<Movie> movies = myDb.viewData();

        mAdapter = new MoviesAdapter(getActivity(), movies, true);
        mRecyclerView.setAdapter(mAdapter);


        System.out.println(movies.get(0).getPoster());

        //Toast.makeText(getActivity(), movies.get(0).getPoster(), Toast.LENGTH_LONG).show();


        /*Picasso.with(getActivity())
                .load()
                .placeholder(R.color.colorAccent)
                .into();*/


    }

    private void getTopularMovies() {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.themoviedb.org/3")
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addEncodedQueryParam("api_key", "f93d0797460573f3254b707975a09ed5&append_to_response=videos");
                    }
                })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        TopMovieApi service = restAdapter.create(TopMovieApi.class);

        service.getPopularMovies(new Callback<Movie.TopMovieResult>() {

            @Override
            public void success(Movie.TopMovieResult topMovieResult, Response response) {
                System.out.println(topMovieResult.getResults().get(0).getPoster());
                mAdapter = new MoviesAdapter(getActivity(), topMovieResult.getResults(), false);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });



    }

}