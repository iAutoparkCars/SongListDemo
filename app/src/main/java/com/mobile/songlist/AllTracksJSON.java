package com.mobile.songlist;

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

/**
 * Created by Steven on 7/30/2017.
 */

public class AllTracksJSON
{
    private final String TAG = getClass().getName();
    private JSONArray songs;
    private URL URL;
    private HttpURLConnection urlConnection;
    private StringBuilder urlData;

    public AllTracksJSON() {
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
                Log.d(TAG, line);
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
    public void cacheJSON() {
        try{
            JSONObject urlDataJSON = new JSONObject(urlData.toString());
            JSONArray songsJ = urlDataJSON.getJSONArray("results");

            for (int i = 0; i < songsJ.length(); i++){
                JSONObject songJSON = songsJ.getJSONObject(i);
                int trackID = songJSON.getInt("trackId");
                Log.d(TAG, (i+1)+" trackId: " + trackID);
            }
        }

        catch (JSONException e){
            Log.e(TAG, "Error converting JSON stream to JSON Array");
        }
    }

}
