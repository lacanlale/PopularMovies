package com.example.android.popularmovies.utilities;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.android.popularmovies.Movie;
import com.example.android.popularmovies.R;

import java.util.List;

/**
 * Created by Jonathan on 6/18/2017.
 */

public class MovieAdapter extends ArrayAdapter<Movie>{
    private static final String LOG_TAG = MovieAdapter.class.getSimpleName();

    public MovieAdapter(Activity activity, List<Movie> movies){
        super(activity, 0, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);

        if(convertView != null) convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_main, parent, false);

        ImageView icon = (ImageView) convertView.findViewById(R.id.iv_moviePoster);
        icon.setImageResource(Integer.parseInt(movie.getmPoster()));

        return convertView;
    }
}
