package com.example.android.popularmovies.utilities;

import android.content.ContentValues;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Jonathan on 6/5/2017.
 * TODO NEARLY THERE
 * include methods to get movie data
 * Simple and full versions
 */
public class MovieJSONUtils {
    public static Movie[] getSimpleMovieData(Context context, String movieJSONstr) throws JSONException, IOException {
        HttpURLConnection moviePage = null;
        BufferedReader reader = null;

        JSONObject movieJSON = new JSONObject(movieJSONstr);
        JSONArray movieArray = movieJSON.getJSONArray("results");

        Movie[] parsedData = new Movie[movieArray.length()];
        for (int x = 0; x < movieArray.length(); x++) {
            JSONObject movieInfo = movieArray.getJSONObject(x);
            String movieOverview = movieInfo.getString("overview");
            String movieRating = movieInfo.getString("vote_average").toString();
            String movieTitle = movieInfo.getString("title");
            String movieReleaseDate = movieInfo.getString("release_date");
            String moviePoster = movieInfo.getString("poster_path");
            parsedData[x] = new Movie(movieOverview, movieRating, movieTitle, movieReleaseDate, moviePoster);
        }
        return parsedData;
    }

    public static ContentValues[] getFullMovieData(Context context, String movieJSONstr, String preferred) {

    }
}