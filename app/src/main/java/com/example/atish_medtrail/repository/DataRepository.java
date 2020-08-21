package com.example.atish_medtrail.repository;


import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.paging.PagedList;

import com.example.atish_medtrail.model.PhotoModel;
import com.example.atish_medtrail.repository.network.NetworkRepos;
import com.example.atish_medtrail.repository.network.paging.NetReposDataSourceFactory;
import com.example.atish_medtrail.repository.storage.FlickrDataRoomDataBase;

import rx.schedulers.Schedulers;

public class DataRepository {
    private static final String TAG = DataRepository.class.getSimpleName();
    final private NetworkRepos network;
    final private FlickrDataRoomDataBase database;
    final private MediatorLiveData liveDataMerger;

    private DataRepository(Context context, String query) {

        NetReposDataSourceFactory dataSourceFactory = new NetReposDataSourceFactory(query);

        //Initialize
        network = new NetworkRepos(dataSourceFactory, boundaryCallback);
        database = FlickrDataRoomDataBase.getInstance(context.getApplicationContext(), query);


        //Save in DB

        liveDataMerger = new MediatorLiveData<>();
        liveDataMerger.addSource(network.getPagedData(), value -> {
            liveDataMerger.setValue(value);
            Log.d(TAG, value.toString());
        });

        // Save Data into DB
        dataSourceFactory.getRepos().
                observeOn(Schedulers.io()).
                subscribe(repo -> {
                    database.dataDAO().insertRepo(repo);
                });

    }

    private PagedList.BoundaryCallback<PhotoModel> boundaryCallback = new PagedList.BoundaryCallback<PhotoModel>() {
        @Override
        public void onZeroItemsLoaded() {
            super.onZeroItemsLoaded();
            liveDataMerger.addSource(database.getRepos(), value -> {
                liveDataMerger.setValue(value);
                liveDataMerger.removeSource(database.getRepos());
            });
        }
    };

    public static DataRepository getRepository(Context context, String query) {
        return new DataRepository(context, query);
    }

    public LiveData<PagedList<PhotoModel>> getPhotosData() {
        return liveDataMerger;
    }


}
