package com.example.atish_medtrail.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    //URL To fetch Data
    @GET("rest/")
    Call<String> getPhotos(@Query("method") String method, @Query("api_key") String api_key, @Query("format") String format, @Query("nojsoncallback") String nojsoncallback, @Query(
            "page") int page, @Query("per_page") int per_page, @Query("text") String text);
}
