package com.example.android.popularmovies.utilities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.data.MoviePreferences;

import java.net.URL;

public class DetailActivity extends AppCompatActivity {
    String detailedInformation;
    TextView movieInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        movieInfo = (TextView) findViewById(R.id.tv_movieInfo);
        try {
            URL popularMovie = new URL(NetworkUtils.specificMovieBuilder(/* TODO */));
            String response = NetworkUtils.getHTTPResponse(popularMovie);
            detailedInformation = MovieJSONUtils.getMovieDetails(response);
        }
        catch(Exception e){ e.printStackTrace(); }
    }
}
