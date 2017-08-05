package com.mobile.songlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = getClass().getName();

    private ArrayList<Track> tracks;
    public RecyclerView recyclerView;
    private RelativeLayout trackView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get list of tracks from Welcome activity
        this.tracks = DataPasser.getInstance().retrieve("track-list");

        //hide action bar
        try {
            getSupportActionBar().hide();
        } catch (NullPointerException e) {
            Log.e(TAG, "Exception thrown when trying to hide SupportActionBar");
            e.printStackTrace();
        }

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new RecyclerViewAdapter(this, getData()));

       /* int i = 0;
        for (Track t : this.tracks){
            Log.d(TAG, (i+1)+" collectionName: " + t.collectionName);
            i++;
        }*/

    }

    private ArrayList<Track> getData()
    {
        return this.tracks;
    }

    /*@Override
    public void onBackPressed()
    {
        Toast.makeText(this, "leaving App", Toast.LENGTH_SHORT).show();
        moveTaskToBack(true);
    }*/

}
