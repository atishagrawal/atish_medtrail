package com.example.atish_medtrail.view;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.atish_medtrail.R;
import com.example.atish_medtrail.model.PhotoModel;


public class FlickrRepoListAdapter extends PagedListAdapter<PhotoModel, FlickrRepoListAdapter.RepoDataViewHolder> {

    private static final String TAG = FlickrRepoListAdapter.class.getSimpleName();

    public FlickrRepoListAdapter() {
        super(DIFF_CALLBACK);
    }

    @Override
    public RepoDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.image_layout, parent, false);
        RepoDataViewHolder viewHolder = new RepoDataViewHolder(view);
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(RepoDataViewHolder holder, int position) {

        //Get Data from position and populate row layout
        PhotoModel singlePhotoModel = getItem(position);

        String url = "https://farm" + singlePhotoModel.getFarm() + ".staticflickr.com/" + singlePhotoModel.getServer() + "/" + singlePhotoModel.getId() + "_" + singlePhotoModel.getSecret() + ".jpg";
        Glide.with(holder.itemView.getContext())
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.loader_test)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageViewSingle);


    }


    /**
     * Basic DIFF_CALLBACK. Used to convey whether the current data is same or has changed
     */
    private static DiffUtil.ItemCallback<PhotoModel> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<PhotoModel>() {
                @Override
                public boolean areItemsTheSame(PhotoModel oldItem, PhotoModel newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(PhotoModel oldItem, PhotoModel newItem) {
                    return oldItem.equals(newItem);
                }
            };


    static class RepoDataViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageViewSingle;


        public RepoDataViewHolder(View view) {
            super(view);
            imageViewSingle = itemView.findViewById(R.id.imageview_single_image);
        }


    }


}
