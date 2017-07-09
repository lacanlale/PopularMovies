package com.example.android.popularmovies;

/**
 * Created by Jonathan on 6/18/2017.
 */

public class Movie{
    private int mId;

    private String mPoster;
    private String mTitle;
    private String mRating;
    private String mReleaseDate;
    private String mOverview;

    public Movie(int id, String poster, String title, String rating, String releaseDate, String overview) {
        this.mId = id;
        this.mPoster = poster;
        this.mTitle = title;
        this.mRating = rating;
        this.mReleaseDate = releaseDate;
        this.mOverview = overview;
    }
    public String getmPoster() {
        return mPoster;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmRating() {
        return mRating;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public String getmOverview() {
        return mOverview;
    }

    public int  getmId() { return  mId; }
}
