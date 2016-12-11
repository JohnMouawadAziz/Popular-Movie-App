package com.gmail.aziz.mouawad.john.johnmovieapp;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment  {

    public TrailerAdapter trailerAdapter;
    public ReviewAdapter reviewAdapter;

    MoviesApiService service;

    // String photo;

    // String id_for_reveiwe;

    DatabaseHelper myDb;

    Button btnAddData;


    Movie mMovie;
   // Trailer Tmovie;

    ImageView backdrop, poster;

    TextView title, description, id, releaseData, vote, rev;

   // String KEY_VEDIO;
    private CollapsingToolbarLayout toolbarLayout;

    public ListView trailerList;
    public ListView reviewList;
    private boolean isInserted;


    public DetailFragment() {
        setHasOptionsMenu(true);
    }

    public static DetailFragment getInstance(Movie movie) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("movie", movie);
        detailFragment.setArguments(bundle);
        return detailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);

        myDb = new DatabaseHelper(getActivity());
        btnAddData = (Button) v.findViewById(R.id.add);

        toolbarLayout = (CollapsingToolbarLayout) getActivity().findViewById(R.id.toolbar_layout);

        backdrop = (ImageView) getActivity().findViewById(R.id.backdrop);
        title = (TextView) v.findViewById(R.id.movie_title);
        description = (TextView) v.findViewById(R.id.movie_description);
        poster = (ImageView) v.findViewById(R.id.movie_poster);
        id = (TextView) v.findViewById(R.id.movie_id);
        releaseData = (TextView) v.findViewById(R.id.release_date);
        vote = (TextView) v.findViewById(R.id.vote_avrage);

        trailerList = (ListView) v.findViewById(R.id.trailer_list);
        //trailerList.setOnItemClickListener(this);
        Button trilerVedio= (Button) v.findViewById(R.id.trailer_button);

       /* trilerVedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String youtubeVedio= Tmovie.getKey().toString();
                Intent intent = new Intent();
                intent.putExtra("youtube_Key", youtubeVedio);
                startActivity(intent);
                Toast.makeText(getContext(),""+ Tmovie.getKey().toString(),Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(),""+ Tmovie.getKey().toString(),Toast.LENGTH_SHORT).show();

            }
        });*/






        trailerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("hereee");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Trailer TM=trailerAdapter.getItem(position);
                intent.setData(Uri.parse("http://www.youtube.com/watch?v=" + TM.getKey()));
                getContext().startActivity(intent);

            }
        });


        reviewList = (ListView) v.findViewById(R.id.review_list);

        return v;
    }


    @Override
    public void onStart() {
        super.onStart();

        mMovie = getArguments().getParcelable("movie");
        //  toolbarLayout.setTitle(mMovie.getTitle());
        title.setText(mMovie.getTitle());
        description.setText(mMovie.getDescription());
        id.setText(mMovie.getId());
        releaseData.setText(mMovie.getRelaseDate());
        vote.setText(mMovie.getVote());


        Picasso.with(getActivity()).load(mMovie.getBackdrop()).into(poster);
        //  Picasso.with(getContext()).load(mMovie.getBackdrop()).into(backdrop);

        AddData();

        getReviewsMovies(mMovie.getId());
        getMovieTrailers(mMovie.getId());
    }

    public void AddData() {

        btnAddData.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
//
                        if(!myDb.getID().contains(mMovie.getId())){
                            isInserted = myDb.insertData(id.getText().toString(),
                                    title.getText().toString(),
                                    description.getText().toString(),
                                    vote.getText().toString(),
                                    releaseData.getText().toString(),
                                    mMovie.getPoster().toString()
                            );}else{
                            Toast.makeText(getActivity(),"Alerdy exist",Toast.LENGTH_SHORT).show();
                        }

                        if (isInserted = true)
                            Toast.makeText(getActivity(), "inserted Success", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getActivity(), "inserted Faild", Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }


/////////////////////////////////////////////////Review////////////////////////////////////////////////

    private void getReviewsMovies(String id_for_reveiwe) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.themoviedb.org/3")
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestInterceptor.RequestFacade request) {
                        request.addEncodedQueryParam("api_key", "f93d0797460573f3254b707975a09ed5");
                    }
                })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        service = restAdapter.create(MoviesApiService.class);

        service.getReviewsMovies(id_for_reveiwe, new Callback<Review.RevieweResult>() {


            @Override
            public void success(Review.RevieweResult revieweResult, Response response) {
                reviewAdapter = new ReviewAdapter(getActivity(),  revieweResult.getResults());
                reviewList.setAdapter(reviewAdapter);

            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
                Toast.makeText(getActivity(), "Reviewe FAILD connectting to server " , Toast.LENGTH_SHORT).show();

            }
        });
    }





///////////////////////////////////////////Trailer///////////////////////////////////////////////////




    private void getMovieTrailers(String id_for_reveiwe) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.themoviedb.org/3")
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestInterceptor.RequestFacade request) {
                        request.addEncodedQueryParam("api_key", "f93d0797460573f3254b707975a09ed5");
                    }
                })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        service = restAdapter.create(MoviesApiService.class);

        service.getMovieTrailers(id_for_reveiwe, new Callback<Trailer.TrailerResult>() {

            @Override
            public void success(Trailer.TrailerResult trailerResult, Response response) {
               // Toast.makeText(getActivity(), "Trailer success  " , Toast.LENGTH_LONG).show();
                trailerAdapter = new TrailerAdapter(getActivity(),  trailerResult.getTResults());
                trailerList.setAdapter(trailerAdapter);




            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
                Toast.makeText(getActivity(), "Reviewe FAILD " , Toast.LENGTH_SHORT).show();

            }
        });
    }




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

//        if (id == R.id.favorit) {
//            Intent intent = new Intent(getActivity(), M.class);
//            startActivity(intent);
//
//
//
//
//        }

        return true;
    }

//   @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
////       Intent intent = new Intent(Intent.ACTION_VIEW);
////       Trailer TM=trailerAdapter.getItem(position);
////
////       intent.setData(Uri.parse("http://www.youtube.com/watch?v=" + TM.getKey()));
////
////       getContext().startActivity(intent);
//
//    }


}
