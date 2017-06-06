package com.example.android.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.android.popularmovies.data.MoviePreferences;

public class MainActivity extends AppCompatActivity {
    ImageView mPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPoster = (ImageView) findViewById(R.id.iv_poster);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movies, menu);
        return true;
    }
    //TODO finish loadMovieData after FetchMovieTask is finished
    void loadMovieData(){
        String location = MoviePreferences.getPreferredLocation(this);
        new
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int movieId = item.getItemId();
        if(movieId == R.id.action_refresh){
            mPoster.setImageResource(0); //REMEMBER TO FIX THIS

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
