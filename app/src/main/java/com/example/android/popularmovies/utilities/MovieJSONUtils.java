package com.example.android.popularmovies.utilities;

import android.util.Log;

import com.example.android.popularmovies.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jonathan on 6/5/2017.
 * include methods to get movie data
 * Current issue is most likely with API reader. Fix JSON utils class
 * TODO JSON OFFICIALLY WORKING PROPERLY AS OF 6/30
 */
public class MovieJSONUtils {
    public static ArrayList<String> getSimpleMovieData(String movieJSONstr) throws JSONException {
        Log.d("simple", "I made it here");
        Log.d("simple", "This is what I'll pass: " + movieJSONstr);

        Movie[] movieData = getMovieDetails(movieJSONstr);

        Log.d("simple", "and here");

        ArrayList<String> parsedData = new ArrayList<>();
        for(Movie m : movieData){
            parsedData.add(m.getmPoster());
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
    public static Movie[] getMovieDetails(String movieJSONstr) throws JSONException {
        Log.i("JSON", "Parameter caught: " + movieJSONstr);

        JSONObject movieJSON = new JSONObject(movieJSONstr);
        JSONArray movieArray = movieJSON.getJSONArray("results");

        Movie[] parsedData = new Movie[movieArray.length()];
        for (int x = 0; x < movieArray.length(); x++) {
            JSONObject movie = movieArray.getJSONObject(x);

            int id = movie.getInt("id");
            String moviePoster = movie.getString("poster_path");
            String movieOverview = movie.getString("overview");
            String movieRating = movie.getString("vote_average");
            String movieTitle = movie.getString("title");
            String movieReleaseDate = movie.getString("release_date");

            Log.i("JSON", "Movie #" + x);
            Log.i("JSON", "ID: " + id);
            Log.i("JSON", "Poster: " + movie.getString("poster_path"));
            Log.i("JSON", "Overview: " + movie.getString("overview"));
            Log.i("JSON", "Vote Average: " + movie.getString("vote_average"));
            Log.i("JSON", "Title: " + movie.getString("title"));
            Log.i("JSON", "Release Date: " + movie.getString("release_date"));

            Movie data = new Movie(id, moviePoster, movieTitle, movieRating, movieReleaseDate, movieOverview);

            parsedData[x] = data;
        }
        return parsedData;
    }
}