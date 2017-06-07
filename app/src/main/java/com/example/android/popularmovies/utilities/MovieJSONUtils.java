package com.example.android.popularmovies.utilities;

import android.content.ContentValues;
import android.content.Context;
import android.media.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

/**
 * Created by Jonathan on 6/5/2017.
 * TODO NEARLY THERE
 * include methods to get movie data
 * Simple and full versions
 */
public class MovieJSONUtils {
    public Movie[] getSimpleMovieData(Context context, String movieJSONstr) throws JSONException{
        final String LIST = "list";
        final String MOVIES = "movies";
        final String OVERVIEW = "overview";
        final String RELEASE_DATE = "release date";
        final String RATING = "rating";
        final String CODE = "cod";

        JSONObject movieJSON = new JSONObject(movieJSONstr);
        if (movieJSON.has(CODE)) {
            int errorCode = movieJSON.getInt(CODE);
            switch (errorCode) {
                case HttpURLConnection.HTTP_OK:
                    break;
                case HttpURLConnection.HTTP_NOT_FOUND:
                    return null;
                default:
                    return null;
            }
        }
        JSONArray movieArray = movieJSON.getJSONArray(LIST);
        Movie[] parsedData = new Movie[movieArray.length()];
        for(int x = 0; x < movieArray.length(); x++){
            JSONObject specificMovie = movieArray.getJSONObject(x);

            String movieOverview;
            String movieRating;
            String movieTitle;
            String movieReleaseDate;
            Image moviePoster;

        }
    }
    public ContentValues[] getFullMovieData(Context context, String movieJSONstr){

    }
}
