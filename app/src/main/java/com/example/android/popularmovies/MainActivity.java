package com.example.android.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.android.popularmovies.data.MoviePreferences;
import com.example.android.popularmovies.utilities.MovieAdapter;
import com.example.android.popularmovies.utilities.MovieJSONUtils;
import com.example.android.popularmovies.utilities.NetworkUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private GridView movieDisplays;
    private MovieAdapter movieAdapter;
    private ArrayList<Movie> posterData;
    private ImageButton movieButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieDisplays = (GridView) findViewById(R.id.gv_movieData);
        movieAdapter = new MovieAdapter(this, R.layout.movie_posters, posterData);
        movieButton = (ImageButton) findViewById(R.id.ib_moviePoster);

        new FetchMovieTask().execute(MoviePreferences.getPreferredCategory());

        movieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("AHHH", "x");
            }
        });
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
                Log.i("POSTER_DATA", posterData.toString());
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
