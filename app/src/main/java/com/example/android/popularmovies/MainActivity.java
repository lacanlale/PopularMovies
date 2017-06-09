package com.example.android.popularmovies;

import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.android.popularmovies.data.MoviePreferences;
import com.example.android.popularmovies.utilities.Movie;
import com.example.android.popularmovies.utilities.MovieJSONUtils;
import com.example.android.popularmovies.utilities.NetworkUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView posterImage;
    GridView movieDisplays;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        posterImage = (ImageView) findViewById(R.id.iv_poster);
        movieDisplays = (GridView) findViewById(R.id.gv_movieData);
        loadMovieData();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movies, menu);
        return true;
    }
    void loadMovieData(){


        String preference = MoviePreferences.getPreferredCategory();
        new FetchMovieTask().execute();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int movieId = item.getItemId();
        if(movieId == R.id.action_refresh){
            posterImage.setImageResource(0); //REMEMBER TO FIX THIS

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

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
                Movie[] data = MovieJSONUtils.getSimpleMovieData(MainActivity.this, response);
            }
            catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }
    }

}
