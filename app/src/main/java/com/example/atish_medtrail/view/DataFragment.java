package com.example.atish_medtrail.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.atish_medtrail.R;
import com.example.atish_medtrail.viewmodel.FlickrDataListViewModel;
import com.example.atish_medtrail.viewmodel.FlickrDataViewModelFactory;

public class DataFragment extends Fragment {

    private static final String TAG = DataFragment.class.getSimpleName();

    protected FlickrDataListViewModel viewModel;

    protected RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModelStore().clear();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repos_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewRepos);


        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        String searchedText = getArguments().getString("search");
        getViewModelStore().clear();
        //Initialize ViewModel
        viewModel =
                ViewModelProviders.of(getActivity(), new FlickrDataViewModelFactory(this, searchedText)).get(FlickrDataListViewModel.class);

        //Start Observer to listen for Data Changes
        final FlickrRepoListAdapter pageListAdapter = new FlickrRepoListAdapter();
        viewModel.getPhotosData().observe(getViewLifecycleOwner(), pageListAdapter::submitList);
        recyclerView.setAdapter(pageListAdapter);


        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getViewModelStore().clear();
        Log.d(TAG, "Destroy");
    }


}

