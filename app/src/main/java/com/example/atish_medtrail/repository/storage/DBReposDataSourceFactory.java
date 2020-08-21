package com.example.atish_medtrail.repository.storage;


import androidx.paging.DataSource;

public class DBReposDataSourceFactory extends DataSource.Factory {

    private DBReposPageKeyedDataSource dbReposPageKeyedDataSource;

    public DBReposDataSourceFactory(FlickrDataDao dao, String query) {
        dbReposPageKeyedDataSource = new DBReposPageKeyedDataSource(dao, query);
    }

    @Override
    public DataSource create() {
        return dbReposPageKeyedDataSource;
    }

}
