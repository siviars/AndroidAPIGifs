package com.gifimages;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.gifimages.ApiData.Data;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;

public class MainRepository {

    private MutableLiveData<List<String>> volumesResponseLiveData;
    private GifSearchService gifSearchService;
    private List<String> returnList = new ArrayList<>();



    public MainRepository(GifSearchService gifSearchService) {
        volumesResponseLiveData = new MutableLiveData<>();
        this.gifSearchService = gifSearchService;
    }

    public LiveData<List<String>> searchGifs(String apiKey, String limit, String query) {
        Log.e("AAA", gifSearchService.searchVolumes(apiKey, limit, query) + "");
        gifSearchService.searchVolumes(apiKey, limit, query)
                .enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {
                        if (response.body() != null) {
                            returnList.clear();
                            for (int count = 0; count < response.body().getData().size(); count++) {
                                setList(response.body().getData().get(count).getImages().getOriginal().getUrl());
                            }
                            volumesResponseLiveData.postValue(returnList);
                        }
                    }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {
                        volumesResponseLiveData.postValue(null);
                    }
                });
        return volumesResponseLiveData;
    }

    public void setList(String setUrl) {
        if (setUrl != null) {
            returnList.add(setUrl);
        }
    }

    public LiveData<List<String>> getVolumesResponseLiveData() {
        return volumesResponseLiveData;
    }

}
