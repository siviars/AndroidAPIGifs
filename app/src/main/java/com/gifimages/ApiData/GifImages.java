package com.gifimages.ApiData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GifImages {

    @SerializedName("images")
    @Expose
    private OriginalImages images;

    public GifImages(OriginalImages images) {
        this.images = images;
    }

    public OriginalImages getImages() {
        return images;
    }

    public void setImages(OriginalImages images) {
        this.images = images;
    }
}
