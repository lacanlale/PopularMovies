package com.example.android.popularmovies.data;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.popularmovies.MainActivity;
import com.example.android.popularmovies.Movie;
import com.example.android.popularmovies.utilities.MovieJSONUtils;
import com.example.android.popularmovies.utilities.NetworkUtils;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Jonathan on 7/3/2017.
 */

public class FetchMovieTask extends AsyncTask<String, Void, Movie[]> {
    @Override
    //TODO fix NetworkOnMainThreadException
    protected void onPreExecute() {
        super.onPreExecute();

        String response = NetworkUtils.movieData();
        ArrayList<String> posterData = new ArrayList<>();

        try{
            posterData = MovieJSONUtils.getSimpleMovieData(response);
        }
        catch (Exception e) { e.printStackTrace(); }

        loading.setVisibility(View.VISIBLE);
        movieDisplays.setAdapter(movieAdapter);
    }
    @Override
    protected Movie[] doInBackground(String... params) {
        if(params.length == 0) return null;
        String desired = params[0];

        Log.i("MAINACT", "desired in doInBackground(): " + desired);
        try {
            URL popularMovie = new URL(NetworkUtils.categoryBuilder(desired));
            String response = NetworkUtils.getHTTPResponse(popularMovie);

            Log.i("MAINACT", "Response in doInBackground(): " + response);

            return MovieJSONUtils.getMovieDetails(response);
        }
        catch(Exception e){
            Log.d("stupid", "Exception: " + e);
            e.printStackTrace();
            return null;
        }
    }
}