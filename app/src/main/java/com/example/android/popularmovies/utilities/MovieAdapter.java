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
    private Context mContext;
    private int mLayoutResourceId;
    private ArrayList<String> mData = new ArrayList<>();
    private LayoutInflater inflater;

    public MovieAdapter(Context context, int layoutResourceId,
                                 ArrayList<String> data) {
        super(context, layoutResourceId, data);
        mLayoutResourceId = layoutResourceId;
        mContext = context;
        mData = data;
        inflater = ( LayoutInflater )mContext.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<String> mGridData) {
        mData=mGridData;
        notifyDataSetChanged();
    }

    //TODO code is working. Posters arent displayed :(
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //inflater = ((Activity) mContext).getLayoutInflater();
        convertView = inflater.inflate(mLayoutResourceId, null);
        ImageView image = (ImageView) convertView.findViewById(R.id.iv_moviePoster);
        convertView.setTag(image);
        try {
            String poster = NetworkUtils.posterBuilder(mData.get(position));
            Picasso.with(mContext).
                    load(poster).
                    into(image);
        }
        catch(NullPointerException e) { e.printStackTrace(); }
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
