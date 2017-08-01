package com.mobile.songlist;

import java.net.URL;

/**
 * Created by Steven on 7/30/2017.
 */

//should the trackId be the Key?

public class Track {

    String trackName;
    URL artworkUrl100;

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

    public void setArtworkUrl100(URL artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public String getCollectionName() {
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


}
