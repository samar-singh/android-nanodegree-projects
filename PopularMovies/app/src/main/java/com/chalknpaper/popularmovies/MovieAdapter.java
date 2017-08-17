package com.chalknpaper.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chalknpaper.popularmovies.data.MdbPageResult;
import com.chalknpaper.popularmovies.data.Result;
import com.chalknpaper.popularmovies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.net.URL;

/**
 * Created by samarsingh on 11/06/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.NumberViewHolder> {

    private static final String TAG = MovieAdapter.class.getSimpleName();

    // COMPLETED (3) Create a final private ListItemClickListener called mOnClickListener
    /*
     * An on-click handler that we've defined to make it easy for an Activity to interface with
     * our RecyclerView
     */
    final private ListItemClickListener mOnClickListener;


    private static int viewHolderCount;

    private MdbPageResult aMovieData;
    private Context context;

    /**
     * The interface that receives onClick messages.
     */
    public interface ListItemClickListener {
        void onListItemClick(Result clickedItemIndex);
    }

    // COMPLETED (4) Add a ListItemClickListener as a parameter to the constructor and store it in mOnClickListener
    /**
     * Constructor for MovieAdapter that accepts a number of items to display and the specification
     * for the ListItemClickListener.
     *
     *
     * @param listener Listener for list item clicks
     */
    public MovieAdapter(ListItemClickListener listener) {
        mOnClickListener = listener;
        viewHolderCount = 0;
    }

    /**
     *
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param viewGroup The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you
     *                  can use this viewType integer to provide a different layout. See
     *                  {@link android.support.v7.widget.RecyclerView.Adapter#getItemViewType(int)}
     *                  for more details.
     * @return A new NumberViewHolder that holds the View for each list item
     */
    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        viewHolderCount++;
        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: "
                + viewHolderCount);
        return viewHolder;
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the correct
     * indices in the list for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
            Result singleMovieDetails = aMovieData.getResults().get(position);
            String mMoviePosterPath = singleMovieDetails.getPosterPath();
            URL mPosterUrl = NetworkUtils.buildUrlPoster(mMoviePosterPath);


            // Use Picasso here to load images onto Grid
        Picasso.with(holder.viewHolderIndex.getContext()).load(mPosterUrl.toString()).into(holder.viewHolderIndex);

    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available
     */
    @Override
    public int getItemCount() {
        return aMovieData.getResults().size();
    }

    // COMPLETED (5) Implement OnClickListener in the NumberViewHolder class
    /**
     * Cache of the children views for a list item.
     */
    class NumberViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        // Will display which ViewHolder is displaying this data
        final ImageView viewHolderIndex;

        /**
         * Constructor for our ViewHolder. Within this constructor, we get a reference to our
         * TextViews and set an onClickListener to listen for clicks. Those will be handled in the
         * onClick method below.
         * @param itemView The View that you inflated in
         *                 {@link MovieAdapter#onCreateViewHolder(ViewGroup, int)}
         */
        public NumberViewHolder(View itemView) {
            super(itemView);

            viewHolderIndex = (ImageView) itemView.findViewById(R.id.tv_view_holder_instance);
            // COMPLETED (7) Call setOnClickListener on the View passed into the constructor (use 'this' as the OnClickListener)

            itemView.setOnClickListener(this);
        }

        // Override onClick, passing the clicked item's position (getAdapterPosition()) to mOnClickListener via its onListItemClick method
        /**
         * Called whenever a user clicks on an item in the list.
         * @param v The View that was clicked
         */
        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            Result singleMovieDetails = aMovieData.getResults().get(clickedPosition);
            mOnClickListener.onListItemClick(singleMovieDetails);
        }
    }
    public void setMovieData(MdbPageResult movieData) {
        aMovieData = movieData;
        notifyDataSetChanged();
    }

}
