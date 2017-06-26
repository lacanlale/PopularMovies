package com.example.android.popularmovies;

import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;

import com.example.android.popularmovies.data.MoviePreferences;
import com.example.android.popularmovies.utilities.MovieAdapter;
import com.example.android.popularmovies.utilities.MovieJSONUtils;
import com.example.android.popularmovies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.net.URL;
import java.util.ArrayList;

//TODO FOCUS ON GETTING POSTERS DISPLAYED. POSSIBLY FIX ADAPTER
public class MainActivity extends AppCompatActivity{
    GridView movieDisplays;
    TextView errorMessage;
    ProgressBar progressBar;
    ImageView moviePoster;
    ArrayList<String> movieData = new ArrayList<>();
    MovieAdapter movieAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieDisplays = (GridView) findViewById(R.id.gv_movieData);
        errorMessage = (TextView) findViewById(R.id.tv_error);
        progressBar = (ProgressBar) findViewById(R.id.pb_loadingBar);
        moviePoster = (ImageView) findViewById(R.id.iv_moviePoster);

        //TODO should be in AsyncTask
//        String jsonResponse = NetworkUtils.moiveData();
//        try { movieData = MovieJSONUtils.getSimpleMovieData(jsonResponse); }
//        catch (JSONException e){ e.printStackTrace(); }
//
//        movieAdapter = new MovieAdapter(this, R.id.gv_movieData, movieData);
//        movieAdapter.setData(movieData);
//
//        movieDisplays.setAdapter(movieAdapter);
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
        errorMessage.setVisibility(View.INVISIBLE);
    }
    //TODO ERROR IS CRASHING APP. MAY JUST REMOVE
    void showErrorMessage(){
        movieDisplays.setVisibility(View.INVISIBLE);
        errorMessage.setVisibility(View.VISIBLE);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int movieId = item.getItemId();
        if(movieId == R.id.action_refresh) {
            //loadMovieData();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class FetchMovieTask extends AsyncTask<String, Void, String[]> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            MovieAdapter adapter = new MovieAdapter(
                    MainActivity.this ,R.layout.activity_main, movieData);
            if(movieData.size()>0)
            {
                movieAdapter.setData(movieData);
                movieDisplays.setAdapter(adapter);
            }
        }
        @Override
        protected String[] doInBackground(String... params) {
            //todo biggest complication is issue with networking.
            String length = "Length of params(L104): " + params.length;


            if(params.length == 0) return null;
            String desired = params[0];
            try {
                URL popularMovie = new URL(NetworkUtils.categoryBuilder(desired));
                String response = NetworkUtils.getHTTPResponse(popularMovie);

                Log.d("NETWORKING", response);

                return MovieJSONUtils.getMovieDetails(response);
            }
            catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }
    }
}
