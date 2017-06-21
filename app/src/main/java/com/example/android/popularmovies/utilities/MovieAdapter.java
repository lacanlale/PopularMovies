package com.example.android.popularmovies.utilities;

import android.app.Activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.android.popularmovies.Movie;
import com.example.android.popularmovies.R;

import java.net.URL;
import java.util.List;

/**
 * Created by Jonathan on 6/18/2017.
 */

public class MovieAdapter extends ArrayAdapter<Movie>{
    public MovieAdapter(Activity context, List<Movie> movies){
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);

        if(convertView != null) convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_main, parent, false);

        ImageView icon = (ImageView) convertView.findViewById(R.id.iv_moviePoster);
        //Drawable image = Drawable.createFromPath(
          //      NetworkUtils.getHTTPResponse(
               //         new URL(
                  //              NetworkUtils.posterBuilder())));
        //icon.setImageDrawable(image);

        return convertView;
    }
}
