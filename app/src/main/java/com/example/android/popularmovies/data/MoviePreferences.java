package com.example.android.popularmovies.data;

import android.content.Context;

/**
 * Created by Jonathan on 6/4/2017.
 */
//TODO: Finish this class so that it gets the location details of the user.
    //Location details should display the popular movies IN THE AREA
public class MoviePreferences {
    public static final String CITY = "city_name";
    public static final String LATITUDE = "coord_lat";
    public static final String LONGITUTE = "coord_long";

    /* this default is 11399 Strathern St, Los Angeles, California */
    static final String DEFAULT_LOCATION = "91352,USA";
    static final double[] DEFAULT_COORDINATES = {34.2157033, -118.3790548};
    static final String DEFAULT_ADDRESS = "11399 Strathern St, Los Angeles, California";

    static public void setLocationDetails(Context context, String city, double longitude, double latitude){

    }
    static public void setLocation(Context context, String location, double longitude, double latitude){

    }
    static public void resetCoordinates(Context context){

    }
    static public String getPreferredLocation(Context context){

        return DEFAULT_ADDRESS;
    }
    static public boolean isLocationAvailable(Context context){

        return false;
    }
}
