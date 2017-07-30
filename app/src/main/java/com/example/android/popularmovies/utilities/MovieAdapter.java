package com.example.android.popularmovies.utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.android.popularmovies.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
/**
 * Created by Jonathan on 6/18/2017.
 * Adapter for displaying movie posters to gridView
 */
public class MovieAdapter extends ArrayAdapter<String>{
    private Context mContext;
    private int mLayoutResourceId;
    private ArrayList<String> mData = new ArrayList<>();
    private LayoutInflater inflater;
    private Target target;

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(mLayoutResourceId, parent, false);
        final ImageButton imageButton = (ImageButton) convertView.findViewById(R.id.ib_moviePoster);
        convertView.setTag(imageButton);

        if(target == null){
             target = new Target() {
                @Override
                public void onBitmapFailed(Drawable arg0) {
                }

                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    imageButton.setBackgroundDrawable(new BitmapDrawable(bitmap));
                }

                @Override
                public void onPrepareLoad(Drawable arg0) {
                }
            };
        }

        try {
            String poster = NetworkUtils.posterBuilder(mData.get(position));
            Picasso.with(mContext).
                    load(poster).
                    into(target);
        }
        catch(NullPointerException e) { e.printStackTrace(); }
        return imageButton;
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
