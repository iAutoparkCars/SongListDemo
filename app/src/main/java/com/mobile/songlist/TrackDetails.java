package com.mobile.songlist;

/**
 * Created by Steven on 8/1/2017.
 */

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
