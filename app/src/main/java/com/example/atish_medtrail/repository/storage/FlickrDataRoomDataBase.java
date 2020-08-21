package com.example.atish_medtrail.repository.storage;


import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.atish_medtrail.model.PhotoModel;
import com.example.atish_medtrail.utils.Constants;

@Database(entities = {PhotoModel.class}, version = 1)
public abstract class FlickrDataRoomDataBase extends RoomDatabase {

    public abstract FlickrDataDao dataDAO();

    private static final Object sLock = new Object();
    private LiveData<PagedList<PhotoModel>> reposData;

    public static FlickrDataRoomDataBase getInstance(Context context, String query) {
        synchronized (sLock) {
            FlickrDataRoomDataBase instance = Room.databaseBuilder(context.getApplicationContext(),
                    FlickrDataRoomDataBase.class, Constants.DATABASE_NAME)
                    .build();
            instance.init(query);

            return instance;
        }
    }

    private void init(String query) {
        PagedList.Config pagedListConfig = (new PagedList.Config.Builder()).setEnablePlaceholders(false)
                .setInitialLoadSizeHint(Integer.MAX_VALUE).setPageSize(Integer.MAX_VALUE).build();
        DBReposDataSourceFactory dataSourceFactory = new DBReposDataSourceFactory(dataDAO(), query);
        LivePagedListBuilder livePagedListBuilder = new LivePagedListBuilder(dataSourceFactory, pagedListConfig);
        reposData = livePagedListBuilder.build();
    }

    public LiveData<PagedList<PhotoModel>> getRepos() {
        return reposData;
    }
}
