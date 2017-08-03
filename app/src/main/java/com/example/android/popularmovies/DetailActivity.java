package com.example.android.popularmovies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.example.android.popularmovies.data.MoviePreferences;
import com.example.android.popularmovies.utilities.MovieDetailsAdapter;
import com.example.android.popularmovies.utilities.MovieJSONUtils;
import com.example.android.popularmovies.utilities.NetworkUtils;

import java.util.ArrayList;

//TODO Class still needs to be finished
//Class should be receiving the unique MOVIE KEY
//This could be used to be passed to MovieJSONUtils to get the details of a single movie.

/**
 * Details are used for onClick
 * Class should utilize methods that are for displaying
 * the title, rating, release date, and overview
 */
public class DetailActivity extends AppCompatActivity {
    TextView movieInfo;
    ArrayList<String> movieDetails = new ArrayList<>();
    MovieDetailsAdapter detailsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        movieInfo = (TextView) findViewById(R.id.tv_movieInfo);

        Log.d("DETAILS", getApplicationContext().toString());

        new FetchMovieDetails().execute(MoviePreferences.getPreferredCategory());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout, menu);
        return true;
    }

    private class FetchMovieDetails extends AsyncTask<String, Void, Movie[]>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Movie[] doInBackground(String... params) {
            if(params.length == 0) return null;
            String response = NetworkUtils.movieData();
            try {
                movieDetails = MovieJSONUtils.getSingleMovie(response);
                return MovieJSONUtils.getJSONMovieArray(response);
            }
            catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Movie[] movies) {
            super.onPostExecute(movies);
            //detailsAdapter.setData(detailsData);
            //movieInfo.setAdapter(detailsAdapter);
        }
    }
}
