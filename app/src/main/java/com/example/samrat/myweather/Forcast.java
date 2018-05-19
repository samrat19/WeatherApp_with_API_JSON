package com.example.samrat.myweather;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Forcast extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Data>>{

    private  String movieUrl;//="https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22kolkata%2C%20in%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
    private static final int LEAGUE_LOADER_ID = 1;
    private ForcastAdapter forcastAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forcast);

        Intent intent = getIntent();

        movieUrl=intent.getStringExtra("THIS");

        ListView listView = (ListView)findViewById(R.id.datalist);
        forcastAdapter = new ForcastAdapter(this,new ArrayList<Data>());
        listView.setAdapter(forcastAdapter);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(LEAGUE_LOADER_ID, null, this);
        } else {

        }
    }

    @Override
    public Loader<List<Data>> onCreateLoader(int id, Bundle args) {
        return  new ForcastLoader(this,movieUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<Data>> loader, List<Data> data) {

        forcastAdapter.clear();
        if (data != null && !data.isEmpty()) {
            forcastAdapter.addAll(data);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Data>> loader) {

        forcastAdapter.clear();

    }
}
