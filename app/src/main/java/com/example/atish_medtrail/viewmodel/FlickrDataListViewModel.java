package com.example.atish_medtrail.viewmodel;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.atish_medtrail.model.PhotoModel;
import com.example.atish_medtrail.repository.DataRepository;

public class FlickrDataListViewModel extends AndroidViewModel {

    private DataRepository repository;

    public FlickrDataListViewModel(@NonNull Fragment mFragment, String query) {
        super(mFragment.getActivity().getApplication());
        repository = DataRepository.getRepository(mFragment.getContext(), query);
    }

    public LiveData<PagedList<PhotoModel>> getPhotosData() {
        return repository.getPhotosData();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("ViewModel", "Cleared");
    }
}
