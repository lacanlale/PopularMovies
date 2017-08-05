package com.example.android.popularmovies.utilities;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.android.popularmovies.Movie;
import com.example.android.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Jonathan on 6/18/2017.
 * Adapter for displaying movie posters to gridView
 * TODO change to be passed an arraylist of movies
 * by being passed an arraylist of movies,
 * we can utilize the movie class
 * and store the movie ID in the tag/id of the imageview
 * this might help retrieving the details
 */
public class MovieAdapter extends ArrayAdapter<Movie>{
    private Context mContext;
    private int mLayoutResourceId;
    private ArrayList<Movie> mData = new ArrayList<>();
    private LayoutInflater inflater;

    public MovieAdapter(Context context, int layoutResourceId,
                                 ArrayList<Movie> data) {
        super(context, layoutResourceId, data);
        mLayoutResourceId = layoutResourceId;
        mContext = context;
        mData = data;
        inflater = ( LayoutInflater )mContext.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setPosterData(ArrayList<Movie> mGridData) {
        mData=mGridData;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(mLayoutResourceId, parent, false);
        ImageView imageButton = (ImageView) convertView.findViewById(R.id.iv_moviePoster);
        convertView.setTag(imageButton);
        try {
            String poster = NetworkUtils.posterBuilder(mData.get(position).getmPoster());

            Log.i("POSTERS", poster);

            Picasso.with(mContext).
                    load(poster).
                    into(imageButton);
        }
        catch(NullPointerException e) { e.printStackTrace(); }

        imageButton.setId(mData.get(position).getmId());
        Log.i("MOVIE_DATA", "ID: " + imageButton.getId());

        return imageButton;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Movie getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
