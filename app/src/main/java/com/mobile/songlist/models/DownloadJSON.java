package com.mobile.songlist.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Steven on 7/30/2017.
 */

public class DownloadJSON
{
    private final String TAG = getClass().getName();
    private JSONArray songs;
    private URL URL;
    private HttpURLConnection urlConnection;
    private StringBuilder urlData;

    public ArrayList<Track> tracks;

    public DownloadJSON() {

        tracks = new ArrayList<Track>();

        try{
                //stream stored in urlData
            urlData = new StringBuilder();

                //get JSON
            URL = new URL("https://itunes.apple.com/search?term=Michael+jackson");
            urlConnection = (HttpURLConnection) URL.openConnection();

                //read stream
            InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            String line;
            while ((line = reader.readLine()) != null){
                //Log.d(TAG, line);
                urlData.append(line);
            }
            reader.close();

        } catch(MalformedURLException e){
            Log.e(TAG, "Invalid URL.");
            e.printStackTrace();
        }catch (IOException e) {
            Log.e(TAG, "Unable to open URL stream");
            e.printStackTrace();
        }finally{
            urlConnection.disconnect();
            Log.d(TAG, "Finished and closed URL connection");
        }
    }

        //store data in JSON objects
    public void cacheJSON() throws MalformedURLException {
        try{
            JSONObject urlDataJSON = new JSONObject(urlData.toString());
            JSONArray songsJ = urlDataJSON.getJSONArray("results");

            for (int i = 0; i < songsJ.length(); i++){
                JSONObject trackJSON = songsJ.getJSONObject(i);
                initTrack(trackJSON, i);
            }
        }

        catch (JSONException e){
            Log.e(TAG, "Error converting JSON stream to JSON Array");
        }
    }

    public void initTrack(JSONObject songJSON, int i) throws JSONException, MalformedURLException {
        //do all your logic to instantiate POJO's here.

        Track track = new Track();

        try {
            //essentials
            if (songJSON.has("trackName")) {
                track.setTrackName(songJSON.getString("trackName"));
            }
            } catch (JSONException e1) {
            e1.printStackTrace();
        }

        try {
            //essentials
            if (songJSON.has("artworkUrl100")) {
                track.setArtwork100(new URL(songJSON.getString("artworkUrl100")));
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        if (songJSON.has("previewUrl")) {
            track.setPreviewURL(new URL(songJSON.getString("previewUrl")));
        }

        if (songJSON.has("trackTimeMillis")) {
            track.setTrackTimeMillis(songJSON.getInt("trackTimeMillis"));
        }

        if (songJSON.has("trackCount")) {
            track.setTrackCount(songJSON.getInt("trackCount"));
        }

        if (songJSON.has("trackId")) {
            track.setTrackId(songJSON.getInt("trackId"));
        }

        if (songJSON.has("trackNumber")){
            track.setTrackNumber(songJSON.getInt("trackNumber"));
        }

        if (songJSON.has("collectionName")){
            track.setCollectionName(songJSON.getString("collectionName"));
        }


            //collectionName, trackNumber, trackCount is throwing error "Error converting JSON stream to JSON array" ALL at 33rd item.
            //33rd item is not a track. So there's no track number. If key is null, how to return a null value instead of throwing an error?

            //fragment
            //String collectionName = songJSON.getString("collectionName");
            //int trackNumber = songJSON.getInt("trackNumber");

            //non-essential

            //below works
            //Log.d(TAG, (i + 1) + " trackName: " + track.getTrackName() + " artworkUrl: " + track.getTrackNumber() + " trackId: " + track.getTrackTimeMillis());

            //Log.d(TAG, (i+1)+" collectionName " + " previewUrl: "  + "trackNumber: " );
            //Log.d(TAG, (i+1)+"trackName: "+trackName+ " trackNumber: " + trackNumber);

        tracks.add(track);
    }


}
