package com.gifimages.ApiData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OriginalImages {

    @SerializedName("original")
    @Expose
    private Url original;

    public OriginalImages(Url original) {
        this.original = original;
    }

    public Url getOriginal() {
        return original;
    }

    public void setOriginal(Url original) {
        this.original = original;
    }
}
