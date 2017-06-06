package com.example.android.popularmovies.utilities;

import android.net.Uri;

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
    final String BASE_URL = "http://api.themoviedb.org/3/movie/";
    final String API_KEY = "?api_key=";
    final String POPULAR_CATEGORY = "popular";
    final String TOP_RATED_CATEGORY = "top_rated";

    //TODO FINISH THIS METHOD
    public String categoryBuilder(String desiredCategory){

    }

    public String getHTTPResponse(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try{
            InputStream input = connection.getInputStream();
            Scanner in = new Scanner(input);
            in.useDelimiter("\\A");

            boolean hasNext = in.hasNext();
            if(hasNext) return in.next();
            else return null;
        }
        finally{
            connection.disconnect();
        }
}
