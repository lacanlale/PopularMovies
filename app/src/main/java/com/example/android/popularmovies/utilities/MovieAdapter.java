package com.example.android.popularmovies.utilities;

import android.app.Activity;

import android.content.Context;
import android.support.annotation.Nullable;
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
        Log.i("ADAPTER", context.toString() + " / " + layoutResourceId + " / " + data.toString());
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

    //TODO code is working. Posters arent displayed :(
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("ADAPTER", "PARAMETERS: " + position + " / " + convertView + " / " + parent.toString());

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();

        Log.i("ADAPTER", "if entered");

        convertView = inflater.inflate(mLayoutResourceId, parent, false);

        Log.i("ADAPTER", "view reinitialized");

        ImageView image = (ImageView) convertView.findViewById(R.id.iv_moviePoster);

        Log.i("ADAPTER", "image reinitialized");

        convertView.setTag(image);

        Log.i("ADAPTER", "tag set");

        try {
            String poster = NetworkUtils.posterBuilder(mData.get(position));
            Log.i("ADAPTER", poster);

            Picasso.with(mContext).
                    load(poster).
                    fit().
                    into(image);

            Log.i("ADAPTER", "poster shown");
        }
        catch(NullPointerException e) { e.printStackTrace(); }

        Log.i("ADAPTER", "GOODBYE");

        return image;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return mData.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
}
