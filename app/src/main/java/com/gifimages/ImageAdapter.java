package com.gifimages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

import java.util.List;

public class ImageAdapter extends BaseAdapter {
    Context context;
    List<String> pictures;
    LayoutInflater inflter;

    public ImageAdapter(Context applicationContext, List<String> pictures) {
        this.context = applicationContext;
        this.pictures = pictures;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return pictures.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.activity_listview, null);
        ImageView icon = (ImageView) view.findViewById(R.id.gifView);
        Glide.with(context).load(pictures.get(i)).into(icon);
        return view;
    }
}
