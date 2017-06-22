package com.example.android.popularmovies.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Jonathan on 6/2/2017.
 * API KEY IS NOT SHOWN FOR LEGALITY REASONS
 */

public final class NetworkUtils {
    private final static String BASE_URL = "http://api.themoviedb.org/3/movie/";
    private final static String API_KEY = "?api_key=d0db4a575a2312422dde257f93e6c101";
    private final static String POPULAR_CATEGORY = "popular";
    private final static String TOP_RATED_CATEGORY = "top_rated";
    private final static String BASE_IMAGE_PATH = "image.tmdb.org/t/p/w640/";
    private final static String BASE_MOVIE_PATH = "https://api.themoviedb.org/3/movie/";

    public static String categoryBuilder(String desiredCategory) {
        String url = BASE_URL + API_KEY;
        url += desiredCategory.equals("popular") ? POPULAR_CATEGORY : TOP_RATED_CATEGORY;
        return url;
    }
    public static String posterBuilder(String path){
        return BASE_IMAGE_PATH + path;
    }
    public static String specificMovieBuilder(String id){
        return BASE_MOVIE_PATH + id + API_KEY;
    }
    public static String moiveData() {
        String response = "";
        try{
            URL popularMovie = new URL(NetworkUtils.categoryBuilder(POPULAR_CATEGORY));
            response = getHTTPResponse(popularMovie);
        }
        catch(Exception e) { e.printStackTrace(); }
        return response;
    }
    public static String getHTTPResponse(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            InputStream input = connection.getInputStream();
            Scanner in = new Scanner(input);
            in.useDelimiter("\\A");

            boolean hasNext = in.hasNext();
            if (hasNext) return in.next();
            else return null;
        } finally {
            connection.disconnect();
        }
    }
}
