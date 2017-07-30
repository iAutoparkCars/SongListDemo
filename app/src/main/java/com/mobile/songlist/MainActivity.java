package com.mobile.songlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mobile.songlist.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

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
    }

    private ArrayList<SportViewModel> getData()
    {
        ArrayList<SportViewModel> data = new ArrayList<>();
        SportViewModel model = new SportViewModel();
        model.sportName = "Football";
        model.imgSrcId.set(R.drawable.star_outline);

        data.add(model);

        model = new SportViewModel();
        model.sportName = "Cricket";
        model.imgSrcId.set(R.drawable.star_outline);

        data.add(model);

        model = new SportViewModel();
        model.sportName = "Tennis";
        model.imgSrcId.set(R.drawable.star_outline);

        data.add(model);

        model = new SportViewModel();
        model.sportName = "Badminton";
        model.imgSrcId.set(R.drawable.star_outline);

        data.add(model);

        model = new SportViewModel();
        model.sportName = "Golf";
        model.imgSrcId.set(R.drawable.star_outline);

        data.add(model);

        model = new SportViewModel();
        model.sportName = "Rugby";
        model.imgSrcId.set(R.drawable.star_outline);

        data.add(model);

        model = new SportViewModel();
        model.sportName = "Hockey";
        model.imgSrcId.set(R.drawable.star_outline);

        data.add(model);
        return data;
    }

}
