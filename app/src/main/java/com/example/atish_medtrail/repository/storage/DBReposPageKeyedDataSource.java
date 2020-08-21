package com.example.atish_medtrail.repository.storage;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.atish_medtrail.model.PhotoModel;

import java.util.List;

public class DBReposPageKeyedDataSource extends PageKeyedDataSource<Integer, PhotoModel> {

    public static final String TAG = DBReposPageKeyedDataSource.class.getSimpleName();
    private final FlickrDataDao flickrDataDao;
    private String searchedText;

    public DBReposPageKeyedDataSource(FlickrDataDao dao, String query) {
        flickrDataDao = dao;
        searchedText = query;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, PhotoModel> callback) {
        Log.d(TAG, "Loading Local Data: " + params.requestedLoadSize);
        List<PhotoModel> storedRepos = flickrDataDao.getStoredRepos("%" + searchedText + "%");
        if (storedRepos.size() != 0) {
            callback.onResult(storedRepos, 0, 1);
        }
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, final @NonNull LoadCallback<Integer, PhotoModel> callback) {
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, PhotoModel> callback) {
    }
}
