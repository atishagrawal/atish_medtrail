package com.example.atish_medtrail.repository.network.paging;


import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.atish_medtrail.model.PhotoModel;

import rx.subjects.ReplaySubject;


public class NetReposDataSourceFactory extends DataSource.Factory {


    private MutableLiveData<NetReposPageKeyedDataSource> mutableDataSource;
    private NetReposPageKeyedDataSource netReposPageKeyedDataSource;

    public NetReposDataSourceFactory(String query) {
        this.mutableDataSource = new MutableLiveData<>();
        netReposPageKeyedDataSource = new NetReposPageKeyedDataSource(query);
    }


    @Override
    public DataSource create() {
        mutableDataSource.postValue(netReposPageKeyedDataSource);
        return netReposPageKeyedDataSource;
    }


    public ReplaySubject<PhotoModel> getRepos() {
        return netReposPageKeyedDataSource.getRepos();
    }

}
