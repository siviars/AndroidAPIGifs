package com.gifimages;

import com.gifimages.ApiData.Data;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GifSearchService {
    @GET("/v1/gifs/search")
    Call<Data> searchVolumes(
            @Query("api_key") String apiKey,
            @Query("limit") String limit,
            @Query("q") String query
    );

}
