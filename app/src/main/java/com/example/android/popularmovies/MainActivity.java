package com.example.android.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.android.popularmovies.data.MoviePreferences;
import com.example.android.popularmovies.utilities.MovieAdapter;
import com.example.android.popularmovies.utilities.MovieJSONUtils;
import com.example.android.popularmovies.utilities.NetworkUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private GridView movieDisplays;
    private MovieAdapter movieAdapter;
    private ArrayList<Movie> posterData;
    private ImageView movieButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieDisplays = (GridView) findViewById(R.id.gv_movieData);
        movieAdapter = new MovieAdapter(this, R.layout.movie_posters, posterData);
        movieButton = (ImageView) findViewById(R.id.iv_moviePoster);

        new FetchMovieTask().execute(MoviePreferences.getPreferredCategory());
    }

    //TODO store id in intent extra text
    @Override
    public void onClick(View v) {
        Intent intentToStartDetailActivity = new Intent(MainActivity.this, DetailActivity.class);
        startActivity(intentToStartDetailActivity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movies, menu);
        return true;
    }

    private class FetchMovieTask extends AsyncTask<String, Void, Movie[]> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Movie[] doInBackground(String... params) {
            if(params.length == 0) return null;
            String response = NetworkUtils.movieData();
            try {
                posterData = MovieJSONUtils.getMovieDetails(response);
                Log.i("POSTERDATA", posterData.toString());
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
            movieAdapter.setPosterData(posterData);
            movieDisplays.setAdapter(movieAdapter);
        }
    }
}
