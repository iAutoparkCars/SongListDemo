package com.mobile.songlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    private RelativeLayout trackView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //hide action bar
        getSupportActionBar().hide();

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new RecyclerViewAdapter(this, getData()));


        /*trackView = (RelativeLayout) findViewById(R.id.track_cell);
        trackView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                Toast.makeText(getBaseContext(), "track view clicked",Toast.LENGTH_SHORT).show();
                return true;
            }
        });*/

    }

    private ArrayList<Track> getData()
    {
        ArrayList<Track> data = new ArrayList<>();
        Track model = new Track();
        model.trackName = "Football";
        model.collectionName = "collection 1";
        model.imgSrcId.set(R.drawable.flag_small_icon);
        try {
            model.artworkUrl100 = new URL("http://is2.mzstatic.com/image/thumb/Video/v4/54/84/96/548496ea-06f7-0276-7ab1-6592ca217a29/source/100x100bb.jpg");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        data.add(model);

        model = new Track();
        model.trackName = "Cricket";
        model.collectionName = "collection 2";
        model.imgSrcId.set(R.drawable.star_outline);

        data.add(model);

        model = new Track();
        model.trackName = "Tennis";
        model.imgSrcId.set(R.drawable.star_outline);

        data.add(model);

        model = new Track();
        model.trackName = "Badminton";
        model.imgSrcId.set(R.drawable.star_outline);

        data.add(model);

        model = new Track();
        model.trackName = "Golf";
        model.imgSrcId.set(R.drawable.star_outline);

        data.add(model);

        model = new Track();
        model.trackName = "Rugby";
        model.imgSrcId.set(R.drawable.star_outline);

        data.add(model);

        model = new Track();
        model.trackName = "Hockey";
        model.imgSrcId.set(R.drawable.star_outline);

        data.add(model);
        return data;
    }

}
