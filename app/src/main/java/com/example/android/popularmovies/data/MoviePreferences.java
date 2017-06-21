package com.example.android.popularmovies.data;

/**
 * Created by Jonathan on 6/4/2017.
 */
public class MoviePreferences {
    private static String preferred_category = "";
    private static final String DEFAULT_CATEGORY = "most_popular";

    static public void setCategory(String category){
        preferred_category = category;
    }
    static public String getPreferredCategory(){
        return preferred_category.equals("") ? DEFAULT_CATEGORY : preferred_category;
    }
}
