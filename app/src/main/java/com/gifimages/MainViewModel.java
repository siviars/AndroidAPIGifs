package com.gifimages;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MainViewModel extends ViewModel {

    GifSearchService gifSearchService;
    private MainRepository mainRepository;
    private LiveData<List<String>> volumesResponseLiveData;
    private final String apiKey = "h18h8Fk5bL7NPhHZwzKTQvjIZl68yHPO";
    private final String limit = "50";

    public MainViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
        volumesResponseLiveData = this.mainRepository.getVolumesResponseLiveData();
    }

    @Override
    public void onCleared() {
        super.onCleared();
    }

    public LiveData<List<String>> searchGifs(String query) {
        return mainRepository.searchGifs(apiKey, limit, query);
    }

    public LiveData<List<String>> getVolumesResponseLiveData() {
        return volumesResponseLiveData;
    }

}

