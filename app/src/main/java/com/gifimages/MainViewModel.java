package com.gifimages;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MainViewModel extends ViewModel {

    private MainRepository mainRepository;
    private LiveData<List<String>> volumesResponseLiveData;
    private final String apiKey = "h18h8Fk5bL7NPhHZwzKTQvjIZl68yHPO";
    private final String limit = "25";

    public MainViewModel() {
        mainRepository = new MainRepository();
        volumesResponseLiveData = mainRepository.getVolumesResponseLiveData();
    }

    @Override
    public void onCleared() {
        super.onCleared();
    }

    public void searchGifs(String query) {
        mainRepository.searchGifs(apiKey, limit, query);
    }

    public LiveData<List<String>> getVolumesResponseLiveData() {
        return volumesResponseLiveData;
    }

}

