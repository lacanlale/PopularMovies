package com.example.android.popularmovies.utilities;

import android.media.Image;

/**
 * Created by Jonathan on 6/6/2017.
 */

public class Movie {
    String mTitle;
    String mReleaseDate;
    String mRating;
    String mSynopsis;
    Image mPoster;

    public Movie(String title, String releaseDate, String rating, String synopsis, Image poster){
        mTitle = title;
        mReleaseDate = releaseDate;
        mRating = rating;
        mSynopsis = synopsis;
        mPoster = poster;
    }
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

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

    public Image getPoster() {
        return mPoster;
    }

    public void setPoster(Image mPoster) {
        this.mPoster = mPoster;
    }
}
