package com.mobile.songlist;

import com.mobile.songlist.models.ViewHolder;

/**
 * Created by Steven on 8/1/2017.
 */

// this class is the ViewModel. I can make variables in this class that are observable

public class TrackDetails {

    ViewHolder holder;
    public TrackDetails(ViewHolder holder){
        this.holder = holder;
    }

    public void onTrackViewClick(){}

    public ViewHolder getHolder(){
        return this.holder;
    }

}
