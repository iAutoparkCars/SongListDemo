package com.mobile.songlist;

import android.util.Log;

import org.json.JSONArray;

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

public class TunesJSON
{
    final String TAG = getClass().getName();
    private JSONArray songs;
    private URL URL;
    HttpURLConnection urlConnection;
    StringBuilder urlData;

    public TunesJSON() {
        try{
                //get JSON
            URL = new URL("https://itunes.apple.com/search?term=Michael+jackson");
            urlConnection = (HttpURLConnection) URL.openConnection();

                //read stream
            //BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            while ((reader.readLine()) != null){
                //urlData.append(reader.readLine());
                Log.d(TAG, reader.readLine());
            }

        }catch(MalformedURLException e){
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

}
