package com.mobile.songlist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Steven on 8/4/2017.
 */

// new DownloadSetImgTask(imageView).execute(url);
public class DownloadSetImgTask extends AsyncTask<URL, Void, Bitmap>{

    String TAG = getClass().getName();
    ImageView imageView;

    public DownloadSetImgTask(ImageView image){
        this.imageView = image;
    }

    // output Bitmap type goes into onPostExecute's input
    @Override
    protected Bitmap doInBackground(URL ...urls){
        return fetchBitmap(urls[0]);
    }
    // setting ImageView runs on Main thread
    @Override
    protected void onPostExecute(Bitmap result) {
        imageView.setImageBitmap(result);
        Log.d(TAG, "got Bitmap from URL. Successfully set image with Bitmap");
    }

    public static Bitmap fetchBitmap(URL url){
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

}
