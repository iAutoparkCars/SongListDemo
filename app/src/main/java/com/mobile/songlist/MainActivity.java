package com.mobile.songlist;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

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

    private ArrayList<SportViewModel> getData()
    {
        ArrayList<SportViewModel> data = new ArrayList<>();
        SportViewModel model = new SportViewModel();
        model.trackName = "Football";
        model.imgSrcId.set(R.drawable.star_outline);

        data.add(model);

        model = new SportViewModel();
        model.trackName = "Cricket";
        model.imgSrcId.set(R.drawable.star_outline);

        data.add(model);

        model = new SportViewModel();
        model.trackName = "Tennis";
        model.imgSrcId.set(R.drawable.star_outline);

        data.add(model);

        model = new SportViewModel();
        model.trackName = "Badminton";
        model.imgSrcId.set(R.drawable.star_outline);

        data.add(model);

        model = new SportViewModel();
        model.trackName = "Golf";
        model.imgSrcId.set(R.drawable.star_outline);

        data.add(model);

        model = new SportViewModel();
        model.trackName = "Rugby";
        model.imgSrcId.set(R.drawable.star_outline);

        data.add(model);

        model = new SportViewModel();
        model.trackName = "Hockey";
        model.imgSrcId.set(R.drawable.star_outline);

        data.add(model);
        return data;
    }

}
