package com.example.android.popularmovies.utilities;

import android.app.Activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Network;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.Movie;
import com.example.android.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 6/18/2017.
 */

public class MovieAdapter extends ArrayAdapter<String>{
    Context mContext;
    int mLayoutResourceId;
    ArrayList<String> mData = new ArrayList<>();

    public MovieAdapter(Context context, int layoutResourceId,
                                 ArrayList<String> data) {
        super(context, layoutResourceId, data);
        mLayoutResourceId = layoutResourceId;
        mContext = context;
        mData = data;
    }

    public void setData(ArrayList<String> mGridData)
    {
        mData=mGridData;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ImageView image = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, parent, false);
            image = (ImageView) row.findViewById(R.id.iv_moviePoster);
            row.setTag(image);
        }
        try {
            Picasso.with(mContext).
                    load(NetworkUtils.posterBuilder(mData.get(position))).
                    into(image);
        }
        catch(NullPointerException e) { e.printStackTrace(); }
        return row;
    }
}
