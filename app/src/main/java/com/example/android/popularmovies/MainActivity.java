package com.example.android.popularmovies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.popularmovies.data.MoviePreferences;
import com.example.android.popularmovies.utilities.MovieJSONUtils;
import com.example.android.popularmovies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.net.URL;

public class MainActivity extends AppCompatActivity{
    GridView movieDisplays;
    TextView errorMessage;
    ProgressBar progressBar;
    ImageView movieViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieDisplays = (GridView) findViewById(R.id.gv_movieData);
        errorMessage = (TextView) findViewById(R.id.tv_error);
        progressBar = (ProgressBar) findViewById(R.id.pb_loadingBar);
        movieViewAdapter = (ImageView) findViewById(R.id.iv_moviePoster);

        movieViewAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO FINISH SHOULD LOAD DETAILS
            }
        });
        loadMovieData();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movies, menu);
        return true;
    }
    void showMovieDataView(){
        movieDisplays.setVisibility(View.VISIBLE);
        errorMessage.setVisibility(View.INVISIBLE);
    }
    void showErrorMessage(){
        errorMessage.setVisibility(View.VISIBLE);
        movieDisplays.setVisibility(View.INVISIBLE);
    }
    void loadMovieData(){
        showMovieDataView();
        String preference = MoviePreferences.getPreferredCategory();
        new FetchMovieTask().execute(preference);
        String[] posters;
        try {
            URL popularMovie = new URL(NetworkUtils.categoryBuilder(preference));
            String response = NetworkUtils.getHTTPResponse(popularMovie);
            posters = MovieJSONUtils.getMovieDetails(response);
            displayPosters(posters);
        }
        catch(Exception e){ e.printStackTrace(); }
    }
    void displayPosters(String[] posterPaths){
        for(int count = 0; count < posterPaths.length; count++){
            Picasso.with(this).load(NetworkUtils.posterBuilder(posterPaths[count])).into(movieViewAdapter);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int movieId = item.getItemId();
        if(movieId == R.id.action_refresh) {
            loadMovieData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class FetchMovieTask extends AsyncTask<String, Void, String[]> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String[] data) {
            progressBar.setVisibility(View.VISIBLE);
            if(data != null){
                showMovieDataView();
                //TODO have statement for implementing GridView display
            }
            else showErrorMessage();
        }

        @Override
        protected String[] doInBackground(String... params) {
            if(params.length == 0) return null;
            String desired = params[0];
            try {
                URL popularMovie = new URL(NetworkUtils.categoryBuilder(desired));
                String response = NetworkUtils.getHTTPResponse(popularMovie);
                return MovieJSONUtils.getMovieDetails(response);
            }
            catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }
    }

}
