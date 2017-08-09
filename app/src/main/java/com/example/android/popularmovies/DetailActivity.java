package com.example.android.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.example.android.popularmovies.utilities.MovieJSONUtils;
import com.example.android.popularmovies.utilities.NetworkUtils;

import java.net.URL;
/**
 * Details are used for onClick
 * Class should utilize methods that are for displaying
 * the title, rating, release date, and overview
 */
public class DetailActivity extends AppCompatActivity {
    TextView title, rating, releaseDate, overview;
    Movie movieDetails;
    String movieID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        title = (TextView) findViewById(R.id.tv_title);
        rating = (TextView) findViewById(R.id.tv_rating);
        releaseDate = (TextView) findViewById(R.id.tv_releaseDate);
        overview = (TextView) findViewById(R.id.tv_overview);
        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
                movieID = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
                Log.i("MOVIE_DETAILS", movieID);
            }
        }
        new FetchMovieDetails().execute(movieID);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout, menu);
        return true;
    }

    private class FetchMovieDetails extends AsyncTask<String, Void, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            if(params.length == 0) return null;
            String url = NetworkUtils.specificMovieBuilder(params[0]);
            try {
                String response = NetworkUtils.getHTTPResponse(new URL(url));
                movieDetails = MovieJSONUtils.getSingleMovie(response);
                return response;
            }
            catch(Exception e){
                Log.e("MOVIE_DETAILS", "!!!"+e.toString()+"!!!");
                return null;
            }
        }

        @Override
        protected void onPostExecute(String details) {
            super.onPostExecute(details);
            title.setText(movieDetails.getmTitle());
            rating.setText("Rating: " + movieDetails.getmRating());
            releaseDate.setText("Released: " + movieDetails.getmReleaseDate());
            overview.setText(movieDetails.getmOverview());
        }
    }
}
