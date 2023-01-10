package com.gifimages;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String GIF_SERVICE_BASE = "https://api.giphy.com/";
    EditText searchText;
    RecyclerView recyclerView;
    ImageAdapter imageAdapter;
    ArrayList<String> rowsArrayList = new ArrayList<>();

    boolean isLoading = false;
    private MainViewModel mainViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        GifSearchService gifSearchService = new retrofit2.Retrofit.Builder().
                baseUrl(GIF_SERVICE_BASE).
                client(client).addConverterFactory(GsonConverterFactory.create()).
                build().create(GifSearchService.class);
        MainRepository mainRepository = new MainRepository(gifSearchService);
        mainViewModel = new MainViewModel(mainRepository);

        //mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        searchText = findViewById(R.id.name_et);
        recyclerView = findViewById(R.id.recyclerView);
        initScrollListener();
        searchText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getGifs();
                    }
                }, 3000);
            }
        });
    }

    public void getGifs() {
        mainViewModel.searchGifs(searchText.getText().toString()).observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> data) {
                if (data != null) {
                    rowsArrayList.clear();
                    for (int gifCount = 0; gifCount <= 10; gifCount++) {
                        rowsArrayList.add(data.get(gifCount));
                    }
                    imageAdapter = new ImageAdapter(getApplicationContext(), rowsArrayList);
                    recyclerView.setAdapter(imageAdapter);
                }
            }
        });
    }

    private void initScrollListener() {
        recyclerView.addOnScrollListener
                (new RecyclerView.OnScrollListener() {
                     @Override
                     public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                         super.onScrollStateChanged(recyclerView, newState);
                     }

                     @Override
                     public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                         super.onScrolled(recyclerView, dx, dy);
                         LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                         if (!isLoading) {
                             if (linearLayoutManager != null &&
                                     linearLayoutManager.findLastCompletelyVisibleItemPosition() == rowsArrayList.size() - 1) {
                                 if (rowsArrayList.size() < 44) {
                                     loadMore();
                                     isLoading = true;
                                 }
                             }
                         }
                     }
                 }
                );
    }

    private void loadMore() {
        rowsArrayList.add(null);
        imageAdapter.notifyItemInserted(rowsArrayList.size() - 1);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                rowsArrayList.remove(rowsArrayList.size() - 1);
                int scrollPosition = rowsArrayList.size();
                imageAdapter.notifyItemRemoved(scrollPosition);
                int currentSize = scrollPosition;
                int nextLimit = currentSize + 10;

                while (currentSize - 1 < nextLimit) {
                    rowsArrayList.add(mainViewModel.getVolumesResponseLiveData().getValue().get(currentSize));
                    currentSize++;
                }
                imageAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        }, 2000);
    }


}

