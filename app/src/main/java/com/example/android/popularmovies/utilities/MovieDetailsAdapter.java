package com.example.android.popularmovies.utilities;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Jonathan on 7/30/2017.
 * Adapter used for Movie Details
 */

public class MovieDetailsAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private int mLayoutResourceId;
    private ArrayList<String> mData = new ArrayList<>();
    private LayoutInflater inflater;

    public MovieDetailsAdapter(Context context, int layoutResourceId,
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(mLayoutResourceId, parent, false);
        TextView movieDetails = (TextView) convertView.findViewById(R.id.tv_movieInfo);
        convertView.setTag(movieDetails);
        return movieDetails;
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
