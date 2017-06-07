package com.example.android.popularmovies.data;

import android.graphics.Movie;
import android.os.AsyncTask;

import com.example.android.popularmovies.MainActivity;
import com.example.android.popularmovies.utilities.MovieJSONUtils;
import com.example.android.popularmovies.utilities.NetworkUtils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jonathan on 6/5/2017.
 * TODO NOT UNNECESSARY BUT MOST LIKELY DONE INCORRECTLY
 */

public class FetchMovieTask extends AsyncTask<String, Void, String[]> {
    @Override
    protected void onPostExecute(String[] strings) {
        super.onPostExecute(strings);
    }

    @Override
    protected String[] doInBackground(String... params) {
        if(params.length == 0) return null;
        String desired = params[0];
        URL popularMovie = null;
        try {
            popularMovie = new URL(NetworkUtils.categoryBuilder(desired));
            String response = NetworkUtils.getHTTPResponse(popularMovie);
            Movie[] data = MovieJSONUtils.getSimpleMovieData(MainActivity.getContext(), response);
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
