package com.chalknpaper.popularmovies;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chalknpaper.popularmovies.data.MdbSingleTrailerResult;
import com.chalknpaper.popularmovies.data.MdbVideoTrailerResult;
import com.chalknpaper.popularmovies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by samarsingh on 21/08/17.
 */

public class MovieTrailerAdapter extends RecyclerView.Adapter<MovieTrailerAdapter.TrailerViewHolder> {

    private static final String TAG = MovieTrailerAdapter.class.getSimpleName();
    private Activity activity;
    private ArrayList trailerImageList;
    private MdbVideoTrailerResult mMdbVideoTrailerResult;

    public MovieTrailerAdapter(Activity activity) {
        this.activity = activity;
        this.trailerImageList = trailerImageList;
    }

    @Override
    public MovieTrailerAdapter.TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_trailer, parent, false);

        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieTrailerAdapter.TrailerViewHolder holder, int position) {

//        holder.videoPreviewImage.setImageDrawable((Drawable) trailerImageList.get(position));
        //       holder.videoPreviewImage.setBackgroundColor(0xe6e600);
        MdbSingleTrailerResult singleMovieTrailerResult = mMdbVideoTrailerResult.getResults().get(position);
        String trailerId = singleMovieTrailerResult.getKey();
        URL mPosterUrl = NetworkUtils.buildUrlTrailer(trailerId);
        // Use Picasso here to load images onto Grid
        Picasso.with(holder.videoPreviewImage.getContext()).load(mPosterUrl.toString()).into(holder.videoPreviewImage);

        // TODO: 24/08/17 : Implement onClickListener here!
    }

    @Override
    public int getItemCount() {
        return mMdbVideoTrailerResult.getResults().size();
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.video_preview_image)
        ImageView videoPreviewImage;

        public TrailerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setMovieData(MdbVideoTrailerResult movieTrailerData) {
        mMdbVideoTrailerResult = movieTrailerData;
        notifyDataSetChanged();
    }
}

