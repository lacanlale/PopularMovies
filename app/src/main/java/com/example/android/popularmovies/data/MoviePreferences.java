package com.example.android.popularmovies.data;

import android.content.Context;

/**
 * Created by Jonathan on 6/4/2017.
 */
public class MoviePreferences {
    static String preferred_category = "";
    static final String DEFAULT_CATEGORY = "most_popular";

    static public void setCategory(String category){
        preferred_category = category;
    }
    static public String getPreferredCategory(){
        return preferred_category;
    }
}
