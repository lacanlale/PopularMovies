package com.example.android.popularmovies.utilities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.data.MoviePreferences;

import java.net.URL;
//TODO Class still needs to be finished

/**
 * Details are used for onClick
 * Class should utilize methods that are for displaying
 * the title, rating, release date, and overview
 */
public class DetailActivity extends AppCompatActivity {
    String detailedInformation;
    TextView movieInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        movieInfo = (TextView) findViewById(R.id.tv_movieInfo);
    }
}
