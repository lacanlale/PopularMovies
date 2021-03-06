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
 */
public class MovieJSONUtils {
    /**
     * Used for getting individual movie data
     * @param movieJSONstr response from HTTP caller
     * @return Details of a single Movie
     * @throws JSONException
     */
    public static Movie getSingleMovie(String movieJSONstr) throws JSONException {
        JSONObject movieJSON = new JSONObject(movieJSONstr);

        int movieId = movieJSON.getInt("id");
        String moviePoster = movieJSON.getString("poster_path");
        String movieOverview = movieJSON.getString("overview");
        String movieRating = movieJSON.getString("vote_average");
        String movieTitle = movieJSON.getString("title");
        String movieReleaseDate = movieJSON.getString("release_date");

        Movie parsedData = new Movie(movieId, moviePoster, movieTitle, movieRating, movieReleaseDate, movieOverview);

        return parsedData;
    }

    /**
     * Used for getting data from a whole page of movies
     * @param movieJSONstr
     * @return returns data (in string[] format) of movies
     * @throws JSONException
     */
    public static ArrayList<Movie> getMovieDetails(String movieJSONstr) throws JSONException {
        Log.i("JSON", "Parameter caught: " + movieJSONstr);

        JSONObject movieJSON = new JSONObject(movieJSONstr);
        JSONArray movieArray = movieJSON.getJSONArray("results");

        ArrayList<Movie> parsedData = new ArrayList<>();
        for (int x = 0; x < movieArray.length(); x++) {
            JSONObject movie = movieArray.getJSONObject(x);

            int id = movie.getInt("id");
            String moviePoster = movie.getString("poster_path");
            String movieOverview = movie.getString("overview");
            String movieRating = movie.getString("vote_average");
            String movieTitle = movie.getString("title");
            String movieReleaseDate = movie.getString("release_date");

            Movie data = new Movie(id, moviePoster, movieTitle, movieRating, movieReleaseDate, movieOverview);

            parsedData.add(data);
        }
        return parsedData;
    }
    public static Movie[] getJSONMovieArray(String movieJSONstr) throws JSONException {
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

            Movie data = new Movie(id, moviePoster, movieTitle, movieRating, movieReleaseDate, movieOverview);

            parsedData[x] = data;
        }
        return parsedData;
    }
}