package com.example.atish_medtrail.repository.network.paging;


import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.atish_medtrail.api.RetrofitClient;
import com.example.atish_medtrail.model.PhotoModel;
import com.example.atish_medtrail.utils.Constants;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.subjects.ReplaySubject;

public class NetReposPageKeyedDataSource extends PageKeyedDataSource<Integer, PhotoModel> {

    private static final String TAG = NetReposPageKeyedDataSource.class.getSimpleName();
    private final ReplaySubject<PhotoModel> reposObservable;
    private String searchedText = "";

    NetReposPageKeyedDataSource(String query) {
        reposObservable = ReplaySubject.create();
        searchedText = query;
    }


    public ReplaySubject<PhotoModel> getRepos() {
        return reposObservable;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, PhotoModel> callback) {
        Log.i(TAG, "Loading Initial Range, Count " + params.requestedLoadSize);

        Call<String> callBack = RetrofitClient.getInstance().getApi().getPhotos("flickr.photos.search", "3189212285dcb4cf5b2f044edcb0544e", "json", "1",
                Constants.FIRST_PAGE,
                Constants.PAGE_SIZE, searchedText);
        callBack.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                List<PhotoModel> processedPhotos = parseJson(response.body());
                callback.onResult(processedPhotos, 1, 2);

                for (int i = 0; i < processedPhotos.size(); i++)
                    reposObservable.onNext(processedPhotos.get(i));
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                String errorMessage;
                if (t.getMessage() == null) {
                    errorMessage = "unknown error";
                } else {
                    errorMessage = t.getMessage();
                }
                Log.e("API CALL", errorMessage);
                callback.onResult(new ArrayList<>(), 1, 2);
            }
        });
    }


    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, final @NonNull LoadCallback<Integer, PhotoModel> callback) {
        Log.i(TAG, "Loading page " + params.key);

        Call<String> callBack = RetrofitClient.getInstance().getApi().getPhotos("flickr.photos.search", "3189212285dcb4cf5b2f044edcb0544e", "json", "1", params.key, Constants.PAGE_SIZE, searchedText);
        callBack.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                List<PhotoModel> processedPhotos = parseJson(response.body());
                callback.onResult(processedPhotos, params.key + 1);

                for (int i = 0; i < processedPhotos.size(); i++)
                    reposObservable.onNext(processedPhotos.get(i));

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                String errorMessage;
                if (t.getMessage() == null) {
                    errorMessage = "unknown error";
                } else {
                    errorMessage = t.getMessage();
                }
                Log.e("API CALL", errorMessage);
                callback.onResult(new ArrayList<>(), params.key);
            }
        });
    }


    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, PhotoModel> callback) {

    }


    private List<PhotoModel> parseJson(String response) {

        List<PhotoModel> apiResults = new ArrayList<>();

        if (TextUtils.isEmpty(response)){
            return apiResults;
        }
        try {
            JSONObject jsonObject = new JSONObject(response);

            JSONObject photosJsonObject = jsonObject.getJSONObject("photos");
            JSONArray jsonArray = photosJsonObject.getJSONArray("photo");

            for (int i = 0; i < jsonArray.length(); i++) {
                Gson g = new Gson();
                PhotoModel photoModel = g.fromJson(jsonArray.getString(i), PhotoModel.class);
                if (photoModel.getId().equals("0") || photoModel.getServer().equals("0"))
                    continue;
                photoModel.setText(searchedText);

                apiResults.add(photoModel);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i(getClass().getSimpleName(), String.valueOf(apiResults.size()));
        return apiResults;

    }
}
