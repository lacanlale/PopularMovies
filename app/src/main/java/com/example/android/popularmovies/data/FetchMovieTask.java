package com.example.android.popularmovies.data;

import android.os.AsyncTask;

import com.example.android.popularmovies.utilities.NetworkUtils;

import java.net.URL;

/**
 * Created by Jonathan on 6/5/2017.
 */

public class FetchMovieTask extends AsyncTask<String, Void, String[]> {
   /* @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }*/

    @Override
    protected void onPostExecute(String[] strings) {
        super.onPostExecute(strings);
    }

    @Override
    protected String[] doInBackground(String... params) {
        if(params.length == 0) return null;
        String desired = params[0];
        URL popularMovieRegion = NetworkUtils.categoryBuilder(desired);
        try {
            String response = NetworkUtils.getHTTPResponse(desired);
            //TODO finish MovieJSONUtils class to finish this class
            //String[] data =
            return data;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
