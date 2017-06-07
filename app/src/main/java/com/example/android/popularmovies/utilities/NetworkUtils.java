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
    final static String BASE_URL = "http://api.themoviedb.org/3/movie/?api_key=";
    final static String API_KEY = "";
    final static String POPULAR_CATEGORY = "popular";
    final static String TOP_RATED_CATEGORY = "top_rated";

    public static String categoryBuilder(String desiredCategory) {
        String url = BASE_URL + API_KEY;
        url += desiredCategory.equals("popular") ? POPULAR_CATEGORY : TOP_RATED_CATEGORY;
        return url;
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
