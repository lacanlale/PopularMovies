package com.example.android.popularmovies.utilities;

import android.app.Activity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.android.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
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

    public void setData(ArrayList<String> mGridData) {
        Log.i("ADAPTER", mGridData.toString());
        Log.i("ADAPTER", "data changed");
        mData=mGridData;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("ADAPTER", "getView");
        View view = convertView;
        ImageView image = null;

        if (view == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            view = inflater.inflate(mLayoutResourceId, parent, false);
            image = (ImageView) view.findViewById(R.id.iv_moviePoster);
            view.setTag(image);
        }
        try {
            String poster = NetworkUtils.posterBuilder(mData.get(position));

            Log.i("ADAPTER", poster);

            Picasso.with(mContext).
                    load(poster).
                    into(image);
        }
        catch(NullPointerException e) { e.printStackTrace(); }
        return view;
    }
}
