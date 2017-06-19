package com.example.android.popularmovies.utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by Jonathan on 6/5/2017.
 * include methods to get movie data
 */
public class MovieJSONUtils {
    public static String[] getSimpleMovieData(String movieJSONstr) throws JSONException {
        JSONObject movieJSON = new JSONObject(movieJSONstr);
        JSONArray movieArray = movieJSON.getJSONArray("results");

        String[] parsedData = new String[movieArray.length()];
        for (int x = 0; x < movieArray.length(); x++) {
            JSONObject movieInfo = movieArray.getJSONObject(x);
            String moviePoster = movieInfo.getString("poster_path");
            parsedData[x] = moviePoster;
        }
        return parsedData;
    }
    public static String getMovieDetails(String movieJSONstr) throws JSONException {
        JSONObject movieJSON = new JSONObject(movieJSONstr);

        String movieOverview = movieJSON.getString("overview");
        String movieRating = movieJSON.getString("vote_average");
        String movieTitle = movieJSON.getString("title");
        String movieReleaseDate = movieJSON.getString("release_date");

        String parsedData = ("Title: " + movieTitle +
                "\nRating: " + movieRating +
                "\nOverview: " + movieOverview +
                "\nRelease Date: " + movieReleaseDate);

        return parsedData;
    }
}