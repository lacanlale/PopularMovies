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

    /**
     * Used for getting individual movie data
     * @param movieJSONstr response from HTTP caller
     * @return Details of a single Movie
     * @throws JSONException
     */
    public static String getSingleMovie(String movieJSONstr) throws JSONException {
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

    /**
     * Used for getting data from a whole page of movies
     * @param movieJSONstr
     * @return returns data (in string[] format) of movies
     * @throws JSONException
     */
    public static String[] getMovieDetails(String movieJSONstr) throws JSONException {
        JSONObject movieJSON = new JSONObject(movieJSONstr);
        JSONArray movieArray = movieJSON.getJSONArray("results");

        String[] parsedData = new String[movieArray.length()];
        for (int x = 0; x < movieArray.length(); x++) {
            JSONObject movie = movieArray.getJSONObject(x);

            String movieOverview = movie.getString("overview");
            String movieRating = movie.getString("vote_average");
            String movieTitle = movie.getString("title");
            String movieReleaseDate = movie.getString("release_date");

            parsedData[x] = ("Title: " + movieTitle +
                    "\nRating: " + movieRating +
                    "\nOverview: " + movieOverview +
                    "\nRelease Date: " + movieReleaseDate);
        }
        return parsedData;
    }
}