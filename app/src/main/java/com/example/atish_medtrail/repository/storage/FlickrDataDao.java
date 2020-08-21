package com.example.atish_medtrail.repository.storage;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.atish_medtrail.model.PhotoModel;

import java.util.List;


@Dao
public interface FlickrDataDao {
    //Replace older data with the new one
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRepo(PhotoModel resultModel);

    //Basic query to fetch data
    @Query("SELECT * from photo_data where text like :queryString ORDER BY id ASC")
    List<PhotoModel> getStoredRepos(String queryString);
}
