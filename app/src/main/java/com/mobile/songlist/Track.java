package com.mobile.songlist;

import android.app.Activity;
import android.databinding.ObservableInt;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class Track {

    public final ObservableInt imgSrcId = new ObservableInt();

    String trackName;
    public URL artworkUrl100;
    public Bitmap art100Bitmap;

    //fragment
    String collectionName;
    URL previewURL;
    int trackTimeMillis;
    int trackNumber;
    int trackCount;

    public Track(){
        trackCount = 0;
        trackNumber = 0;
        collectionName = null;
        artworkUrl100 = null;
		previewURL = null;
        trackTimeMillis = 1;
    }

    //non-essential
    int trackID;

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public URL getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtwork100(URL artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
        //this.art100Bitmap = fetchBitmap()
    }

    public String getCollectionName() {
        if (collectionName==null)
            return "No name";
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public URL getPreviewURL() {
		
        return previewURL;
    }

    public void setPreviewURL(URL previewURL) {
        this.previewURL = previewURL;
    }

    public int getTrackTimeMillis() {
        return trackTimeMillis;
    }

    public void setTrackTimeMillis(int trackTimeMillis) {
        this.trackTimeMillis = trackTimeMillis;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    public int getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(int trackCount) {
        this.trackCount = trackCount;
    }

    public int getTrackID() {
        return trackID;
    }

    public void setTrackId(int trackID) {
        this.trackID = trackID;
    }

    public Bitmap fetchBitmap(URL url){
        Bitmap result = null;

        try {

            InputStream inputStream = url.openStream();
            result = BitmapFactory.decodeStream(inputStream);
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Activity activity;

    /*collectionName, trackNumber, trackCount is may throw errors
    because KEY or value not found.
    "Error converting JSON stream to JSON array" ALL at 33rd item.*/
}
