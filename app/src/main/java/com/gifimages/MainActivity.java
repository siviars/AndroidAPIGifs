package com.gifimages;

import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText searchText;
    Button button;
    ListView listView;
    private MainViewModel mainViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        searchText = findViewById(R.id.name_et);
        button = findViewById(R.id.btn_hel);
        listView = (ListView) findViewById(R.id.listView);
        button.setOnClickListener(this);
    }

    public void setList(List<String> gifList) {
        ImageAdapter customAdapter = new ImageAdapter(getApplicationContext(), gifList);
        listView.setAdapter(customAdapter);
    }

    @Override
    public void onClick(View v) {
        mainViewModel.searchGifs(searchText.getText().toString());
        mainViewModel.getVolumesResponseLiveData().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> data) {
                if (data != null) {
                    setList(data);
                }
            }
        });

    }

}

