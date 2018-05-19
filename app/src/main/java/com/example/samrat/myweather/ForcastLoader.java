package com.example.samrat.myweather;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class ForcastLoader extends AsyncTaskLoader<List<Data>> {

    private String mUrl;
    public ForcastLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Data> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<Data> teamLists = QueryUtils.fetchmoviedata(mUrl);
        return teamLists;
    }
}
