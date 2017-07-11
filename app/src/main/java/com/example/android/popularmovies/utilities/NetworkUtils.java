package com.example.android.popularmovies.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Jonathan on 6/2/2017.
 * API KEY IS NOT SHOWN FOR LEGALITY REASONS
 * All Networking methods are handled here.
 */

public final class NetworkUtils {
    private final static String BASE_URL = "http://api.themoviedb.org/3/movie/";
    private final static String API_KEY = "?api_key=";
    private final static String POPULAR_CATEGORY = "popular";
    private final static String TOP_RATED_CATEGORY = "top_rated";
    private final static String BASE_IMAGE_PATH = "https://image.tmdb.org/t/p/w640/";
    private final static String BASE_MOVIE_PATH = "https://api.themoviedb.org/3/movie/";

    /**
     * Builds the category url based on the parameter
     * @param desiredCategory should either be popular or top_rated
     * @return String url based on the desired category
     */
    public static String categoryBuilder(String desiredCategory) {
        String url = BASE_URL;
        url += desiredCategory.equals("popular") ? POPULAR_CATEGORY : TOP_RATED_CATEGORY;
        url += API_KEY;
        return url;
    }
    public static String posterBuilder(String path){
        return BASE_IMAGE_PATH + path;
    }
    public static String specificMovieBuilder(String id){
        return BASE_MOVIE_PATH + id + API_KEY;
    }

    /**
     * Method was made as a default data retriever
     * This is used primarly when there is no change to the category preference
     * @return JSON data from the popular category
     */
    public static String movieData() {
        String response = "";
        try{
            URL popularMovie = new URL(NetworkUtils.categoryBuilder(POPULAR_CATEGORY));
            response = getHTTPResponse(popularMovie);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public static String getHTTPResponse(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream input = connection.getInputStream();
        String response, inputRead = null;
        try {
            Scanner in = new Scanner(input);
            in.useDelimiter("\\A");
            if (in.hasNext()) {
                inputRead = in.next();
            }
            response = inputRead;
            return response;
        } finally {
            connection.disconnect();
        }
    }
}