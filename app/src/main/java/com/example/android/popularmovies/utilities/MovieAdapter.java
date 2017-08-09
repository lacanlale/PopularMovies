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
        inflater = (LayoutInflater) mContext.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setPosterData(ArrayList<Movie> mGridData) {
        mData=mGridData;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        convertView = inflater.inflate(mLayoutResourceId, parent, false);
        ImageView imageButton = (ImageView) convertView.findViewById(R.id.iv_moviePoster);
        try {
            String poster = NetworkUtils.posterBuilder(getItem(position).getmPoster());

            Log.i("POSTERS", poster);

            Picasso.with(mContext).
                    load(poster).
                    into(imageButton);
        }
        catch(NullPointerException e) { e.printStackTrace(); }
        convertView.setTag(imageButton);
        convertView.setId(getItem(position).getmId());
        return convertView;
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

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
