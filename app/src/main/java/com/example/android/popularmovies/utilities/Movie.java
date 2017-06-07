package com.example.android.popularmovies.utilities;

import android.media.Image;

/**
 * Created by Jonathan on 6/6/2017.
 * Separate class for the Movie items
 */

public class Movie {
    String mTitle;
    String mReleaseDate;
    String mRating;
    String mSynopsis;
    String mPoster;

    /**
     * Movie Object constructor
     * @param title String value of the Movie title
     * @param releaseDate String value of the Release Date
     * @param rating Numerical value (as a String) of the Movie rating
     * @param synopsis String value of the synopsis
     * @param poster .PNG/.JPG url of the Movie poster. URL passed to Picasso handler
     */
    public Movie(String title, String releaseDate, String rating, String synopsis, String poster){
        mTitle = title;
        mReleaseDate = releaseDate;
        mRating = rating;
        mSynopsis = synopsis;
        mPoster = poster;
    }
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) { this.mTitle = mTitle; }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public String getRating() {
        return mRating;
    }

    public void setRating(String mRating) {
        this.mRating = mRating;
    }

    public String getSynopsis() {
        return mSynopsis;
    }

    public void setSynopsis(String mSynopsis) {
        this.mSynopsis = mSynopsis;
    }

    public String getPoster() { return mPoster; }

    public void setPoster(String mPoster) {
        this.mPoster = mPoster;
    }
}
