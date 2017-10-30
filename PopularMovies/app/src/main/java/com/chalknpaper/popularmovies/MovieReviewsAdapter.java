package com.chalknpaper.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.chalknpaper.popularmovies.data.MdbMovieReviewsResult;
import com.chalknpaper.popularmovies.data.MdbSingleMovieReviewResult;
import com.chalknpaper.popularmovies.databinding.ListItemReviewBinding;

/**
 * Created by samarsingh on 21/08/17.
 */

public class MovieReviewsAdapter extends RecyclerView.Adapter<MovieReviewsAdapter.ReviewsViewHolder> {

     ListItemReviewBinding mListItemReviewBinding;

    private static final String TAG = MovieReviewsAdapter.class.getSimpleName();
    private MdbMovieReviewsResult mMdbMovieReviewsResult;
    Activity activity;
    Context mContext;

    public MovieReviewsAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public MovieReviewsAdapter.ReviewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);

        mListItemReviewBinding = ListItemReviewBinding.inflate(inflater,parent,false);
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.list_item_trailer, parent, false);

        return new ReviewsViewHolder(mListItemReviewBinding);
    }

    @Override
    public void onBindViewHolder(MovieReviewsAdapter.ReviewsViewHolder holder, final int position) {

//        holder.videoPreviewImage.setImageDrawable((Drawable) trailerImageList.get(position));
        //       holder.videoPreviewImage.setBackgroundColor(0xe6e600);
        MdbSingleMovieReviewResult singleMovieReviewsResult = mMdbMovieReviewsResult.getResults().get(position);
        mListItemReviewBinding.authorName.setText(singleMovieReviewsResult.getAuthor());
        mListItemReviewBinding.reviewContent.setText(singleMovieReviewsResult.getContent());
    }

    @Override
    public int getItemCount() {
        return mMdbMovieReviewsResult.getResults().size();
    }

    public class ReviewsViewHolder extends RecyclerView.ViewHolder {

        ListItemReviewBinding mListItemReviewBinding;

        public ReviewsViewHolder(ListItemReviewBinding binding) {
            super(binding.getRoot());
            mListItemReviewBinding = binding;
        }
    }

    public void setMovieData(MdbMovieReviewsResult movieReviewResultData) {
        mMdbMovieReviewsResult = movieReviewResultData;
        notifyDataSetChanged();
    }
}

