package com.example.android.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.android.popularmovies.data.FetchMovieTask;
import com.example.android.popularmovies.data.MoviePreferences;
import com.example.android.popularmovies.utilities.MovieAdapter;
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
        Log.i("MAINACT", "--Beginning--");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieDisplays = (GridView) findViewById(R.id.gv_movieData);
        progressBar = (ProgressBar) findViewById(R.id.pb_loadingBar);
        moviePoster = (ImageView) findViewById(R.id.iv_moviePoster);

        movieAdapter = new MovieAdapter(MainActivity.this, 0, posterData);
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
}
