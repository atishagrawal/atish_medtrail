package com.example.atish_medtrail.repository.network;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.atish_medtrail.model.PhotoModel;
import com.example.atish_medtrail.repository.network.paging.NetReposDataSourceFactory;
import com.example.atish_medtrail.utils.Constants;


public class NetworkRepos {

    final private static String TAG = NetworkRepos.class.getSimpleName();
    final private LiveData<PagedList<PhotoModel>> reposList;

    public NetworkRepos(NetReposDataSourceFactory dataSourceFactory, PagedList.BoundaryCallback<PhotoModel> boundaryCallback) {

        //Prepare paging config for loading limited data over internet

        PagedList.Config pagedListConfig = (new PagedList.Config.Builder()).setEnablePlaceholders(false)
                .setInitialLoadSizeHint(Constants.PAGE_SIZE).setPageSize(Constants.PAGE_SIZE).build();

        Log.d(TAG, "Initialize Network Data Source");
        reposList = new LivePagedListBuilder<Integer, PhotoModel>(dataSourceFactory, pagedListConfig).setBoundaryCallback(boundaryCallback).build();

    }

    public LiveData<PagedList<PhotoModel>> getPagedData() {
        return reposList;
    }


}
