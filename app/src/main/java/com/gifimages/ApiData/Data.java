package com.gifimages.ApiData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("data")
    @Expose
    private List<GifImages> data;

    public Data(List<GifImages> data) {
        this.data = data;
    }

    public List<GifImages> getData() {
        return data;
    }

    public void setData(List<GifImages> data) {
        this.data = data;
    }
}
