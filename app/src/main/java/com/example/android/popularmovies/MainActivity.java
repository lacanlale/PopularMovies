package com.example.android.popularmovies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.android.popularmovies.data.MoviePreferences;
import com.example.android.popularmovies.utilities.MovieAdapter;
import com.example.android.popularmovies.utilities.MovieJSONUtils;
import com.example.android.popularmovies.utilities.NetworkUtils;

import java.net.URL;
import java.util.ArrayList;

//TODO FOCUS ON GETTING POSTERS DISPLAYED. POSSIBLY FIX ADAPTER
public class MainActivity extends AppCompatActivity{
    private GridView movieDisplays;
    private ProgressBar progressBar;
    private ImageView moviePoster;
    private MovieAdapter movieAdapter;
    private ArrayList<String> posterData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieDisplays = (GridView) findViewById(R.id.gv_movieData);
        progressBar = (ProgressBar) findViewById(R.id.pb_loadingBar);
        moviePoster = (ImageView) findViewById(R.id.iv_moviePoster);
        movieAdapter = new MovieAdapter(this, R.layout.activity_main, posterData);
        /*movieDisplays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentForMovieInfo = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(intentForMovieInfo);
            }
        });*/
        new FetchMovieTask().execute(MoviePreferences.getPreferredCategory());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movies, menu);
        return true;
    }
    void showMovieDataView(){
        movieDisplays.setVisibility(View.VISIBLE);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int movieId = item.getItemId();
        if(movieId == R.id.action_refresh) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class FetchMovieTask extends AsyncTask<String, Void, Movie[]> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected Movie[] doInBackground(String... params) {
            if(params.length == 0) return null;
            String response = NetworkUtils.movieData();
            try {
                posterData = MovieJSONUtils.getSimpleMovieData(response);
                return MovieJSONUtils.getMovieDetails(response);
            }
            catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Movie[] movies) {
            super.onPostExecute(movies);
            movieAdapter.setData(posterData);
            movieDisplays.setAdapter(movieAdapter);
            showMovieDataView();
        }
    }
}
