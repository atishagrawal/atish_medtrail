package com.example.atish_medtrail.viewmodel;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FlickrDataViewModelFactory implements ViewModelProvider.Factory {

    private Fragment mFragment;
    private String searchedText;

    public FlickrDataViewModelFactory(Fragment fragment, String query) {
        this.mFragment = fragment;
        this.searchedText = query;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FlickrDataListViewModel(mFragment, searchedText);
    }
}
