package com.mobile.songlist;

import android.app.Activity;
import android.databinding.ObservableInt;


public class SportViewModel {

    public String trackName;
    public String collectionName;
    public final ObservableInt imgSrcId = new ObservableInt();


    public Activity activity;

}
