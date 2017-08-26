package com.chalknpaper.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chalknpaper.popularmovies.data.MdbSingleTrailerResult;
import com.chalknpaper.popularmovies.data.MdbVideoTrailerResult;
import com.chalknpaper.popularmovies.databinding.ListItemTrailerBinding;
import com.chalknpaper.popularmovies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by samarsingh on 21/08/17.
 */

public class MovieTrailerAdapter extends RecyclerView.Adapter<MovieTrailerAdapter.TrailerViewHolder> {

    ListItemTrailerBinding mListItemTrailerBinding;

    private static final String TAG = MovieTrailerAdapter.class.getSimpleName();
    private Activity activity;
    private ArrayList trailerImageList;
    private MdbVideoTrailerResult mMdbVideoTrailerResult;
    Context mContext;

    public MovieTrailerAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public MovieTrailerAdapter.TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);

        mListItemTrailerBinding = ListItemTrailerBinding.inflate(inflater,parent,false);
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.list_item_trailer, parent, false);

        return new TrailerViewHolder(mListItemTrailerBinding);
    }

    @Override
    public void onBindViewHolder(MovieTrailerAdapter.TrailerViewHolder holder, final int position) {

//        holder.videoPreviewImage.setImageDrawable((Drawable) trailerImageList.get(position));
        //       holder.videoPreviewImage.setBackgroundColor(0xe6e600);
        MdbSingleTrailerResult singleMovieTrailerResult = mMdbVideoTrailerResult.getResults().get(position);
        String trailerId = singleMovieTrailerResult.getKey();
        URL mPosterUrl = NetworkUtils.buildUrlTrailer(trailerId);
        // Use Picasso here to load images onto Grid
        Picasso.with(mContext).load(mPosterUrl.toString()).into(holder.mListItemTrailerBinding.videoPreviewImage);

        // TODO: 24/08/17 : Implement onClickListener here!
        holder.mListItemTrailerBinding.videoPreviewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playYouTubeTrailed(position);
            }
        });

    }

    private void playYouTubeTrailed(int position) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" +
                    mMdbVideoTrailerResult.getResults().get(position).getKey()));
            mContext.startActivity(intent);
        } catch (Exception ex) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" +
                    mMdbVideoTrailerResult.getResults().get(position).getKey()));
            mContext.startActivity(intent);
        }
    }

    @Override
    public int getItemCount() {
        return mMdbVideoTrailerResult.getResults().size();
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder {

        ListItemTrailerBinding mListItemTrailerBinding;

        public TrailerViewHolder(ListItemTrailerBinding binding) {
            super(binding.getRoot());
            mListItemTrailerBinding = binding;
        }
    }

    public void setMovieData(MdbVideoTrailerResult movieTrailerData) {
        mMdbVideoTrailerResult = movieTrailerData;
        notifyDataSetChanged();
    }
}

